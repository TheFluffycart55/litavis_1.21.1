package net.thefluffycart.litavis.item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class ModFoodComponents {
    public static final FoodComponent TIRIIM_BERRY = new FoodComponent.Builder().nutrition(3).saturationModifier(0.15f)
            .statusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 10), 0.001f).build();

}