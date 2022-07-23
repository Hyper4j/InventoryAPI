package com.hyperstudio.InventoryAPI;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.plugin.Plugin;

public class InventoryAPI extends InventoryCore {
	
	private Plugin plugin;
	
	public InventoryAPI(Plugin plugin) {
		this.plugin = plugin;
	}
	
	public Plugin getPlugin() {
		return this.plugin;
	}
	
	/**
	 * 영구데이터 인벤토리를 전부 불러옵니다.
	 * @return
	 */
	public List<Inventory> getInventoryLists() {
		List<Inventory> list = new ArrayList<Inventory>();
		File f = new File("plugins/InventoryAPI");
		File[] inventoryList = f.listFiles();
		for(File folder : inventoryList) {
			String name = folder.getName();
			Inventory inv = new Inventory(name);
			list.add(inv);
		}
		return list;
	}
}
 