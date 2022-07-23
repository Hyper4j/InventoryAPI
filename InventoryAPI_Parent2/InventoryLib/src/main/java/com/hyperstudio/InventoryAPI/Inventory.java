package com.hyperstudio.InventoryAPI;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

import com.hyperstudio.InventoryAPI.exceptions.CannotFoundInventoryCauseNameIsNull;
import com.hyperstudio.InventoryAPI.options.InventoryOption;
import com.hyperstudio.InventoryAPI.utils.FileBuilder;
import com.hyperstudio.InventoryAPI.utils.ItemAPI;

/**
 * 해당 Inventory 객체는, 영구데이터 인벤토리 객체이며,
 * InventoryAPI 를 통해서만, 접근이 가능하도록 설계 되어있습니다.
 * @author Hyper4j
 *
 */
public class Inventory  {
	
	// Debugging 용의 API 를 가져옵니다.
	InventoryAPI api = new InventoryAPI(null);
	
	// 인벤토리의 이름
	private String name;
	
	// 인벤토리의 사이즈(크기)
	private int size = 0;
	
	/**
	 * 슬롯에 대한 아이템의 리스트
	 * @param Integer -> Slot
	 * @param ItemStack -> Item
	 */
	private HashMap<Integer, ItemStack> map = new HashMap<>();
	
	// 인벤토리 부과옵션
	private InventoryOption option = new InventoryOption(this);
	
	
	/**
	 * 인벤토리를 추가하기 위한 생성자
	 * @param name
	 * @param size
	 */
	protected Inventory(String name, InventorySize size) {
		this.name = name;
		if(size == InventorySize.MINI) this.size = 27;
		else if(size == InventorySize.BIG) this.size = 54;
	}
	
	/**
	 * 인벤토리를 불러오기 위한 생성자
	 * @param name
	 */
	protected Inventory(String name) {
		this.name = name;
		try {
			loadItems();
		} catch (CannotFoundInventoryCauseNameIsNull e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * InveentoryOption 객체를 추가합니다.
	 * @param option
	 */
	public void addOption(InventoryOption option) {
		this.option = option;
	}
	
	/**
	 * 인벤토리 옵션을 불러옵니다.
	 * @return
	 */
	public InventoryOption getInventoryOption() {
		return this.option;
	}
	
	/**
	 * 인벤토리의 이름을 불러옵닏다.
	 * @return
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * 인벤토리의 사이즈를 불러옵닏다.
	 * @return
	 */
	public int getSize() {
		return this.size;
	}
	
	/**
	 * 인벤토리의 이름을 변경합니다.
	 * @param name
	 */
	public void changeName(String name) {
		saveInventory();
		
		File before = new File("plugins/InventoryAPI/"+getName());
		
		File f = new File("plugins/InventoryAPI/"+name);
		f.mkdir();
		
		copy(before, f);
		
		this.name = name;
	}
	
	/**
	 * Inventory 객체를 org.bukkit.inventory.Inventory 객체로 변환
	 * @return
	 * @throws CannotFoundInventoryCauseNameIsNull
	 */
	public org.bukkit.inventory.Inventory create() throws CannotFoundInventoryCauseNameIsNull {
		
		if(api.canCreate(getName())) throw new CannotFoundInventoryCauseNameIsNull();
		org.bukkit.inventory.Inventory inv = Bukkit.createInventory(null, getSize(), getName());
		if(map.isEmpty()) loadItems();
		
		Iterator<Integer> iterator = map.keySet().iterator();
		while(iterator.hasNext()) {
			int slot = iterator.next();
			ItemStack item = map.get(slot);
			
			inv.setItem(slot, item);
		}
		return inv;
		
	}
	
	/**
	 * Inventory 객체에 대한 모든 정보를 저장합니다.
	 */
	private void saveInventory() {
		File inventoryFolder = new File("plugins/InventoryAPI/"+getName());
		if(!api.canCreate(name)) saveCover(inventoryFolder);
		else save(inventoryFolder);
		return;
	}
	
	/**
	 * Inventory 객체에 대한 모든 정보를 덮어쓰기용으로 저장합니다.
	 * @param folder
	 */
	private void saveCover(File folder) {
		File[] list = folder.listFiles();
		for(File f : list) {
			if(f.getName().equals("option.txt")) continue;
			f.delete();
		}
		
		folder.delete();
		save(folder);
		return;
	}
	
	/**
	 * Inventory 객체에 대한 모든 정보를 일반적으로 저장합니다.
	 * @param folder
	 */
	private void save(File folder) {
		File temp;
		
		folder.mkdir();
		if(!map.isEmpty()) {
			Iterator<Integer> iterator = map.keySet().iterator();
			while(iterator.hasNext()) {
				// 저장하기
				int slot = iterator.next();
				temp = new File(folder, slot+".txt");
				FileBuilder.createFile(temp, ItemAPI.getString(map.get(slot)));
			}
			temp = new File(folder, "option.txt");
			FileBuilder.createFile(temp, option.toString());
		}
		return;
	}
	
	/**
	 * 복사대상의 텍스트를 복사하여, 붙어넣을 대상에 붙어넣습니다.
	 * @param copy -> 복사대상
	 * @param paste -> 붙어넣을 대상
	 */
	private void copy(File copy, File paste) {
		File temp;
		
		File[] list = copy.listFiles();
		for(File file : list) {
			String filename = file.getName();
			temp = new File(copy, filename);
			
			String a = FileBuilder.readFile(temp);
			temp = new File(paste, filename);
			
			FileBuilder.createFile(temp, a);
		}
		return;
	}
	
	/**
	 * ItemStack, InventoryOption 등등을 불러옵니다.
	 * Inventory 객체에 LOAD 합니다.
	 * @throws CannotFoundInventoryCauseNameIsNull
	 */
	private void loadItems() throws CannotFoundInventoryCauseNameIsNull {
		File inventoryFolder = new File("plugins/InventoryAPI/"+getName());
		if(api.canCreate(getName())) throw new CannotFoundInventoryCauseNameIsNull();
		
		// 불러오기
		File[] list = inventoryFolder.listFiles();
		for(File f : list) {
			if(f.getName().equals("option.txt")) {
				// 옵션 로드하기
				option.clear();
				option.parseOption(new File(f.getAbsolutePath()));
				this.size = option.getSize();
				
				continue;
			}
			int slot = Integer.parseInt(f.getName().replace(".txt", ""));
			ItemStack item = ItemAPI.parseItem(FileBuilder.readFile(f));
			map.put(slot, item);
		}
		return;
	}
	
	/**
	 * 슬롯에 아이템을 추가합니다.
	 * @param item
	 * @param slot
	 * @return
	 */
	public boolean setItem(ItemStack item, int slot) {
		map.put(slot, item);
		saveInventory();
		return true;
	}
	
	/**
	 * 아이템을 인벤토리에서 전체 제거합니다.
	 * @param item
	 * @return
	 */
	public boolean removeItem(ItemStack item) {
		if(map.containsValue(item)) {
			Iterator<Integer> iterator = map.keySet().iterator();
			while(iterator.hasNext()) {
				if(map.get(iterator.next()) == item) {
					map.remove(iterator.next());
					saveInventory();
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 슬롯에 있는 아이템을 제거합니다.
	 * @param slot
	 * @return
	 */
	public boolean removeItem(int slot) {
		if(map.get(slot) == null) return false;
		map.remove(slot);
		saveInventory();
		return true;
	}
	
}
