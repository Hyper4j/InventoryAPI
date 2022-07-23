package com.hyperstudio.InventoryAPI.options.actions;

import org.bukkit.entity.Player;

import com.hyperstudio.InventoryAPI.Inventory;
import com.hyperstudio.InventoryAPI.exceptions.CannotFoundInventoryCauseNameIsNull;
import com.hyperstudio.InventoryAPI.options.ActionEvent;

public class OpenInventory implements ActionEvent<OpenInventory> {
	
	private Player player;
	private Inventory inventory;
	
	public OpenInventory(Player player, Inventory inventory) {
		this.player = player;
		this.inventory = inventory;
	}
	

	@Override
	public void action(OpenInventory o) {
		// TODO Auto-generated method stub
		try {
			
			org.bukkit.inventory.Inventory inv = inventory.create();
			player.closeInventory();
			player.openInventory(inv);
			
		} catch (CannotFoundInventoryCauseNameIsNull e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	
}
