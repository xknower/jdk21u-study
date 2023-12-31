package com.sun.hotspot.igv.view;

import com.sun.hotspot.igv.data.InputBlock;
import com.sun.hotspot.igv.data.InputGraph;
import com.sun.hotspot.igv.data.Properties.RegexpPropertyMatcher;
import com.sun.hotspot.igv.data.services.InputGraphProvider;
import com.sun.hotspot.igv.util.LookupHistory;
import com.sun.hotspot.igv.util.StringUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.netbeans.spi.quicksearch.SearchProvider;
import org.netbeans.spi.quicksearch.SearchRequest;
import org.netbeans.spi.quicksearch.SearchResponse;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.NotifyDescriptor.Message;

public class BlockQuickSearch implements SearchProvider {

    @Override
    public void evaluate(SearchRequest request, SearchResponse response) {
        String rawValue = request.getText();
        if (rawValue.trim().isEmpty()) {
            return;
        }
        String value = ".*" + Pattern.quote(rawValue) + ".*";

        final InputGraphProvider p = LookupHistory.getLast(InputGraphProvider.class);
        if (p == null || p.getGraph() == null) {
            return;
        }

        InputGraph matchGraph = p.getGraph();
        // Search the current graph
        List<InputBlock> matches = findMatches(value, p.getGraph(), response);
        if (matches == null) {
            // See if the it hits in a later graph
            for (InputGraph graph : p.searchForward()) {
                matches = findMatches(value, graph, response);
                if (matches != null) {
                    matchGraph = graph;
                    break;
                }
            }
        }
        if (matches == null) {
            // See if it hits in a earlier graph
            for (InputGraph graph : p.searchBackward()) {
                matches = findMatches(value, graph, response);
                if (matches != null) {
                    matchGraph = graph;
                    break;
                }
            }
        }
        if (matches != null) {
            // Rank the matches.
            matches.sort((InputBlock a, InputBlock b) ->
                         compareByRankThenNumVal(rawValue,
                                                 "B" + a.getName(),
                                                 "B" + b.getName()));

            final InputGraph theGraph = p.getGraph() != matchGraph ? matchGraph : null;
            for (final InputBlock b : matches) {
                if (!response.addResult(() -> {
                            final EditorTopComponent editor = EditorTopComponent.getActive();
                            assert(editor != null);
                            if (theGraph != null) {
                                editor.getModel().selectGraph(theGraph);
                            }
                            editor.clearSelectedNodes();
                            editor.addSelectedNodes(b.getNodes(), true);
                            editor.centerSelectedNodes();
                            editor.requestActive();
                        },
                        "B" + b.getName() + (theGraph != null ? " in " + theGraph.getName() : ""))) {
                    return;
                }
            }
        }
    }

    private List<InputBlock> findMatches(String blockName, InputGraph inputGraph, SearchResponse response) {
        try {
            RegexpPropertyMatcher matcher = new RegexpPropertyMatcher("", blockName, Pattern.CASE_INSENSITIVE);
            List<InputBlock> matches = new ArrayList<>();
            for (InputBlock b : inputGraph.getBlocks()) {
                if (matcher.match("B" + b.getName())) {
                    matches.add(b);
                }
            }
            return matches.size() == 0 ? null : matches;
        } catch (Exception e) {
            final String msg = e.getMessage();
            response.addResult(() -> {
                    Message desc = new NotifyDescriptor.Message("An exception occurred during the search, "
                            + "perhaps due to a malformed query string:\n" + msg,
                            NotifyDescriptor.WARNING_MESSAGE);
                    DialogDisplayer.getDefault().notify(desc);
                },
                "(Error during search)"
            );
        }
        return null;
    }

    private int compareByRankThenNumVal(String qry, String b1, String b2) {
        int key1 = StringUtils.rankMatch(qry, b1);
        int key2 = StringUtils.rankMatch(qry, b2);
        if (key1 == key2) {
            // If the matches have the same rank, compare the numeric values of
            // their first words, if applicable.
            try {
                key1 = Integer.parseInt(b1.replace("B", ""));
                key2 = Integer.parseInt(b2.replace("B", ""));
            } catch (Exception e) {
                // Not applicable, return equality value.
                return 0;
            }
        }
        return Integer.compare(key1, key2);
    }

}
