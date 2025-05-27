package br.com.store.domain.enumeration;

import br.com.store.domain.Box;
import br.com.store.domain.Box.Dimension;

public enum BoxEnum {
    BOX_1("Caixa 1", new Box(new Dimension(30, 40, 80))),
    BOX_2("Caixa 2", new Box(new Dimension(80, 50, 40))),
    BOX_3("Caixa 3", new Box(new Dimension(50, 80, 60)));

    private final String id;
    private final Box box;

    BoxEnum(String id, Box box) {
        this.id = id;
        this.box = box;
    }

    public String getId() {
        return id;
    }

    public Box getBox() {
        return box;
    }
}
