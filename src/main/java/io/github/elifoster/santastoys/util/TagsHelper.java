package io.github.elifoster.santastoys.util;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class TagsHelper {
    /**
     * Checks if the given block is tagged as the given tag
     * @param state the block
     * @param tag the tag
     */
    public static boolean isBlockTaggedAs(BlockState state, TagKey<Block> tag) {
        return state.getTags().anyMatch(t -> t.equals(tag));
    }
}
