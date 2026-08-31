package com.klyo.url_shortener.controller;

import com.klyo.url_shortener.dto.ShortenURLRequest;
import com.klyo.url_shortener.dto.ShortenURLResponse;
import com.klyo.url_shortener.service.URLShortenerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping
public class URLShortenerController {

    @Autowired
    URLShortenerService urlShortenerService;

    @PostMapping("/shorten")
    public ResponseEntity shortenURL(@Valid @RequestBody ShortenURLRequest request) {
        String shortCode = urlShortenerService.shortenUrl(request.getLongUrl());
        ShortenURLResponse shortenURLResponse = new ShortenURLResponse(shortCode);
        return ResponseEntity.accepted().body(shortenURLResponse);
    }


    @GetMapping("/{shortCode:[a-zA-Z0-9]+}")
    public ResponseEntity redirect(@PathVariable String shortCode){
        String longURL = urlShortenerService.getLongURL(shortCode);

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .location(URI.create(longURL))
                .build();
    }

}
