package io.github.elifoster.santastoys;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.github.elifoster.santastoys.items.ItemHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

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