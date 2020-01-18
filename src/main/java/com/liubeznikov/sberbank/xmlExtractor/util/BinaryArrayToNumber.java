package com.liubeznikov.sberbank.xmlExtractor.util;

import java.util.List;


public class BinaryArrayToNumber {

    public static int ConvertBinaryArrayToInt(List<Integer> binary) {

        int sum = 0;
        int base =2;
        for(int i = binary.size()-1;i>=0;i--)
        {
            sum+=  binary.get(i) == 1 ? Math.pow(base,binary.size() -i -1) : 0;
        }
        return  sum;
    }
}