package com.milo.java8.Primitives;

import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Result {

    public static Collection getFilteredCollecion(Collection collection, Predicate predicate) {
        return  (Collection) collection
                .stream()
                .filter(predicate).collect(Collectors.toList());
    }

    public static void printFilteredCollection(String message, Collection collection) {
        System.out.println(message);
        collection.forEach( e -> System.out.println(e));
    }
}
