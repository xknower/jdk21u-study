package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.generic.BranchHandle;
import com.sun.org.apache.bcel.internal.generic.InstructionHandle;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Jacek Ambroziak
 * @author Santiago Pericas-Geertsen
 * @LastModified: Oct 2017
 */
public final class FlowList {
    private List<InstructionHandle> _elements;

    public FlowList() {
        _elements = null;
    }

    public FlowList(InstructionHandle bh) {
        _elements = new ArrayList<>();
        _elements.add(bh);
    }

    public FlowList(FlowList list) {
        _elements = list._elements;
    }

    public FlowList add(InstructionHandle bh) {
        if (_elements == null) {
            _elements = new ArrayList<>();
        }
        _elements.add(bh);
        return this;
    }

    public FlowList append(FlowList right) {
        if (_elements == null) {
            _elements = right._elements;
        }
        else {
            final List<InstructionHandle> temp = right._elements;
            if (temp != null) {
                final int n = temp.size();
                for (int i = 0; i < n; i++) {
                    _elements.add(temp.get(i));
                }
            }
        }
        return this;
    }

    /**
     * Back patch a flow list. All instruction handles must be branch handles.
     */
    public void backPatch(InstructionHandle target) {
        if (_elements != null) {
            final int n = _elements.size();
            for (int i = 0; i < n; i++) {
                BranchHandle bh = (BranchHandle)_elements.get(i);
                bh.setTarget(target);
            }
            _elements.clear();          // avoid backpatching more than once
        }
    }

    /**
     * Redirect the handles from oldList to newList. "This" flow list
     * is assumed to be relative to oldList.
     */
    public FlowList copyAndRedirect(InstructionList oldList,
        InstructionList newList)
    {
        final FlowList result = new FlowList();
        if (_elements == null) {
            return result;
        }

        final int n = _elements.size();
        final Iterator<InstructionHandle> oldIter = oldList.iterator();
        final Iterator<InstructionHandle> newIter = newList.iterator();

        while (oldIter.hasNext()) {
            final InstructionHandle oldIh = oldIter.next();
            final InstructionHandle newIh = newIter.next();

            for (int i = 0; i < n; i++) {
                if (_elements.get(i) == oldIh) {
                    result.add(newIh);
                }
            }
        }
        return result;
    }
}
