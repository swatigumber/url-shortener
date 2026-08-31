package com.klyo.url_shortener.repository;

import com.klyo.url_shortener.entity.URLMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface URLMappingRepository extends JpaRepository<URLMapping , Long> {
    Optional<URLMapping> findByShortURL(String shortURL);

    URLMapping findByLongURL(String longURL);
}