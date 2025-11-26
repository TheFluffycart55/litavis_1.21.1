package net.thefluffycart.litavis.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.SkullBlockEntity;
import net.minecraft.util.math.BlockPos;

public class BurrowHeadBlockEntity extends BlockEntity {
    public BurrowHeadBlockEntity(BlockPos pos, BlockState state) {
        super(LitavisBlockEntityType.BURROW_HEAD, pos, state);
    }
}