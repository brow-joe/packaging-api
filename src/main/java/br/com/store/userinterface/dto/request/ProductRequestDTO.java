package br.com.store.userinterface.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Informações do produto")
public record ProductRequestDTO(
        @JsonProperty("produto_id")
        @NotNull String id,
        @JsonProperty("dimensoes")
        @NotNull @Valid DimensionRequestDTO dimension
) { }
