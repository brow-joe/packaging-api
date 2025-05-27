package br.com.store.userinterface.usecase;

import br.com.store.userinterface.dto.request.OrdersRequestDTO;
import br.com.store.userinterface.dto.response.OrdersResponseDTO;

@FunctionalInterface
public interface PackOrdersUseCase {
    OrdersResponseDTO execute(OrdersRequestDTO in);
}
