package io.github.elifoster.santastoys.items;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public abstract class GenericBlaster extends Item {
    GenericBlaster() {
        super(new Item.Properties()
          .stacksTo(1));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack heldItem = player.getItemInHand(hand);
        if (!level.isClientSide() && (player.isCreative() || canFire(level, player, hand))) {
            ThrowableItemProjectile blast = createProjectile(level, player);
            blast.shootFromRotation(player, player.getXRot(), player.getYRot(), 0, 2.5f, 1);
            level.addFreshEntity(blast);
            level.playSound(null, player.blockPosition(), ItemHandler.BLASTER_SOUND.get(), SoundSource.PLAYERS, 1f, 1f);
            afterFire(level, player, hand);
            return InteractionResultHolder.pass(heldItem);
        }
        return InteractionResultHolder.fail(heldItem);
    }

    /**
     * Called to check if the player meets the requirements to fire a round from the blaster.
     */
    public abstract boolean canFire(Level level, Player player, InteractionHand hand);

    /**
     * Create a new projectile entity to be fired from the blaster.
     */
    public abstract ThrowableItemProjectile createProjectile(Level level, Player player);

    /**
     * Called after the blaster has fired its round. Use this to, for example, deplete ammunition.
     */
    public abstract void afterFire(Level level, Player player, InteractionHand hand);
}
