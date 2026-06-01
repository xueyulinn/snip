package edu.neu.snip.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateMappingRequest {

  @NotBlank
  @Size(max = 2048)
  String originalUrl;
}
