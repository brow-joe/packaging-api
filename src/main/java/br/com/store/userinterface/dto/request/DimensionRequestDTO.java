package br.com.store.userinterface.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Dimensões do produto")
public record DimensionRequestDTO(
        @JsonProperty("altura")
        @NotNull int height,
        @JsonProperty("largura")
        @NotNull int width,
        @JsonProperty("comprimento")
        @NotNull int length
) { }
