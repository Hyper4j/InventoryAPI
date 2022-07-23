package com.hyperstudio.InventoryAPI;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.hyperstudio.CommandAPI.CommandAPI;
import com.hyperstudio.CommandAPI.Interface.CommandMap;
import com.hyperstudio.CommandAPI.Interface.RegisterException;
import com.hyperstudio.InventoryAPI.command.InventoryAPI_CMD;
import com.hyperstudio.InventoryAPI.events.InventoryEVT;
import com.hyperstudio.InventoryAPI.utils.NMSHelper;

/**
 * InventoryLib 을 플러그인화 하기위한 작업.
 * @author Hyper4j
 *
 */
public class Main extends JavaPlugin {
	
	// InventoryAPI 를 불러옵니다.
	public static InventoryAPI api;
	
	@Override
	public void onEnable() {
		// 이 플러그인으로 API 를 등록합니다.
		api = new InventoryAPI(this);
		
		onFolderSetup();
		if(Bukkit.getPluginManager().getPlugin("CommandAPI") == null) {
			getLogger().info("Hyper4j's CommandAPI 플러그인을 적용시켜주세요!");
			Bukkit.getPluginManager().disablePlugin(this);
			return;
		}
		
		// EXAMPLE!!
		/**
			Inventory inv;
			if(api.canCreate("안녕하세요")) {
				inv = api.createInventory("안녕하세요", InventorySize.MINI);
				
				InventoryOption inventoryOption = new InventoryOption(inv);
				
				//inventoryOption.onAction(new OpenInventory(null, inv));
				
				inventoryOption
					.setSettingInventory(true);
				inv.addOption(inventoryOption);
				inv.setItem(new ItemStack(Material.APPLE, 1), 0);
				
				
				
			}
			if(api.canCreate("Hello world")) {
				inv = api.createInventory("Hello world", InventorySize.BIG);
				
				InventoryOption inventoryOption = new InventoryOption(inv);
				
				//inventoryOption.onAction(new OpenInventory(null, inv));
				
				inventoryOption
					.setSettingInventory(true)
					.addClickAction(new Inventory("안녕하세요"), 43);
				inv.addOption(inventoryOption);
				inv.setItem(new ItemStack(Material.ANVIL, 2), 43);
				
				
				
			}
		**/
		
		Bukkit.getPluginManager().registerEvents(new InventoryEVT(), this);
		
		// 서버의 버전을 나타냅니다.
		getLogger().info(NMSHelper.getVersion());
		
		/**
		 * CommandAPI-1.0.0.jar
		 * (Author : Hyper4j) -> Public
		 * 
		 */
		CommandAPI api = new CommandAPI(this);
		try {
			api.registerCommand(new CommandMap(this, new InventoryAPI_CMD(), null));
		} catch (RegisterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		getLogger().info("Success for enabled.");
	}
	
	@Override
	public void onDisable() {
		getLogger().info("Success for disabled.");
	}
	
	private void onFolderSetup() {
		File file = new File("plugins/InventoryAPI");
		if(!file.exists()) file.mkdir();
		return;
	}
	
}
