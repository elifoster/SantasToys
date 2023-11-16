package io.github.elifoster.santastoys.items;

import io.github.elifoster.santastoys.entity.EntityEnderBlast;
import io.github.elifoster.santastoys.entity.EntityNetherStarBlast;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.RegistryObject;

import static io.github.elifoster.santastoys.SantasToys.*;

public class ItemHandler {
    public static final String NAME_MATCH = "match";
    public static final String NAME_ENDER_BLASTER = "ender_blaster";
    public static final String NAME_NETHER_BLASTER = "nether_star_blaster";
    public static final String NAME_ENDER_SHOT = "ender_blast_shot";
    public static final String NAME_NETHER_SHOT = "nether_blast_shot";

    public static RegistryObject<Item> MATCH;
    public static RegistryObject<Item> ENDER_BLASTER;
    public static RegistryObject<Item> NETHER_BLASTER;

    public static RegistryObject<EntityType<EntityEnderBlast>> ENDER_BLAST;
    public static RegistryObject<EntityType<EntityNetherStarBlast>> NETHER_BLAST;

    public static void initializeItems() {
        MATCH = REGISTER_ITEMS.register(NAME_MATCH, ItemMatch::new);
        ENDER_BLASTER = REGISTER_ITEMS.register(NAME_ENDER_BLASTER, ItemEnderBlaster::new);
        NETHER_BLASTER = REGISTER_ITEMS.register(NAME_NETHER_BLASTER, ItemNetherStarBlaster::new);

        ENDER_BLAST = REGISTER_ENTITIES.register(NAME_ENDER_SHOT, () -> EntityType.Builder.<EntityEnderBlast>of(EntityEnderBlast::new, MobCategory.MISC)
          .sized(0.25f, 0.25f)
          .clientTrackingRange(4)
          .updateInterval(10)
          .build(new ResourceLocation(MODID, NAME_ENDER_SHOT).toString()));
        NETHER_BLAST = REGISTER_ENTITIES.register(NAME_NETHER_SHOT, () -> EntityType.Builder.<EntityNetherStarBlast>of(EntityNetherStarBlast::new, MobCategory.MISC)
          .sized(0.25f, 0.25f)
          .clientTrackingRange(4)
          .updateInterval(10)
          .build(new ResourceLocation(MODID, NAME_NETHER_SHOT).toString()));
    }
}

