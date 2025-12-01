package dev.url_shortner.service;

import dev.url_shortner.model.UrlMapping;
import dev.url_shortner.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UrlService {

    @Autowired
    private UrlRepository repository;

    // 1. Logic to Shorten a URL
    public UrlMapping shortenUrl(String originalUrl) {
        UrlMapping mapping = new UrlMapping();
        mapping.setOriginalUrl(originalUrl);
        mapping.setShortCode(generateShortCode());
        return repository.save(mapping);
    }

    // 2. Logic to Find and Count Clicks
    public UrlMapping getUrl(String shortCode) {
        UrlMapping mapping = repository.findByShortCode(shortCode)
                .orElseThrow(() -> new RuntimeException("URL not found"));

        // Increment stats
        mapping.setClickCount(mapping.getClickCount() + 1);
        return repository.save(mapping);
    }

    // Helper: Generates a random 6-character string
    private String generateShortCode() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[4]; // 4 bytes = ~6 Base64 chars
        random.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }
}
