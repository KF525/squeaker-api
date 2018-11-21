package com.squeaker.games.squeakerapi.controller;

import com.squeaker.games.squeakerapi.service.GamesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GamesControllerTest {

    @MockBean
    private GamesService service;

    @Autowired
    private GamesController controller;


    @Test
    public void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    public void controllerCallsService() {
        controller.games();
        verify(service).getGames();
    }
}
