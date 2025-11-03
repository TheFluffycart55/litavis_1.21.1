package net.thefluffycart.litavis.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.thefluffycart.litavis.Litavis;
import net.thefluffycart.litavis.block.ModBlocks;

public class LitavisBlockEntityType {
    public static BlockEntityType<PlushieBlockEntity> PLUSHIE_BLOCK_ENTITY;

//    public static void registerAllBlockEntities() {
//        PLUSHIE_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE,
//                Identifier.of(Litavis.MOD_ID, "plushie_block_entity"),
//                FabricBlockEntityTypeBuilder.create(PlushieBlockEntity::new,
//                        ModBlocks.AVERY_PLUSH).build());
//    }
}