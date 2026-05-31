package edu.neu.snip.controller;

import edu.neu.snip.dto.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RequestMapping("short_url")
@RestController
public class ShortUrlController {
    @PostMapping(value = "/api/v1/links")
    public void createShortUrl() {

    }

    @GetMapping(value = "/api/v1/links")
    public void getShortUrl(@PathVariable String shortUrl){

    }

    @GetMapping(value = "/api/health")
    public ApiResponse<String> health(){
        return ApiResponse.success("health", "ok");
    }
}
