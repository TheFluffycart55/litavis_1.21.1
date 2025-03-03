package net.thefluffycart.litavis.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.block.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Slice;

import static net.minecraft.block.Blocks.TORCHFLOWER;

@Mixin(Blocks.class)
public class TorchflowerBlockMixin {

    //Huge thank you to NikitaCartes and JuiceyBeans for the paths and original mixins
    @ModifyExpressionValue(method = "<clinit>",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/block/AbstractBlock$Settings;create()Lnet/minecraft/block/AbstractBlock$Settings;",
                    ordinal = 0),
            slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=torchflower")))
    private static Block.Settings alterTorchflowerSettings(AbstractBlock.Settings settings) {
        boolean canGlow = true;
        if (canGlow) {
            return settings.luminance(state -> 5);
        } return settings;
    }

    //Torchflower crops have a light level of 5
    @ModifyArg(
            method = "<clinit>",
            slice = @Slice(
                    from = @At(
                            value = "CONSTANT",
                            args = "stringValue=torchflower_crop"
                    )
            ),
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/block/TorchflowerBlock;<init>(Lnet/minecraft/block/AbstractBlock$Settings;)V",
                    ordinal = 0
            )
    )
    private static Block.Settings alterTorchflowerCropSettings(AbstractBlock.Settings settings) {
        boolean canGlow = true;
        if (canGlow) {
            return settings.luminance(state -> switch (state.get(TorchflowerBlock.AGE)) {
                case 0 -> 1;
                case 1 -> 3;
                default -> 5;
            });
        } return settings;
    }
}