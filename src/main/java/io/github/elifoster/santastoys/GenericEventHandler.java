package io.github.elifoster.santastoys;

import io.github.elifoster.santastoys.entity.ThrownBrickEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

public class GenericEventHandler {
    @SubscribeEvent
    public void throwBrick(PlayerInteractEvent.RightClickItem event) {
        Player thrower = event.getEntity();
        Level level = thrower.level();
        ItemStack stack = event.getItemStack();

        if (stack.getItem() == Items.BRICK) {
            if (!level.isClientSide()) {
                ThrowableItemProjectile brickProjectile = new ThrownBrickEntity(thrower, level);
                brickProjectile.shootFromRotation(thrower, thrower.getXRot(), thrower.getYRot(), 0, 1.5f, 1);
                level.addFreshEntity(brickProjectile);
                stack.shrink(1);
            }
            level.playSound(thrower, thrower.blockPosition(), SoundEvents.SNOWBALL_THROW, SoundSource.PLAYERS, 0.5f, 0.4f / (level.random.nextFloat() * 0.4f + 0.8f));
            thrower.swing(event.getHand());
        }
    }
}
