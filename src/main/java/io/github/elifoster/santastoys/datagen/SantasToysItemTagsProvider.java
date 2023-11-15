package io.github.elifoster.santastoys.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

import static io.github.elifoster.santastoys.items.ItemHandler.MATCH;

public class SantasToysItemTagsProvider extends ItemTagsProvider {
    public SantasToysItemTagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockLookup, String modid, ExistingFileHelper existingFileHelper) {
        super(packOutput, lookupProvider, blockLookup, modid, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ItemTags.CREEPER_IGNITERS).add(MATCH.get());
    }
}
