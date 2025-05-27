package br.com.store.application.translator;

import br.com.store.userinterface.dto.request.OrderRequestDTO;
import br.com.store.userinterface.dto.request.ProductRequestDTO;
import br.com.store.userinterface.dto.response.BoxResponseDTO;
import br.com.store.userinterface.dto.response.OrderResponseDTO;
import br.com.store.userinterface.dto.response.OrdersResponseDTO;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class OrdersTranslator {
    private static final String UNASSIGNED = "Produto não cabe em nenhuma caixa disponível.";

    public OrdersResponseDTO translate(Stream<OrderResponseDTO> orders) {
        var response = orders.collect(Collectors.toCollection(() ->
                new TreeSet<>(Comparator.comparing(OrderResponseDTO::id))));
        return new OrdersResponseDTO(response);
    }

    public OrderResponseDTO translate(OrderRequestDTO order,
            Map<String, List<ProductRequestDTO>> assigned, List<String> unassigned) {
        return new OrderResponseDTO(
                order.id(),
                translate(assigned, unassigned)
        );
    }

    private Set<BoxResponseDTO> translate(Map<String, List<ProductRequestDTO>> assigned, List<String> unassigned) {
        Set<BoxResponseDTO> boxes = new TreeSet<>(Comparator.comparing(BoxResponseDTO::id,
                Comparator.nullsLast(Comparator.naturalOrder())));
        assigned.forEach((box, products) ->
            boxes.add(BoxResponseDTO.from(box, products))
        );
        if (!unassigned.isEmpty()) {
            boxes.add(BoxResponseDTO.from(unassigned, UNASSIGNED));
        }
        return boxes;
    }
}
