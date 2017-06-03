package santa.toys.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import santa.toys.SantasToys;
import santa.toys.blocks.tileentity.TileEntityMuffler;

public class Muffler /*extends Block*/ {
    /*
    public Muffler() {
        super(Material.cloth);
        this.setCreativeTab(SantasToys.tabSantasToys);
        this.setHardness(0.5F);
        this.setBlockTextureName("minecraft:wool");
        this.setBlockName(BlockInfo.MUFFLER_UNLOCALIZED_NAME);
    }

    public boolean hasTileEntity(int meta) {
        return true;
    }

    public TileEntity createTileEntity(World world, int meta){
        return new TileEntityMuffler();
    }
    */
}
