package io.github.elifoster.santastoys.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import java.util.function.Supplier;

import static io.github.elifoster.santastoys.SantasToys.MODID;
import static io.github.elifoster.santastoys.SantasToys.REGISTER_ENTITIES;

public class EntityHandler {
    public static final String NAME_ENDER_SHOT = "ender_blast_shot";
    public static final String NAME_NETHER_SHOT = "nether_blast_shot";
    public static final String NAME_THROWN_BRICK = "thrown_brick";
    public static Supplier<EntityType<EntityEnderBlast>> ENDER_BLAST;
    public static Supplier<EntityType<EntityNetherStarBlast>> NETHER_BLAST;
    public static Supplier<EntityType<ThrownBrickEntity>> THROWN_BRICK;

    public static void initializeEntities() {
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

        THROWN_BRICK = REGISTER_ENTITIES.register(NAME_THROWN_BRICK, () -> EntityType.Builder.<ThrownBrickEntity>of(ThrownBrickEntity::new, MobCategory.MISC)
          .sized(0.25f, 0.25f)
          .clientTrackingRange(4)
          .updateInterval(10)
          .build(new ResourceLocation(MODID, NAME_THROWN_BRICK).toString()));
    }
}
