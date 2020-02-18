package com.squeaker.games.squeakerapi.repository;

import com.squeaker.games.squeakerapi.model.Game;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface GamesRepository extends ElasticsearchRepository<Game, String> {
    List<Game> findAll();
}
