package br.com.store.domain.catalog;

import br.com.store.application.gateway.BoxGateway;
import br.com.store.domain.enumeration.BoxEnum;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class BoxCatalog implements BoxGateway {
    @Override
    public Optional<BoxEnum> find(int height, int width, int length) {
        return Arrays.stream(BoxEnum.values())
                .filter(value -> value.getBox().fit(height, width, length))
                .findFirst();
    }
}
