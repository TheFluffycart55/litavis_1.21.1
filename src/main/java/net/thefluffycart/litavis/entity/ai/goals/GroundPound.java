package net.thefluffycart.litavis.entity.ai.goals;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.thefluffycart.litavis.entity.custom.BurrowEntity;
import net.thefluffycart.litavis.util.ModTags;

import java.util.EnumSet;
import java.util.List;

public class GroundPound extends Goal {
    private final BurrowEntity burrow;
    private PlayerEntity victim;
    private int cooldown;
    private BlockPos BurrowBlockPos;



    @Override
    public boolean canStart() {
        victim = burrow.getWorld().getClosestPlayer(burrow, 20.0D);
        return victim != null
                && burrow.canSee(victim)
                && !victim.isCreative();
    }

    public GroundPound(BurrowEntity burrow) {
        this.burrow = burrow;
        this.cooldown = 0;
        this.setControls(EnumSet.of(Control.MOVE, Control.LOOK));
    }

    @Override
    public void stop() {
        burrow.getNavigation().recalculatePath();
        burrow.setVelocity(Vec3d.ZERO);
    }

    @Override
    public void tick() {
        World BurrowWorld = burrow.getWorld();
        burrow.getPos().getZ();
        if (victim != null && cooldown <= 0) {
            burrow.getNavigation().stop();
            BurrowBlockPos = burrow.getBlockPos();
            for (int x = -2; x <= 2; x++) {
                for (int z = -2; z <= 2; z++) {
                    if (Math.abs(x) == 2 && Math.abs(z) == 2) {
                        continue;
                    }
                    if (Math.abs(x) == 0 && Math.abs(z) == 0) {
                        continue;
                    }
                    BlockPos targetPos = BurrowBlockPos.add(x, -1, z);
                    if (targetPos.equals(BurrowBlockPos.down())) {
                        continue;
                    }
                    BlockState blockState = BurrowWorld.getBlockState(targetPos);
                    if (blockState.isIn(ModTags.Blocks.EARTH_CHARGE_THROWABLE) || blockState.isIn(BlockTags.LEAVES) || blockState.isIn(BlockTags.LOGS)) {
                        entityLaunch((ServerWorld) BurrowWorld, BurrowBlockPos);
                        spawnUpperFallingBlock((ServerWorld) BurrowWorld, targetPos, blockState);
                        BurrowWorld.removeBlock(targetPos, false);
                        cooldown = 180;
                    }
                }
            }
        }
        else
            {
                cooldown--;
            }
    }

    private void entityLaunch(ServerWorld serverWorld, BlockPos pos) {
        Box impactBox = new Box(pos).expand(2.5, 2.5, 2.5);
        List<LivingEntity> nearbyEntities = serverWorld.getEntitiesByClass(
                LivingEntity.class,
                impactBox,
                entity -> true
        );

        for (LivingEntity entity : nearbyEntities) {
            if (entity != this.burrow)
            {
                Vec3d currentMotion = entity.getVelocity();
                entity.addVelocity(currentMotion.x, 0.5, currentMotion.z);
                entity.velocityModified = true;
            }
        }
    }

    private void spawnUpperFallingBlock(ServerWorld world, BlockPos pos, BlockState blockState) {
        FallingBlockEntity fallingBlockEntity = FallingBlockEntity.spawnFromBlock(world, pos, blockState);
            fallingBlockEntity.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
            fallingBlockEntity.setVelocity(new Vec3d(0, 1.02, 0));
            fallingBlockEntity.velocityModified = true;
            world.spawnEntity(fallingBlockEntity);
    }
}
