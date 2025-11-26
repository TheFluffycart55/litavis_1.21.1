package net.thefluffycart.litavis.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Oxidizable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Optional;

public class EucalyptusOilItem extends Item {
    public EucalyptusOilItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        BlockState state = world.getBlockState(pos);
        if (state.getBlock() instanceof Oxidizable) {
            Optional<Block> copper = Oxidizable.getIncreasedOxidationBlock(state.getBlock());

            if (copper.isPresent()) {
                world.setBlockState(pos, copper.get().getStateWithProperties(state), 3);
                world.playSound(
                        null, pos,
                        SoundEvents.ITEM_HONEYCOMB_WAX_ON,
                        SoundCategory.BLOCKS,
                        1.0f, 1.0f
                );
                context.getStack().decrement(1);
                return ActionResult.SUCCESS;
            }
        }

        return ActionResult.PASS;
    }
}