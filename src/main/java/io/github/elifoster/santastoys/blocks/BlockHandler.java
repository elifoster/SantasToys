package io.github.elifoster.santastoys.blocks;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.RegistryObject;

import java.util.function.Supplier;

import static io.github.elifoster.santastoys.SantasToys.REGISTER_BLOCKS;
import static io.github.elifoster.santastoys.SantasToys.REGISTER_ITEMS;

public class BlockHandler {
    public static final String NAME_GIVE_A_DAMN = "give_a_damn";
    public static final String NAME_HEAVY_LIGHT = "heavy_light";
    public static final String NAME_SPICED_SAND = "spiced_sand";

    public static RegistryObject<Block> GIVE_A_DAMN;
    public static RegistryObject<Item> GIVE_A_DAMN_ITEM;
    public static RegistryObject<Block> HEAVY_LIGHT;
    public static RegistryObject<Item> HEAVY_LIGHT_ITEM;
    public static RegistryObject<Block> SPICED_SAND;
    public static RegistryObject<Item> SPICED_SAND_ITEM;

    private static RegistryObject<Item> createBlockItem(String name, Supplier<Block> forBlockSupplier) {
        return REGISTER_ITEMS.register(name, () -> new BlockItem(forBlockSupplier.get(), new Item.Properties()));
    }

    public static void initializeBlocks() {
        GIVE_A_DAMN = REGISTER_BLOCKS.register(NAME_GIVE_A_DAMN, BlockGiveADamn::new);
        GIVE_A_DAMN_ITEM = createBlockItem(NAME_GIVE_A_DAMN, () -> GIVE_A_DAMN.get());

        HEAVY_LIGHT = REGISTER_BLOCKS.register(NAME_HEAVY_LIGHT, () -> new FallingBlock(BlockBehaviour.Properties.of()
          .strength(0.6F)
          .explosionResistance(1F)
          .lightLevel(state -> 15)
          .sound(SoundType.GLASS)
          .mapColor(MapColor.SAND)));
        HEAVY_LIGHT_ITEM = createBlockItem(NAME_HEAVY_LIGHT, HEAVY_LIGHT);

        SPICED_SAND = REGISTER_BLOCKS.register(NAME_SPICED_SAND, BlockSpicedSand::new);
        SPICED_SAND_ITEM = createBlockItem(NAME_SPICED_SAND, SPICED_SAND);
    }
}