package br.com.store.userinterface.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;

@Schema(description = "Informações dos pedidos")
public record OrdersRequestDTO(
        @JsonProperty("pedidos")
        @NotNull @Size(min = 1) @Valid Set<@Valid OrderRequestDTO> orders
) { }
