package santa.toys;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import santa.toys.items.ItemHandler;

public class SantasToysTab extends CreativeTabs {
    SantasToysTab() {
        super("Santa's Toys");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getTabIconItem() {
        return ItemHandler.netherBlaster;
    }

    @Override
    public boolean hasSearchBar() {
        return true;
    }
}