package edu.neu.snip.controller;

import edu.neu.snip.dto.ApiResponse;
import edu.neu.snip.dto.CreateMappingRequest;
import edu.neu.snip.dto.CreateMappingResponse;
import edu.neu.snip.service.ShortUrlService;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@AllArgsConstructor
@RequestMapping("short_url")
@RestController
public class ShortUrlController {

  private final ShortUrlService shortUrlService;

  @PostMapping(value = "/api/v1/links")
  public ApiResponse<CreateMappingResponse> createShortUrl(@RequestBody CreateMappingRequest req) {
    CreateMappingResponse resp = shortUrlService.createShortUrl(req);
    return ApiResponse.success("success", resp);
  }

  @GetMapping(value = "/api/v1/links/{shortCode}")
  public ApiResponse<CreateMappingResponse> getShortUrlInfo(@PathVariable String shortCode) {
    CreateMappingResponse createMappingResponse = shortUrlService.getShortUrlInfo(shortCode);
    if (createMappingResponse == null) {
      ApiResponse.fail(404, "short url not found");
    }
    return ApiResponse.success("success", createMappingResponse);
  }

  @GetMapping(value = "/{shortCode}")
  public void redirect(@PathVariable String shortCode, HttpServletResponse resp) {
    CreateMappingResponse createMappingResponse = shortUrlService.getShortUrlInfo(shortCode);
    try {
      if (createMappingResponse == null) {
        resp.sendError(HttpServletResponse.SC_NOT_FOUND, "original url not found");
      }

      resp.setStatus(HttpServletResponse.SC_FOUND);
      resp.sendRedirect(createMappingResponse.getOriginalUrl());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @GetMapping(value = "/api/health")
  public ApiResponse<String> health() {
    return ApiResponse.success("health", "ok");
  }
}
