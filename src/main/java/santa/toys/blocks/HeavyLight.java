package santa.toys.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import santa.toys.SantasToys;

public class HeavyLight extends BlockFalling {
    public HeavyLight() {
        super(Material.iron);
        this.setBlockName(BlockInfo.HEAVY_UNLOCALIZED_NAME);
        this.setCreativeTab(SantasToys.tabSantasToys);
        this.setHardness(0.6F);
        this.setResistance(1.0F);
        this.setStepSound(Block.soundTypeMetal);
        this.setLightLevel(1.0F);
        this.setBlockTextureName("minecraft:glowstone");
    }
}
