package com.liubeznikov.sberbank.xmlExtractor.util;

import java.util.HashMap;
import java.util.Map;

public class Vowels {

    public static int getCount(String str) {
        int vowelsCount = 0;
        Map<String,Integer> vowels = new HashMap<>();
        vowels.put("a",0);
        vowels.put("e",0);
        vowels.put("i",0);
        vowels.put("o",0);
        vowels.put("u",0);
        for(String ch : str.split(""))
        {
           vowels.computeIfPresent(ch,(a,b)-> b=b+1);
        }
        vowelsCount= vowels.values().stream().reduce(0, Integer::sum);
        return vowelsCount;
    }

}