package net.thefluffycart.litavis.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.thefluffycart.litavis.Litavis;
import net.thefluffycart.litavis.block.ModBlocks;
import net.thefluffycart.litavis.entity.ModBoats;
import net.thefluffycart.litavis.entity.ModEntities;
import net.thefluffycart.litavis.item.custom.CapsizerItem;
import net.thefluffycart.litavis.item.custom.EarthChargeItem;
import net.thefluffycart.litavis.item.custom.TerraformerItem;
import net.thefluffycart.litavis.sound.ModSounds;

public class ModItems {
    //ENTOMBED RUINS ITEMS
    public static final Item BURIED_DISC_FRAGMENT = registerItem("buried_disc_fragment", new Item(new Item.Settings().maxCount(1)));
    public static final Item RELIC_DISC_FRAGMENT = registerItem("relic_disc_fragment", new Item(new Item.Settings().maxCount(1)));
    public static final Item ECHOES_MUSIC_DISC = registerItem("echoes_music_disc",
            new Item(new Item.Settings().jukeboxPlayable(ModSounds.ECHOES_KEY).maxCount(1).rarity(Rarity.RARE)));
    public static final Item BURROW_ROD = registerItem("burrow_rod", new Item(new Item.Settings()));
    public static final Item ENTOMBED_KEY = registerItem("entombed_key", new Item(new Item.Settings()));
    public static final Item ENTOMBED_ARMOR_TRIM_SMITHING_TEMPLATE = registerItem("entombed_armor_trim_smithing_template", SmithingTemplateItem.of(Identifier.of("litavis", "entombed_armor_trim")));
    public static final Item DRIP_ARMOR_TRIM_SMITHING_TEMPLATE = registerItem("drip_armor_trim_smithing_template", SmithingTemplateItem.of(Identifier.of("litavis", "drip_armor_trim")));
    public static final Item BURROW_SPAWN_EGG = registerItem("burrow_spawn_egg",
            new SpawnEggItem(ModEntities.BURROW, 0xffc8a1, 0xa16133, new Item.Settings()));
    public static final Item COPPER_GOLEM_STATUE = registerItem("copper_golem_statue",
            new SpawnEggItem(ModEntities.COPPER_GOLEM, 0xFFFFFF, 0xFFFFFF, new Item.Settings()));
    public static final Item TUNING_FORK = registerItem("tuning_fork", new Item(new Item.Settings().maxCount(1)));
    public static final Item TERRA_POTTERY_SHERD = registerItem("terra_pottery_sherd", new Item(new Item.Settings()));
    public static final Item TOMB_POTTERY_SHERD = registerItem("tomb_pottery_sherd", new Item(new Item.Settings()));

    public static final Item EARTH_CHARGE = registerItem("earth_charge",
            new EarthChargeItem(new Item.Settings()));

    public static final Item TIRIM_BERRIES = registerItem("tirim_berries",
            new AliasedBlockItem(ModBlocks.TIRIM_BERRY_BUSH, new Item.Settings().food(ModFoodComponents.TIRIM_BERRY)));

    public static final Item SCULPTED_CORE = Items.register(new BlockItem(ModBlocks.SCULPTED_CORE, new Item.Settings().rarity(Rarity.EPIC)));
    public static final Item THEFLUFFYCART_PLUSHIE = Items.register(new BlockItem(ModBlocks.THEFLUFFYCART_PLUSHIE, new Item.Settings().rarity(Rarity.RARE)));
    public static final Item BURROW_PLUSHIE = Items.register(new BlockItem(ModBlocks.BURROW_PLUSHIE, new Item.Settings().rarity(Rarity.RARE)));

//    public static final Item EUCALYPTUS_SIGN = registerItem("eucalyptus_sign",
//            new SignItem(new Item.Settings().maxCount(16), ModBlocks.STANDING_EUCALYPTUS_SIGN, ModBlocks.WALL_EUCALYPTUS_SIGN));
//    public static final Item HANGING_EUCALYPTUS_SIGN = registerItem("eucalyptus_hanging_sign",
//            new HangingSignItem(ModBlocks.HANGING_EUCALYPTUS_SIGN, ModBlocks.WALL_HANGING_EUCALYPTUS_SIGN, new Item.Settings().maxCount(16)));
//    public static final Item CRISPEN_SIGN = registerItem("crispen_sign",
//            new SignItem(new Item.Settings().maxCount(16), ModBlocks.STANDING_CRISPEN_SIGN, ModBlocks.WALL_CRISPEN_SIGN));
//    public static final Item HANGING_CRISPEN_SIGN = registerItem("crispen_hanging_sign",
//            new HangingSignItem(ModBlocks.HANGING_CRISPEN_SIGN, ModBlocks.WALL_HANGING_CRISPEN_SIGN, new Item.Settings().maxCount(16)));

    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {
        entries.add(BURROW_ROD);
        entries.add(ENTOMBED_ARMOR_TRIM_SMITHING_TEMPLATE);
        entries.add(EARTH_CHARGE);
    }

    public static final Item TERRAFORMER = registerItem("terraformer", new TerraformerItem(ModToolMaterials.TERRACOTTA, new Item.Settings().maxCount(1).attributeModifiers(TerraformerItem.createAttributeModifiers(3)).maxDamage(200).rarity(Rarity.EPIC)));
    public static final Item TERRAFORMER_BLACK = registerItem("black_terraformer", new TerraformerItem(ModToolMaterials.TERRACOTTA, new Item.Settings().maxCount(1).attributeModifiers(TerraformerItem.createAttributeModifiers(3)).maxDamage(200).rarity(Rarity.EPIC)));
    public static final Item TERRAFORMER_BLUE = registerItem("blue_terraformer", new TerraformerItem(ModToolMaterials.TERRACOTTA, new Item.Settings().maxCount(1).attributeModifiers(TerraformerItem.createAttributeModifiers(3)).maxDamage(200).rarity(Rarity.EPIC)));
    public static final Item TERRAFORMER_BROWN = registerItem("brown_terraformer", new TerraformerItem(ModToolMaterials.TERRACOTTA, new Item.Settings().maxCount(1).attributeModifiers(TerraformerItem.createAttributeModifiers(3)).maxDamage(200).rarity(Rarity.EPIC)));
    public static final Item TERRAFORMER_CYAN = registerItem("cyan_terraformer", new TerraformerItem(ModToolMaterials.TERRACOTTA, new Item.Settings().maxCount(1).attributeModifiers(TerraformerItem.createAttributeModifiers(3)).maxDamage(200).rarity(Rarity.EPIC)));
    public static final Item TERRAFORMER_GRAY = registerItem("gray_terraformer", new TerraformerItem(ModToolMaterials.TERRACOTTA, new Item.Settings().maxCount(1).attributeModifiers(TerraformerItem.createAttributeModifiers(3)).maxDamage(200).rarity(Rarity.EPIC)));
    public static final Item TERRAFORMER_GREEN = registerItem("green_terraformer", new TerraformerItem(ModToolMaterials.TERRACOTTA, new Item.Settings().maxCount(1).attributeModifiers(TerraformerItem.createAttributeModifiers(3)).maxDamage(200).rarity(Rarity.EPIC)));
    public static final Item TERRAFORMER_LIGHT_BLUE = registerItem("light_blue_terraformer", new TerraformerItem(ModToolMaterials.TERRACOTTA, new Item.Settings().maxCount(1).attributeModifiers(TerraformerItem.createAttributeModifiers(3)).maxDamage(200).rarity(Rarity.EPIC)));
    public static final Item TERRAFORMER_LIGHT_GRAY = registerItem("light_gray_terraformer", new TerraformerItem(ModToolMaterials.TERRACOTTA, new Item.Settings().maxCount(1).attributeModifiers(TerraformerItem.createAttributeModifiers(3)).maxDamage(200).rarity(Rarity.EPIC)));
    public static final Item TERRAFORMER_LIME = registerItem("lime_terraformer", new TerraformerItem(ModToolMaterials.TERRACOTTA, new Item.Settings().maxCount(1).attributeModifiers(TerraformerItem.createAttributeModifiers(3)).maxDamage(200).rarity(Rarity.EPIC)));
    public static final Item TERRAFORMER_MAGENTA = registerItem("magenta_terraformer", new TerraformerItem(ModToolMaterials.TERRACOTTA, new Item.Settings().maxCount(1).attributeModifiers(TerraformerItem.createAttributeModifiers(3)).maxDamage(200).rarity(Rarity.EPIC)));
    public static final Item TERRAFORMER_ORANGE = registerItem("orange_terraformer", new TerraformerItem(ModToolMaterials.TERRACOTTA, new Item.Settings().maxCount(1).attributeModifiers(TerraformerItem.createAttributeModifiers(3)).maxDamage(200).rarity(Rarity.EPIC)));
    public static final Item TERRAFORMER_PINK = registerItem("pink_terraformer", new TerraformerItem(ModToolMaterials.TERRACOTTA, new Item.Settings().maxCount(1).attributeModifiers(TerraformerItem.createAttributeModifiers(3)).maxDamage(200).rarity(Rarity.EPIC)));
    public static final Item TERRAFORMER_PURPLE = registerItem("purple_terraformer", new TerraformerItem(ModToolMaterials.TERRACOTTA, new Item.Settings().maxCount(1).attributeModifiers(TerraformerItem.createAttributeModifiers(3)).maxDamage(200).rarity(Rarity.EPIC)));
    public static final Item TERRAFORMER_RED = registerItem("red_terraformer", new TerraformerItem(ModToolMaterials.TERRACOTTA, new Item.Settings().maxCount(1).attributeModifiers(TerraformerItem.createAttributeModifiers(3)).maxDamage(200).rarity(Rarity.EPIC)));
    public static final Item TERRAFORMER_WHITE = registerItem("white_terraformer", new TerraformerItem(ModToolMaterials.TERRACOTTA, new Item.Settings().maxCount(1).attributeModifiers(TerraformerItem.createAttributeModifiers(3)).maxDamage(200).rarity(Rarity.EPIC)));
    public static final Item TERRAFORMER_YELLOW = registerItem("yellow_terraformer", new TerraformerItem(ModToolMaterials.TERRACOTTA, new Item.Settings().maxCount(1).attributeModifiers(TerraformerItem.createAttributeModifiers(3)).maxDamage(200).rarity(Rarity.EPIC)));
    public static final Item CAPSIZER = registerItem("capsizer", new CapsizerItem(ModToolMaterials.TERRACOTTA, new Item.Settings().maxCount(1).rarity(Rarity.EPIC)));

//    public static final Item EUCALYPTUS_BOAT = TerraformBoatItemHelper.registerBoatItem(ModBoats.EUCALYPTUS_BOAT_ID, ModBoats.EUCALYPTUS_BOAT_KEY, false);
//    public static final Item EUCALYPTUS_CHEST_BOAT = TerraformBoatItemHelper.registerBoatItem(ModBoats.EUCALYPTUS_CHEST_BOAT_ID, ModBoats.EUCALYPTUS_BOAT_KEY, true);
//
//    public static final Item CRISPEN_BOAT = TerraformBoatItemHelper.registerBoatItem(ModBoats.CRISPEN_BOAT_ID, ModBoats.CRISPEN_BOAT_KEY, false);
//    public static final Item CRISPEN_CHEST_BOAT = TerraformBoatItemHelper.registerBoatItem(ModBoats.CRISPEN_CHEST_BOAT_ID, ModBoats.CRISPEN_BOAT_KEY, true);
    //SECRETS???
    public static final Item SEERALITE_PIT = registerItem("seeralite_pit", new Item(new Item.Settings()));
    public static final Item SEERALITE_INGOT = registerItem("seeralite_ingot", new Item(new Item.Settings()));


    private static void addItemsToBuildingBlocksGroup(FabricItemGroupEntries entries)
    {
        entries.add(ModBlocks.GRANITE_BRICKS);
        entries.add(ModBlocks.GRANITE_PILLAR);
        entries.add(ModBlocks.GRANITE_BRICK_STAIRS);
        entries.add(ModBlocks.GRANITE_BRICK_SLAB);
        entries.add(ModBlocks.GRANITE_BRICK_WALL);

        entries.add(ModBlocks.CRACKED_GRANITE_BRICKS);
        entries.add(ModBlocks.CRACKED_GRANITE_PILLAR);
        entries.add(ModBlocks.CRACKED_GRANITE_BRICK_STAIRS);
        entries.add(ModBlocks.CRACKED_GRANITE_BRICK_SLAB);
        entries.add(ModBlocks.CRACKED_GRANITE_BRICK_WALL);

        entries.add(ModBlocks.MOSSY_GRANITE_BRICKS);
        entries.add(ModBlocks.MOSSY_GRANITE_PILLAR);
        entries.add(ModBlocks.MOSSY_GRANITE_BRICK_STAIRS);
        entries.add(ModBlocks.MOSSY_GRANITE_BRICK_SLAB);
        entries.add(ModBlocks.MOSSY_GRANITE_BRICK_WALL);

        entries.add(ModBlocks.CRACKED_MOSSY_GRANITE_BRICKS);
        entries.add(ModBlocks.CRACKED_MOSSY_GRANITE_PILLAR);
        entries.add(ModBlocks.CRACKED_MOSSY_GRANITE_BRICK_STAIRS);
        entries.add(ModBlocks.CRACKED_MOSSY_GRANITE_BRICK_SLAB);
        entries.add(ModBlocks.CRACKED_MOSSY_GRANITE_BRICK_WALL);

        entries.add(ModBlocks.EUCALYPTUS_LOG);
        entries.add(ModBlocks.EUCALYPTUS_WOOD);
        entries.add(ModBlocks.EUCALYPTUS_STAIRS);
        entries.add(ModBlocks.EUCALYPTUS_SLAB);
        entries.add(ModBlocks.EUCALYPTUS_FENCE);
        entries.add(ModBlocks.EUCALYPTUS_FENCE_GATE);
        entries.add(ModBlocks.EUCALYPTUS_BUTTON);
        entries.add(ModBlocks.EUCALYPTUS_PRESSURE_PLATE);
        entries.add(ModBlocks.EUCALYPTUS_DOOR);
        entries.add(ModBlocks.EUCALYPTUS_TRAPDOOR);
        entries.add(ModBlocks.STRIPPED_EUCALYPTUS_LOG);
        entries.add(ModBlocks.STRIPPED_EUCALYPTUS_WOOD);
        entries.add(ModBlocks.EUCALYPTUS_PLANKS);
        entries.add(ModBlocks.EUCALYPTUS_SAPLING);
        entries.add(ModBlocks.EUCALYPTUS_LEAVES);

        entries.add(ModBlocks.TRIPSLATE);
        entries.add(ModBlocks.POLISHED_TRIPSLATE);
        entries.add(ModBlocks.TRIPSLATE_BRICKS);
        entries.add(ModBlocks.TRIPSLATE_BRICK_STAIRS);
        entries.add(ModBlocks.TRIPSLATE_BRICK_SLAB);
        entries.add(ModBlocks.TRIPSLATE_BRICK_WALL);
        entries.add(ModBlocks.CRACKED_TRIPSLATE_BRICKS);
        entries.add(ModBlocks.CRACKED_TRIPSLATE_BRICK_STAIRS);
        entries.add(ModBlocks.CRACKED_TRIPSLATE_BRICK_SLAB);
        entries.add(ModBlocks.CRACKED_TRIPSLATE_BRICK_WALL);
        entries.add(ModBlocks.MOSSY_TRIPSLATE_BRICKS);
        entries.add(ModBlocks.MOSSY_TRIPSLATE_BRICK_STAIRS);
        entries.add(ModBlocks.MOSSY_TRIPSLATE_BRICK_SLAB);
        entries.add(ModBlocks.MOSSY_TRIPSLATE_BRICK_WALL);
        entries.add(ModBlocks.CHISELED_TRIPSLATE);
        entries.add(ModBlocks.CALIBRATED_TRIPSLATE);

    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Litavis.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Litavis.LOGGER.info("Registering Mod Items for " + Litavis.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientItemGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(ModItems::addItemsToBuildingBlocksGroup);
    }
}
