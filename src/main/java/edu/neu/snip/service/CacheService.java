package edu.neu.snip.service;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Two-tier cache (in-process Caffeine cache fronting Redis) for short-code lookups.
 */
@Service
@RequiredArgsConstructor
public class CacheService {

  private static final Logger logger = LoggerFactory.getLogger(CacheService.class);

  private static final Duration DEFAULT_EXPIRE_TIME = Duration.ofHours(1);
  private static final Duration HOT_DATA_EXPIRE_TIME = Duration.ofHours(24);
  private final RedisTemplate<String, String> redisTemplate;
  private final Cache<String, String> processCache = Caffeine.newBuilder().maximumSize(10000)
      .expireAfterWrite(DEFAULT_EXPIRE_TIME).build();

  String getFromCache(String url) {
    String val = getProcessCache(url);
    if (val != null) {
      return val;
    }
    logger.debug("process cache missed with key {}", url);

    val = getRedisCache(url);
    if (val == null) {
      logger.debug("redis cache missed with key {}", url);
    }
    return val;
  }

  void setCache(String url, String longUrl) {
    setProcessCache(url, longUrl);
    setRedisCache(url, longUrl);
  }

  private void setRedisCache(String url, String longUrl) {
    redisTemplate.opsForValue().set(url, longUrl, DEFAULT_EXPIRE_TIME);
  }

  private void setProcessCache(String url, String longUrl) {
    processCache.put(url, longUrl);
  }

  private String getProcessCache(String url) {
    return processCache.getIfPresent(url);
  }

  private String getRedisCache(String url) {
    return redisTemplate.opsForValue().get(url);
  }

}
