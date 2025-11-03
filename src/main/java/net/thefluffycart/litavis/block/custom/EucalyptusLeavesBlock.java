package net.thefluffycart.litavis.block.custom;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class EucalyptusLeavesBlock extends LeavesBlock {
    //public static BooleanProperty HAS_OIL = BooleanProperty.of("HAS_OIL");
    public EucalyptusLeavesBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        float f = random.nextFloat();
        if (!(f > 0.05F)) {
                if (EucalyptusLeavesBlock.canSpawnParticles(world.getBlockState(pos.down())))
                {
                    createParticle(world, pos, state, random);
                }
        }
    }

    public static boolean canSpawnParticles(BlockState state)
    {
        return state.isAir();
    }

    private static void createParticle(World world, BlockPos pos, BlockState state, Random random) {
        Vec3d vec3d = state.getModelOffset(world, pos);
        double d = (double)0.0625F;
        double e = (double)pos.getX() + (double)0.5F + vec3d.x + (random.nextDouble() - 0.5);
        double f = (double)((float)(pos.getY() + 0.6) - 0.6875F) - (double)0.0625F;
        double g = (double)pos.getZ() + (double)0.5F + vec3d.z + (random.nextDouble() - 0.5);
        ParticleEffect particleEffect = ParticleTypes.DRIPPING_HONEY;
        world.addParticle(particleEffect, e, f, g, (double)0.0, (double)0.0F, (double)0.0F);
    }
}
