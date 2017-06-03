package santa.toys.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import santa.toys.SantasToys;
import santa.toys.entity.EntitySapling;

import java.util.List;

public class SaplingBlaster extends Item {
/*
    public SaplingBlaster() {
        super();
        this.maxStackSize = 1;
        this.setCreativeTab(SantasToys.tabSantasToys);
        this.setUnlocalizedName(ItemInfo.SAPLING_UNLOCALIZED_NAME);
        this.setTextureName("santastoys:saplingblaster");
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (par3EntityPlayer.capabilities.isCreativeMode || par3EntityPlayer.inventory.hasItem(Item.getItemFromBlock(Blocks.sapling))) {
            par2World.spawnEntityInWorld(new EntitySapling(par2World, par3EntityPlayer));
        }
        return par1ItemStack;
    }

    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        par3List.add("Used to kill cows because cows.");
    }
*/
}