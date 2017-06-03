package santa.toys.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import santa.toys.Config;

public class BlockHandler {
    public static Block giveADamn;
    public static Block glowstone;
    public static Block sandSpiced;

    public static void initializeBlocks() {
        if (Config.enableGiveADamn) {
            giveADamn = new GiveADamn();
        }

        if (Config.enableGlowstone) {
            glowstone = new HeavyLight();
        }

        if (Config.enableSpicedSand) {
            sandSpiced = new SpicedSand();
        }
    }

    public static void registerBlocks() {
        if (Config.enableGiveADamn) {
            GameRegistry.registerBlock(giveADamn, BlockInfo.DAMN_KEY);
        }

        if (Config.enableGlowstone) {
            GameRegistry.registerBlock(glowstone, BlockInfo.HEAVY_KEY);
        }

        if (Config.enableSpicedSand) {
            GameRegistry.registerBlock(sandSpiced, BlockInfo.SAND_KEY);
        }
    }


    public static void addRecipes(){
        if (Config.enableGiveADamn) {
            GameRegistry.addRecipe(new ItemStack(giveADamn, 1), new Object[] {
              "X ",
              "ZX",
              Character.valueOf('X'), Items.rotten_flesh, Character.valueOf('Z'), Blocks.dirt
            });
        }

        if (Config.enableGlowstone) {
            GameRegistry.addRecipe(new ShapedOreRecipe(glowstone, true, new Object[] {
              " X ",
              "XYX",
              " X ",
              Character.valueOf('X'), "ingotIron", Character.valueOf('Y'), Blocks.glowstone
            }));
        }

        if (Config.enableSpicedSand) {
            GameRegistry.addRecipe(new ItemStack(sandSpiced, 1), new Object[] {
              "///",
              "/#/",
              "///",
              Character.valueOf('/'), Items.blaze_rod, Character.valueOf('#'), Blocks.sand
            });
        }
    }
}