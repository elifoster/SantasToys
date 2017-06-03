package santa.toys.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntitySapling extends EntityThrowable {
    public EntitySapling(World world) {
        super(world);
        this.setSize(0.25F, 0.25F);
    }

    public EntitySapling(World world, EntityPlayer player) {
        super(world, player);
    }

    public EntitySapling(World world, double x, double y, double z) {
        super(world, x, y, z);
    }

    @Override
    protected void onImpact(MovingObjectPosition movingobjpos) {
        // TODO
    }

    @Override
    protected float getGravityVelocity() {
        return 0.01F;
    }

    @SideOnly(Side.CLIENT)
    public float getShadowSize() {
        return 0.2F;
    }
}
