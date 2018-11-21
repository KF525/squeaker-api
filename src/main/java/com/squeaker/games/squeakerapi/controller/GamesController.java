package com.squeaker.games.squeakerapi.controller;

import com.squeaker.games.squeakerapi.model.Game;
import com.squeaker.games.squeakerapi.service.GamesService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(path = "games")
public class GamesController {

    public GamesController(GamesService service) {
        this.service = service;
    }

    private GamesService service;

    @GetMapping
    public @ResponseBody List<Game> games() {
        return service.getGames();
    }
}
