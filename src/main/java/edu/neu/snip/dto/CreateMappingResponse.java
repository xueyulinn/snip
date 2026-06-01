package edu.neu.snip.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
public class CreateMappingResponse {
    private String shortCode;
    private String shortUrl;
    private String originalUrl;
    private LocalDateTime createTime;
    private LocalDateTime expireTime;
}
