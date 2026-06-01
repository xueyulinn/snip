package edu.neu.snip.repository;

import edu.neu.snip.model.ShortUrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for {@link ShortUrlMapping} entities.
 */
public interface ShortUrlMappingRepository extends JpaRepository<ShortUrlMapping, String> {

  ShortUrlMapping findByShortCode(String shortCode);
}
