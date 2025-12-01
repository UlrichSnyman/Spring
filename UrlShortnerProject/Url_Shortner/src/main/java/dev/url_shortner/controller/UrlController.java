package dev.url_shortner.controller;

import dev.url_shortner.model.UrlMapping;
import dev.url_shortner.service.UrlService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@Tag(name = "URL Shortener", description = "Endpoints to shorten and retrieve URLs")
public class UrlController {

    @Autowired
    private UrlService service;

    @Operation(summary = "Shorten a long URL")
    @PostMapping("/shorten")
    public UrlMapping shorten(@RequestBody String originalUrl) {
        return service.shortenUrl(originalUrl);
    }

    @Operation(summary = "Redirect to original URL")
    @GetMapping("/{shortCode}")
    public void redirect(@PathVariable String shortCode, HttpServletResponse response) throws IOException {
        UrlMapping mapping = service.getUrl(shortCode);
        // Command response to redirect to original URL
        response.sendRedirect(mapping.getOriginalUrl());
    }

    @Operation(summary = "Get stats for a short code")
    @GetMapping("/stats/{shortCode}")
    public UrlMapping getStats(@PathVariable String shortCode) {
        return service.getUrl(shortCode);
    }
}