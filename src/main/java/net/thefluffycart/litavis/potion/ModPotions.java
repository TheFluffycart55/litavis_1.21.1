package net.thefluffycart.litavis.potion;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.thefluffycart.litavis.Litavis;
import net.thefluffycart.litavis.effect.ModEffects;

public class ModPotions {
    public static final RegistryEntry<Potion> IGNITION_POTION = registerPotion("ignition_potion",
            new Potion(new StatusEffectInstance(ModEffects.IGNITION, 400, 0)));

    public static final RegistryEntry<Potion> STRONG_IGNITION_POTION = registerPotion("strong_ignition_potion",
            new Potion(new StatusEffectInstance(ModEffects.IGNITION, 200, 1)));

    public static final RegistryEntry<Potion> LONG_IGNITION_POTION = registerPotion("long_ignition_potion",
            new Potion(new StatusEffectInstance(ModEffects.IGNITION, 800, 0)));


    private static RegistryEntry<Potion> registerPotion(String name, Potion potion) {
        return Registry.registerReference(Registries.POTION, Identifier.of(Litavis.MOD_ID, name), potion);
    }

    public static void registerPotions() {
        Litavis.LOGGER.info("Registering Mod Potions for " + Litavis.MOD_ID);
    }
}