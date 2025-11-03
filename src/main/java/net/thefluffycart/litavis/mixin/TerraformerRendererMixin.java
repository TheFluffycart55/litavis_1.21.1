package net.thefluffycart.litavis.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import net.thefluffycart.litavis.item.custom.TerraformerItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HeldItemRenderer.class)
public abstract class TerraformerRendererMixin {

    @Inject(method = "applyEquipOffset", at = @At("HEAD"), cancellable = true)
    private void cancelUseBobbing(MatrixStack matrices, Arm arm, float equipProgress, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        PlayerEntity player = client.player;

        if (player != null && arm == Arm.LEFT) {
            ItemStack offHandStack = player.getOffHandStack();

            if (offHandStack.getItem() instanceof TerraformerItem && player.isUsingItem()) {
                System.out.println("Cancelling bobbing");
                ci.cancel();
            }
        }
    }
}