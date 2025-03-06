package net.thefluffycart.litavis.entity.custom;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.thefluffycart.litavis.Litavis;
import net.thefluffycart.litavis.entity.ModEntities;
import net.thefluffycart.litavis.item.ModItems;
import net.thefluffycart.litavis.util.ModTags;

import java.util.List;

public class EarthChargeEntity extends ThrownItemEntity {
    //NO IDEA WHY THIS HAD TO BE 3 SEPARATE CONSTRUCTORS, BUT IT WORKS
    public EarthChargeEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public EarthChargeEntity(World world, LivingEntity owner) {
        super(ModEntities.EARTH_CHARGE, owner, world); // null will be changed later
    }

    @Environment(EnvType.CLIENT)
    private ParticleEffect getParticleParameters() {
        ItemStack itemStack = this.getStack();
        return (ParticleEffect)(itemStack.isEmpty() ? ParticleTypes.MYCELIUM : new ItemStackParticleEffect(ParticleTypes.ITEM, itemStack));
    }

    //AN HOUR OF STRUGGLE, FOR THE SOLUTION TO BE THIS SHIT
    @Override
    public boolean hasNoGravity() {
        return true;
    }

    @Environment(EnvType.CLIENT)
    public void handleStatus(byte status) {
        if (status == 1) {
            ParticleEffect particleEffect = this.getParticleParameters();

            for(int i = 0; i < 8; ++i) {
                this.getWorld().addParticle(particleEffect, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        World world = this.getWorld();

        if (!world.isClient) {
            BlockPos hitPos = blockHitResult.getBlockPos();
            replaceBlocks((ServerWorld) world, hitPos);
            entityLaunch((ServerWorld) world, hitPos);
            this.discard();
        }
        super.onBlockHit(blockHitResult);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        World world = this.getWorld();
        if (!world.isClient)
        {
            BlockPos hitPos = entityHitResult.getEntity().getBlockPos();
            replaceBlocks((ServerWorld) world, hitPos);
            entityLaunch((ServerWorld) world, hitPos);
            this.discard();
        }
        super.onEntityHit(entityHitResult);
    }

    private void entityLaunch(ServerWorld serverWorld, BlockPos pos) {
        Box impactBox = new Box(pos).expand(2.5, 2.5, 2.5); // 5x5x5 radius
        List<LivingEntity> nearbyEntities = serverWorld.getEntitiesByClass(
                LivingEntity.class,
                impactBox,
                entity -> true
        );

        for (LivingEntity entity : nearbyEntities) {
            Vec3d currentMotion = entity.getVelocity();
            entity.addVelocity(currentMotion.x, 1.5, currentMotion.z);
            entity.velocityModified = true;
        }
    }

    //SET BLOCKS TO AIR, AND SPAWN FALLING BLOCKS FROM THEM
    private void replaceBlocks(ServerWorld world, BlockPos centerPos) {
        if (this.getWorld().getGameRules().getBoolean(Litavis.EARTH_CHARGE_GRIEFING)) {
            if (this.getWorld().getGameRules().getBoolean(Litavis.EARTH_CHARGE_RESTRICTED))
            {
                playSound(SoundEvents.ENTITY_SNIFFER_DIGGING_STOP, 3, 1);
                for (int x = -2; x <= 2; x++) {
                    for (int z = -2; z <= 2; z++) {
                        if (Math.abs(x) == 2 && Math.abs(z) == 2) {
                            continue;
                        }
                        BlockPos targetPos = centerPos.add(x, 0, z);
                        BlockState blockState = world.getBlockState(targetPos);

                        if (blockState.isIn(ModTags.Blocks.EARTH_CHARGE_RESTRICTED)) {
                            spawnUpperFallingBlock(world, targetPos, blockState);
                            world.removeBlock(targetPos, false);
                        }
                    }
                }
            }

            else
            {
                playSound(SoundEvents.ENTITY_SNIFFER_DIGGING_STOP, 3, 1);
                for (int x = -2; x <= 2; x++) {
                    for (int z = -2; z <= 2; z++) {
                        if (Math.abs(x) == 2 && Math.abs(z) == 2) {
                            continue;
                        }
                        BlockPos targetPos = centerPos.add(x, 0, z);
                        BlockState blockState = world.getBlockState(targetPos);
                        if (blockState.isIn(ModTags.Blocks.EARTH_CHARGE_THROWABLE) || blockState.isIn(BlockTags.LEAVES) || blockState.isIn(BlockTags.LOGS)) {
                            spawnUpperFallingBlock(world, targetPos, blockState);
                            world.removeBlock(targetPos, false);
                        }
                    }
                }
            }

            for (int x = -1; x <= 1; x++) {
                for (int z = -1; z <= 1; z++) {
                    BlockPos targetPos = centerPos.add(x, -1, z);
                    BlockState blockState = world.getBlockState(targetPos);

                    if (blockState.isIn(ModTags.Blocks.EARTH_CHARGE_THROWABLE)) {
                        spawnLowerFallingBlock(world, targetPos, blockState);
                        world.removeBlock(targetPos, false);
                    }
                }
            }
        }

    }

    //FALLING BLOCK HANDLER FOR 3X3 AREA
    private void spawnLowerFallingBlock(ServerWorld world, BlockPos pos, BlockState blockState) {
        FallingBlockEntity fallingBlockEntity = FallingBlockEntity.spawnFromBlock(world, pos, blockState);
        if (fallingBlockEntity != null) {
            fallingBlockEntity.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
            fallingBlockEntity.setVelocity(new Vec3d(0, 1, 0));
            fallingBlockEntity.velocityModified = true;
            world.spawnEntity(fallingBlockEntity);

            world.spawnEntity(fallingBlockEntity);
        }
    }
    //FALLING BLOCK HANDLER FOR 5X5 AREA
    private void spawnUpperFallingBlock(ServerWorld world, BlockPos pos, BlockState blockState) {
        FallingBlockEntity fallingBlockEntity = FallingBlockEntity.spawnFromBlock(world, pos, blockState);
        if (fallingBlockEntity != null) {
            fallingBlockEntity.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
            fallingBlockEntity.setVelocity(new Vec3d(0, 1.02, 0));
            fallingBlockEntity.velocityModified = true;
            world.spawnEntity(fallingBlockEntity);

            world.spawnEntity(fallingBlockEntity);
        }
    }

    protected Item getDefaultItem() {
        return ModItems.EARTH_CHARGE;
    }
}
