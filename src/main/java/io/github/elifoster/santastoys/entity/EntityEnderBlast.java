package io.github.elifoster.santastoys.entity;

import io.github.elifoster.santastoys.util.MiscHelper;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

import javax.annotation.Nonnull;

public class EntityEnderBlast extends ThrowableItemProjectile {
    private static final byte EVENT_ID = 2;

    public EntityEnderBlast(Level level, Player player) {
        super(EntityHandler.ENDER_BLAST.get(), player, level);
    }

    public EntityEnderBlast(Level level, double x, double y, double z) {
        super(EntityHandler.ENDER_BLAST.get(), x, y, z, level);
    }

    public EntityEnderBlast(EntityType<? extends EntityEnderBlast> entityEntityType, Level level) {
        super(entityEntityType, level);
    }

    @Override
    protected void onHitEntity(@Nonnull EntityHitResult result) {
        Entity entity = result.getEntity();
        float damage = (entity instanceof EnderMan || entity instanceof EnderDragon || entity instanceof Endermite) ? 0 : 5;
        entity.hurt(entity.damageSources().thrown(this, getOwner()), damage);

        if (!level().isClientSide()) {
            level().broadcastEntityEvent(this, EVENT_ID);
            discard();
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult hitResult) {
        super.onHitBlock(hitResult);
        if (!level().isClientSide()) {
            level().broadcastEntityEvent(this, EVENT_ID);
            discard();
        }
    }

    @Override
    public void handleEntityEvent(byte id) {
        if (id == EVENT_ID) {
            for (int i = 0; i < 32; ++i) {
                level().addParticle(ParticleTypes.PORTAL, getX(), getY(), getZ(), MiscHelper.entityBreakVelocity(), MiscHelper.entityBreakVelocity(), MiscHelper.entityBreakVelocity());
            }
        }
    }

    @Override
    protected float getGravity() {
        return 0.01F;
    }

    @Override
    protected Item getDefaultItem() {
        return Items.ENDER_PEARL;
    }
}
