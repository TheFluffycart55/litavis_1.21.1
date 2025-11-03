package net.thefluffycart.litavis.util;

import net.minecraft.util.Identifier;
import net.thefluffycart.litavis.Litavis;
import net.thefluffycart.litavis.item.custom.TerraformerItem;
import software.bernie.geckolib.GeckoLib;
import software.bernie.geckolib.model.GeoModel;

public class TerraformerModel extends GeoModel<TerraformerItem> {
    private final Identifier model = Identifier.of(Litavis.MOD_ID, "geo/item/terraformer.json");
    private final Identifier texture = Identifier.of(Litavis.MOD_ID, "textures/item/terraformer.png");
    private final Identifier animations = Identifier.of(Litavis.MOD_ID, "animations/terraformer.animation.json");

    @Override
    public Identifier getModelResource(TerraformerItem terraformerItem) {
        return this.model;
    }

    @Override
    public Identifier getTextureResource(TerraformerItem terraformerItem) {
        return this.texture;
    }

    @Override
    public Identifier getAnimationResource(TerraformerItem terraformerItem) {
        return this.animations;
    }
}