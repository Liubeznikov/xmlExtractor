package com.liubeznikov.sberbank.xmlExtractor.util;

public class PangramChecker {
    public boolean check(String sentence){
        sentence =sentence.toLowerCase();
        for(char i = 'a';i<='z';i++)
        {
            if(!sentence.contains(String.valueOf(i)))return false;
        }
        return true;
    }
}