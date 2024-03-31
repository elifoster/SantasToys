package io.github.elifoster.santastoys.blocks;

import io.github.elifoster.santastoys.blocks.blockentities.LiquidSensorBlockEntity;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.util.ColorRGBA;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ColoredFallingBlock;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.Collections;
import java.util.function.Supplier;

import static io.github.elifoster.santastoys.SantasToys.*;

public class BlockHandler {
    public static final String NAME_HEAVY_LIGHT = "heavy_light";
    public static final String NAME_DECAYING_LIGHT_BLOCK = "decaying_light_block";
    public static final String NAME_LIQUID_SENSOR = "liquid_sensor";

    public static DeferredHolder<Block, FallingBlock> HEAVY_LIGHT;
    public static DeferredHolder<Item, BlockItem> HEAVY_LIGHT_ITEM;
    public static DeferredHolder<Block, DecayingLightBlock> DECAYING_LIGHT_BLOCK;
    public static DeferredHolder<Block, LiquidSensorBlock> LIQUID_SENSOR;
    public static DeferredHolder<Item, BlockItem> LIQUID_SENSOR_ITEM;
    public static Supplier<BlockEntityType<LiquidSensorBlockEntity>> LIQUID_SENSOR_BE_TYPE;

    private static DeferredHolder<Item, BlockItem> createBlockItem(String name, DeferredHolder<Block, ? extends Block> forBlockSupplier) {
        return REGISTER_ITEMS.register(name, () -> new BlockItem(forBlockSupplier.get(), new Item.Properties()));
    }

    public static void initializeBlocks() {
        HEAVY_LIGHT = REGISTER_BLOCKS.register(NAME_HEAVY_LIGHT, () -> new ColoredFallingBlock(new ColorRGBA(-7968712),
          BlockBehaviour.Properties.of()
              .strength(0.6F)
              .explosionResistance(1F)
              .lightLevel(state -> 15)
              .sound(SoundType.GLASS)
              .mapColor(MapColor.SAND)));
        HEAVY_LIGHT_ITEM = createBlockItem(NAME_HEAVY_LIGHT, HEAVY_LIGHT);
        DECAYING_LIGHT_BLOCK = REGISTER_BLOCKS.register(NAME_DECAYING_LIGHT_BLOCK, DecayingLightBlock::new);
        LIQUID_SENSOR = REGISTER_BLOCKS.register(NAME_LIQUID_SENSOR, LiquidSensorBlock::new);
        LIQUID_SENSOR_ITEM = createBlockItem(NAME_LIQUID_SENSOR, LIQUID_SENSOR);
        LIQUID_SENSOR_BE_TYPE = REGISTER_BLOCK_ENTITY_TYPES.register(NAME_LIQUID_SENSOR, () -> BlockEntityType.Builder.of(LiquidSensorBlockEntity::new, LIQUID_SENSOR.get()).build(null));
    }

    public static BlockLootSubProvider getLootSubProvider() {
        return new BlockLootSubProvider(Collections.emptySet(), FeatureFlags.REGISTRY.allFlags()) {
            @Override
            protected void generate() {
                dropSelf(HEAVY_LIGHT.get());
                dropSelf(LIQUID_SENSOR.get());
            }

            @Override
            protected Iterable<Block> getKnownBlocks() {
                return REGISTER_BLOCKS.getEntries().stream().map((s) -> (Block) s.get()).toList();
            }
        };
    }
}