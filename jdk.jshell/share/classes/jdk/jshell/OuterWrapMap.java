package jdk.jshell;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.stream.Collectors;
import jdk.jshell.Wrap.CompoundWrap;
import static jdk.jshell.Util.PREFIX_PATTERN;
import static jdk.jshell.Util.REPL_CLASS_PREFIX;
import static jdk.jshell.Util.REPL_DOESNOTMATTER_CLASS_NAME;
import static jdk.jshell.Util.asLetters;

/**
 *
 * @author Robert Field
 */
class OuterWrapMap {

    private final JShell state;
    private final Map<String,OuterSnippetsClassWrap> classOuters = new HashMap<>();

    OuterWrapMap(JShell state) {
        this.state = state;
    }

    OuterSnippetsClassWrap getOuter(String className) {
        Matcher matcher = PREFIX_PATTERN.matcher(className);
        String cn;
        if (matcher.find() && (cn = matcher.group("class")) != null) {
            return classOuters.get(cn);
        }
        return null;
    }

    private CompoundWrap wrappedInClass(String className, String imports, List<Wrap> wraps) {
        List<Object> elems = new ArrayList<>(wraps.size() + 2);
        elems.add(imports + "\n" +
                "class " + className + " {\n");
        elems.addAll(wraps);
        elems.add("}\n");
        return new CompoundWrap(elems.toArray());
    }

    OuterWrap wrapInClass(Set<Key> except, Collection<Snippet> plus,
            List<Snippet> snippets, List<Wrap> wraps) {
        String imports = state.maps.packageAndImportsExcept(except, plus);
        // className is unique to the set of snippets and their version (seq)
        String className = REPL_CLASS_PREFIX + snippets.stream()
                .sorted((sn1, sn2) -> sn1.key().index() - sn2.key().index())
                .map(sn -> "" + sn.key().index() + asLetters(sn.sequenceNumber()))
                .collect(Collectors.joining("_"));
        CompoundWrap w = wrappedInClass(className, imports, wraps);
        OuterSnippetsClassWrap now = new OuterSnippetsClassWrap(className, w, snippets, wraps);
        classOuters.put(className, now);
        return now;
    }

    OuterWrap wrapInTrialClass(Wrap wrap) {
        String imports = state.maps.packageAndImportsExcept(null, null);
        CompoundWrap w = wrappedInClass(REPL_DOESNOTMATTER_CLASS_NAME, imports,
                Collections.singletonList(wrap));
        return new OuterWrap(w);
    }

    OuterWrap wrapImport(Wrap guts, Snippet sn) {
        return new OuterImportSnippetWrap(guts, sn);
    }
}
