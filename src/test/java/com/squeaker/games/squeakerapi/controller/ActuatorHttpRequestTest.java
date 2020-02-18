package com.squeaker.games.squeakerapi.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.annotation.Resource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ActuatorHttpRequestTest {
    @Resource
    private TestRestTemplate restTemplate;

    @Test
    void healthCheckReturns() {
        ResponseEntity<String> response = restTemplate.getForEntity("/actuator/health", String.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo("{\"status\":\"UP\"}");
        assertThat(response.getHeaders().getContentType()).isEqualByComparingTo(MediaType.APPLICATION_JSON);
    }

    @Test
    void unknownPathReturns404() {
        ResponseEntity<String> response = restTemplate.getForEntity("/nope", String.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(404);
    }
}
