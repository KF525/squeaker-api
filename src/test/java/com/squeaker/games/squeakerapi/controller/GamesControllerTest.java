package com.squeaker.games.squeakerapi.controller;

import com.squeaker.games.squeakerapi.model.Game;
import com.squeaker.games.squeakerapi.service.GamesService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = GamesController.class)
class GamesControllerTest {

    @MockBean
    private GamesService mockService;
    @Resource
    private GamesController controller;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    void getGamesPassesThroughToService() {
        List<Game> expected = List.of(Game.builder().id(UUID.randomUUID().toString()).name("game").build());
        when(mockService.getGames()).thenReturn(expected);

        List<Game> actual = controller.getGames();

        assertThat(actual).isEqualTo(expected);
        verify(mockService).getGames();
    }

    @Test
    void addGamePassesThroughToService() {
        Game expected = Game.builder().id(UUID.randomUUID().toString()).name("game").build();
        when(mockService.addGame(any(Game.class))).thenReturn(expected);

        ResponseEntity<Game> actual = controller.addGame(expected);

        assertThat(actual.getBody()).isEqualTo(expected);
        verify(mockService).addGame(expected);
    }

    @Test
    void deleteGamePassesThroughToService() {
        String id = UUID.randomUUID().toString();
        controller.deleteGame(id);

        verify(mockService).deleteGame(id);
    }
}
