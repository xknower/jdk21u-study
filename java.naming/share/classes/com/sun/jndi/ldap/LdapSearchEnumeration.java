package com.sun.jndi.ldap;

import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Vector;
import javax.naming.*;
import javax.naming.directory.*;
import javax.naming.ldap.*;
import javax.naming.ldap.LdapName;

import com.sun.jndi.toolkit.ctx.Continuation;
import com.sun.naming.internal.NamingManagerHelper;
import com.sun.naming.internal.ObjectFactoriesFilter;

final class LdapSearchEnumeration
        extends AbstractLdapNamingEnumeration<SearchResult> {

    private Name startName;             // prefix of names of search results
    private LdapCtx.SearchArgs searchArgs = null;

    @SuppressWarnings("removal")
    private final AccessControlContext acc = AccessController.getContext();

    LdapSearchEnumeration(LdapCtx homeCtx, LdapResult search_results,
        String starter, LdapCtx.SearchArgs args, Continuation cont)
        throws NamingException {

        super(homeCtx, search_results,
              args.name, /* listArg */
              cont);

        // fully qualified name of starting context of search
        startName = new LdapName(starter);
        searchArgs = args;
    }

    @SuppressWarnings("removal")
    @Override
    protected SearchResult createItem(String dn, Attributes attrs,
                                      Vector<Control> respCtls)
            throws NamingException {

        Object obj = null;

        String relStart;         // name relative to starting search context
        String relHome;          // name relative to homeCtx.currentDN
        boolean relative = true; // whether relative to currentDN

        // need to strip off all but lowest component of dn
        // so that is relative to current context (currentDN)

        try {
            Name parsed = new LdapName(dn);
            // System.err.println("dn string: " + dn);
            // System.err.println("dn name: " + parsed);

            if (startName != null && parsed.startsWith(startName)) {
                relStart = parsed.getSuffix(startName.size()).toString();
                relHome = parsed.getSuffix(homeCtx.currentParsedDN.size()).toString();
            } else {
                relative = false;
                relHome = relStart =
                    LdapURL.toUrlString(homeCtx.hostname, homeCtx.port_number,
                    dn, homeCtx.hasLdapsScheme);
            }
        } catch (NamingException e) {
            // could not parse name
            relative = false;
            relHome = relStart =
                LdapURL.toUrlString(homeCtx.hostname, homeCtx.port_number,
                dn, homeCtx.hasLdapsScheme);
        }

        // Name relative to search context
        CompositeName cn = new CompositeName();
        if (!relStart.isEmpty()) {
            cn.add(relStart);
        }

        // Name relative to homeCtx
        CompositeName rcn = new CompositeName();
        if (!relHome.isEmpty()) {
            rcn.add(relHome);
        }
        //System.err.println("relStart: " + cn);
        //System.err.println("relHome: " + rcn);

        // Fix attributes to be able to get schema
        homeCtx.setParents(attrs, rcn);

        // only generate object when requested
        if (searchArgs.cons.getReturningObjFlag()) {

            if (attrs.get(Obj.JAVA_ATTRIBUTES[Obj.CLASSNAME]) != null) {
                // Entry contains Java-object attributes (ser/ref object)
                // serialized object or object reference
                try {
                    PrivilegedExceptionAction<Object> pea = () -> Obj.decodeObject(attrs);
                    obj = AccessController.doPrivileged(pea, acc);
                } catch (PrivilegedActionException e) {
                    throw (NamingException)e.getException();
                }
            }
            if (obj == null) {
                obj = new LdapCtx(homeCtx, dn);
            }

            // Call getObjectInstance before removing unrequested attributes
            try {
                // rcn is either relative to homeCtx or a fully qualified DN
                obj = NamingManagerHelper.getDirObjectInstance(
                    obj, rcn, (relative ? homeCtx : null),
                    homeCtx.envprops, attrs, ObjectFactoriesFilter::checkLdapFilter);
            } catch (NamingException e) {
                throw e;
            } catch (Exception e) {
                NamingException ne =
                    new NamingException(
                            "problem generating object using object factory");
                ne.setRootCause(e);
                throw ne;
            }

            // remove Java attributes from result, if necessary
            // Even if CLASSNAME attr not there, there might be some
            // residual attributes

            String[] reqAttrs;
            if ((reqAttrs = searchArgs.reqAttrs) != null) {
                // create an attribute set for those requested
                Attributes rattrs = new BasicAttributes(true); // ignore case
                for (int i = 0; i < reqAttrs.length; i++) {
                    rattrs.put(reqAttrs[i], null);
                }
                for (int i = 0; i < Obj.JAVA_ATTRIBUTES.length; i++) {
                    // Remove Java-object attributes if not requested
                    if (rattrs.get(Obj.JAVA_ATTRIBUTES[i]) == null) {
                        attrs.remove(Obj.JAVA_ATTRIBUTES[i]);
                    }
                }
            }

        }

        /*
         * name in search result is either the stringified composite name
         * relative to the search context that can be passed directly to
         * methods of the search context, or the fully qualified DN
         * which can be used with the initial context.
         */
        SearchResult sr;
        if (respCtls != null) {
            sr = new SearchResultWithControls(
                (relative ? cn.toString() : relStart), obj, attrs,
                relative, homeCtx.convertControls(respCtls));
        } else {
            sr = new SearchResult(
                (relative ? cn.toString() : relStart),
                obj, attrs, relative);
        }
        sr.setNameInNamespace(dn);
        return sr;
    }

    @Override
    public void appendUnprocessedReferrals(LdapReferralException ex) {

        // a referral has been followed so do not create relative names
        startName = null;
        super.appendUnprocessedReferrals(ex);
    }

    @Override
    protected AbstractLdapNamingEnumeration<? extends NameClassPair> getReferredResults(
            LdapReferralContext refCtx) throws NamingException {
        // repeat the original operation at the new context
        return (AbstractLdapNamingEnumeration<? extends NameClassPair>)refCtx.search(
                searchArgs.name, searchArgs.filter, searchArgs.cons);
    }

    @Override
    protected void update(AbstractLdapNamingEnumeration<? extends NameClassPair> ne) {
        super.update(ne);

        // Update search-specific variables
        LdapSearchEnumeration se = (LdapSearchEnumeration)ne;
        startName = se.startName;
    }

    void setStartName(Name nm) {
        startName = nm;
    }
}
