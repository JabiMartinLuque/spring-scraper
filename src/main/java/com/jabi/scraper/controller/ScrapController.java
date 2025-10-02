package com.jabi.scraper.controller;

import com.jabi.scraper.DTO.ScrapDTO;
import com.jabi.scraper.service.ScrapService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/scraper")
public class ScrapController {

    private final ScrapService scrapService;

    public ScrapController(ScrapService scrapService) {
        this.scrapService = scrapService;
    }

    @GetMapping
    public List<ScrapDTO> scrape(
            @RequestParam String url,
            @RequestParam(required = false) String selector,
            @RequestParam(required = false) String attr) throws IOException {
        return scrapService.scrape(url, selector, attr);
    }
}
