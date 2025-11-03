package net.thefluffycart.litavis.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

public class PlushieBlockEntity extends BlockEntity implements GeoBlockEntity {
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    private boolean shouldPlayAnimation = false;

    public PlushieBlockEntity(BlockPos pos, BlockState state) {
        super(LitavisBlockEntityType.PLUSHIE_BLOCK_ENTITY, pos, state);
    }

    public void triggerAnimation() {
        shouldPlayAnimation = true;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(
                this,
                "controller",
                0,
                state -> {
                    if (shouldPlayAnimation) {
                        System.out.println("Playing animtion");
                        state.setAnimation(RawAnimation.begin().then("animation.plush.squeak", Animation.LoopType.PLAY_ONCE));
                        return PlayState.CONTINUE;
                    }
                    shouldPlayAnimation = false;
                    return PlayState.STOP;
                }
        ));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}