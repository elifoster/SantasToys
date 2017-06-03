package io.github.elifoster.santastoys.proxies;

import cpw.mods.fml.client.registry.RenderingRegistry;
import io.github.elifoster.santastoys.entity.EntityNetherStarBlast;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.init.Items;
import io.github.elifoster.santastoys.entity.EntityEnderBlast;

public class ClientProxy extends CommonProxy {
    @Override
    public void initRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(EntityEnderBlast.class, new RenderSnowball(Items.ender_pearl));
        RenderingRegistry.registerEntityRenderingHandler(EntityNetherStarBlast.class, new RenderSnowball(Items.nether_star));
    }
}
