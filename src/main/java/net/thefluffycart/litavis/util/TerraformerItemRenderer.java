package net.thefluffycart.litavis.util;

import net.thefluffycart.litavis.item.custom.TerraformerItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class TerraformerItemRenderer extends GeoItemRenderer<TerraformerItem> {
    public TerraformerItemRenderer() {
        super(new TerraformerModel());
    }
}