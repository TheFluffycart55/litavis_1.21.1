package net.thefluffycart.litavis.block.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;
import net.thefluffycart.litavis.entity.client.ModEntityModelLayers;

@Environment(EnvType.CLIENT)
public class BurrowHeadModel extends BurrowHeadEntityModel {
    private final ModelPart head;
    protected final ModelPart grass2;
    protected final ModelPart browL;
    protected final ModelPart browR;

    public BurrowHeadModel(ModelPart root) {
        this.head = root.getChild("head");
        this.grass2 = this.head.getChild("grass2");
        this.browL = this.head.getChild("browL");
        this.browR = this.head.getChild("browR");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -3.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 8.0F, 0.0F));

        ModelPartData grass2 = head.addChild("grass2", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -3.0F, 0.0F));

        ModelPartData grass2_r1 = grass2.addChild("grass2_r1", ModelPartBuilder.create().uv(0, 22).cuboid(-3.0F, -6.0F, 0.0F, 6.0F, 6.0F, 0.0F, new Dilation(0.0F))
                .uv(0, 16).cuboid(0.0F, -6.0F, -3.0F, 0.0F, 6.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

        ModelPartData browL = head.addChild("browL", ModelPartBuilder.create().uv(12, 16).cuboid(2.0F, -0.5F, -4.1F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 1.0F, 0.0F));

        ModelPartData browR = head.addChild("browR", ModelPartBuilder.create().uv(12, 16).cuboid(-3.0F, -0.5F, -4.1F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 1.0F, 0.0F));
        return TexturedModelData.of(modelData, 32, 32);
    }

    public static ModelData getModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F), ModelTransform.NONE);
        return modelData;
    }

    public static TexturedModelData getHeadTexturedModelData() {
        ModelData modelData = getModelData();
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.getChild("head").addChild("hat", ModelPartBuilder.create().uv(32, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.25F)), ModelTransform.NONE);
        return TexturedModelData.of(modelData, 64, 64);
    }

    public static TexturedModelData getSkullTexturedModelData() {
        ModelData modelData = getModelData();
        return TexturedModelData.of(modelData, 64, 32);
    }

    public void setHeadRotation(float animationProgress, float yaw, float pitch) {
        this.head.yaw = yaw * ((float) Math.PI / 180F);
        this.head.pitch = pitch * ((float) Math.PI / 180F);
    }

    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
        this.head.render(matrices, vertices, light, overlay, color);
    }
}