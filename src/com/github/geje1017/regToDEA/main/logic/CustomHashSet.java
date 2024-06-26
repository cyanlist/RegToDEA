package com.github.geje1017.regToDEA.main.logic;

import java.util.HashSet;
import java.util.Iterator;

public class CustomHashSet<E> extends HashSet<E> {
    public CustomHashSet() {
        super();
    }

    @Override
    public String toString() {
        Iterator<E> it = iterator();
        if (!it.hasNext())
            return "{}";

        StringBuilder sb = new StringBuilder();
        sb.append('{');
        while (it.hasNext()) {
            E e = it.next();
            sb.append(e == this ? "(this Set)" : e);
            if (!it.hasNext())
                return sb.append('}').toString();
            sb.append(',').append(' ');
        }
        return sb.toString();
    }
}
