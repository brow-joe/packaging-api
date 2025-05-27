package br.com.store.userinterface.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Set;

@Schema(description = "Detalhes do pedido")
@JsonPropertyOrder({"pedido_id", "caixas"})
public record OrderResponseDTO(
        @JsonProperty("pedido_id")
        Long id,
        @JsonProperty("caixas")
        Set<BoxResponseDTO> boxes
) { }
