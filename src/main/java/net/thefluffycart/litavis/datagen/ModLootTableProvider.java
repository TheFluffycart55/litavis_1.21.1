package net.thefluffycart.litavis.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.thefluffycart.litavis.block.ModBlocks;
import net.thefluffycart.litavis.block.custom.TirimBerryBushBlock;
import net.thefluffycart.litavis.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {

    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);

        addDrop(ModBlocks.TRIPSLATE);
        addDrop(ModBlocks.CALIBRATED_TRIPSLATE);
        addDrop(ModBlocks.TRIPSLATE_BRICKS);
        addDrop(ModBlocks.POLISHED_TRIPSLATE);

        addDrop(ModBlocks.GRANITE_BRICKS);
        addDrop(ModBlocks.GRANITE_PILLAR);
        addDrop(ModBlocks.GRANITE_BRICK_STAIRS);
        addDrop(ModBlocks.GRANITE_BRICK_SLAB, slabDrops(ModBlocks.GRANITE_BRICK_SLAB));
        addDrop(ModBlocks.GRANITE_BRICK_WALL);
        addDrop(ModBlocks.CHISELED_GRANITE);

        addDrop(ModBlocks.CRACKED_GRANITE_BRICKS);
        addDrop(ModBlocks.CRACKED_GRANITE_PILLAR);
        addDrop(ModBlocks.CRACKED_GRANITE_BRICK_STAIRS);
        addDrop(ModBlocks.CRACKED_GRANITE_BRICK_SLAB, slabDrops(ModBlocks.CRACKED_GRANITE_BRICK_SLAB));
        addDrop(ModBlocks.CRACKED_GRANITE_BRICK_WALL);

        addDrop(ModBlocks.MOSSY_GRANITE_BRICKS);
        addDrop(ModBlocks.MOSSY_GRANITE_PILLAR);
        addDrop(ModBlocks.MOSSY_GRANITE_BRICK_STAIRS);
        addDrop(ModBlocks.MOSSY_GRANITE_BRICK_SLAB, slabDrops(ModBlocks.MOSSY_GRANITE_BRICK_SLAB));
        addDrop(ModBlocks.MOSSY_GRANITE_BRICK_WALL);

        addDrop(ModBlocks.CRACKED_MOSSY_GRANITE_BRICKS);
        addDrop(ModBlocks.CRACKED_MOSSY_GRANITE_PILLAR);
        addDrop(ModBlocks.CRACKED_MOSSY_GRANITE_BRICK_STAIRS);
        addDrop(ModBlocks.CRACKED_MOSSY_GRANITE_BRICK_SLAB, slabDrops(ModBlocks.CRACKED_MOSSY_GRANITE_BRICK_SLAB));
        addDrop(ModBlocks.CRACKED_MOSSY_GRANITE_BRICK_WALL);

        addDrop(ModBlocks.TRIPSLATE_BRICKS);
        addDrop(ModBlocks.TRIPSLATE_BRICK_STAIRS);
        addDrop(ModBlocks.TRIPSLATE_BRICK_SLAB, slabDrops(ModBlocks.TRIPSLATE_BRICK_SLAB));
        addDrop(ModBlocks.TRIPSLATE_BRICK_WALL);

        addDrop(ModBlocks.CRACKED_TRIPSLATE_BRICKS);
        addDrop(ModBlocks.CRACKED_TRIPSLATE_BRICK_STAIRS);
        addDrop(ModBlocks.CRACKED_TRIPSLATE_BRICK_SLAB, slabDrops(ModBlocks.CRACKED_TRIPSLATE_BRICK_SLAB));
        addDrop(ModBlocks.CRACKED_TRIPSLATE_BRICK_WALL);

        addDrop(ModBlocks.MOSSY_TRIPSLATE_BRICKS);
        addDrop(ModBlocks.MOSSY_TRIPSLATE_BRICK_STAIRS);
        addDrop(ModBlocks.MOSSY_TRIPSLATE_BRICK_SLAB, slabDrops(ModBlocks.MOSSY_TRIPSLATE_BRICK_SLAB));
        addDrop(ModBlocks.MOSSY_TRIPSLATE_BRICK_WALL);

        addDrop(ModBlocks.EUCALYPTUS_LOG);
        addDrop(ModBlocks.EUCALYPTUS_WOOD);
        addDrop(ModBlocks.STRIPPED_EUCALYPTUS_LOG);
        addDrop(ModBlocks.STRIPPED_EUCALYPTUS_WOOD);
        addDrop(ModBlocks.EUCALYPTUS_PLANKS);
        addDrop(ModBlocks.EUCALYPTUS_DOOR, doorDrops(ModBlocks.EUCALYPTUS_DOOR));
        addDrop(ModBlocks.EUCALYPTUS_SAPLING);

        addDrop(ModBlocks.EUCALYPTUS_LEAVES, leavesDrops(ModBlocks.EUCALYPTUS_LEAVES, ModBlocks.EUCALYPTUS_SAPLING, 0.0625f));

        this.addDrop(ModBlocks.TIRIM_BERRY_BUSH,
                block -> this.applyExplosionDecay(
                        block, LootTable.builder().pool(LootPool.builder().conditionally(
                                                BlockStatePropertyLootCondition.builder(ModBlocks.TIRIM_BERRY_BUSH).properties(StatePredicate.Builder.create().exactMatch(TirimBerryBushBlock.AGE, 3))
                                        )
                                        .with(ItemEntry.builder(ModItems.TIRIM_BERRIES))
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 3.0F)))
                                        .apply(ApplyBonusLootFunction.uniformBonusCount(impl.getOrThrow(Enchantments.FORTUNE)))
                        ).pool(LootPool.builder().conditionally(
                                        BlockStatePropertyLootCondition.builder(ModBlocks.TIRIM_BERRY_BUSH).properties(StatePredicate.Builder.create().exactMatch(TirimBerryBushBlock.AGE, 2))
                                ).with(ItemEntry.builder(ModItems.TIRIM_BERRIES))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F)))
                                .apply(ApplyBonusLootFunction.uniformBonusCount(impl.getOrThrow(Enchantments.FORTUNE))))));
    }
    public LootTable.Builder multipleOreDrops(Block drop, Item item, float minDrops, float maxDrops) {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        return this.dropsWithSilkTouch(drop, this.applyExplosionDecay(drop, ((LeafEntry.Builder<?>)
                ItemEntry.builder(item).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(minDrops, maxDrops))))
                .apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE)))));
    }
}
