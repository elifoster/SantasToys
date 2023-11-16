package io.github.elifoster.santastoys.util;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.ForgeRegistries;

public class TagsHelper {
    /**
     * Checks if the given block is tagged as the given tag
     * @param block the block
     * @param tag the tag
     */
    public static boolean isBlockTaggedAs(Block block, TagKey<Block> tag) {
        return ForgeRegistries.BLOCKS.tags().getTag(tag).contains(block);
    }
}
