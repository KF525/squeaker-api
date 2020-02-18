package com.squeaker.games.squeakerapi.controller;

import com.squeaker.games.squeakerapi.service.GamesService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = GamesController.class)
class GamesControllerTest {

    @MockBean
    private GamesService service;
    @Resource
    private GamesController controller;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    void controllerCallsService() {
        controller.games();
        verify(service).getGames();
    }
}
