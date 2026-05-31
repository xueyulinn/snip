package edu.neu.snip.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RequestMapping()
@RestController
public class ShortUrlController {
    @PostMapping(value = "/api/v1/links")
    public void createShortUrl() {

    }

    @GetMapping(value = "/api/v1/links")
    public void getShortUrl(@PathVariable String shortUrl){

    }
}
