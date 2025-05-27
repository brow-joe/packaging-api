package br.com.store.application.translator;

import br.com.store.userinterface.dto.request.DimensionRequestDTO;
import br.com.store.userinterface.dto.request.OrderRequestDTO;
import br.com.store.userinterface.dto.request.ProductRequestDTO;
import br.com.store.userinterface.dto.response.BoxResponseDTO;
import br.com.store.userinterface.dto.response.OrderResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

@DisplayName("OrdersTranslator")
public class OrdersTranslatorTest extends Assertions {
    private final OrdersTranslator translator = new OrdersTranslator();

    @Test
    @DisplayName("OrdersTranslator.translateToOrdersResponseDTO")
    public void translateToOrdersResponseDTO() {
        var stream = Stream.of(
                new OrderResponseDTO(2L, Set.of(
                        new BoxResponseDTO("Caixa 1", List.of("Call of Duty", "Joystick", "Fifa 24"), null)
                )),
                new OrderResponseDTO(1L, Set.of(
                        new BoxResponseDTO("Caixa 2", List.of("PS5", "Volante"), null)
                )),
                new OrderResponseDTO(6L, Set.of(
                        new BoxResponseDTO("Caixa 3", List.of("Monitor"), null)
                ))
        );
        var result = translator.translate(stream);
        assertNotNull(result);
        assertNotNull(result.orders());
        assertEquals(3, result.orders().size());
    }

    @Test
    @DisplayName("OrdersTranslator.translateToOrderResponseDTO")
    public void translateToOrderResponseDTO() {
        var order = new OrderRequestDTO(4L, Set.of(
                new ProductRequestDTO("Mouse Gamer", new DimensionRequestDTO(5, 8, 12)),
                new ProductRequestDTO("Teclado Mecânico", new DimensionRequestDTO(4, 45, 15)),
                new ProductRequestDTO("Cadeira Gamer", new DimensionRequestDTO(120, 60, 70))
        ));
        var assigned = Map.of("Caixa 1", List.of(
                new ProductRequestDTO("Mouse Gamer", new DimensionRequestDTO(5, 8, 12)),
                new ProductRequestDTO("Teclado Mecânico", new DimensionRequestDTO(4, 45, 15))
        ));
        var unassigned = List.of("Cadeira Gamer");

        var result = translator.translate(order, assigned, unassigned);
        assertNotNull(result);
        assertNotNull(result.boxes());
        assertEquals(2, result.boxes().size());
    }
}
