package io.github.elifoster.santastoys.items;

import io.github.elifoster.santastoys.SantasToys;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.function.Supplier;

import static io.github.elifoster.santastoys.SantasToys.REGISTER_ITEMS;
import static io.github.elifoster.santastoys.SantasToys.REGISTER_SOUNDS;

public class ItemHandler {
    public static final String NAME_MATCH = "match";
    public static final String NAME_ENDER_BLASTER = "ender_blaster";
    public static final String NAME_NETHER_BLASTER = "nether_star_blaster";

    public static DeferredHolder<Item, ItemMatch> MATCH;
    public static DeferredHolder<Item, ItemEnderBlaster> ENDER_BLASTER;
    public static DeferredHolder<Item, ItemNetherStarBlaster> NETHER_BLASTER;

    public static Supplier<SoundEvent> BLASTER_SOUND;

    public static void initializeItems() {
        MATCH = REGISTER_ITEMS.register(NAME_MATCH, ItemMatch::new);
        ENDER_BLASTER = REGISTER_ITEMS.register(NAME_ENDER_BLASTER, ItemEnderBlaster::new);
        NETHER_BLASTER = REGISTER_ITEMS.register(NAME_NETHER_BLASTER, ItemNetherStarBlaster::new);

        BLASTER_SOUND = REGISTER_SOUNDS.register("blaster_shot", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(SantasToys.MODID, "blaster_shot")));
    }
}

