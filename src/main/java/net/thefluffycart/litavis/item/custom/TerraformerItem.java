package net.thefluffycart.litavis.item.custom;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.thefluffycart.litavis.util.TerraformerItemRenderer;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Consumer;
import java.util.logging.Level;

public class TerraformerItem extends ToolItem implements GeoItem {

    public static final Identifier BASE_REACH_RANGE_MODIFIER_ID = Identifier.ofVanilla("base_player_block_interaction_range");
    public static final Identifier BASE_MINING_SPEED_MODIFIER_ID = Identifier.ofVanilla("base_player_mining_speed");
    private static final RawAnimation OPEN_ANIM = RawAnimation.begin().thenPlay("animation.terraformer.open");

    public final AnimatableInstanceCache terraformerCache = GeckoLibUtil.createInstanceCache(this);

    public TerraformerItem(ToolMaterial material, Settings settings) {
        super(material, settings);
        SingletonGeoAnimatable.registerSyncedAnimatable(this);

    }


    public static AttributeModifiersComponent createAttributeModifiers(int baseReachRange, float miningSpeedBoost) {
        return AttributeModifiersComponent.builder().add(EntityAttributes.PLAYER_BLOCK_INTERACTION_RANGE,
                new EntityAttributeModifier(BASE_REACH_RANGE_MODIFIER_ID, (float)baseReachRange, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.OFFHAND)
                .add(EntityAttributes.PLAYER_BLOCK_BREAK_SPEED, new EntityAttributeModifier(BASE_MINING_SPEED_MODIFIER_ID,
                        miningSpeedBoost, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.OFFHAND).build();
    }


    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private TerraformerItemRenderer renderer;

            @Override
            public @Nullable BuiltinModelItemRenderer getGeoItemRenderer() {
                if (this.renderer == null)
                    this.renderer = new TerraformerItemRenderer();

                return this.renderer;
            }
        });
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.NONE;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        return super.useOnBlock(context);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world instanceof ServerWorld serverLevel)
        {
            UseBlockCallback.EVENT.register((player, level, userHand, hitResult) -> {
                ItemStack offhandStack = player.getOffHandStack();
                ItemStack mainHandStack = player.getMainHandStack();
                BlockPos blockPos = hitResult.getBlockPos();

                if (mainHandStack.getItem() instanceof BlockItem blockItem) {
                    if (offhandStack.getItem() instanceof TerraformerItem) {

                        if (!player.canPlaceOn(blockPos, hitResult.getSide(), mainHandStack)) {
                            return ActionResult.PASS;
                        }

                        ItemPlacementContext context = new ItemPlacementContext(player, userHand, mainHandStack, hitResult);

                        ActionResult result = blockItem.place(context);

                        if (result.isAccepted()) {
                            triggerAnim(user, GeoItem.getOrAssignId(user.getStackInHand(hand), serverLevel), "open_controller", "open");
                            offhandStack.damage(1, player, EquipmentSlot.OFFHAND);
                        }

                        return result;
                    }
                }

                return ActionResult.PASS;
            });
        }

        return super.use(world, user, hand);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "open_controller", state -> PlayState.CONTINUE)
                .triggerableAnim("open", OPEN_ANIM));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return terraformerCache;
    }
}


