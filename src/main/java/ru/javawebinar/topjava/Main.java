package ru.javawebinar.topjava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @see <a href="http://topjava.herokuapp.com">Demo application</a>
 * @see <a href="https://github.com/JavaOPs/topjava">Initial project</a>
 */
public class Main {
    public static void main(String[] args) {
//        System.out.format("Hello TopJava Enterprise!");


        HashMap<Integer, List<String>> map = new HashMap<>();
        map.put(1, Arrays.asList("one", "one"));
        map.put(2, Arrays.asList("two", "two"));
        map.put(3, Arrays.asList("three", "three"));
        map.put(4, Arrays.asList("for", "for"));
        map.put(5, Arrays.asList("five", "five"));
        map.put(6, Arrays.asList("six", "six"));
        map.put(7, Arrays.asList("seven", "seven"));

    }


}
