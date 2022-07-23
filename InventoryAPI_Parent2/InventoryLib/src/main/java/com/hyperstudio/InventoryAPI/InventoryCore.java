package com.hyperstudio.InventoryAPI;

import java.io.File;
import java.lang.reflect.Array;
import java.nio.file.Path;

public class InventoryCore implements ICore {

	@Override
	public Inventory createInventory(String name, InventorySize size) {
		// TODO Auto-generated method stub
		if(!canCreate(name)) return null;
		Inventory inventory = new Inventory(name, size);
		return inventory;
	}

	@Override
	public void removeInventory(Inventory inventory) {
		// TODO Auto-generated method stub
		File folder = new File("plugins/InventoryAPI/"+inventory.getName());
		if(folder.exists()) {
			File[] list = folder.listFiles();
			for(File f : list) {
				f.delete();
			}
			folder.delete();
		}
		return;
	}

	@Override
	public boolean canCreate(String inventoryName) {
		File f = new File("plugins/InventoryAPI");
		File[] list = f.listFiles();
		for(File folder : list) {
			String name = folder.getName();
			if(name.equals(inventoryName)) return false;
		}
		return true;
	}
	
	

	@Override
	public Inventory loadInventory(String name) {
		if(!canCreate(name)) {
			Inventory inventory = new Inventory(name);
			return inventory;
		}
		return null;
	}
	
}
