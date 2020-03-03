package com.squeaker.games.squeakerapi.repository;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchAutoConfiguration;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.elasticsearch.rest.RestClientAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@EnableAutoConfiguration
@SpringBootTest(classes = GamesRepository.class)
@ImportAutoConfiguration({ElasticsearchDataAutoConfiguration.class, ElasticsearchAutoConfiguration.class, ElasticsearchRepositoriesAutoConfiguration.class, RestClientAutoConfiguration.class})
class GamesRepositoryTest {

    // TODO Integration Test against embedded(?) Elasticsearch
    @Resource
    private GamesRepository repository;

}
