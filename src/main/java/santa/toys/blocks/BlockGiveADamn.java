package santa.toys.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

import santa.toys.SantasToys;

public class BlockGiveADamn extends BlockRotatedPillar {
    public BlockGiveADamn() {
        super(Material.ground);
        this.setBlockName(BlockInfo.DAMN_UNLOCALIZED_NAME);
        this.setCreativeTab(SantasToys.tabSantasToys);
        this.setHardness(1.0F);
        this.setResistance(1.0F);
        this.setStepSound(Block.soundTypeSand);
    }

    @SideOnly(Side.CLIENT)
    public static IIcon frontIcon;

    @SideOnly(Side.CLIENT)
    public static IIcon elseIcon;

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister ir) {
        frontIcon = ir.registerIcon("santastoys:damn_front");
        elseIcon = ir.registerIcon("santastoys:damn_else");
    }

    @Override
    protected IIcon getSideIcon(int i) {
        return elseIcon;
    }

    @Override
    protected IIcon getTopIcon(int i) {
        return frontIcon;
    }
}
