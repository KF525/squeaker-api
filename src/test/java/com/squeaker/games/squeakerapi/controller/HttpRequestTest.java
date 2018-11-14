package com.squeaker.games.squeakerapi.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.net.URI;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

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

    private URI buildUri(String... pathSegments) {
        return new DefaultUriBuilderFactory().builder()
                .scheme("http")
                .host("localhost")
                .port(port)
                .pathSegment(pathSegments)
                .build();
    }
}
