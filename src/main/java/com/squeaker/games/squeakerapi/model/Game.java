package com.squeaker.games.squeakerapi.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "games")
public class Game {
    @Id
    @Null
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String id;

    @NotBlank
    @Schema(required = true)
    private String name;
}
