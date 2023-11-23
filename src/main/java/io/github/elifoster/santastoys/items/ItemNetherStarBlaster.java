package io.github.elifoster.santastoys.items;

import io.github.elifoster.santastoys.entity.EntityNetherStarBlast;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.Tags;

public class ItemNetherStarBlaster extends GenericBlaster {
    @Override
    public boolean canFire(Level level, Player player, InteractionHand hand) {
        return player.getInventory().contains(Tags.Items.NETHER_STARS);
    }

    @Override
    public ThrowableItemProjectile createProjectile(Level level, Player player) {
        return new EntityNetherStarBlast(level, player);
    }

    @Override
    public void afterFire(Level level, Player player, InteractionHand hand) {
        // do nothing
    }
}

