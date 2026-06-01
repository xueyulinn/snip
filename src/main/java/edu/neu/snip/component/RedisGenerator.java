package edu.neu.snip.component;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedisGenerator {

  private final StringRedisTemplate redisTemplate;
  private static final String KEY = "snip:id:counter";


  public long nextId() {
    Long id = redisTemplate.opsForValue().increment(KEY);
    return id;
  }
}

