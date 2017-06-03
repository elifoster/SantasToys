package santa.toys.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import santa.toys.SantasToys;
import santa.toys.entity.EntityEnderBlast;

import java.util.List;

public class ItemEnderBlaster extends Item {
    public ItemEnderBlaster() {
        super();
        this.maxStackSize = 1;
        this.setCreativeTab(SantasToys.tabSantasToys);
        this.setUnlocalizedName(ItemInfo.ENDER_UNLOCALIZED_NAME);
        this.setTextureName("santastoys:enderblaster");
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (player.capabilities.isCreativeMode || player.inventory.hasItem(Items.ender_pearl)) {
            world.spawnEntityInWorld(new EntityEnderBlast(world, player));
        }
        return stack;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4){
        list.add("Used to kill cows because cows.");
    }
}
