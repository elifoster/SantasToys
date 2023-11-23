package io.github.elifoster.santastoys.entity;

import io.github.elifoster.santastoys.util.MiscHelper;
import io.github.elifoster.santastoys.util.TagsHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.neoforged.neoforge.common.Tags;

public class ThrownBrickEntity extends ThrowableItemProjectile {
    private static final byte EVENT_ID = 1;

    public ThrownBrickEntity(EntityType<? extends ThrowableItemProjectile> type, Level level) {
        super(type, level);
    }

    public ThrownBrickEntity(double x, double y, double z, Level level) {
        super(EntityHandler.THROWN_BRICK.get(), x, y, z, level);
    }

    public ThrownBrickEntity(LivingEntity thrower, Level level) {
        super(EntityHandler.THROWN_BRICK.get(), thrower, level);
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        if (!level().isClientSide()) {
            Entity victim = result.getEntity();
            if (victim instanceof Player || victim instanceof Villager) {
                double hitY = getY();
                double eyeY = victim.getEyeY();
                if (hitY >= eyeY - 0.3 && hitY <= eyeY + 0.3) {
                    ((LivingEntity) victim).addEffect(new MobEffectInstance(MobEffects.CONFUSION, 10 * 20, 1));
                }
            }
            victim.hurt(victim.damageSources().thrown(this, getOwner()), 3);
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        if (!level().isClientSide()) {
            BlockPos hitPos = result.getBlockPos();
            Block hitBlock = level().getBlockState(hitPos).getBlock();
            if (TagsHelper.isBlockTaggedAs(hitBlock, Tags.Blocks.GLASS) || TagsHelper.isBlockTaggedAs(hitBlock, Tags.Blocks.GLASS_PANES)) {
                level().destroyBlock(hitPos, false, getOwner());
            }
        }
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        if (!level().isClientSide()) {
            level().broadcastEntityEvent(this, EVENT_ID);
            discard();
        }
        level().playSound(null, blockPosition(), SoundEvents.STONE_HIT, SoundSource.NEUTRAL, 0.5f, 0.4f / (random.nextFloat() * 0.4f + 0.8f));
    }

    @Override
    public void handleEntityEvent(byte id) {
        if (id == EVENT_ID) {
            ParticleOptions particles = new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(getDefaultItem()));
            for (int i = 0; i < 8; ++i) {
                level().addParticle(particles, getX(), getY(), getZ(), MiscHelper.entityBreakVelocity(), MiscHelper.entityBreakVelocity(), MiscHelper.entityBreakVelocity());
            }
        }
    }

    @Override
    protected float getGravity() {
        return 0.1f;
    }

    @Override
    protected Item getDefaultItem() {
        return Items.BRICK;
    }
}
