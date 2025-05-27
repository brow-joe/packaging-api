package br.com.store.application.gateway;

import br.com.store.domain.enumeration.BoxEnum;

import java.util.Optional;

public interface BoxGateway {
    Optional<BoxEnum> find(int height, int width, int length);
}
