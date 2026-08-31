package com.klyo.url_shortener.dto;

import com.klyo.url_shortener.validation.ValidURL;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ShortenURLRequest {

    @NotBlank(message = "URL cannot be empty")
    @ValidURL(message = "Invalid input URL")
    @Size(max = 2048 , message = "URL cannot exceed 2048 characters")
    private String longUrl;

    public ShortenURLRequest() {
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }
}
