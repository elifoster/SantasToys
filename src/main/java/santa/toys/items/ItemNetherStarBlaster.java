package santa.toys.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import santa.toys.SantasToys;
import santa.toys.entity.EntityNetherStarBlast;

import java.util.List;

public class ItemNetherStarBlaster extends Item {
    public ItemNetherStarBlaster() {
        super();
        this.maxStackSize = 1;
        this.setCreativeTab(SantasToys.tabSantasToys);
        this.setUnlocalizedName(ItemInfo.NETHER_UNLOCALIZED_NAME);
        this.setTextureName("santastoys:netherblaster");
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if(player.capabilities.isCreativeMode || player.inventory.hasItem(Items.nether_star)) {
            world.spawnEntityInWorld(new EntityNetherStarBlast(world, player));
        }
        return stack;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4){
        list.add("Similar to the Ender Blaster, but 2 op 4 cows");
    }
}

