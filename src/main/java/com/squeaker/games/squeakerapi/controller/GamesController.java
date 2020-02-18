package com.squeaker.games.squeakerapi.controller;

import com.squeaker.games.squeakerapi.model.Game;
import com.squeaker.games.squeakerapi.service.GamesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("games")
@RequiredArgsConstructor
public class GamesController {

    private final GamesService service;

    @GetMapping
    public @ResponseBody List<Game> games() {
        return service.getGames();
    }
}
