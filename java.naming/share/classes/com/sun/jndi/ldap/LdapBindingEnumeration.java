package com.sun.jndi.ldap;

import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Vector;
import javax.naming.*;
import javax.naming.directory.*;
import javax.naming.ldap.Control;

import com.sun.jndi.toolkit.ctx.Continuation;
import com.sun.naming.internal.NamingManagerHelper;
import com.sun.naming.internal.ObjectFactoriesFilter;

final class LdapBindingEnumeration
        extends AbstractLdapNamingEnumeration<Binding> {

    @SuppressWarnings("removal")
    private final AccessControlContext acc = AccessController.getContext();

    LdapBindingEnumeration(LdapCtx homeCtx, LdapResult answer, Name remain,
        Continuation cont) throws NamingException
    {
        super(homeCtx, answer, remain, cont);
    }

    @SuppressWarnings("removal")
    @Override
    protected Binding
      createItem(String dn, Attributes attrs, Vector<Control> respCtls)
        throws NamingException {

        Object obj = null;
        String atom = getAtom(dn);

        if (attrs.get(Obj.JAVA_ATTRIBUTES[Obj.CLASSNAME]) != null) {
            // serialized object or object reference
            try {
                PrivilegedExceptionAction<Object> pa = () -> Obj.decodeObject(attrs);
                obj = AccessController.doPrivileged(pa, acc);
            } catch (PrivilegedActionException e) {
                throw (NamingException)e.getException();
            }
        }
        if (obj == null) {
            // DirContext object
            obj = new LdapCtx(homeCtx, dn);
        }

        CompositeName cn = new CompositeName();
        cn.add(atom);

        try {
            obj = NamingManagerHelper.getDirObjectInstance(obj, cn, homeCtx,
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

        Binding binding;
        if (respCtls != null) {
           binding = new BindingWithControls(cn.toString(), obj,
                                homeCtx.convertControls(respCtls));
        } else {
            binding = new Binding(cn.toString(), obj);
        }
        binding.setNameInNamespace(dn);
        return binding;
    }

    @Override
    protected AbstractLdapNamingEnumeration<? extends NameClassPair> getReferredResults(
            LdapReferralContext refCtx) throws NamingException{
        // repeat the original operation at the new context
        return (AbstractLdapNamingEnumeration<? extends NameClassPair>)refCtx.listBindings(listArg);
    }
}
