package santa.toys.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import santa.toys.SantasToys;

import java.util.List;

public class ItemMatch extends Item {
    public ItemMatch() {
        super();
        this.maxStackSize = 32;
        this.setCreativeTab(SantasToys.tabSantasToys);
        this.setUnlocalizedName(ItemInfo.MATCH_UNLOCALIZED_NAME);
        this.setTextureName("santastoys:match");
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int dir, float par8, float par9, float par10) {
        if (world.isRemote) {
            return true;
        } else {
            if (dir == 0) {
                --y;
            }

            if (dir == 1) {
                ++y;
            }

            if (dir == 2) {
                --z;
            }

            if (dir == 3) {
                ++z;
            }

            if (dir == 4) {
                --x;
            }

            if (dir == 5) {
                ++x;
            }

            if (!player.canPlayerEdit(x, y, z, dir, stack)) {
                return false;
            } else {
                if (world.isAirBlock(x, y, z)) {
                    world.playSoundEffect((double) x + 0.5D, (double) y + 0.5D, (double) z + 0.5D,
                      "fire.ignite", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
                    world.setBlock(x, y, z, Blocks.fire);
                }

                if (!player.capabilities.isCreativeMode) {
                    --stack.stackSize;
                }

                return true;
            }
        }
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4){
        list.add("Fire Charges are for losers.");
    }
}