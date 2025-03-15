package net.thefluffycart.litavis.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.thefluffycart.litavis.Litavis;

public class CapsizerItem extends ToolItem {
    private int chargeTimer = 0;
    private boolean canUse;
    public CapsizerItem(ToolMaterial material, Settings settings) {
        super(material, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (hand.equals(Hand.OFF_HAND))
        {
            startCharge(world, user, hand);
        }
        return super.use(world, user, hand);
    }

    public void startCharge(World world, PlayerEntity user, Hand hand)
    {
            Vec3d direction = user.getRotationVector(user.getPitch(), user.getYaw());
            user.setVelocity(direction.multiply(1.1));
            user.velocityModified = true;
            user.getItemCooldownManager().set(this, world.getGameRules().getInt(Litavis.CAPSIZER_COOLDOWN));
    }
}
