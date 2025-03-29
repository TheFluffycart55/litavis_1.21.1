package net.thefluffycart.litavis.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.thefluffycart.litavis.block.ModBlocks;
import net.thefluffycart.litavis.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(ItemTags.LOGS)
                .add(ModBlocks.EUCALYPTUS_LOG.asItem(), ModBlocks.EUCALYPTUS_WOOD.asItem(),
                        ModBlocks.STRIPPED_EUCALYPTUS_LOG.asItem(), ModBlocks.STRIPPED_EUCALYPTUS_WOOD.asItem());

        getOrCreateTagBuilder(ItemTags.DECORATED_POT_SHERDS)
                .add(ModItems.TERRA_POTTERY_SHERD)
                .add(ModItems.TOMB_POTTERY_SHERD);

        getOrCreateTagBuilder(ItemTags.PLANKS)
                .add(ModBlocks.EUCALYPTUS_PLANKS.asItem(), ModBlocks.CRISPEN_PLANKS.asItem());

        getOrCreateTagBuilder(ItemTags.DOORS)
                .add(ModBlocks.EUCALYPTUS_DOOR.asItem(), ModBlocks.CRISPEN_DOOR.asItem());

        getOrCreateTagBuilder(ItemTags.WOODEN_TRAPDOORS)
                .add(ModBlocks.EUCALYPTUS_TRAPDOOR.asItem(), ModBlocks.CRISPEN_TRAPDOOR.asItem());

//        getOrCreateTagBuilder(ItemTags.SIGNS)
//                .add(ModItems.EUCALYPTUS_SIGN, ModItems.CRISPEN_SIGN);
//
//        getOrCreateTagBuilder(ItemTags.HANGING_SIGNS)
//                .add(ModItems.HANGING_EUCALYPTUS_SIGN, ModItems.HANGING_CRISPEN_SIGN);
//
//        getOrCreateTagBuilder(ItemTags.BOATS)
//                .add(ModItems.EUCALYPTUS_BOAT, ModItems.CRISPEN_BOAT);
//
//        getOrCreateTagBuilder(ItemTags.CHEST_BOATS)
//                .add(ModItems.EUCALYPTUS_CHEST_BOAT,ModItems.CRISPEN_CHEST_BOAT);

        getOrCreateTagBuilder(ItemTags.DURABILITY_ENCHANTABLE)
                .add(ModItems.TERRAFORMER)
                .add(ModItems.TERRAFORMER_BLACK)
                .add(ModItems.TERRAFORMER_BLUE)
                .add(ModItems.TERRAFORMER_BROWN)
                .add(ModItems.TERRAFORMER_CYAN)
                .add(ModItems.TERRAFORMER_GRAY)
                .add(ModItems.TERRAFORMER_GREEN)
                .add(ModItems.TERRAFORMER_LIGHT_BLUE)
                .add(ModItems.TERRAFORMER_LIGHT_GRAY)
                .add(ModItems.TERRAFORMER_LIME)
                .add(ModItems.TERRAFORMER_MAGENTA)
                .add(ModItems.TERRAFORMER_ORANGE)
                .add(ModItems.TERRAFORMER_PINK)
                .add(ModItems.TERRAFORMER_PURPLE)
                .add(ModItems.TERRAFORMER_RED)
                .add(ModItems.TERRAFORMER_WHITE)
                .add(ModItems.TERRAFORMER_YELLOW);
        getOrCreateTagBuilder(ItemTags.VANISHING_ENCHANTABLE)
                .add(ModItems.TERRAFORMER)
                .add(ModItems.TERRAFORMER_BLACK)
                .add(ModItems.TERRAFORMER_BLUE)
                .add(ModItems.TERRAFORMER_BROWN)
                .add(ModItems.TERRAFORMER_CYAN)
                .add(ModItems.TERRAFORMER_GRAY)
                .add(ModItems.TERRAFORMER_GREEN)
                .add(ModItems.TERRAFORMER_LIGHT_BLUE)
                .add(ModItems.TERRAFORMER_LIGHT_GRAY)
                .add(ModItems.TERRAFORMER_LIME)
                .add(ModItems.TERRAFORMER_MAGENTA)
                .add(ModItems.TERRAFORMER_ORANGE)
                .add(ModItems.TERRAFORMER_PINK)
                .add(ModItems.TERRAFORMER_PURPLE)
                .add(ModItems.TERRAFORMER_RED)
                .add(ModItems.TERRAFORMER_WHITE)
                .add(ModItems.TERRAFORMER_YELLOW);
    }
}