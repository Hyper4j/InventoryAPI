package com.hyperstudio.InventoryAPI.v1_18_2;

import org.bukkit.craftbukkit.v1_18_R2.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.nbt.MojangsonParser;
import net.minecraft.nbt.NBTTagCompound;

public class ItemAPI_v1_18_2 {
	
	public static String checkItem(Player player) {
		ItemStack item = player.getItemInHand();
		net.minecraft.world.item.ItemStack items = CraftItemStack.asNMSCopy(item);
		String jsonString = items.b(new NBTTagCompound()).toString();
		return jsonString;
	}
	
	public static String getString(ItemStack item) {
		
		net.minecraft.world.item.ItemStack items = CraftItemStack.asNMSCopy(item);
		String jsonString = items.b(new NBTTagCompound()).toString();
		
		return jsonString;
	}
	
	public static ItemStack parseItem(String metadata) {
		net.minecraft.world.item.ItemStack itemStack = null;
			try {
				itemStack = net.minecraft.world.item.ItemStack.a(MojangsonParser.a(metadata));
			} catch (CommandSyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		ItemStack deserializedItemStack = CraftItemStack.asBukkitCopy(itemStack);
		//ItemMeta itemmeta = metadata instanceof ItemMeta ? (ItemMeta) metadata : null;
		return deserializedItemStack;
	}
	
}
