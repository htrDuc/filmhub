package com.filmhub.catalog.controller;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/catalog")
public class PingController {
    @GetMapping ("/ping")
    String ping() {
        return "pong";
    }

    @GetMapping ("/me")
    Me me (@AuthenticationPrincipal Jwt jwt) {
        Map<String, Object> realmAccess = jwt.getClaimAsMap("realm_access");
        @SuppressWarnings ("unchecked")
        List<String> roles = realmAccess == null ? List.of(): (List<String>) realmAccess.get("roles");
        return new Me(jwt.getSubject(), jwt.getClaimAsString("preferred_username"), roles);
    }

    record Me(String sub, String preferredUsername, List<String> roles) {}
}
