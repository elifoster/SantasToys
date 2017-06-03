package io.github.elifoster.santastoys.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityNetherStarBlast extends EntityThrowable {
    private int explosionRadius = 1;

    public EntityNetherStarBlast(World world){
        super(world);
        this.setSize(0.25F, 0.25F);
    }

    public EntityNetherStarBlast(World world, EntityPlayer player){
        super(world, player);
    }

    public EntityNetherStarBlast(World world, double x, double y, double z){
        super(world, x, y, z);
    }

    @Override
    protected void onImpact(MovingObjectPosition movingobjpos){
        if (movingobjpos.entityHit != null) {
            byte b0 = 5;

            if (movingobjpos.entityHit instanceof EntityWither) {
                b0 = 10;
            }

            movingobjpos.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this,
              this.getThrower()), (float) b0);
        }
        this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ,
          (float) this.explosionRadius, true);
        this.setDead();
    }

    @Override
    protected float getGravityVelocity()
    {
        return 0.01F;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float getShadowSize()
    {
        return 15.0F;
    }
}
