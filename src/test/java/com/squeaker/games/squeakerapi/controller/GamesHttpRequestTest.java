package com.squeaker.games.squeakerapi.controller;

import com.google.common.collect.ImmutableList;
import com.squeaker.games.squeakerapi.model.Game;
import com.squeaker.games.squeakerapi.service.GamesService;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class GamesHttpRequestTest extends HttpRequestTestBase {

    @MockBean
    private GamesService service;

    @Test
    public void gamesReturnsOkAndExpectedGamesList() {
        List<Game> expected = ImmutableList.of(new Game("Bears vs Babies"));
        when(service.getGames()).thenReturn(expected);

        ResponseEntity<List<Game>> response = restTemplate.exchange(
                buildUri("games"),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Game>>() {});

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(expected);
    }
}
