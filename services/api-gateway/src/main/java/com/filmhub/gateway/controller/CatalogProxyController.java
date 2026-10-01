package com.filmhub.gateway.controller;

import java.net.URI;
import java.util.Collections;
import java.util.Set;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
public class CatalogProxyController {
    private final String catalogUrl;
    private final RestClient restClient = RestClient.create();
    private static final Set<String> HOP_BY_HOP = Set.of(
            "host",
            "connection",
            "keep-alive",
            "content-length",
            "transfer-encoding",
            "upgrade",
            "expect"
    );

    public CatalogProxyController(@Value("${gateway.catalog-url}") String catalogUrl) {
        this.catalogUrl = catalogUrl;
    }

    @RequestMapping("/api/catalog/**")
    ResponseEntity<byte[]> proxy(HttpServletRequest request, @RequestBody(required = false) byte[] body) {
        String query = request.getQueryString();
        URI target = URI.create(catalogUrl + request.getRequestURI() + (query == null ? "" : "?" + query));

        RestClient.RequestBodySpec spec = restClient
            .method(HttpMethod.valueOf(request.getMethod()))
            .uri(target)
            .headers(headers -> copyRequestHeaders(request, headers));

        if (body != null && body.length > 0) {
            spec = spec.body(body);
        }

        return spec.exchange((req, res) -> {
            HttpHeaders responseHeaders = new HttpHeaders();
            res.getHeaders().forEach((name, values) -> {
                if (!HOP_BY_HOP.contains(name.toLowerCase())) {
                    responseHeaders.addAll(name, values);
                }
            });
            return ResponseEntity.status(res.getStatusCode())
                    .headers(responseHeaders)
                    .body(res.getBody().readAllBytes());
        });
    }

    private static void copyRequestHeaders(HttpServletRequest request, HttpHeaders headers) {
        for (String headerName : Collections.list(request.getHeaderNames())) {
            if (!HOP_BY_HOP.contains(headerName.toLowerCase())) {
                // HTTP Header maybe multi-valued, so we need to add all values for the header
                headers.addAll(headerName, Collections.list(request.getHeaders(headerName)));
            }
        }
    }
}
