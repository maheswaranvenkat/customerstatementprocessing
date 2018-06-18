package com.rabo.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public abstract class CommonUtils {

    public static final String CSV_SEPARATOR = ",";

    public static  <T> Pair<Set<T>,List<T>> resultSet(List<T> all) {
        Set<T> set = new HashSet<>();
        List<T> duplicateElements=new ArrayList<>();
        // Set#add returns false if the set does not change, which
        // indicates that a duplicate element has been added.
        for (T each: all) if (!set.add(each)) duplicateElements.add(each);
        return new ImmutablePair<>(set,duplicateElements);
    }

}
