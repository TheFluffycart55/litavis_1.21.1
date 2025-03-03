package net.thefluffycart.litavis;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.thefluffycart.litavis.block.ModBlocks;
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
		//EVENT HANDLER FOR THE TERRAFORMER DURABILITY LOSS
//		DefaultItemComponentEvents.MODIFY.register(ctx -> ctx.modify(
//				Predicate.isEqual(ModItems.TERRAFORMER),
//				(builder, item) -> builder.add(DataComponentTypes.ITEM_NAME, Text.translatable(item.getTranslationKey()).withColor(0xff0000))
//		));

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
		

		//REGISTER EVERYTHING
		registerStrippables();

		ModSounds.registerSounds();
		ModEntities.registerModEntities();
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModBlocks.registerModBlocks();
		ModTrunkPlacerTypes.register();
		ModBoats.registerBoats();
		ModWorldGeneration.generateModWorldGeneration();

		FabricDefaultAttributeRegistry.register(ModEntities.BURROW, BurrowEntity.createburrowAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.COPPER_GOLEM, CopperGolemEntity.createCopperGolemAttributes());
	}


	public static final GameRules.Key<GameRules.BooleanRule> EARTH_CHARGE_GRIEFING =
			GameRuleRegistry.register("earthChargeGriefing", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(true));

	private static void registerStrippables(){
		StrippableBlockRegistry.register(ModBlocks.EUCALYPTUS_LOG, ModBlocks.STRIPPED_EUCALYPTUS_LOG);
		StrippableBlockRegistry.register(ModBlocks.EUCALYPTUS_WOOD, ModBlocks.STRIPPED_EUCALYPTUS_WOOD);
	}
}


