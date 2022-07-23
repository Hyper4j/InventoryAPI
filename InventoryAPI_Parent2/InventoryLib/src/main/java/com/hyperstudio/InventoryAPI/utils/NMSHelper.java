package com.hyperstudio.InventoryAPI.utils;

import org.bukkit.Bukkit;

public class NMSHelper {
	
	/**
	 * 버킷 버전 가져오기
	 * @return
	 */
	public static String getVersion() {
		return Bukkit.getBukkitVersion();
	}
	
	public static boolean isV1_12_2() {
		if(getVersion().contains("1.12.2")) return true;
		return false;
	}
	
	public static boolean isV1_13_2() {
		if(getVersion().contains("1.13.2")) return true;
		return false;
	}
	
	public static boolean isV1_14_4() {
		if(getVersion().contains("1.14.4")) return true;
		return false;
	}
	
	public static boolean isV1_15_2() {
		if(getVersion().contains("1.15.2")) return true;
		return false;
	}
	
	public static boolean isV1_16_5() {
		if(getVersion().contains("1.16.5")) return true;
		return false;
	}
	
	public static boolean isV1_17_1() {
		if(getVersion().contains("1.17.1")) return true;
		return false;
	}
	
	public static boolean isV1_18_2() {
		if(getVersion().contains("1.18.2")) return true;
		return false;
	}
	
	public static boolean isV1_19() {
		if(getVersion().contains("1.19")) return true;
		return false;
	}
	
}
