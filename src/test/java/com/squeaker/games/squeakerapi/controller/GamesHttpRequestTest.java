package com.squeaker.games.squeakerapi.controller;

import com.squeaker.games.squeakerapi.model.Game;
import com.squeaker.games.squeakerapi.service.GamesService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GamesHttpRequestTest {

    @MockBean
    private GamesService mockService;
    @Resource
    private TestRestTemplate restTemplate;

    @Test
    public void getGamesReturnsOkAndExpectedGamesList() {
        List<Game> expected = List.of(new Game(UUID.randomUUID().toString(), "Bears vs Babies"));
        when(mockService.getGames()).thenReturn(expected);

        ResponseEntity<List<Game>> response = restTemplate.exchange("/games", HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {});

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(expected);

        verify(mockService).getGames();
    }

    @Test
    public void deleteGamesReturnsNoContent() {
        String expected = UUID.randomUUID().toString();
        ResponseEntity<String> response = restTemplate.exchange("/games/{id}", HttpMethod.DELETE, null, String.class, expected);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(response.getBody()).isNull();

        verify(mockService).deleteGame(expected);
    }

    @Test
    public void postGameReturnsGameAndLocation() {
        Game expected = Game.builder().id(UUID.randomUUID().toString()).name("Bears vs Babies").build();
        when(mockService.addGame(any(Game.class))).thenReturn(expected);

        ResponseEntity<Game> response = restTemplate.postForEntity("/games", expected, Game.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(expected);
        assertThat(response.getHeaders().getLocation()).hasPath("/games/" + expected.getId());

        verify(mockService).addGame(expected);
    }

    @Test
    public void postGameReturnsBadRequestWhenRequiredFieldsMissing() {
        ResponseEntity<String> response = restTemplate.postForEntity("/games", Game.builder().build(), String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).contains("NotBlank", "game.name");

        verifyNoInteractions(mockService);
    }
}
