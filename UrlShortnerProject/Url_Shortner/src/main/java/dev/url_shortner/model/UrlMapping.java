package dev.url_shortner.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data // Lombok generates Getters/Setters automatically
@NoArgsConstructor
@Table(name = "url_mapping")
public class UrlMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // The short code (e.g., "Ab3d9")
    // unique=true makes the DB incredibly fast at finding this
    @Column(unique = true, nullable = false)
    private String shortCode;

    // The real URL (e.g., "https://google.com...")
    @Column(nullable = false)
    private String originalUrl;

    // Stats: How many times people clicked it
    private long clickCount = 0;

    private LocalDateTime createdAt = LocalDateTime.now();
}
