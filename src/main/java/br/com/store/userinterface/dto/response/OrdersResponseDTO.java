package br.com.store.userinterface.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Set;

@Schema(description = "Detalhes dos pedidos")
public record OrdersResponseDTO(
        @JsonProperty("pedidos")
        Set<OrderResponseDTO> orders
) { }
