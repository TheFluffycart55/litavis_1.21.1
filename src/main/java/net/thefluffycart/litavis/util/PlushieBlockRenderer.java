package net.thefluffycart.litavis.util;

import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.thefluffycart.litavis.block.entity.LitavisBlockEntityType;
import net.thefluffycart.litavis.block.entity.PlushieBlockEntity;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class PlushieBlockRenderer extends GeoBlockRenderer<PlushieBlockEntity> {
    public PlushieBlockRenderer(BlockEntityRendererFactory.Context context) {
        super(new PlushieBlockModel());
    }
}