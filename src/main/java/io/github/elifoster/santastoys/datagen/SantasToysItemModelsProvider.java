package io.github.elifoster.santastoys.datagen;

import io.github.elifoster.santastoys.blocks.BlockHandler;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.ForgeRegistries;

import static io.github.elifoster.santastoys.blocks.BlockHandler.*;
import static io.github.elifoster.santastoys.items.ItemHandler.*;

public class SantasToysItemModelsProvider extends ItemModelProvider {
    public SantasToysItemModelsProvider(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
        super(output, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        withExistingParent(SPICED_SAND.getId().getPath(), modLoc("block/" + BlockHandler.NAME_SPICED_SAND));
        withExistingParent(HEAVY_LIGHT.getId().getPath(), mcLoc("glowstone"));
        basicItem(MATCH.get());
        blasterItem(ENDER_BLASTER.get());
        blasterItem(NETHER_BLASTER.get());
    }

    /**
     * Helper method for generating a blaster item model, based loosely on the vanilla bow model.
     * @param item the item
     * @return
     */
    private ItemModelBuilder blasterItem(Item item) {
        ResourceLocation loc = ForgeRegistries.ITEMS.getKey(item);
        return getBuilder(loc.toString())
          .parent(new ModelFile.UncheckedModelFile("item/handheld"))
          .texture("layer0", new ResourceLocation(loc.getNamespace(), "item/" + loc.getPath()))
          .transforms()
            .transform(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND).rotation(40, 260, -40).translation(0, 5, 0).end()
            .transform(ItemDisplayContext.THIRD_PERSON_LEFT_HAND).rotation(40, -280, 40).translation(0, 5, 0).end()
            .transform(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND).rotation(0, -90, 25).translation(1.13f, 3.2f, 1.13f).end()
            .transform(ItemDisplayContext.FIRST_PERSON_LEFT_HAND).rotation(0, 90, -25).translation(1.13f, 3.2f, 1.13f).end()
          .end();
    }
}
