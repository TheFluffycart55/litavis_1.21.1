package net.thefluffycart.litavis.entity.variant;

import java.util.Arrays;
import java.util.Comparator;

public enum BurrowVariant {
    BURROW_BASE(0),
    BLACK(1),
    BLUE(2),
    BROWN(3),
    CYAN(4),
    GRAY(5),
    GREEN(6),
    LIGHT_BLUE(7),
    LIGHT_GRAY(8),
    LIME(9),
    MAGENTA(10),
    ORANGE(11),
    PINK(12),
    PURPLE(13),
    RED(14),
    WHITE(15),
    YELLOW(16),
    LAYERED(17);

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
