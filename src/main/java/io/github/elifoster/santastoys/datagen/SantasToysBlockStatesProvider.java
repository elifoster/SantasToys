package io.github.elifoster.santastoys.datagen;

import io.github.elifoster.santastoys.blocks.BlockHandler;
import io.github.elifoster.santastoys.blocks.LiquidSensorBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class SantasToysBlockStatesProvider extends BlockStateProvider {
    public SantasToysBlockStatesProvider(PackOutput output, String modid, ExistingFileHelper exFileHelper) {
        super(output, modid, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(BlockHandler.HEAVY_LIGHT.get(), cubeAll(Blocks.GLOWSTONE));
        getVariantBuilder(BlockHandler.DECAYING_LIGHT_BLOCK.get())
          .forAllStates(state -> new ConfiguredModel[] { new ConfiguredModel(models().getExistingFile(mcLoc("block/air"))) });
        getVariantBuilder(BlockHandler.LIQUID_SENSOR.get())
          .forAllStates(state -> {
              String inverted = state.getValue(LiquidSensorBlock.INVERTED) ? "inverted_" : "";
              String on = state.getValue(LiquidSensorBlock.POWERED) ? "on" : "off";
              ResourceLocation loc = modLoc("block/liquid_sensor_" + inverted + on);

              return ConfiguredModel.builder()
                .modelFile(models().getBuilder(loc.getPath())
                  .parent(models().getExistingFile(mcLoc("block/cube_all")))
                  .texture("all", loc))
                .build();
          });
    }
}
