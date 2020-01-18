package com.liubeznikov.sberbank.xmlExtractor.util;

public class DRoot {
    public static int digital_root(int n) {
        int k = 0;
        if (n > 0 ) k = n%10 + digital_root(n/10);
        if (k<10) return k;
        return digital_root(k);
    }
}