package com.jabi.scraper.service;

import com.jabi.scraper.DTO.ScrapDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ScrapService {

    public List<ScrapDTO> scrape(String url, String selector, String attr) throws IOException {
        Document doc = Jsoup.connect(url).get();

        // Si no se proporciona selector, devolver todo el texto del body
        if (selector == null || selector.isEmpty()) {
            String fullText = doc.body().text();
            return List.of(new ScrapDTO(fullText));
        }

        // Si hay selector, buscar elementos específicos
        Elements elements = doc.select(selector);
        
        return elements.stream()
                .map(e -> {
                    if (attr != null && !attr.isEmpty()) {
                        return new ScrapDTO(e.attr(attr));
                    } else {
                        return new ScrapDTO(e.text());
                    }
                })
                .toList();
    }
}
