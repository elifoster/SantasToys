package santa.toys;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import santa.toys.blocks.BlockHandler;
import santa.toys.items.ItemHandler;
import santa.toys.proxies.CommonProxy;
import santa.toys.world.SpicedSandGenerator;

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

