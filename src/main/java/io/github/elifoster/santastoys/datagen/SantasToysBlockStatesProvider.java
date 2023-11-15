package io.github.elifoster.santastoys.datagen;

import io.github.elifoster.santastoys.blocks.BlockHandler;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import static io.github.elifoster.santastoys.SantasToys.MODID;

public class SantasToysBlockStatesProvider extends BlockStateProvider {
    public SantasToysBlockStatesProvider(PackOutput output, String modid, ExistingFileHelper exFileHelper) {
        super(output, modid, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        horizontalBlock(BlockHandler.GIVE_A_DAMN.get(),
          new ResourceLocation(MODID, "block/damn_else"),
          new ResourceLocation(MODID, "block/damn_front"),
          new ResourceLocation(MODID, "block/damn_else"));
        simpleBlock(BlockHandler.SPICED_SAND.get());
        simpleBlock(BlockHandler.HEAVY_LIGHT.get(), cubeAll(Blocks.GLOWSTONE));
    }
}
