package com.hyperstudio.InventoryAPI.events;

import java.util.Iterator;
import java.util.List;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;

import com.hyperstudio.InventoryAPI.Inventory;
import com.hyperstudio.InventoryAPI.InventoryAPI;
import com.hyperstudio.InventoryAPI.Main;
import com.hyperstudio.InventoryAPI.exceptions.CannotFoundInventoryCauseNameIsNull;

public class InventoryEVT implements Listener {
	
	InventoryAPI api = Main.api;
	
	/*
	 * MoveItem 에 대한 이벤트 작업
	 */
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		org.bukkit.inventory.Inventory inv = e.getClickedInventory();
		if(inv == null) return;
		List<Inventory> list = api.getInventoryLists();
		Iterator<Inventory> iterator = list.iterator();
		while(iterator.hasNext()) {
			Inventory i = iterator.next();
			String name = i.getName();
			if(name.equals(e.getView().getTitle())) {
				if(i.getInventoryOption().getSettingInventory()) e.setCancelled(true);
			}
		}
	}
	
	/**
	 * ClickAction 에 대한 이벤트 작업
	 * @param e
	 */
	@EventHandler
	public void onInventoryClickAction(InventoryClickEvent e) {
		org.bukkit.inventory.Inventory inv = e.getClickedInventory();
		if(inv == null) return;
		List<Inventory> list = api.getInventoryLists();
		Iterator<Inventory> iterator = list.iterator();
		while(iterator.hasNext()) {
			Inventory i = iterator.next();
			String name = i.getName();
			if(name.equals(e.getView().getTitle())) {
				try {
					if(i.getInventoryOption().getClickActions()
							.get(e.getSlot()) != null) {
						e.getWhoClicked().openInventory(api.loadInventory(i.getInventoryOption().getClickActions()
								.get(e.getSlot())).create());
					}
				} catch (CannotFoundInventoryCauseNameIsNull e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * MoveItem 에 대한 인벤토리 작업 2
	 * @param e
	 */
	@EventHandler
	public void onInventory(InventoryDragEvent e) {
		org.bukkit.inventory.Inventory inv = e.getInventory();
		if(inv == null) return;
		List<Inventory> list = Main.api.getInventoryLists();
		Iterator<Inventory> iterator = list.iterator();
		while(iterator.hasNext()) {
			Inventory i = iterator.next();
			String name = i.getName();
			if(name.equals(e.getView().getTitle())) {
				if(i.getInventoryOption().getSettingInventory()) e.setCancelled(true);
			}
		}
	}
	
	// To do
	@EventHandler
	public void onInventory(InventoryPickupItemEvent e) {
		org.bukkit.inventory.Inventory inv = e.getInventory();
		
	}
	
	// To do
	@EventHandler
	public void onInventory(InventoryMoveItemEvent e) {
		org.bukkit.inventory.Inventory inv = e.getDestination();
		
	}
	
	// To do
	@EventHandler
	public void onInventory(InventoryInteractEvent e) {
		org.bukkit.inventory.Inventory inv = e.getInventory();
		
	}
	
}
