package com.liubeznikov.sberbank.xmlExtractor.util;

public class BitCounting {

    public static int countBits(int n){
        int k =0 ;
        int b = 1;
        while (n>0)
        {
            k+=n&b;
            n = n>>1;
        }
        return k;
    }
}