package edu.neu.snip.repository;

import edu.neu.snip.model.ShortUrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortUrlMappingRepository extends JpaRepository<ShortUrlMapping, String> {
    ShortUrlMapping findByShortCode(String shortCode);
}
