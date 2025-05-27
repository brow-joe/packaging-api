package br.com.store.application.service;

import br.com.store.application.gateway.BoxGateway;
import br.com.store.application.translator.OrdersTranslator;
import br.com.store.domain.Box.Dimension;
import br.com.store.userinterface.dto.request.OrderRequestDTO;
import br.com.store.userinterface.dto.request.OrdersRequestDTO;
import br.com.store.userinterface.dto.request.ProductRequestDTO;
import br.com.store.userinterface.dto.response.OrderResponseDTO;
import br.com.store.userinterface.dto.response.OrdersResponseDTO;
import br.com.store.userinterface.usecase.PackOrdersUseCase;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PackOrdersService implements PackOrdersUseCase {
    private final BoxGateway gateway;
    private final OrdersTranslator translator;

    public PackOrdersService(BoxGateway gateway, OrdersTranslator translator) {
        this.gateway = gateway;
        this.translator = translator;
    }

    /**
     * Executa o processo de empacotamento de todos os pedidos recebidos.
     * Traduz a lista de pedidos processados em uma resposta adequada.
     */
    @Override
    public OrdersResponseDTO execute(final OrdersRequestDTO in) {
        return translator.translate(
                in.orders().stream()
                        .map(this::process)
        );
    }

    /**
     * Processa um único pedido:
     * - Inicializa mapas de produtos atribuídos e não atribuídos a um pacote.
     * - Realiza o empacotamento dos produtos.
     * - Traduz o resultado em um DTO de resposta.
     */
    private OrderResponseDTO process(final OrderRequestDTO order) {
        var assigned = new HashMap<String, List<ProductRequestDTO>>();
        var unassigned = new ArrayList<String>();
        pack(order.products(), assigned, unassigned);
        return translator.translate(order, assigned, unassigned);
    }

    /**
     * Tenta empacotar todos os produtos em caixas disponíveis.
     * - Enquanto houver produtos restantes:
     *   - Tenta combinar um grupo de produtos em uma única caixa.
     *   - Se não conseguir, move os produtos rejeitados para tentar com menos itens.
     *   - Se não houver mais como dividir, tenta empacotar produto por produto.
     */
    private void pack(Set<ProductRequestDTO> products, Map<String, List<ProductRequestDTO>> assigned, List<String> unassigned) {
        var remaining = new ArrayList<>(products);

        while (!remaining.isEmpty()) {
            var group = new ArrayList<>(remaining);
            var rejected = new ArrayList<ProductRequestDTO>();

            while (!group.isEmpty()) {
                if (combine(assigned, group, remaining, rejected)) {
                    break; // Sucesso: grupo encaixou em uma caixa
                }
            }

            // Se o grupo foi esvaziado e sobrou produto rejeitado, tenta empacotar individualmente
            if (group.isEmpty() && !rejected.isEmpty()) {
                complete(assigned, unassigned, remaining, rejected);
            }
        }
    }

    /**
     * Tenta empacotar um grupo de produtos em uma caixa compatível.
     * - Calcula as dimensões combinadas do grupo.
     * - Verifica se existe uma caixa que suporte esse volume.
     * - Se sim, atribui os produtos à caixa.
     * - Se não, remove o último item do grupo e adiciona aos rejeitados.
     *
     * @return true se conseguiu encontrar uma caixa para o grupo.
     */
    private boolean combine(Map<String, List<ProductRequestDTO>> assigned, List<ProductRequestDTO> group,
        List<ProductRequestDTO> remaining, List<ProductRequestDTO> rejected) {
            var combined = combine(group); // Combina as dimensões dos produtos do grupo
            var finder = gateway.find(combined.height(), combined.width(), combined.length());
            var response = finder.isPresent();

            if (response) {
                var box = finder.get();
                assigned.computeIfAbsent(box.getId(), id -> new ArrayList<>()).addAll(group); // Atribui à caixa
                remaining.removeAll(group); // Remove do conjunto restante
            } else {
                rejected.addFirst(group.removeLast()); // Remove o último item e tenta com menos produtos
            }
            return response;
    }

    /**
     * Tenta empacotar individualmente os produtos rejeitados.
     * - Para cada produto:
     *   - Tenta encontrar uma caixa compatível.
     *   - Se não encontrar, adiciona o ID à lista de não atribuídos.
     *   - Reinsere os outros produtos rejeitados para nova tentativa de empacotamento.
     */
    private void complete(Map<String, List<ProductRequestDTO>> assigned, List<String> unassigned,
        List<ProductRequestDTO> remaining, List<ProductRequestDTO> rejected) {
            var product = rejected.removeFirst();
            var dimension = product.dimension();

            var finder = gateway.find(
                    dimension.height(),
                    dimension.width(),
                    dimension.length()
            );
            if (finder.isPresent()) {
                var box = finder.get();
                assigned.computeIfAbsent(box.getId(), id -> new ArrayList<>()).add(product);
            } else {
                unassigned.add(product.id()); // Produto não pôde ser empacotado
            }
            remaining.remove(product);
            remaining.addAll(rejected); // Tenta novamente empacotar os rejeitados
    }

    /**
     * Combina as dimensões de uma lista de produtos para simular o volume total necessário.
     * - A altura é somada (empilhamento).
     * - A largura e o comprimento usam o maior valor (base da caixa).
     */
    private Dimension combine(List<ProductRequestDTO> products) {
        var height = 0;
        var width = 0;
        var length = 0;

        for (ProductRequestDTO product : products) {
            height += product.dimension().height(); // Soma as alturas
            width = Math.max(width, product.dimension().width());
            length = Math.max(length, product.dimension().length());
        }
        return new Dimension(height, width, length);
    }
}
