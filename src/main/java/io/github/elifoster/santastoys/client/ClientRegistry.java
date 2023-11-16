package io.github.elifoster.santastoys.client;

import io.github.elifoster.santastoys.entity.EntityHandler;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

import static io.github.elifoster.santastoys.SantasToys.MODID;

@Mod.EventBusSubscriber(modid = MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientRegistry {
    @SubscribeEvent
    public static void onRegisterRenders(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityHandler.ENDER_BLAST.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EntityHandler.NETHER_BLAST.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EntityHandler.THROWN_BRICK.get(), ThrownItemRenderer::new);
    }
}
