package net.thefluffycart.litavis.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.SkullBlockEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.item.Equipment;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RotationPropertyHelper;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.thefluffycart.litavis.block.entity.BurrowHeadBlockEntity;
import net.thefluffycart.litavis.block.entity.LitavisBlockEntityType;
import org.jetbrains.annotations.Nullable;

public class BurrowSkullBlock extends BlockWithEntity implements Equipment {
    public static final int MAX_ROTATION_INDEX = RotationPropertyHelper.getMax();
    private static final int MAX_ROTATIONS;
    public static final IntProperty ROTATION;
    protected static final VoxelShape SHAPE;

    public BurrowSkullBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)this.getDefaultState().with(ROTATION, 0));
    }

    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    protected VoxelShape getCullingShape(BlockState state, BlockView world, BlockPos pos) {
        return VoxelShapes.empty();
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return (BlockState)((BlockState)this.getDefaultState().with(ROTATION, RotationPropertyHelper.fromYaw(ctx.getPlayerYaw())));
    }

    public float getRotationDegrees(BlockState state) {
        return RotationPropertyHelper.toDegrees((Integer)state.get(ROTATION));
    }

    protected BlockState rotate(BlockState state, BlockRotation rotation) {
        return (BlockState)state.with(ROTATION, rotation.rotate((Integer)state.get(ROTATION), 16));
    }

    protected BlockState mirror(BlockState state, BlockMirror mirror) {
        return (BlockState)state.with(ROTATION, mirror.mirror((Integer)state.get(ROTATION), 16));
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(new Property[]{ROTATION});
    }

    static {
        MAX_ROTATIONS = MAX_ROTATION_INDEX + 1;
        ROTATION = Properties.ROTATION;
        SHAPE = Block.createCuboidShape((double)4.0F, (double)0.0F, (double)4.0F, (double)12.0F, (double)8.0F, (double)12.0F);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return null;
    }

    @Override
    public EquipmentSlot getSlotType() {
        return EquipmentSlot.HEAD;
    }

    @Override
    public BurrowHeadBlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new BurrowHeadBlockEntity(pos, state);
    }

    public BlockEntityType<? extends BlockEntity> getType() {
        return LitavisBlockEntityType.BURROW_HEAD;
    }
}