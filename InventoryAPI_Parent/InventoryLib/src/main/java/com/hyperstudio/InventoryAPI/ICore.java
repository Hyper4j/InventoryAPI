package com.hyperstudio.InventoryAPI;

public interface ICore {
	
	/**
	 * 영구데이터 인벤토리를 생성합니다.
	 * @param name
	 * @param size
	 * @return
	 */
	public Inventory createInventory(String name, InventorySize size);
	
	/**
	 * 영구데이터 인벤토리를 제거합니다.
	 * @param inventory
	 */
	public void removeInventory(Inventory inventory);
	
	/**
	 * 영구데이터 인벤토리( 이미 만들어진 )를 불러옵니다.
	 * @param name
	 * @return
	 */
	public Inventory loadInventory(String name);
	
	/**
	 * 해당 이름이 인벤토리로 만들어질 수 있는가?
	 * @param inventoryName
	 * @return
	 */
	public boolean canCreate(String inventoryName);

}
