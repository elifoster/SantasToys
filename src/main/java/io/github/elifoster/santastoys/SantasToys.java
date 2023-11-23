package io.github.elifoster.santastoys;

import io.github.elifoster.santastoys.blocks.BlockHandler;
import io.github.elifoster.santastoys.datagen.*;
import io.github.elifoster.santastoys.entity.EntityHandler;
import io.github.elifoster.santastoys.items.ItemHandler;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.ForgeRegistries;

import java.util.concurrent.CompletableFuture;

import static io.github.elifoster.santastoys.SantasToys.MODID;

@Mod(MODID)
public class SantasToys {
    public static final String MODID = "santastoys";

    public static final DeferredRegister<Block> REGISTER_BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<Item> REGISTER_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<EntityType<?>> REGISTER_ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MODID);

    public static final DeferredRegister<CreativeModeTab> REGISTER_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public SantasToys() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        BlockHandler.initializeBlocks();
        ItemHandler.initializeItems();
        EntityHandler.initializeEntities();

        REGISTER_BLOCKS.register(bus);
        REGISTER_ITEMS.register(bus);
        REGISTER_ENTITIES.register(bus);
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
        generator.addProvider(server, new SantasToysRecipeProvider(packOutput, lookupProvider));
    }
}

