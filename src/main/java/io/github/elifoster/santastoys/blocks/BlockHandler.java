package io.github.elifoster.santastoys.blocks;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.Collections;

import static io.github.elifoster.santastoys.SantasToys.REGISTER_BLOCKS;
import static io.github.elifoster.santastoys.SantasToys.REGISTER_ITEMS;

public class BlockHandler {
    public static final String NAME_HEAVY_LIGHT = "heavy_light";
    public static final String NAME_DECAYING_LIGHT_BLOCK = "decaying_light_block";

    public static DeferredHolder<Block, FallingBlock> HEAVY_LIGHT;
    public static DeferredHolder<Item, BlockItem> HEAVY_LIGHT_ITEM;
    public static DeferredHolder<Block, DecayingLightBlock> DECAYING_LIGHT_BLOCK;

    private static DeferredHolder<Item, BlockItem> createBlockItem(String name, DeferredHolder<Block, ? extends Block> forBlockSupplier) {
        return REGISTER_ITEMS.register(name, () -> new BlockItem(forBlockSupplier.get(), new Item.Properties()));
    }

    public static void initializeBlocks() {
        HEAVY_LIGHT = REGISTER_BLOCKS.register(NAME_HEAVY_LIGHT, () -> new FallingBlock(BlockBehaviour.Properties.of()
          .strength(0.6F)
          .explosionResistance(1F)
          .lightLevel(state -> 15)
          .sound(SoundType.GLASS)
          .mapColor(MapColor.SAND)));
        HEAVY_LIGHT_ITEM = createBlockItem(NAME_HEAVY_LIGHT, HEAVY_LIGHT);
        DECAYING_LIGHT_BLOCK = REGISTER_BLOCKS.register(NAME_DECAYING_LIGHT_BLOCK, DecayingLightBlock::new);
    }

    public static BlockLootSubProvider getLootSubProvider() {
        return new BlockLootSubProvider(Collections.emptySet(), FeatureFlags.REGISTRY.allFlags()) {
            @Override
            protected void generate() {
                dropSelf(HEAVY_LIGHT.get());
            }

            @Override
            protected Iterable<Block> getKnownBlocks() {
                return REGISTER_BLOCKS.getEntries().stream().map((s) -> (Block) s.get()).toList();
            }
        };
    }
}