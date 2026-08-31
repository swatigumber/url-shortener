package com.klyo.url_shortener.service;

import com.klyo.url_shortener.entity.URLMapping;
import com.klyo.url_shortener.exception.ShortCodeNotFoundException;
import com.klyo.url_shortener.repository.URLMappingRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class URLShortenerService {

    private final Base62Encoder base62Encoder;

    private final URLMappingRepository urlMappingRepository;

    private final String DNS = "http://localhost:9090/";

    public URLShortenerService(URLMappingRepository urlMappingRepository ,Base62Encoder base62Encoder) {
        this.urlMappingRepository = urlMappingRepository;
        this.base62Encoder = base62Encoder;
    }


    @Transactional
    public String shortenUrl(String longUrl){

        URLMapping existing = urlMappingRepository.findByLongURL(longUrl);

        if (existing != null) {
            return DNS+existing.getShortURL();
        }


        URLMapping urlMapping = new URLMapping(longUrl);
        urlMapping = urlMappingRepository.save(urlMapping);

        long id = urlMapping.getId();
        String shortCode = base62Encoder.encode(id);
        urlMapping.setShortURL(shortCode);
        urlMappingRepository.save(urlMapping);

        return DNS + shortCode;

    }

    public String getLongURL(String shortCode){
        URLMapping urlMapping = urlMappingRepository.findByShortURL(shortCode)
                .orElseThrow( () -> new ShortCodeNotFoundException(shortCode) );
        return urlMapping.getLongURL();
    }

}
