package io.github.elifoster.santastoys.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import io.github.elifoster.santastoys.Config;

import io.github.elifoster.santastoys.SantasToys;

public class BlockSpicedSand extends Block {
    private static PotionEffect slowness = new PotionEffect(Potion.moveSlowdown.id, 20, 1);
    private static float damage = (float) Config.damageDealtBySand;
    public BlockSpicedSand() {
        super(Material.sand);
        this.setBlockName(BlockInfo.SAND_UNLOCALIZED_NAME);
        this.setCreativeTab(SantasToys.tabSantasToys);
        this.setStepSound(Block.soundTypeSand);
        this.setHardness(0.6F);
        this.setBlockTextureName("santastoys:spicedsand");
    }

    @Override
    public boolean isCollidable() {
        return true;
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        damageEntity(entity, DamageSource.cactus, damage, world, x, y, z);
        poisonEntity(entity, slowness, world);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float hitX, float hitY, float hitZ) {
        if (player.getCurrentEquippedItem() == null) {
            damageEntity(player, DamageSource.cactus, damage, world, x, y, z);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Causes damage to the given entity.
     * @param entity The entity to damage.
     * @param damageSource The damage source to damage them with.
     * @param damage The amount of damage to cause.
     * @param world The world object.
     * @param x The X coordinate to spawn smoke particles at.
     * @param y The Y coordinate to spawn smoke particles at.
     * @param z The Z coordinate to spawn smoke particles at.
     */
    private void damageEntity(Entity entity, DamageSource damageSource, float damage, World world, int x, int y, int z) {
        if (entity instanceof EntityLivingBase) {
            if (!world.isRemote) {
                entity.setFire(1);
                entity.attackEntityFrom(damageSource, damage);
            }
            world.spawnParticle("largesmoke", x, y, z, 0.0D, 0.0D, 0.0D);
        }
    }

    /**
     * Poisons the given entity.
     * @param entity The entity to poison.
     * @param potion The potion effect to give them.
     * @param world The world object.
     */
    private void poisonEntity(Entity entity, PotionEffect potion, World world) {
        if (entity instanceof EntityLivingBase && !world.isRemote) {
            ((EntityLivingBase) entity).addPotionEffect(potion);
        }
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        float f = 0.0625F;
        return AxisAlignedBB.getBoundingBox((double) ((float) x + f), (double) y,
          (double) ((float) z + f), (double) ((float) (x + 1) - f), (double) ((float) (y + 1) - f),
          (double) ((float) (z + 1) - f));
    }
}
