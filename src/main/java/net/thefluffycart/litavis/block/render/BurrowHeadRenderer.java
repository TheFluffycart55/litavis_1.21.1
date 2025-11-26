package net.thefluffycart.litavis.block.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.SkullBlock;
import net.minecraft.block.WallSkullBlock;
import net.minecraft.block.entity.SkullBlockEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.entity.model.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationPropertyHelper;
import net.thefluffycart.litavis.Litavis;
import net.thefluffycart.litavis.block.entity.BurrowHeadBlockEntity;
import net.thefluffycart.litavis.entity.client.ModEntityModelLayers;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class BurrowHeadRenderer implements BlockEntityRenderer<BurrowHeadBlockEntity> {

    private final BurrowHeadModel model;
    private static final Identifier TEXTURE =
            Identifier.of(Litavis.MOD_ID, "textures/block/burrow_head.png");

    public BurrowHeadRenderer(BlockEntityRendererFactory.Context ctx) {
        this.model = new BurrowHeadModel(
                ctx.getLayerModelPart(ModEntityModelLayers.BURROW_HEAD)
        );
    }

    public static BurrowHeadModel getModel(EntityModelLoader loader) {
        return new BurrowHeadModel(
                loader.getModelPart(ModEntityModelLayers.BURROW_HEAD)
        );
    }

    @Override
    public void render(
            BurrowHeadBlockEntity blockEntity, float tickDelta,
            MatrixStack matrices, VertexConsumerProvider consumers,
            int light, int overlay
    ) {
        BlockState state = blockEntity.getCachedState();
        BlockState blockState = blockEntity.getCachedState();
        boolean bl = blockState.getBlock() instanceof WallSkullBlock;
        Direction direction = bl ? (Direction)blockState.get(WallSkullBlock.FACING) : null;
        int rot = bl ? RotationPropertyHelper.fromDirection(direction.getOpposite()) : state.get(SkullBlock.ROTATION);
        float yaw = RotationPropertyHelper.toDegrees(rot);
        RenderLayer layer = RenderLayer.getEntityCutoutNoCull(TEXTURE);
        renderSkull(direction, yaw, matrices, consumers, light, this.model, layer);
    }

    public static void renderSkull(
            @Nullable Direction direction, float yaw,
            MatrixStack matrices, VertexConsumerProvider consumers,
            int light, BurrowHeadModel model, RenderLayer layer
    ) {
        matrices.push();

        if (direction == null) {
            matrices.translate(0.5F, 0.811F, 0.5F);
        } else {
            matrices.translate(0.5F - direction.getOffsetX() * 0.25F, 1.25F, 0.5F - direction.getOffsetZ() * 0.25F);
        }
        matrices.scale(-1.0F, -1.0F, 1.0F);
        VertexConsumer vertex = consumers.getBuffer(layer);
        model.setHeadRotation(0f, yaw, 0f);
        model.render(matrices, vertex, light, OverlayTexture.DEFAULT_UV);
        matrices.pop();
    }

}