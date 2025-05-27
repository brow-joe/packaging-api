package br.com.store.domain.catalog;

import br.com.store.application.gateway.BoxGateway;
import br.com.store.domain.enumeration.BoxEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("BoxCatalog")
public class BoxCatalogTest extends Assertions {
    private final BoxGateway gateway = new BoxCatalog();

    @Test
    @DisplayName("BoxCatalog.find - BOX_1")
    public void find_BOX_1() {
        var result = gateway.find(15, 20, 10);
        assertTrue(result.isPresent());
        assertEquals(BoxEnum.BOX_1, result.get());
    }

    @Test
    @DisplayName("BoxCatalog.find - BOX_2")
    public void find_BOX_2() {
        var result = gateway.find(80, 30, 30);
        assertTrue(result.isPresent());
        assertEquals(BoxEnum.BOX_2, result.get());
    }

    @Test
    @DisplayName("BoxCatalog.find - BOX_3")
    public void find_BOX_3() {
        var result = gateway.find(50, 60, 20);
        assertTrue(result.isPresent());
        assertEquals(BoxEnum.BOX_3, result.get());
    }

    @Test
    @DisplayName("BoxCatalog.find - NOT_FOUND")
    public void find_NOT_FOUND() {
        var result = gateway.find(120, 60, 70);
        assertTrue(result.isEmpty());
    }
}
