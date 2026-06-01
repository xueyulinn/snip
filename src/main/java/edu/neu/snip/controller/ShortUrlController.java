package edu.neu.snip.controller;

import edu.neu.snip.dto.ApiResponse;
import edu.neu.snip.dto.CreateMappingRequest;
import edu.neu.snip.dto.CreateMappingResponse;
import edu.neu.snip.service.ShortUrlService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


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
        if (createMappingResponse == null){
            ApiResponse.fail(404, "short url not found");
        }
        return ApiResponse.success("success", createMappingResponse);
    }

    @GetMapping(value = "/api/health")
    public ApiResponse<String> health() {
        return ApiResponse.success("health", "ok");
    }
}
