package io.github.elifoster.santastoys.datagen;

import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;

import java.util.concurrent.CompletableFuture;

import static io.github.elifoster.santastoys.blocks.BlockHandler.*;
import static io.github.elifoster.santastoys.items.ItemHandler.*;

public class SantasToysRecipeProvider extends RecipeProvider {
    private static final String UNLOCK_RIGHT_AWAY = "unlock_right_away";

    public SantasToysRecipeProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void buildRecipes(RecipeOutput output) {
        // TODO: More usage of tags
        // TODO: recipe book achievements/unlockedBy

        /*
        Blocks
         */
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HEAVY_LIGHT_ITEM.get())
          .pattern(" X ")
          .pattern("XYX")
          .pattern(" X ")
          .define('X', Tags.Items.INGOTS_IRON)
          .define('Y', Items.GLOWSTONE)
          .unlockedBy(UNLOCK_RIGHT_AWAY, PlayerTrigger.TriggerInstance.tick())
          .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPICED_SAND_ITEM.get())
          .pattern("///")
          .pattern("/#/")
          .pattern("///")
          .define('/', Items.BLAZE_ROD)
          .define('#', Items.SAND)
          .unlockedBy(UNLOCK_RIGHT_AWAY, PlayerTrigger.TriggerInstance.tick())
          .save(output);

        /*
        Items
         */

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, MATCH.get())
          .pattern("X")
          .pattern("Z")
          .define('X', Items.FLINT)
          .define('Z', Items.STICK)
          .unlockedBy(UNLOCK_RIGHT_AWAY, PlayerTrigger.TriggerInstance.tick())
          .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ENDER_BLASTER.get())
          .pattern("XFZ")
          .pattern("XGE")
          .pattern("YYX")
          .define('X', Items.IRON_INGOT)
          .define('Z', Items.DIAMOND)
          .define('E', Items.ENDER_PEARL)
          .define('F', Blocks.STICKY_PISTON)
          .define('G', Blocks.STONE_BUTTON)
          .define('Y', Blocks.OBSIDIAN)
          .unlockedBy(UNLOCK_RIGHT_AWAY, PlayerTrigger.TriggerInstance.tick())
          .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, NETHER_BLASTER.get())
          .pattern(" X ")
          .pattern("XZX")
          .pattern(" X ")
          .define('X', Items.NETHER_STAR)
          .define('Z', ENDER_BLASTER.get())
          .unlockedBy(UNLOCK_RIGHT_AWAY, PlayerTrigger.TriggerInstance.tick())
          .save(output);
    }
}
