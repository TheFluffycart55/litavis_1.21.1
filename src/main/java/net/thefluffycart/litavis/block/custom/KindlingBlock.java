package net.thefluffycart.litavis.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiFunction;

public class KindlingBlock extends PlantBlock implements Fertilizable {
    public static final MapCodec<KindlingBlock> CODEC = createCodec(KindlingBlock::new);
    public static final DirectionProperty FACING;
    public static final IntProperty FLOWER_AMOUNT;
    private static final BiFunction<Direction, Integer, VoxelShape> FACING_AND_AMOUNT_TO_SHAPE;

    public MapCodec<KindlingBlock> getCodec() {
        return CODEC;
    }

    public KindlingBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)((BlockState)this.stateManager.getDefaultState()).with(FACING, Direction.NORTH)).with(FLOWER_AMOUNT, 1));
    }

    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return (BlockState)state.with(FACING, rotation.rotate((Direction)state.get(FACING)));
    }

    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation((Direction)state.get(FACING)));
    }

    public boolean canReplace(BlockState state, ItemPlacementContext context) {
        return !context.shouldCancelInteraction() && context.getStack().isOf(this.asItem()) && (Integer)state.get(FLOWER_AMOUNT) < 4 ? true : super.canReplace(state, context);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return FACING_AND_AMOUNT_TO_SHAPE.apply(state.get(FACING), state.get(FLOWER_AMOUNT));
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState blockState = ctx.getWorld().getBlockState(ctx.getBlockPos());
        return blockState.isOf(this) ? (BlockState)blockState.with(FLOWER_AMOUNT, Math.min(4, (Integer)blockState.get(FLOWER_AMOUNT) + 1)) : (BlockState)this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{FACING, FLOWER_AMOUNT});
    }

    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        return true;
    }

    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        int i = (Integer)state.get(FLOWER_AMOUNT);
        if (i < 4) {
            world.setBlockState(pos, (BlockState)state.with(FLOWER_AMOUNT, i + 1), 2);
        } else {
            dropStack(world, pos, new ItemStack(this));
        }

    }

    static {
        FACING = Properties.HORIZONTAL_FACING;
        FLOWER_AMOUNT = Properties.FLOWER_AMOUNT;
        FACING_AND_AMOUNT_TO_SHAPE = Util.memoize((facing, flowerAmount) -> {
            VoxelShape[] voxelShapes = new VoxelShape[]{Block.createCuboidShape(8.0, 0.0, 8.0, 16.0, 1.0, 16.0), Block.createCuboidShape(8.0, 0.0, 0.0, 16.0, 1.0, 8.0), Block.createCuboidShape(0.0, 0.0, 0.0, 8.0, 1.0, 8.0), Block.createCuboidShape(0.0, 0.0, 8.0, 8.0, 1.0, 16.0)};
            VoxelShape voxelShape = VoxelShapes.empty();
            for (int i = 0; i < flowerAmount; ++i) {
                int j = Math.floorMod(i - facing.getHorizontal(), 4);
                voxelShape = VoxelShapes.union(voxelShape, voxelShapes[j]);
            }
            return voxelShape.asCuboid();
        });
    }
}
