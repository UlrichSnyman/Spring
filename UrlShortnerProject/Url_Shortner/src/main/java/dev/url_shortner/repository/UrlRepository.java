package dev.url_shortner.repository;

import dev.url_shortner.model.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UrlRepository extends JpaRepository<UrlMapping, Long> {
    // Spring Data JPA writes the SQL
    Optional<UrlMapping> findByShortCode(String shortCode);
}
