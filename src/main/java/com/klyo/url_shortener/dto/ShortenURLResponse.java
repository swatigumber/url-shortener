package com.klyo.url_shortener.dto;

public class ShortenURLResponse {
    private String shortURL;

    public String getShortURL() {
        return shortURL;
    }

    public ShortenURLResponse(String shortURL) {
        this.shortURL = shortURL;
    }
}
