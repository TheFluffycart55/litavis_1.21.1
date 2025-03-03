package net.thefluffycart.litavis.entity.client;

import com.google.common.collect.Maps;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.thefluffycart.litavis.Litavis;
import net.thefluffycart.litavis.entity.custom.BurrowEntity;
import net.thefluffycart.litavis.entity.variant.BurrowVariant;

import java.util.Map;

public class BurrowRenderer extends MobEntityRenderer<BurrowEntity, BurrowModel>
{
    private static final Map<BurrowVariant, Identifier> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(BurrowVariant.class), map -> {
                map.put(BurrowVariant.BURROW_BASE, Identifier.of(Litavis.MOD_ID, "textures/entity/burrow/burrow.png"));
                map.put(BurrowVariant.RED, Identifier.of(Litavis.MOD_ID, "textures/entity/burrow/red_burrow.png"));
                map.put(BurrowVariant.ORANGE, Identifier.of(Litavis.MOD_ID, "textures/entity/burrow/orange_burrow.png"));
                map.put(BurrowVariant.YELLOW, Identifier.of(Litavis.MOD_ID, "textures/entity/burrow/yellow_burrow.png"));
                map.put(BurrowVariant.GREEN, Identifier.of(Litavis.MOD_ID, "textures/entity/burrow/green_burrow.png"));
                map.put(BurrowVariant.BLUE, Identifier.of(Litavis.MOD_ID, "textures/entity/burrow/blue_burrow.png"));
                map.put(BurrowVariant.PINK, Identifier.of(Litavis.MOD_ID, "textures/entity/burrow/pink_burrow.png"));
                map.put(BurrowVariant.PURPLE, Identifier.of(Litavis.MOD_ID, "textures/entity/burrow/purple_burrow.png"));
                map.put(BurrowVariant.BROWN, Identifier.of(Litavis.MOD_ID, "textures/entity/burrow/brown_burrow.png"));
                map.put(BurrowVariant.CALICO, Identifier.of(Litavis.MOD_ID, "textures/entity/burrow/calico_burrow.png"));
                map.put(BurrowVariant.FRUITY, Identifier.of(Litavis.MOD_ID, "textures/entity/burrow/fruity_burrow.png"));
                map.put(BurrowVariant.BEACHBALL, Identifier.of(Litavis.MOD_ID, "textures/entity/burrow/beachball_burrow.png"));
            });
    public BurrowRenderer(EntityRendererFactory.Context context) {
        super(context, new BurrowModel(context.getPart(ModEntityModelLayers.BURROW)), .2f);
    }

    @Override
    public Identifier getTexture(BurrowEntity entity) {
        return LOCATION_BY_VARIANT.get(entity.getVariant());
    }

    @Override
    public void render(BurrowEntity livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {

        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
