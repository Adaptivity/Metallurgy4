package com.teammetallurgy.metallurgy;

import java.util.ArrayList;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import com.teammetallurgy.metallurgy.handlers.ConfigHandler;
import com.teammetallurgy.metallurgy.items.ItemDrawer;
import com.teammetallurgy.metallurgy.items.ItemFeritilizer;
import com.teammetallurgy.metallurgy.items.ItemTar;
import com.teammetallurgy.metallurgy.metals.MetalItem;
import com.teammetallurgy.metallurgy.metals.VanillaMetals;

import cpw.mods.fml.common.registry.GameRegistry;

public class ItemList
{
    public static Item drawer;
    public static Item fertilizer;
    public static Item tar;
    public static MetalItem tabItem;
    public static boolean drawerEnabled = true;
    public static boolean fertilizerEnabled = true;
    public static boolean tarEnabled = true;

    public static void init()
    {
        VanillaMetals.initItems();

        String itemDrawerName = "drawer";

        drawerEnabled = ConfigHandler.itemEnabled(itemDrawerName);
        fertilizerEnabled = ConfigHandler.itemEnabled("fertilizer");
        tarEnabled = ConfigHandler.itemEnabled("tar");

        if (drawerEnabled)
        {

            ItemList.drawer = new ItemDrawer().setUnlocalizedName("metallurgy." + itemDrawerName);

            ItemList.registerItem(ItemList.drawer, itemDrawerName);
        }

        if (fertilizerEnabled)
        {
            ItemList.fertilizer = new ItemFeritilizer();
            ItemList.registerItem(ItemList.fertilizer, "fertilizer");
        }

        if (tarEnabled)
        {
            ItemList.tar = new ItemTar();
            ItemList.registerItem(ItemList.tar, "tar");
        }

        ItemList.tabItem = new MetalItem("tab.item");
        ItemList.registerItem(ItemList.tabItem, "tab.item");
        ItemList.tabItem.setCreativeTab(null);
        ItemList.tabItem.addSubItem(0, "tab3", 0, "metallurgy:nether/ceruclase_ingot");
        ItemList.tabItem.addSubItem(1, "tab4", 0, "metallurgy:base/damascus_steel_sword");
        ItemList.tabItem.addSubItem(2, "tab5", 0, "metallurgy:fantasy/tartarite_helmet");

    }

    public static void addRecipes()
    {
        if (fertilizerEnabled)
        {
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ItemList.fertilizer, 8), "dustPhosphorus", "dustSaltpeter", "dustPotash", "dustMagnesium"));
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ItemList.fertilizer, 6), "dustSaltpeter", "dustPotash", "dustMagnesium"));
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ItemList.fertilizer, 6), "dustPhosphorus", "dustPotash", "dustMagnesium"));
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ItemList.fertilizer, 6), "dustPhosphorus", "dustSaltpeter", "dustMagnesium"));
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ItemList.fertilizer, 6), "dustPhosphorus", "dustSaltpeter", "dustPotash"));
        }

        if (tarEnabled)
        {
            ItemStack tarStack = new ItemStack(ItemList.tar);
            OreDictionary.registerOre("slimeball", tarStack.copy());

            ArrayList<ItemStack> bitumenDusts = OreDictionary.getOres("dustBitumen");
            if (bitumenDusts.size() > 0)
            {
                ItemStack bitumen = bitumenDusts.get(0);

                GameRegistry.addSmelting(bitumen.copy(), tarStack.copy(), 0.7F);
            }
        }
    }

    public static void registerItem(Item item, String itemName)
    {
        GameRegistry.registerItem(item, itemName);
    }
}
