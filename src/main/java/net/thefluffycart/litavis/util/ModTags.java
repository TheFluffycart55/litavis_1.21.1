package net.thefluffycart.litavis.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.thefluffycart.litavis.Litavis;

public class ModTags {
    public static class Blocks {
        private static TagKey<Block> createTag(String name)
        {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(Litavis.MOD_ID, name));
        }
        //UNUSED TAG TO MAKE TRIPSLATE VARIANTS FALL TOGETHER. NEEDS WORK DUE TO THE WAY THE WALLS, STAIRS, AND SLABS ARE IMPLEMENTED
        public static final TagKey<Block> EARTH_CHARGE_THROWABLE =
                createTag("earth_charge_throwable");
    }

    public static class Items {
        private static TagKey<Item> createTag(String name)
        {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(Litavis.MOD_ID, name));
        }
        public static final TagKey<Item> EARTH_CHARGE_THROWABLE =
                createTag("earth_charge_throwable");

    }
}