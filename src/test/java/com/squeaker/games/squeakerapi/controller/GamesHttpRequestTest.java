package com.squeaker.games.squeakerapi.controller;

import com.squeaker.games.squeakerapi.model.Game;
import com.squeaker.games.squeakerapi.service.GamesService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GamesHttpRequestTest {

    @MockBean
    private GamesService service;
    @Resource
    private TestRestTemplate restTemplate;

    @Test
    public void gamesReturnsOkAndExpectedGamesList() {
        List<Game> expected = List.of(new Game(UUID.randomUUID().toString(), "Bears vs Babies"));
        when(service.getGames()).thenReturn(expected);

        ResponseEntity<List<Game>> response = restTemplate.exchange("/games", HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {});

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(expected);
    }
}
