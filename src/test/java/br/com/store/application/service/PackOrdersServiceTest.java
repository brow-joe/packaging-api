package br.com.store.application.service;

import br.com.store.application.gateway.BoxGateway;
import br.com.store.application.translator.OrdersTranslator;
import br.com.store.domain.catalog.BoxCatalog;
import br.com.store.userinterface.dto.request.DimensionRequestDTO;
import br.com.store.userinterface.dto.request.OrderRequestDTO;
import br.com.store.userinterface.dto.request.OrdersRequestDTO;
import br.com.store.userinterface.dto.request.ProductRequestDTO;
import br.com.store.userinterface.dto.response.BoxResponseDTO;
import br.com.store.userinterface.dto.response.OrderResponseDTO;
import br.com.store.userinterface.dto.response.OrdersResponseDTO;
import br.com.store.userinterface.usecase.PackOrdersUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@DisplayName("PackOrdersService")
public class PackOrdersServiceTest extends Assertions {
    private final OrdersTranslator translator = new OrdersTranslator();
    private final BoxGateway gateway = new BoxCatalog();
    private final PackOrdersUseCase service = new PackOrdersService(gateway, translator);

    @ParameterizedTest
    @MethodSource("provideArguments")
    @DisplayName("CreateOrderService.execute")
    public void execute(final OrdersRequestDTO in, final OrdersResponseDTO expected) {
        var result = service.execute(in);
        assertEquals(expected, result);
    }

    private static Stream<Arguments> provideArguments() {
        return Stream.of(
                Arguments.of(
                        new OrdersRequestDTO(Set.of(new OrderRequestDTO(1L, Set.of(
                                new ProductRequestDTO("PS5", new DimensionRequestDTO(40, 10, 25)),
                                new ProductRequestDTO("Volante", new DimensionRequestDTO(40, 30, 30))
                        )))),
                        new OrdersResponseDTO(Set.of(new OrderResponseDTO(1L, Set.of(
                                new BoxResponseDTO("Caixa 2", List.of("PS5", "Volante"), null)
                        ))))
                ),
                Arguments.of(
                        new OrdersRequestDTO(Set.of(new OrderRequestDTO(2L, Set.of(
                                new ProductRequestDTO("Joystick", new DimensionRequestDTO(15, 20, 10)),
                                new ProductRequestDTO("Fifa 24", new DimensionRequestDTO(10, 30, 10)),
                                new ProductRequestDTO("Call of Duty", new DimensionRequestDTO(30, 15, 10))
                        )))),
                        new OrdersResponseDTO(Set.of(new OrderResponseDTO(2L, Set.of(
                                new BoxResponseDTO("Caixa 1", List.of("Call of Duty", "Joystick", "Fifa 24"), null)
                        ))))
                ),
                Arguments.of(
                        new OrdersRequestDTO(Set.of(new OrderRequestDTO(5L, Set.of(
                                new ProductRequestDTO("Cadeira Gamer", new DimensionRequestDTO(120, 60, 70))
                        )))),
                        new OrdersResponseDTO(Set.of(new OrderResponseDTO(5L, Set.of(
                                new BoxResponseDTO(null, List.of("Cadeira Gamer"), "Produto não cabe em nenhuma caixa disponível.")
                        ))))
                ),
                Arguments.of(
                        new OrdersRequestDTO(Set.of(new OrderRequestDTO(6L, Set.of(
                                new ProductRequestDTO("Webcam", new DimensionRequestDTO(7, 10, 5)),
                                new ProductRequestDTO("Microfone", new DimensionRequestDTO(25, 10, 10)),
                                new ProductRequestDTO("Monitor", new DimensionRequestDTO(50, 60, 20)),
                                new ProductRequestDTO("Notebook", new DimensionRequestDTO(2, 35, 25))
                        )))),
                        new OrdersResponseDTO(Set.of(new OrderResponseDTO(6L, Set.of(
                                new BoxResponseDTO("Caixa 1", List.of("Microfone", "Webcam", "Notebook"), null),
                                new BoxResponseDTO("Caixa 3", List.of("Monitor"), null)
                        ))))
                )
        );
    }
}
