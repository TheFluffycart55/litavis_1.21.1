package net.thefluffycart.litavis.block;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.sound.BlockSoundGroup;

import static net.thefluffycart.litavis.block.ModBlocks.registerBlock;

public class ModCompatBlocks {
    public static Block secretCrackedGraniteBricks;
    public static Block secretCrackedMossyGraniteBricks;
    public static Block secretCrackedTripslateBricks;

    public static void register() {
        if (FabricLoader.getInstance().isModLoaded("zeldacraft"))
        {
            secretCrackedGraniteBricks = registerBlock("secret_cracked_granite_bricks",
                    new Block(AbstractBlock.Settings.copy(Blocks.GRANITE).sounds(BlockSoundGroup.MUD_BRICKS)));

            secretCrackedMossyGraniteBricks = registerBlock("secret_cracked_mossy_granite_bricks",
                    new Block(AbstractBlock.Settings.copy(Blocks.GRANITE).sounds(BlockSoundGroup.MUD_BRICKS)));

            secretCrackedTripslateBricks = registerBlock("secret_cracked_tripslate_bricks",
                    new Block(AbstractBlock.Settings.create().instrument(NoteBlockInstrument.BANJO).mapColor(MapColor.TERRACOTTA_BROWN).requiresTool().strength(1.5F, 6.0F).sounds(BlockSoundGroup.DEEPSLATE_BRICKS)));
        }
    }
}
