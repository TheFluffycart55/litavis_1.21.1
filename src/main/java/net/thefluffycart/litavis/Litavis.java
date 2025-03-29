package net.thefluffycart.litavis;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.ComposterBlock;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.thefluffycart.litavis.block.ModBlocks;
import net.thefluffycart.litavis.block.ModCompatBlocks;
import net.thefluffycart.litavis.entity.ModBoats;
import net.thefluffycart.litavis.entity.ModEntities;
import net.thefluffycart.litavis.entity.custom.BurrowEntity;
import net.thefluffycart.litavis.entity.custom.CopperGolemEntity;
import net.thefluffycart.litavis.item.ModItemGroups;
import net.thefluffycart.litavis.item.ModItems;
import net.thefluffycart.litavis.item.custom.TerraformerItem;
import net.thefluffycart.litavis.sound.ModSounds;
import net.thefluffycart.litavis.world.gen.ModWorldGeneration;
import net.thefluffycart.litavis.world.tree.ModTrunkPlacerTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Litavis implements ModInitializer {

	public static final String MOD_ID = "litavis";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		FuelRegistry.INSTANCE.add(ModBlocks.KINDLING, 1000);

		ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.TIRIM_BERRIES, 0.3f);
		ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModBlocks.KINDLING.asItem(), 0.85f);
		ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModBlocks.CRISPEN_LEAVES.asItem(), 0.5f);
		ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModBlocks.EUCALYPTUS_LEAVES.asItem(), 0.3f);
		ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModBlocks.EUCALYPTUS_SAPLING.asItem(), 0.3f);

		UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
			ItemStack offhandStack = player.getOffHandStack();
			ItemStack mainHandStack = player.getMainHandStack();
			BlockPos blockPos = hitResult.getBlockPos();
			BlockState blockState = world.getBlockState(blockPos);

			if (mainHandStack.getItem() instanceof BlockItem blockItem) {
				if (offhandStack.getItem() instanceof TerraformerItem) {
							ItemPlacementContext context = new ItemPlacementContext(
									new ItemPlacementContext(player, hand, mainHandStack, hitResult)
							);

							if (blockItem.place(context).isAccepted()) {
								offhandStack.damage(1, player, EquipmentSlot.OFFHAND);
								return ActionResult.SUCCESS;
						}
				}
			}

			return ActionResult.PASS;
		});

		PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, blockEntity) -> {
			ItemStack offhandStack = player.getOffHandStack();

			if (offhandStack.getItem() instanceof TerraformerItem) {
				offhandStack.damage(1, player, EquipmentSlot.OFFHAND);
			}
		});
		registerStrippables();

		ModSounds.registerSounds();
		ModEntities.registerModEntities();
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModBlocks.registerModBlocks();
		ModTrunkPlacerTypes.register();
		ModCompatBlocks.register();
		//ModBoats.registerBoats();
		ModWorldGeneration.generateModWorldGeneration();

		FabricDefaultAttributeRegistry.register(ModEntities.BURROW, BurrowEntity.createburrowAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.COPPER_GOLEM, CopperGolemEntity.createCopperGolemAttributes());
	}

	public static final GameRules.Key<GameRules.BooleanRule> EARTH_CHARGE_GRIEFING =
			GameRuleRegistry.register("earthChargeGriefing", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(true));

	public static final GameRules.Key<GameRules.BooleanRule> EARTH_CHARGE_RESTRICTED =
			GameRuleRegistry.register("earthChargeRestricted", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(false));

	public static final GameRules.Key<GameRules.IntRule> EARTH_CHARGE_COOLDOWN =
			GameRuleRegistry.register("earthChargeCooldown", GameRules.Category.MISC, GameRuleFactory.createIntRule(300));

	public static final GameRules.Key<GameRules.IntRule> CAPSIZER_COOLDOWN =
			GameRuleRegistry.register("capsizerCooldown", GameRules.Category.MISC, GameRuleFactory.createIntRule(200));

	private static void registerStrippables(){
		StrippableBlockRegistry.register(ModBlocks.EUCALYPTUS_LOG, ModBlocks.STRIPPED_EUCALYPTUS_LOG);
		StrippableBlockRegistry.register(ModBlocks.EUCALYPTUS_WOOD, ModBlocks.STRIPPED_EUCALYPTUS_WOOD);
		StrippableBlockRegistry.register(ModBlocks.CRISPEN_LOG, ModBlocks.STRIPPED_CRISPEN_LOG);
		StrippableBlockRegistry.register(ModBlocks.CRISPEN_WOOD, ModBlocks.STRIPPED_CRISPEN_WOOD);
	}
}


