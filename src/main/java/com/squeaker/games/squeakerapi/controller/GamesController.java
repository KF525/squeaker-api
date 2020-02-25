package com.squeaker.games.squeakerapi.controller;

import com.squeaker.games.squeakerapi.model.Game;
import com.squeaker.games.squeakerapi.service.GamesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("games")
@RequiredArgsConstructor
public class GamesController {

    private final GamesService service;

    @GetMapping
    @ResponseBody
    public List<Game> getGames() {
        return service.getGames();
    }

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Game> addGame(@RequestBody @Valid Game game) {
        Game created = service.addGame(game);
        return ResponseEntity
                .created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(created.getId()).toUri())
                .body(created);
    }

    @DeleteMapping("{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGame(@PathVariable String id) {
        service.deleteGame(id);
    }
}
