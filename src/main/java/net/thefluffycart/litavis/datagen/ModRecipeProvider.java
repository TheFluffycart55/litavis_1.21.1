package net.thefluffycart.litavis.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.thefluffycart.litavis.Litavis;
import net.thefluffycart.litavis.block.ModBlocks;
import net.thefluffycart.litavis.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_TRIPSLATE, 4)
                .pattern("TT")
                .pattern("TT")
                .input('T', ModBlocks.TRIPSLATE)
                .criterion(hasItem(ModBlocks.TRIPSLATE), conditionsFromItem(ModBlocks.TRIPSLATE));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.TRIPSLATE_BRICKS, 4)
                .pattern("TT")
                .pattern("TT")
                .input('T', ModBlocks.POLISHED_TRIPSLATE)
                .criterion(hasItem(ModBlocks.POLISHED_TRIPSLATE), conditionsFromItem(ModBlocks.POLISHED_TRIPSLATE));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.TRIPSLATE, 4)
                .pattern("GM")
                .pattern("MG")
                .input('M', Blocks.MUD)
                .input('G', Blocks.GRAVEL)
                .criterion(hasItem(Blocks.GRAVEL), conditionsFromItem(Blocks.GRAVEL))
                .criterion(hasItem(Blocks.MUD), conditionsFromItem(Blocks.MUD));
    }
}
