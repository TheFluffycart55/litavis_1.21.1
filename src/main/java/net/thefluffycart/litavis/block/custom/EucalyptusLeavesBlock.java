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
        //this.setDefaultState(this.getDefaultState().with(HAS_OIL, false));
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

//    @Override
//    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
//        Item item = stack.getItem();
//        boolean oil = state.get(HAS_OIL);
//        if (stack.isOf(Items.GLASS_BOTTLE)) {
//            if(oil)
//            {
//                stack.decrement(1);
//                world.setBlockState(pos, (BlockState)state.cycle(HAS_OIL), Block.NOTIFY_LISTENERS);
//                world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
//                if (stack.isEmpty()) {
//                    player.setStackInHand(hand, new ItemStack(Items.HONEY_BOTTLE));
//                } else if (!player.getInventory().insertStack(new ItemStack(Items.HONEY_BOTTLE))) {
//                    player.dropItem(new ItemStack(Items.HONEY_BOTTLE), false);
//                }
//            }
//        }
//        return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
//    }

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

//    @Override
//    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
//        builder.add(HAS_OIL);
//    }
//
//    @Override
//    public BlockState getPlacementState(ItemPlacementContext ctx) {
//        return this.getDefaultState().with(HAS_OIL, Boolean.TRUE);
//    }
}
