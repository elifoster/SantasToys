package santa.toys.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import santa.toys.Config;

public class ItemHandler {
    public static Item match;
    public static Item enderBlaster;
    public static Item netherBlaster;

    public static void initializeItems() {
        if (Config.enableMatch) {
            match = new ItemMatch();
        }

        if (Config.enableEnderBlaster) {
            enderBlaster = new ItemEnderBlaster();
            if (Config.enableNetherBlaster) {
                netherBlaster = new ItemNetherStarBlaster();
            }
        }
    }

    public static void registerItems() {
        if (Config.enableMatch) {
            GameRegistry.registerItem(match, ItemInfo.MATCH_KEY);
        }
        if (Config.enableEnderBlaster) {
            GameRegistry.registerItem(enderBlaster, ItemInfo.ENDER_KEY);
            if (Config.enableNetherBlaster) {
                GameRegistry.registerItem(netherBlaster, ItemInfo.NETHER_KEY);
            }
        }
    }

    public static void addRecipes(){
        if (Config.enableMatch) {
            GameRegistry.addRecipe(new ItemStack(match, 1),
              "X",
              "Z",
              'X', Items.flint,
              'Z', Items.stick);
        }

        if (Config.enableEnderBlaster) {
            GameRegistry.addRecipe(new ItemStack(enderBlaster, 1),
              "XFZ",
              "XGE",
              "YYX",
              'X', Items.iron_ingot,
              'Z', Items.diamond,
              'E', Items.ender_pearl,
              'F', Blocks.sticky_piston,
              'G', Blocks.stone_button,
              'Y', Blocks.obsidian);
            if (Config.enableNetherBlaster) {
                GameRegistry.addRecipe(new ItemStack(netherBlaster, 1),
                  " X ",
                  "XZX",
                  " X ",
                  'X', Items.nether_star,
                  'Z', enderBlaster);
            }
        }
    }
}

