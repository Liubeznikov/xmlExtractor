package com.liubeznikov.sberbank.xmlExtractor.util;

public class Arge {

    public static int nbYear(int p0, double percent, int aug, int p) {
        int n =0;
        if (p0 < p) {
            n= 1 + nbYear(p0 + (int) (p0 * (percent / 100)) + aug, percent, aug, p);
        }
        return n;
    }
}