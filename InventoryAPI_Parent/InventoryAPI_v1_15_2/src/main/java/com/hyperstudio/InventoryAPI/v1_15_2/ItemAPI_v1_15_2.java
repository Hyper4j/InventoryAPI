package com.hyperstudio.InventoryAPI.v1_15_2;

import org.bukkit.craftbukkit.v1_15_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.server.v1_15_R1.MojangsonParser;
import net.minecraft.server.v1_15_R1.NBTTagCompound;

public class ItemAPI_v1_15_2 {
	
	public static String checkItem(Player player) {
		ItemStack item = player.getItemInHand();
		
		net.minecraft.server.v1_15_R1.ItemStack items = CraftItemStack.asNMSCopy(item);
		String jsonString = items.save(new NBTTagCompound()).toString();
		return jsonString;
	}
	
	public static String getString(ItemStack item) {
		
		net.minecraft.server.v1_15_R1.ItemStack items = CraftItemStack.asNMSCopy(item);
		String jsonString = items.save(new NBTTagCompound()).toString();
		
		return jsonString;
	}
	
	public static ItemStack parseItem(String metadata) {
		net.minecraft.server.v1_15_R1.ItemStack itemStack = null;
		try {
			itemStack = net.minecraft.server.v1_15_R1.ItemStack.a(MojangsonParser.parse(metadata));
		} catch (CommandSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ItemStack deserializedItemStack = CraftItemStack.asBukkitCopy(itemStack);
		//ItemMeta itemmeta = metadata instanceof ItemMeta ? (ItemMeta) metadata : null;
		return deserializedItemStack;
	}
	
}
