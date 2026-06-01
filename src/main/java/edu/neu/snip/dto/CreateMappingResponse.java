package edu.neu.snip.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateMappingResponse {
    private String shortCode;
    private String shortUrl;
    private String originalUrl;
    private LocalDateTime createTime;
    private LocalDateTime expireTime;
}
