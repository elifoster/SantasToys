package io.github.elifoster.santastoys.datagen;

import io.github.elifoster.santastoys.blocks.BlockHandler;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class SantasToysItemModelsProvider extends ItemModelProvider {
    public SantasToysItemModelsProvider(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
        super(output, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        withExistingParent(BlockHandler.GIVE_A_DAMN.getId().getPath(), loc(BlockHandler.NAME_GIVE_A_DAMN));
        withExistingParent(BlockHandler.SPICED_SAND.getId().getPath(), loc(BlockHandler.NAME_SPICED_SAND));
        withExistingParent(BlockHandler.HEAVY_LIGHT.getId().getPath(), mcLoc("glowstone"));
    }

    private ResourceLocation loc(String name) {
        return modLoc("block/" + name);
    }
}
