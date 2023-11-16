package io.github.elifoster.santastoys.datagen;

import io.github.elifoster.santastoys.entity.EntityHandler;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;

import java.util.concurrent.CompletableFuture;

public class SantasToysEntityTypeTagsProvider extends EntityTypeTagsProvider {
    public SantasToysEntityTypeTagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(EntityTypeTags.IMPACT_PROJECTILES)
          .add(EntityHandler.ENDER_BLAST.get())
          .add(EntityHandler.NETHER_BLAST.get())
          .add(EntityHandler.THROWN_BRICK.get());
    }
}
