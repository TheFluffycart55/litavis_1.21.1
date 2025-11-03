package net.thefluffycart.litavis.util;

import net.minecraft.util.Identifier;
import net.thefluffycart.litavis.Litavis;
import net.thefluffycart.litavis.block.entity.PlushieBlockEntity;
import software.bernie.geckolib.model.GeoModel;

public class PlushieBlockModel extends GeoModel<PlushieBlockEntity> {
    @Override
    public Identifier getModelResource(PlushieBlockEntity animatable) {
        return Identifier.of(Litavis.MOD_ID, "geo/block/avery_plush.geo.json");
    }

    @Override
    public Identifier getTextureResource(PlushieBlockEntity animatable) {
        return Identifier.of(Litavis.MOD_ID, "textures/block/avery_plush.png");
    }

    @Override
    public Identifier getAnimationResource(PlushieBlockEntity animatable) {
        return Identifier.of(Litavis.MOD_ID, "animations/avery_plush.animation.json");
    }
}