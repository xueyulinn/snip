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


/**
 * REST endpoints for creating, querying, and redirecting short URLs.
 */
@AllArgsConstructor
@RequestMapping("short_url")
@RestController
public class ShortUrlController {

  private final ShortUrlService shortUrlService;

  /**
   * Creates a short URL mapping for the given original URL.
   *
   * @param req the request containing the original URL
   * @return an {@link ApiResponse} wrapping the created mapping
   */
  @PostMapping(value = "/api/v1/links")
  public ApiResponse<CreateMappingResponse> createShortUrl(@RequestBody CreateMappingRequest req) {
    CreateMappingResponse resp = shortUrlService.createShortUrl(req);
    return ApiResponse.success("success", resp);
  }

  /**
   * Returns the mapping information for the given short code.
   *
   * @param shortCode the short code to look up
   * @return an {@link ApiResponse} wrapping the mapping, or a not-found result if absent
   */
  @GetMapping(value = "/api/v1/links/{shortCode}")
  public ApiResponse<CreateMappingResponse> getShortUrlInfo(@PathVariable String shortCode) {
    CreateMappingResponse createMappingResponse = shortUrlService.getShortUrlInfo(shortCode);
    if (createMappingResponse == null) {
      ApiResponse.fail(404, "short url not found");
    }
    return ApiResponse.success("success", createMappingResponse);
  }

  /**
   * Redirects to the original URL associated with the given short code.
   *
   * @param shortCode the short code to resolve
   * @param resp the servlet response used to issue the redirect or a not-found error
   */
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

  /**
   * Simple health check endpoint.
   *
   * @return an {@link ApiResponse} indicating the service is up
   */
  @GetMapping(value = "/api/health")
  public ApiResponse<String> health() {
    return ApiResponse.success("health", "ok");
  }
}
