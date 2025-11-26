package net.thefluffycart.litavis.entity.ai.goals;

import net.minecraft.block.BlockState;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.passive.GoatBrain;
import net.minecraft.entity.passive.GoatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.*;
import net.thefluffycart.litavis.entity.custom.BurrowEntity;

import java.util.EnumSet;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;

import java.util.EnumSet;
import java.util.List;

public class BurrowCharge extends Goal {
    private final BurrowEntity burrow;
    private PlayerEntity victim;


    private int chargeTimer = 0;
    private static final int CHARGE_DURATION = 40;
    private static final double CHARGE_SPEED = 1.05;
    private static final int PREPARE_DURATION = 40;
    private static final float DAMAGE_AMOUNT = 5f;
    private boolean lockRotation = false;
    private float lockedYaw;
    private float lockedPitch;

    public BurrowCharge(BurrowEntity burrow) {
        this.burrow = burrow;
        this.setControls(EnumSet.of(Control.MOVE, Control.LOOK));
    }

    @Override
    public boolean canStart() {
        victim = burrow.getWorld().getClosestPlayer(burrow, 20.0D);
        return victim != null
                && burrow.canSee(victim)
                && !victim.isCreative()
                && (burrow.getState() == BurrowEntity.State.IDLE || burrow.getState() == BurrowEntity.State.CHARGE);
    }

    @Override
    public void start() {
        chargeTimer = 0;
        burrow.setState(BurrowEntity.State.CHARGE);
    }

    @Override
    public void stop() {
        burrow.setState(BurrowEntity.State.IDLE);
        this.lockRotation = false;
        chargeTimer = 0;
        burrow.getNavigation().stop();
        burrow.setVelocity(Vec3d.ZERO);
    }

    @Override
    public void tick() {
        if (victim != null && !lockRotation) {
            Vec3d targetPos = victim.getPos();
            Vec3d mobPos = burrow.getPos();
            double dx = targetPos.x - mobPos.x;
            double dz = targetPos.z - mobPos.z;
            double dy = (targetPos.y + victim.getStandingEyeHeight()) - (mobPos.y + burrow.getStandingEyeHeight());

            float targetYaw = (float) (MathHelper.atan2(-dx, dz) * (180.0 / Math.PI));

            burrow.setYaw(MathHelper.lerp((float) 0.5, burrow.getYaw(), targetYaw));
            burrow.setBodyYaw(burrow.getYaw());
            burrow.setHeadYaw(burrow.getYaw());

            float targetPitch = (float) (MathHelper.atan2(dy, Math.sqrt(dx * dx + dz * dz)) * (180.0 / Math.PI));

            burrow.setPitch(MathHelper.lerp((float) 0.3, burrow.getPitch(), targetPitch));

        }

        else if (lockRotation) {
            burrow.setYaw(lockedYaw);
            burrow.setPitch(lockedPitch);
            burrow.setBodyYaw(lockedYaw);
            burrow.setHeadYaw(lockedYaw);
        }

        switch (burrow.getState()) {
            case CHARGE -> prepareToCharge();
            case DASH -> startCharge();
        }
    }

    @Override
    public boolean shouldContinue() {
        return burrow.getState() == BurrowEntity.State.CHARGE || burrow.getState() == BurrowEntity.State.DASH;
    }

    private void prepareToCharge() {
        lockRotation = false;
        burrow.getNavigation().stop();
        if (++chargeTimer >= PREPARE_DURATION) {
            burrow.setState(BurrowEntity.State.DASH);
            chargeTimer = 0;
            Vec3d direction = victim.getPos().subtract(burrow.getPos()).normalize().multiply(CHARGE_SPEED);
            burrow.setVelocity(direction);
            lockedYaw = burrow.getYaw();
            lockedPitch = burrow.getPitch();
        }
    }

    private void startCharge() {
        this.lockRotation = true;
        Vec3d velocity = burrow.getVelocity().normalize().multiply(CHARGE_SPEED);
        burrow.setVelocity(velocity);
        Box boundingBox = burrow.getBoundingBox();
        BlockPos min = new BlockPos((int) boundingBox.minX, (int) boundingBox.minY, (int) boundingBox.minZ);
        BlockPos max = new BlockPos((int) boundingBox.maxX, (int) boundingBox.maxY, (int) boundingBox.maxZ);
        Box collisionBox = burrow.getBoundingBox();
        List<LivingEntity> entities = burrow.getWorld().getEntitiesByClass(
                LivingEntity.class,
                collisionBox,
                entity -> entity != burrow
        );

        for (LivingEntity entity : entities) {
            entity.damage(burrow.getDamageSources().mobAttack(burrow), DAMAGE_AMOUNT);
        }

        if (!burrow.getWorld().isClient) {
            BlockPos frontPos = burrow.getBlockPos().offset(burrow.getHorizontalFacing());
            BlockState blockState = burrow.getWorld().getBlockState(frontPos);
            for (BlockPos pos : BlockPos.iterate(min, max)) {
                if (burrow.getWorld().getBlockState(pos).isSolidBlock(burrow.getWorld(), pos)) {
                    stop();
                    return;
                }
            }
            for (LivingEntity entity : entities) {
                entity.damage(burrow.getDamageSources().mobAttack(burrow), DAMAGE_AMOUNT);
                stop();
                return;
            }
            if (++chargeTimer >= CHARGE_DURATION || burrow.collidesWith(victim)) {
                burrow.setState(BurrowEntity.State.IDLE);
                stop();
            }
        }
    }
}