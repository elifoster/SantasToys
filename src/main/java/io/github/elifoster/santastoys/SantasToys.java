package io.github.elifoster.santastoys;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import io.github.elifoster.santastoys.blocks.BlockHandler;
import io.github.elifoster.santastoys.items.ItemHandler;
import io.github.elifoster.santastoys.proxies.CommonProxy;
import io.github.elifoster.santastoys.world.SpicedSandGenerator;
import net.minecraft.creativetab.CreativeTabs;

@Mod(modid = "santastoys", name = "Santa's Toys", version = "0.4")
public class SantasToys {
    @SidedProxy(clientSide="santa.toys.proxies.ClientProxy", serverSide="santa.toys.proxies.CommonProxy")
    public static CommonProxy proxy;
    public static CreativeTabs tabSantasToys = new SantasToysTab();

    @Mod.EventHandler
    void preInit(FMLPreInitializationEvent event) {
        Config.load(event);

        BlockHandler.initializeBlocks();
        BlockHandler.registerBlocks();
        BlockHandler.addRecipes();

        GameRegistry.registerWorldGenerator(new SpicedSandGenerator(), 1);
    }

    @Mod.EventHandler
    void init(FMLInitializationEvent event) {
        ItemHandler.initializeItems();
        ItemHandler.registerItems();
        ItemHandler.addRecipes();

        proxy.initRenderers();
    }
}

