package com.squeaker.games.squeakerapi.controller;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.net.URI;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class HttpRequestTestBase {

    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate restTemplate;

    URI buildUri(String... pathSegments) {
        return new DefaultUriBuilderFactory().builder()
                .scheme("http")
                .host("localhost")
                .port(port)
                .pathSegment(pathSegments)
                .build();
    }
}
