package net.thefluffycart.litavis.entity.client;

import com.google.common.collect.Maps;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.thefluffycart.litavis.Litavis;
import net.thefluffycart.litavis.entity.custom.CopperGolemEntity;
import net.thefluffycart.litavis.entity.variant.BurrowVariant;

import java.util.Map;

public class CopperGolemRenderer extends MobEntityRenderer<CopperGolemEntity, CopperGolemModel> {

    private static final Identifier TEXTURE = Identifier.of(Litavis.MOD_ID, "textures/entity/copper_golem/copper_golem.png");

    public CopperGolemRenderer(EntityRendererFactory.Context context) {
        super(context, new CopperGolemModel(context.getPart(ModEntityModelLayers.COPPER_GOLEM)), 0.5f);
    }

    @Override
    public Identifier getTexture(CopperGolemEntity entity) {
        return TEXTURE;// Identifier.of(MCCourseMod.MOD_ID, "textures/entity/dodo/dodo_blue.png");
    }

    @Override
    public void render(CopperGolemEntity livingEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}