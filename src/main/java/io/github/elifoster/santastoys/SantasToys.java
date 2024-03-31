package io.github.elifoster.santastoys;

import io.github.elifoster.santastoys.blocks.BlockHandler;
import io.github.elifoster.santastoys.datagen.*;
import io.github.elifoster.santastoys.entity.EntityHandler;
import io.github.elifoster.santastoys.items.ItemHandler;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static io.github.elifoster.santastoys.SantasToys.MODID;

@Mod(MODID)
public class SantasToys {
    public static final String MODID = "santastoys";

    public static final DeferredRegister<Block> REGISTER_BLOCKS = DeferredRegister.create(BuiltInRegistries.BLOCK, MODID);
    public static final DeferredRegister<BlockEntityType<?>> REGISTER_BLOCK_ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, MODID);
    public static final DeferredRegister<Item> REGISTER_ITEMS = DeferredRegister.create(BuiltInRegistries.ITEM, MODID);
    public static final DeferredRegister<EntityType<?>> REGISTER_ENTITIES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, MODID);
    public static final DeferredRegister<SoundEvent> REGISTER_SOUNDS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, MODID);

    public static final DeferredRegister<CreativeModeTab> REGISTER_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public SantasToys(IEventBus bus) {
        BlockHandler.initializeBlocks();
        ItemHandler.initializeItems();
        EntityHandler.initializeEntities();

        REGISTER_BLOCKS.register(bus);
        REGISTER_BLOCK_ENTITY_TYPES.register(bus);
        REGISTER_ITEMS.register(bus);
        REGISTER_ENTITIES.register(bus);
        REGISTER_SOUNDS.register(bus);
        buildTab();
        REGISTER_TAB.register(bus);

        bus.addListener(SantasToys::generateData);

        NeoForge.EVENT_BUS.register(new GenericEventHandler());
    }

    public void buildTab() {
        REGISTER_TAB.register(MODID, () -> CreativeModeTab.builder()
          .title(Component.translatable("itemGroup." + MODID))
          .icon(() -> new ItemStack(ItemHandler.ENDER_BLASTER.get()))
          .displayItems((enabledFeatures, entries) -> entries.acceptAll(REGISTER_ITEMS.getEntries().stream().map(i -> new ItemStack(i.get())).toList()))
          .build());
    }

    public static void generateData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        boolean client = event.includeClient();
        boolean server = event.includeServer();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();

        generator.addProvider(client, new SantasToysBlockStatesProvider(packOutput, MODID, fileHelper));
        generator.addProvider(client, new SantasToysItemModelsProvider(packOutput, MODID, fileHelper));
        SantasToysBlockTagsProvider blockTagsProvider = new SantasToysBlockTagsProvider(packOutput, lookupProvider, MODID, fileHelper);
        generator.addProvider(server, blockTagsProvider);
        generator.addProvider(server, new SantasToysItemTagsProvider(packOutput, lookupProvider, blockTagsProvider.contentsGetter(), MODID, fileHelper));
        generator.addProvider(server, new SantasToysEntityTypeTagsProvider(packOutput, lookupProvider));
        generator.addProvider(server, new SantasToysRecipeProvider(packOutput));
        generator.addProvider(server, new LootTableProvider(packOutput, Collections.emptySet(), List.of(new LootTableProvider.SubProviderEntry(BlockHandler::getLootSubProvider, LootContextParamSets.BLOCK))));
    }
}

