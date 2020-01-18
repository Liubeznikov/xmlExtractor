package com.liubeznikov.sberbank.xmlExtractor.util;

public class TenMinWalk {
    public static boolean isValid(char[] walk) {
        int ns = 0, we =0;
        if (walk.length != 10) return false;
        for (int i = 0; i< walk.length; i++)
        {
            switch(walk[i]) {
                case 'n':
                    ns++;
                    break;
                case 's':
                    ns--;
                    break;
                case 'e':
                    we++;
                    break;
                case 'w':
                    we--;
                    break;

            }

        }

        return (ns == 0) && (we == 0);
    }
}