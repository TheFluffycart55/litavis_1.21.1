package net.thefluffycart.litavis.entity.variant;

import java.util.Arrays;
import java.util.Comparator;

public enum BurrowVariant {
    BURROW_BASE(0),
    RED(1),
    ORANGE(2),
    YELLOW(3),
    GREEN(4),
    BLUE(5),
    PINK(6),
    PURPLE(7),
    BROWN(8),
    CALICO(9),
    FRUITY(10),
    JOLLY(11);

    private static final BurrowVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.comparingInt(
            BurrowVariant::getId)).toArray(BurrowVariant[]::new);
    private final int id;

    BurrowVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static BurrowVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
