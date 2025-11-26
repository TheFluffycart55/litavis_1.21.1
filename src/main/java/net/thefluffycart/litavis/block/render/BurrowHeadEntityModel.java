package net.thefluffycart.litavis.block.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;

@Environment(EnvType.CLIENT)
public abstract class BurrowHeadEntityModel extends Model {
    public BurrowHeadEntityModel() {
        super(RenderLayer::getEntityTranslucent);
    }

    public abstract void setHeadRotation(float animationProgress, float yaw, float pitch);
}


