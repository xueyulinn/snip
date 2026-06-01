package edu.neu.snip.dto;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * Response payload describing a short URL mapping.
 */
@Data
public class CreateMappingResponse {

  private String shortCode;
  private String shortUrl;
  private String originalUrl;
  private LocalDateTime createTime;
  private LocalDateTime expireTime;
}
