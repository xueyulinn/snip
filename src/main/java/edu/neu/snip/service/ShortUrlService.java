package edu.neu.snip.service;

import edu.neu.snip.component.RedisGenerator;
import edu.neu.snip.dto.CreateMappingRequest;
import edu.neu.snip.dto.CreateMappingResponse;
import edu.neu.snip.model.ShortUrlMapping;
import edu.neu.snip.repository.ShortUrlMappingRepository;
import edu.neu.snip.util.Base64;
import edu.neu.snip.util.Digest;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Core service for creating short URL mappings and resolving them back to original URLs.
 */
@Service
@RequiredArgsConstructor
public class ShortUrlService {

  private static final Logger logger = LoggerFactory.getLogger(ShortUrlService.class);
  private final CacheService cacheService;
  private final ShortUrlMappingRepository shortUrlMappingRepository;
  private final RedisGenerator redisGenerator;

  /**
   * Creates and persists a new short URL mapping for the given original URL.
   *
   * @param req the request containing the original URL
   * @return the created mapping details
   */
  public CreateMappingResponse createShortUrl(CreateMappingRequest req) {
    //       String mapping = cacheService.getFromCache(shortUrl);
    //       if (mapping != null) return true;
    String originalUrl = req.getOriginalUrl();
    long id = redisGenerator.nextId();
    String shortCode = Base64.encode(id);
    String digest = Digest.digest(originalUrl);

    // save db
    ShortUrlMapping shortUrlMapping = new ShortUrlMapping();
    shortUrlMapping.setShortCode(shortCode);
    shortUrlMapping.setOriginalUrl(originalUrl);
    shortUrlMapping.setStatus((short) 1);
    shortUrlMapping.setExpireTime(LocalDateTime.now());
    shortUrlMapping.setOriginUrlHash(digest);
    ShortUrlMapping saved = shortUrlMappingRepository.save(shortUrlMapping);

    CreateMappingResponse resp = new CreateMappingResponse();
    resp.setOriginalUrl(saved.getOriginalUrl());
    resp.setShortUrl("http://" + saved.getShortCode());
    resp.setShortCode(saved.getShortCode());
    resp.setExpireTime(saved.getExpireTime());
    resp.setCreateTime(saved.getCreateTime());
    // save cache
    // cacheService.setCache(shortUrl, longUrl);

    return resp;
  }


  /**
   * Looks up the mapping for the given short code.
   *
   * @param shortUrl the short code to resolve
   * @return the mapping details, or {@code null} if no mapping exists
   */
  public CreateMappingResponse getShortUrlInfo(String shortUrl) {
    ShortUrlMapping saved = shortUrlMappingRepository.findByShortCode(shortUrl);
    if (saved == null) {
      return null;
    }
    CreateMappingResponse resp = new CreateMappingResponse();
    resp.setOriginalUrl(saved.getOriginalUrl());
    resp.setShortUrl("http://" + saved.getShortCode());
    resp.setShortCode(saved.getShortCode());
    resp.setExpireTime(saved.getExpireTime());
    resp.setCreateTime(saved.getCreateTime());
    return resp;
  }

}
