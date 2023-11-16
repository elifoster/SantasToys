package io.github.elifoster.santastoys.entity;

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
import net.minecraft.world.phys.EntityHitResult;

import javax.annotation.Nonnull;

public class EntityEnderBlast extends ThrowableItemProjectile {
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

        if (level().isClientSide()) {
            for (int i = 0; i < 32; ++i) {
                level().addParticle(ParticleTypes.PORTAL, getX(), getY() + random.nextDouble() * 2D, getZ(), random.nextGaussian(), 0D, random.nextGaussian());
            }
        } else {
            discard();
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
