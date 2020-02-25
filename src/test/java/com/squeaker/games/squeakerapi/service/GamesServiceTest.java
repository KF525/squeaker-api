package com.squeaker.games.squeakerapi.service;

import com.squeaker.games.squeakerapi.model.Game;
import com.squeaker.games.squeakerapi.repository.GamesRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = GamesService.class)
class GamesServiceTest {

    @MockBean
    private GamesRepository mockRepository;

    @Resource
    private GamesService service;

    @Test
    void getAllGamesCallsRepository() {
        List<Game> expected = List.of(Game.builder().name("game").build());
        when(mockRepository.findAll()).thenReturn(expected);

        assertThat(service.getGames()).isEqualTo(expected);
        verify(mockRepository).findAll();
    }

    @Test
    void addGameCallsSaveInRepository() {
        Game expected = Game.builder().name("game").build();
        when(mockRepository.save(any(Game.class))).thenReturn(expected);

        assertThat(service.addGame(expected)).isEqualTo(expected);
        verify(mockRepository).save(expected);
    }

    @Test
    void deleteGameCallsRepository() {
        String expected = UUID.randomUUID().toString();

        service.deleteGame(expected);
        verify(mockRepository).deleteById(expected);
    }
}
