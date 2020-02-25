package com.squeaker.games.squeakerapi.service;

import com.squeaker.games.squeakerapi.model.Game;
import com.squeaker.games.squeakerapi.repository.GamesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GamesService {

    private final GamesRepository repository;

    public List<Game> getGames() {
        return repository.findAll();
    }

    public Game addGame(Game game) {
        return repository.save(game);
    }

    public void deleteGame(String id) {
        repository.deleteById(id);
    }
}
