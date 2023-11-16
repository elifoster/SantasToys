package io.github.elifoster.santastoys.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

import javax.annotation.Nonnull;

public class EntityNetherStarBlast extends ThrowableItemProjectile {
    public EntityNetherStarBlast(Level level, Player player) {
        super(EntityHandler.NETHER_BLAST.get(), player, level);
    }

    public EntityNetherStarBlast(Level level, double x, double y, double z) {
        super(EntityHandler.NETHER_BLAST.get(), x, y, z, level);
    }

    public EntityNetherStarBlast(EntityType<? extends EntityNetherStarBlast> entityEntityType, Level level) {
        super(entityEntityType, level);
    }

    @Override
    protected void onHitEntity(@Nonnull EntityHitResult result) {
        super.onHitEntity(result);
        Entity entityHit = result.getEntity();
        float damage = entityHit instanceof WitherBoss ? 10 : 5;
        entityHit.hurt(entityHit.damageSources().thrown(this, getOwner()), damage);
        explode();
    }

    @Override
    protected void onHitBlock(@Nonnull BlockHitResult result) {
        super.onHitBlock(result);
        explode();
    }

    /**
     * Explodes at the current position with an explosion radius of 1.
     */
    private void explode() {
        level().explode(this, getX(), getY(), getZ(), 1, Level.ExplosionInteraction.BLOCK);
    }

    @Override
    protected void onHit(@Nonnull HitResult result) {
        super.onHit(result);
        if (!level().isClientSide()) {
            discard();
        }
    }

    @Override
    protected float getGravity() {
        return 0.01F;
    }

    @Override
    protected Item getDefaultItem() {
        return Items.NETHER_STAR;
    }
}
