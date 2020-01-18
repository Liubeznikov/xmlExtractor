package com.liubeznikov.sberbank.xmlExtractor.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class DuplicateEncoder {
    public static String encode(String word) {
        word = word.toLowerCase();
        Map<String, Integer> letters = new HashMap<>();
        for (String c : word.split("")) {
            letters.putIfAbsent(c, 0);
            letters.compute(c, (k, v) -> v = v + 1);
        }
        String[] arr = word.split("");
        word = Arrays.stream(arr).map(v -> v = letters.get(v) == 1 ? "(" : ")").collect(Collectors.joining());
        return word;
    }
}
