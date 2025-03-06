package net.thefluffycart.litavis.entity.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.thefluffycart.litavis.entity.client.animation.BurrowAnimations;
import net.thefluffycart.litavis.entity.custom.BurrowEntity;

public class BurrowModel extends SinglePartEntityModel<BurrowEntity>{
    private final ModelPart burrow;
    private final ModelPart head;

    public BurrowModel(ModelPart root) {
        this.burrow = root.getChild("burrow");
        this.head = this.burrow.getChild("head");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData burrow = modelPartData.addChild("burrow", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData head = burrow.addChild("head", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -12.0F, 0.0F));

        ModelPartData HeadBase = head.addChild("HeadBase", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F))
                .uv(0, 16).cuboid(-4.5F, -4.0F, -4.5F, 9.0F, 6.0F, 7.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData grass = HeadBase.addChild("grass", ModelPartBuilder.create().uv(32, -5).cuboid(0.0F, -6.0F, -3.0F, 0.0F, 6.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -4.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

        ModelPartData grass2_r1 = grass.addChild("grass2_r1", ModelPartBuilder.create().uv(32, -5).cuboid(0.0F, -8.0F, -3.0F, 0.0F, 6.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 2.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

        ModelPartData rod = head.addChild("rod", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -5.0F, 0.0F));

        ModelPartData rod1 = rod.addChild("rod1", ModelPartBuilder.create().uv(0, 31).cuboid(-1.0F, -8.0F, -1.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, -7.0F));

        ModelPartData rod2 = rod.addChild("rod2", ModelPartBuilder.create().uv(18, 31).cuboid(-1.0F, -8.0F, -1.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 7.0F));

        ModelPartData rod3 = rod.addChild("rod3", ModelPartBuilder.create().uv(9, 31).cuboid(-1.0F, -8.0F, -1.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-7.0F, 0.0F, 0.0F));

        ModelPartData rod4 = rod.addChild("rod4", ModelPartBuilder.create().uv(27, 31).cuboid(-1.0F, -8.0F, -1.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(7.0F, 0.0F, 0.0F));

        ModelPartData butt = burrow.addChild("butt", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData top_base = butt.addChild("top_base", ModelPartBuilder.create().uv(35, 19).cuboid(-3.0F, -1.0F, -3.0F, 6.0F, 2.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -5.0F, 0.0F));

        ModelPartData bottom_base = butt.addChild("bottom_base", ModelPartBuilder.create().uv(42, 32).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -1.0F, 0.0F));

        ModelPartData dust_trail = burrow.addChild("dust_trail", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -6.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }
    @Override
    public void setAngles(BurrowEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);

        this.animateMovement(BurrowAnimations.BURROW_WALK, limbSwing, limbSwingAmount, 6f, 6.5f);
        this.updateAnimation(entity.idleAnimationState, BurrowAnimations.BURROW_IDLE, ageInTicks, 1f);
        this.updateAnimation(entity.chargeAnimationState, BurrowAnimations.BURROW_DRILL_START, ageInTicks, 1f);
        this.updateAnimation(entity.dashAnimationState, BurrowAnimations.BURROW_IDLE, ageInTicks, 1f);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        burrow.render(matrices, vertexConsumer, light, overlay, color);
    }

    @Override
    public ModelPart getPart() {
        return burrow;
    }
}
