package com.hyperstudio.InventoryAPI.options;

import java.io.File;
import java.util.HashMap;

import com.hyperstudio.InventoryAPI.Inventory;
import com.hyperstudio.InventoryAPI.options.actions.OpenInventory;
import com.hyperstudio.InventoryAPI.utils.FileBuilder;

/**
 * Inventory 객체에 부과기능을 더하는 옵션
 * @author Hyper4j
 *
 */
public class InventoryOption {
	
	// 아이템을 못움직이게, 아이템을 그저 가만히있는 상태로 하는가?
	// true : 예 , false : 아니요
	private boolean settinginventory = false;
	
	// 인벤토리 사이즈
	private int size = 27;
	
	// 클릭액션:
	// ex) 15 슬롯에 있는 아이템을 클릭시 Hyper4j 라는 인벤토리 이름의 인벤토리를 연다.
	private String clickaction = "";
	
	/**
	 * 슬롯에 있는 아이템을 클릭 시, 인벤토리 이름의 인벤토리가 열리게 되는 클릭액션 리스트
	 * @param Integer -> Slot
	 * @param String -> Inventory Name
	 */
	private HashMap<Integer, String> clickactionMap = new HashMap<Integer, String>();
	
	/**
	 * 인벤토리 옵션은, 사용자 또는 개발자가 직접 생성하여 추가가 가능하다.
	 * @param inv
	 */
	public InventoryOption(Inventory inv) {
		this.size = inv.getSize();
	}
	
	// Option
	// 사용하지 않는 것을 권장 (미완성)
	@SuppressWarnings("rawtypes")
	public void onAction(ActionEvent action) {
		if(action instanceof OpenInventory) ((OpenInventory) action).action(((OpenInventory) action));
	}
	
	/**
	 * option.txt 파일을, InventoryOption 객체로 불러오는 메소드
	 * @param optionFile
	 */
	public void parseOption(File optionFile) {
		String a = FileBuilder.readFile(optionFile);
		if(parseBoolean(a.split("settinginventory: ")[1].split("\n")[0])) setSettingInventory(true);
		this.clickactionMap = parseMap(a.split("clickaction: ")[1].split("\n")[0]);
		this.size = Integer.parseInt(a.split("size: ")[1].split("\n")[0]);
	}
	
	/**
	 * 인벤토리 옵션을 초기화합니다.
	 */
	public void clear() {
		this.settinginventory = false;
		this.size = 27;
		this.clickaction = "";
		this.clickactionMap = new HashMap<Integer, String>();
	}
	
	
	// 아이템을 인벤토리에서 꺼내도 다시 샐성되는, 무제한 아이템 시스템을 하는 여부
	public InventoryOption setSettingInventory(boolean x) {
		this.settinginventory = x;
		return this;
	}
	
	/**
	 * 클릭액션 추가
	 * @param inventory
	 * @param slot
	 * @return
	 */
	public InventoryOption addClickAction(Inventory inventory, int slot) {
		String name = inventory.getName();
		if(clickaction.equals("")) {
			clickaction = "\""+slot+"\":\""+name+"\"";
			return this;
		}
		else {
			clickaction = ",\""+slot+"\":\""+name+"\"";
			return this;
		}
	}
	
	/**
	 * 인벤토리 세팅 여부 가져오기
	 * @return
	 */
	public boolean getSettingInventory() {
		return this.settinginventory;
	}
	
	/**
	 * 인벤토리 사이즈 가져오기
	 * @return
	 */
	public int getSize() {
		return this.size;
	}
	
	/**
	 * 클릭액션 가져오기
	 * @return
	 */
	public HashMap<Integer, String> getClickActions() {
		return this.clickactionMap;
	}
	
	/**
	 * ClickAction 문자열을 HashMap 으로 파싱하는 메소드
	 * @param clickactionStr
	 * @return
	 */
	private HashMap<Integer, String> parseMap(String clickactionStr) {
		HashMap<Integer, String> map = new HashMap<>();
		if(clickactionStr.contains(",")) {
			for(String a : clickactionStr.split(",")) {
				int slot = Integer.parseInt(a.split(":")[0].replace("\"", ""));
				String inventoryName = a.split(":")[1].replace("\"", "");
				map.put(slot, inventoryName);
			}
			return map;
		}
		else {
			if(clickactionStr.equals("[]")) return map;
			else {
				clickactionStr = clickactionStr.replace("[", "").replace("]", "");
				int slot = Integer.parseInt(clickactionStr.split(":")[0].replace("\"", ""));
				String inventoryName = clickactionStr.split(":")[1].replace("\"", "");
				map.put(slot, inventoryName);
				return map;
			}
		}
	}
	
	/**
	 * 
	 * Boolean 이 true 또는 false 일때, boolean 객체로 변환
	 * @param Boolean
	 * @return
	 */
	private boolean parseBoolean(String Boolean) {
		if(Boolean.equals("true")) {
			return true;
		}
		else if(Boolean.equals("false")) {
			return false;
		}
		return false;
	}
	
	/**
	 * option.txt 파일로 저장하기위한 데이터 String
	 */
	@Override
	public String toString() {
		return "size: "+size+"\r\n"
				+ "settinginventory: "+settinginventory+"\r\n"
				+ "clickaction: ["+clickaction+"]";
	}
	
	
}
