package net.thefluffycart.litavis.entity.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.thefluffycart.litavis.entity.client.animation.CopperGolemAnimations;
import net.thefluffycart.litavis.entity.custom.CopperGolemEntity;

public class CopperGolemModel extends SinglePartEntityModel<CopperGolemEntity> {
    private final ModelPart copper_golem;
    private final ModelPart head;
    private final ModelPart rod;
    private final ModelPart nose;
    private final ModelPart body;
    private final ModelPart arm_l;
    private final ModelPart arm_r;
    private final ModelPart basket;
    private final ModelPart leg_l;
    private final ModelPart leg_r;
    public CopperGolemModel(ModelPart root) {
        this.copper_golem = root.getChild("copper_golem");
        this.head = this.copper_golem.getChild("head");
        this.rod = this.head.getChild("rod");
        this.nose = this.head.getChild("nose");
        this.body = this.copper_golem.getChild("body");
        this.arm_l = this.body.getChild("arm_l");
        this.arm_r = this.body.getChild("arm_r");
        this.basket = this.body.getChild("basket");
        this.leg_l = this.copper_golem.getChild("leg_l");
        this.leg_r = this.copper_golem.getChild("leg_r");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData copper_golem = modelPartData.addChild("copper_golem", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 16.0F, 0.0F));

        ModelPartData head = copper_golem.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -5.0F, -4.0F, 8.0F, 5.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -1.0F, 1.0F));

        ModelPartData rod = head.addChild("rod", ModelPartBuilder.create().uv(24, 30).cuboid(-1.0F, -3.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F))
                .uv(24, 13).cuboid(-2.0F, -6.0F, -2.0F, 4.0F, 3.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -4.0F, 0.0F));

        ModelPartData nose = head.addChild("nose", ModelPartBuilder.create().uv(16, 30).cuboid(-1.0F, -0.5F, -2.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -1.5F, -3.0F));

        ModelPartData body = copper_golem.addChild("body", ModelPartBuilder.create().uv(0, 13).cuboid(-4.0F, -3.0F, -2.0F, 8.0F, 6.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 2.0F, 1.0F));

        ModelPartData arm_l = body.addChild("arm_l", ModelPartBuilder.create().uv(0, 30).cuboid(0.0F, -1.0F, -1.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(4.0F, -2.0F, 0.0F));

        ModelPartData arm_r = body.addChild("arm_r", ModelPartBuilder.create().uv(8, 30).cuboid(-2.0F, -1.0F, -1.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, -2.0F, 0.0F));

        ModelPartData basket = body.addChild("basket", ModelPartBuilder.create().uv(0, 54).cuboid(-3.0F, -3.0F, -2.0F, 6.0F, 6.0F, 4.0F, new Dilation(0.0F))
                .uv(23, 56).cuboid(-2.5F, -1.5F, -2.5F, 5.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 4.0F));

        ModelPartData leg_l = copper_golem.addChild("leg_l", ModelPartBuilder.create().uv(1, 24).cuboid(-1.5F, 0.0F, -1.5F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(2.0F, 5.0F, 1.0F));

        ModelPartData leg_r = copper_golem.addChild("leg_r", ModelPartBuilder.create().uv(18, 24).cuboid(-1.5F, 0.0F, -1.5F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.0F, 5.0F, 1.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }
    @Override
    public void setAngles(CopperGolemEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);

        this.animateMovement(CopperGolemAnimations.COPPER_GOLEM_WALK, limbSwing, limbSwingAmount, 6f, 6.5f);
        this.updateAnimation(entity.idleAnimationState, CopperGolemAnimations.COPPER_GOLEM_IDLE, ageInTicks, 1f);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        copper_golem.render(matrices, vertexConsumer, light, overlay, color);
    }

    @Override
    public ModelPart getPart() {
        return copper_golem;
    }
}