package br.com.store.userinterface.dto.response;

import br.com.store.userinterface.dto.request.ProductRequestDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Detalhes da caixa")
@JsonPropertyOrder({"caixa_id", "produtos", "observacao"})
public record BoxResponseDTO(
        @JsonProperty("caixa_id")
        String id,
        @JsonProperty("produtos")
        List<String> products,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("observacao")
        String details
) {
        public static BoxResponseDTO from(String id, List<ProductRequestDTO> products) {
                var productIds = products.stream().map(ProductRequestDTO::id).toList();
                return new BoxResponseDTO(id, productIds, null);
        }

        public static BoxResponseDTO from (List<String> products, String details) {
                return new BoxResponseDTO(null, products, details);
        }
}
