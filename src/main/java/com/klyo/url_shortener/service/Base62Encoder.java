package com.klyo.url_shortener.service;

import org.springframework.stereotype.Component;

@Component
public class Base62Encoder {

    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private static final int BASE = CHARACTERS.length();

    public String encode(long number){
        if(number == 0){
            return String.valueOf(CHARACTERS.charAt(0));
        }

        StringBuilder encodedString = new StringBuilder("");

        while(number>0){
            int remainder = (int)number%BASE;
            encodedString.append(CHARACTERS.charAt(remainder));
            number = number/BASE;
        }
        return encodedString.reverse().toString();
    }


    public long decode(String code){
        long number =0;
        for(int i = 0; i<code.length();i++){
            char ch = code.charAt(code.length()-1-i);
            number += Math.pow(BASE, i)*CHARACTERS.indexOf(ch);
        }
        return number;
    }
}
