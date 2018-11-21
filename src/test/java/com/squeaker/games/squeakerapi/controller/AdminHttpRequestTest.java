package com.squeaker.games.squeakerapi.controller;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

public class AdminHttpRequestTest extends HttpRequestTestBase {

    @Test
    public void healthCheckReturns() {
        ResponseEntity<String> response = restTemplate.getForEntity(buildUri("admin", "ping"), String.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo("pong");
        assertThat(response.getHeaders().getContentType()).isEqualByComparingTo(new MediaType(MediaType.TEXT_PLAIN, StandardCharsets.UTF_8));
    }

    @Test
    public void unknownPathReturns404() {
        ResponseEntity<String> response = restTemplate.getForEntity(buildUri("nope"), String.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(404);
    }
}
