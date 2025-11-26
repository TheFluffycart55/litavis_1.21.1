package net.thefluffycart.litavis.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageEffects;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.server.world.ServerWorld;

public class IgnitionEffect extends StatusEffect {
    public IgnitionEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        return super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}