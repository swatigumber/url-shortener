package com.klyo.url_shortener.entity;

import jakarta.persistence.*;

@Entity
@Table(name="url_mapping")
public class URLMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, unique = true, length = 2048)
    private String longURL;

    @Column(unique = true)
    private String shortURL;

    public URLMapping() {
    }

    public URLMapping(String longURL) {
        this.longURL = longURL;
    }

    public Long getId() {
        return id;
    }

    public String getLongURL() {
        return longURL;
    }

    public void setLongURL(String longURL) {
        this.longURL = longURL;
    }

    public String getShortURL() {
        return shortURL;
    }

    public void setShortURL(String shortURL) {
        this.shortURL = shortURL;
    }
}
