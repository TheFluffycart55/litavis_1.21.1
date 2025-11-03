package net.thefluffycart.litavis.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.thefluffycart.litavis.Litavis;

public class PowderKegBlock extends FallingBlock {

    public PowderKegBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends FallingBlock> getCodec() {
        return null;
    }

    @Override
    public void onLanding(World world, BlockPos pos, BlockState fallingBlockState, BlockState currentStateInPos, FallingBlockEntity powderKegEntity) {
        super.onLanding(world, pos, fallingBlockState, currentStateInPos, powderKegEntity);
        if (!world.isClient) {
            world.createExplosion(
                    null,
                    pos.getX() + 0.75,
                    pos.getY(),
                    pos.getZ() + 0.75,
                    2f,
                    World.ExplosionSourceType.TNT
            );
            world.removeBlock(pos, false);
        }
    }

    @Override
    public DamageSource getDamageSource(Entity attacker) {
        return super.getDamageSource(attacker);
    }
}
