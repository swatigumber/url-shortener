package com.klyo.url_shortener.exception;

public class ShortCodeNotFoundException extends RuntimeException{

    public ShortCodeNotFoundException(String shortCode){
        super("Short URL not found: " + shortCode);
    }
}
