package santa.toys.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityEnderBlast extends EntityThrowable {
    public EntityEnderBlast(World world){
        super(world);
        this.setSize(0.25F, 0.25F);
    }

    public EntityEnderBlast(World world, EntityPlayer player){
        super(world, player);
    }

    public EntityEnderBlast(World world, double x, double y, double z){
        super(world, x, y, z);
    }

    @Override
    protected void onImpact(MovingObjectPosition movingobjpos){
        if (movingobjpos.entityHit != null) {
            byte b0 = 5;

            if (movingobjpos.entityHit instanceof EntityEnderman ||
              movingobjpos.entityHit instanceof EntityDragon) {
                b0 = 0;
            }

            movingobjpos.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this,
              this.getThrower()), (float) b0);

            for (int i = 0; i < 32; ++i) {
                this.worldObj.spawnParticle("portal", this.posX,
                  this.posY + this.rand.nextDouble() * 2.0D, this.posZ, this.rand.nextGaussian(),
                  0.0D, this.rand.nextGaussian());
            }
            if (!this.worldObj.isRemote) {
                this.setDead();
            }
        }
    }

    @Override
    protected float getGravityVelocity() {
        return 0.01F;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float getShadowSize() {
        return 0.2F;
    }
}
