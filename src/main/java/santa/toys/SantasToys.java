package santa.toys;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import santa.toys.blocks.BlockHandler;
import santa.toys.items.ItemHandler;
import santa.toys.proxies.Client;
import santa.toys.proxies.Common;
import santa.toys.world.SantasToysGenerator;

@Mod(modid = "santastoys", name = "Santa's Toys", version = "0.4")
public class SantasToys {

    @Mod.Instance("santastoys")
    public static SantasToys instance;

    @SidedProxy(clientSide="santa.toys.proxies.Client", serverSide="santa.toys.proxies.Common")
    public static Common proxy;
    public static CreativeTabs tabSantasToys = new SantasToysTab("Santa's Toys");

    @Mod.EventHandler
    void preInit(FMLPreInitializationEvent event) {
        Config.load(event);

        BlockHandler.initializeBlocks();
        BlockHandler.registerBlocks();
        BlockHandler.addRecipes();

        GameRegistry.registerWorldGenerator(new SantasToysGenerator(), 1);
    }

    @Mod.EventHandler
    void init(FMLInitializationEvent event) {
        ItemHandler.initializeItems();
        ItemHandler.registerItems();
        ItemHandler.addRecipes();

        proxy.initRenderers();
    }

    /* TODO
    Make the dispenser shoot the entities rather than the item itself (Will come in 0.5)
    Powered Carts, takes solid fuel to go faster than normal carts. same speed as normal carts if it has no fuel.(0.5)
     */

}

