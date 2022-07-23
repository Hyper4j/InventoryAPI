package com.hyperstudio.InventoryAPI.command;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.hyperstudio.CommandAPI.Interface.Command;
import com.hyperstudio.InventoryAPI.Inventory;
import com.hyperstudio.InventoryAPI.InventoryAPI;
import com.hyperstudio.InventoryAPI.Main;
import com.hyperstudio.InventoryAPI.exceptions.CannotFoundInventoryCauseNameIsNull;

public class InventoryAPI_CMD implements Command {
	
	
	InventoryAPI api = Main.api;
	
	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		// TODO Auto-generated method stub
		
		// COMING SOON...
		Player player = (Player) sender;
		if(args[0].equals("open")) {
			if(args[1] != null) {
				if(args[1].contains("_")) {
					args[1] = args[1].replace("_", " ");
				}
				Inventory inv = api.loadInventory(args[1]);
				if(inv == null) {
					player.sendMessage(ChatColor.RED + "해당 이름의 인벤토리를 찾을 수 없습니다!");
					return true;
				}
				try {
					player.openInventory(inv.create());
				} catch (CannotFoundInventoryCauseNameIsNull e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				player.sendMessage(ChatColor.GREEN + "성공적으로 " + ChatColor.LIGHT_PURPLE + args[1] + ChatColor.GREEN + " 인벤토리를 열었습니다!");
				return true;
			}
			
		}
		else if(args[0].equals("set")) {
			String invName = args[1].replace("_", " ");
			int slot = Integer.parseInt(args[2]);
			Inventory inv = api.loadInventory(invName);
			if(inv == null) {
				player.sendMessage(ChatColor.RED + "해당 이름의 인벤토리를 찾을 수 없습니다!");
				return true;
			}
			inv.setItem(player.getItemInHand(), slot);
			player.sendMessage(ChatColor.GREEN + "성공적으로 " + ChatColor.LIGHT_PURPLE + invName + ChatColor.GREEN + " 에 아이템을 추가했습니다!");
			
		}
		return true;
	}

	@Override
	public List<String> getAliases() {
		// TODO Auto-generated method stub
		List<String> aliases = new ArrayList<String>();
		aliases.add("iapi");
		aliases.add("invapi");
		aliases.add("inventorymanager");
		aliases.add("imanager");
		aliases.add("invmanager");
		aliases.add("invhelper");
		return aliases;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Easily create persistent storage inventory.";
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "inventoryapi";
	}

	@Override
	public String getPermission() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsageMessage() {
		// TODO Auto-generated method stub
		return "/inventoryapi help";
	}

}
