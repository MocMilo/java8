package com.milo.java8.Primitives;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
/*
 * This class contains examples of operations on List<String> using Java8 lambdas
 */

public class Example1 {

    private static List<String> myList
            = Arrays.asList("Tom", "Martin", "Sandy", "Lukas", "stan", "", "andrew", "nameWithDot.", "nameWith,Coma");

    public static void main(String[] args) {
        /* EXAMPLES:

        A) filtering List<String> by element characteristics:

        1) filter string elements containing only letters
        2) filter string elements that contain letter 'a'
        3) filter string that are empty
        4) filter string of length > 3


        B) filtering List<String> by comparing list elements:

        1 filter only 3 first elements of array
        2 filter shortest string element (but not empty)

        C) List<String> elements modification

        1 add characters at the end of string element
        2 change all letters to be capital letters

        */

        System.out.println("*** PREDICATES ***");
        predicateAction();

        System.out.println("*** CUSTOM FUNCTIONS ***");
        functionAction();
    }

    private static void predicateAction() {

        // A1
        Predicate<String> onlyLetters = s -> s.matches("[a-zA-Z]+");
        // A2
        Predicate<String> containsLetterA = s -> s.contains("a");
        // A3
        Predicate<String> removedEmptyStrings = s -> !s.contains("");
        // A4
        Predicate<String> longerThanThreeChars = s -> s.length() > 3;

        Collection filteredCollectionA1 = Result.getFilteredCollecion(myList, onlyLetters);
        Collection filteredCollectionA2 = Result.getFilteredCollecion(myList, containsLetterA);
        Collection filteredCollectionA3 = Result.getFilteredCollecion(myList, longerThanThreeChars);
        Collection filteredCollectionA4 = Result.getFilteredCollecion(myList, removedEmptyStrings);

        Result.printFilteredCollection("A1 contains only letters:", filteredCollectionA1);
        Result.printFilteredCollection("A2 contains letter a:", filteredCollectionA2);
        Result.printFilteredCollection("A3 Removed Empty strings.", filteredCollectionA3);
        Result.printFilteredCollection("A4 contains strings longer than 3 chars:", filteredCollectionA4);

        // B1
        List<String> filteredArray = myList
                .stream()
                .limit(3)
                .collect(Collectors.toList());

        Result.printFilteredCollection("B1 only 3 first elements of list:", filteredArray);

        // B2
        Predicate<String> shortestName = (x -> x.length()
                == (myList.stream()
                .filter(i -> !i.isEmpty())
                .mapToInt(String::length)  //.mapToInt(i -> i.length())
                .min()
                .getAsInt())
        );

        Collection filteredCollectionB2 = Result.getFilteredCollecion(myList, shortestName);
        Result.printFilteredCollection("B2 Shortest not empty string:", filteredCollectionB2);

        // C1
        myList.replaceAll(x -> x + "_abc");
        Result.printFilteredCollection("C1 modified list:", myList);
        // C2
        myList.replaceAll(x -> x.toUpperCase());    // .replaceAll(String::toUpperCase)
        Result.printFilteredCollection("C3 modified list (capital letters):", myList);
    }

    private static void functionAction() {

        /* TODO
        create custom Function that:
        - returns string length (console result: name - numberOfChars)
        - returns string first char (console result: name - firstChar)
        */

        Function<String, Integer> myFunction = x -> x.length();

        Function<String, String> firstLetter = x -> {
            if (!x.isEmpty()) {
                x = x.substring(0, 1);
            }
            return x;
        };
    }
}
