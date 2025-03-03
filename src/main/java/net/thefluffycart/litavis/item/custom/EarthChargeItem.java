package net.thefluffycart.litavis.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.thefluffycart.litavis.entity.custom.EarthChargeEntity;


public class EarthChargeItem extends Item {
    private static final int COOLDOWN = 600;

    public EarthChargeItem(Settings settings) {
        super(settings);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 1F);
        if (!world.isClient) {
            EarthChargeEntity earthChargeEntity = new EarthChargeEntity(world, user);
            earthChargeEntity.setItem(itemStack);
            earthChargeEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 0F);
            world.spawnEntity(earthChargeEntity);
        }
        //USE ITEM
        user.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!user.getAbilities().creativeMode) {
            itemStack.decrement(1);
        }
        world.playSound((PlayerEntity)null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_WIND_CHARGE_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
        user.getItemCooldownManager().set(this, COOLDOWN);
        return TypedActionResult.success(itemStack, world.isClient());
    }
}