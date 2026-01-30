package org.example.authzservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class OpenIdConfigController {

    @GetMapping("/.well-known/openid-configuration")
    public Map<String, Object> openidConfig() {
        Map<String, Object> config = new HashMap<>();
        config.put("issuer", "http://localhost:9000");
        config.put("jwks_uri", "http://localhost:9000/.well-known/jwks.json");
        return config;
    }
}
