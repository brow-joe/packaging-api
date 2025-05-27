package br.com.store.userinterface.controller;

import br.com.store.userinterface.dto.request.OrdersRequestDTO;
import br.com.store.userinterface.dto.response.OrdersResponseDTO;
import br.com.store.userinterface.usecase.PackOrdersUseCase;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(OrdersController.PATH)
@SecurityRequirement(name = "basicAuth")
@Tag(name = "Pedidos", description = "Api de pedidos")
public class OrdersController {
    protected static final String PATH = "/api/v1/orders";

    private final PackOrdersUseCase packOrdersUseCase;

    public OrdersController(PackOrdersUseCase packOrdersUseCase) {
        this.packOrdersUseCase = packOrdersUseCase;
    }

    @PostMapping("/packaging")
    public ResponseEntity<OrdersResponseDTO> packaging(@RequestBody @Valid OrdersRequestDTO request) {
        return ResponseEntity.ok(packOrdersUseCase.execute(request));
    }
}
