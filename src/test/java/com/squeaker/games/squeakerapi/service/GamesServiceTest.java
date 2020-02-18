package com.squeaker.games.squeakerapi.service;

import com.squeaker.games.squeakerapi.model.Game;
import com.squeaker.games.squeakerapi.repository.GamesRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = GamesService.class)
class GamesServiceTest {

    @MockBean
    private GamesRepository mockRepository;

    @Resource
    private GamesService service;

    @Test
    void getAllCallsRepository() {
        List<Game> expected = List.of();
        when(mockRepository.findAll()).thenReturn(expected);

        assertThat(service.getGames()).isEqualTo(expected);
        verify(mockRepository).findAll();
    }
}
