package com.hyperstudio.InventoryAPI.utils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.hyperstudio.InventoryAPI.v1_12_2.ItemAPI_v1_12_2;
import com.hyperstudio.InventoryAPI.v1_13_2.ItemAPI_v1_13_2;
import com.hyperstudio.InventoryAPI.v1_14_4.ItemAPI_v1_14_4;
import com.hyperstudio.InventoryAPI.v1_15_2.ItemAPI_v1_15_2;
import com.hyperstudio.InventoryAPI.v1_16_5.ItemAPI_v1_16_5;
import com.hyperstudio.InventoryAPI.v1_17_1.ItemAPI_v1_17_1;
import com.hyperstudio.InventoryAPI.v1_18_2.ItemAPI_v1_18_2;
import com.hyperstudio.InventoryAPI.v1_19.ItemAPI_v1_19;

/**
 * 
 * NMS Helper
 * @author Hyper4j
 *
 */
public class ItemAPI {
	
	public static String checkItem(Player player) {
		if(NMSHelper.isV1_12_2()) return ItemAPI_v1_12_2.checkItem(player);
		else if(NMSHelper.isV1_13_2()) return ItemAPI_v1_13_2.checkItem(player);
		else if(NMSHelper.isV1_14_4()) return ItemAPI_v1_14_4.checkItem(player);
		else if(NMSHelper.isV1_15_2()) return ItemAPI_v1_15_2.checkItem(player);
		else if(NMSHelper.isV1_16_5()) return ItemAPI_v1_16_5.checkItem(player);
		else if(NMSHelper.isV1_17_1()) return ItemAPI_v1_17_1.checkItem(player);
		else if(NMSHelper.isV1_18_2()) return ItemAPI_v1_18_2.checkItem(player);
		else if(NMSHelper.isV1_19()) return ItemAPI_v1_19.checkItem(player);
		else return null;
	}
	
	public static String getString(ItemStack item) {
		if(NMSHelper.isV1_12_2()) return ItemAPI_v1_12_2.getString(item);
		else if(NMSHelper.isV1_13_2()) return ItemAPI_v1_13_2.getString(item);
		else if(NMSHelper.isV1_14_4()) return ItemAPI_v1_14_4.getString(item);
		else if(NMSHelper.isV1_15_2()) return ItemAPI_v1_15_2.getString(item);
		else if(NMSHelper.isV1_16_5()) return ItemAPI_v1_16_5.getString(item);
		else if(NMSHelper.isV1_17_1()) return ItemAPI_v1_17_1.getString(item);
		else if(NMSHelper.isV1_18_2()) return ItemAPI_v1_18_2.getString(item);
		else if(NMSHelper.isV1_19()) return ItemAPI_v1_19.getString(item);
		else return null;
	}
	
	public static ItemStack parseItem(String metadata) {
		if(NMSHelper.isV1_12_2()) return ItemAPI_v1_12_2.parseItem(metadata);
		else if(NMSHelper.isV1_13_2()) return ItemAPI_v1_13_2.parseItem(metadata);
		else if(NMSHelper.isV1_14_4()) return ItemAPI_v1_14_4.parseItem(metadata);
		else if(NMSHelper.isV1_15_2()) return ItemAPI_v1_15_2.parseItem(metadata);
		else if(NMSHelper.isV1_16_5()) return ItemAPI_v1_16_5.parseItem(metadata);
		else if(NMSHelper.isV1_17_1()) return ItemAPI_v1_17_1.parseItem(metadata);
		else if(NMSHelper.isV1_18_2()) return ItemAPI_v1_18_2.parseItem(metadata);
		else if(NMSHelper.isV1_19()) return ItemAPI_v1_19.parseItem(metadata);
		else return null;
	}
	
}
