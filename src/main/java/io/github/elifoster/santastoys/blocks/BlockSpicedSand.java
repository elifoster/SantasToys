package io.github.elifoster.santastoys.blocks;

import io.github.elifoster.santastoys.Config;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BlockSpicedSand extends Block {
    private static final VoxelShape COLLISION_SHAPE = Block.box(1, 0, 1, 15, 15, 15);

    BlockSpicedSand() {
        super(Properties.of()
          .strength(0.6F)
          .sound(SoundType.SAND)
          .speedFactor(0.4f));
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return COLLISION_SHAPE;
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        damageEntity(entity, level, pos);
        super.entityInside(state, level, pos, entity);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        return touchWithEmptyHand(player, hand, level, pos) ? InteractionResult.SUCCESS : super.use(state, level, pos, player, hand, result);
    }

    @Override
    public void attack(BlockState state, Level level, BlockPos pos, Player player) {
        touchWithEmptyHand(player, InteractionHand.MAIN_HAND, level, pos);
    }

    /**
     * Checks if the hand touching the block is empty, and if so, damages the player and sets them on fire.
     * @param player the player
     * @param hand the hand the player used to touch the block
     * @param level the level
     * @param pos the location of the block
     * @return true if the entity was damaged, otherwise false
     */
    private boolean touchWithEmptyHand(Player player, InteractionHand hand, Level level, BlockPos pos) {
        if (player.getItemInHand(hand).isEmpty()) {
            damageEntity(player, level, pos);
            return true;
        }
        return false;
    }

    /**
     * Sets the entity on fire, causes them damage, and adds smoke particles
     * @param entity the entity to damage
     * @param level the level
     * @param pos the location of the block
     */
    private void damageEntity(Entity entity, Level level, BlockPos pos) {
        if (entity instanceof LivingEntity && !entity.fireImmune()) {
            if (!level.isClientSide()) {
                entity.setSecondsOnFire(1);
                entity.hurt(entity.damageSources().cactus(), Config.damageDealtBySand.get());
            }
            level.addParticle(ParticleTypes.SMOKE, pos.getX(), pos.getY(), pos.getZ(), 0, 0, 0);
        }
    }
}
