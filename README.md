# InventoryAPI

마인크래프트 플러그인 개발자들을 위한, 인벤토리 API
플러그인 및 API 사용버전: 1.12.2 이상

Maven/Gradle 등에서 API 사용을 원할 시에
로컬 저장소에서 Jar 파일을 등록하여 사용하세요!

해당 API 를 사용하실때, 서버에 [CommandAPI](https://github.com/Hyper4j/CommandAPI) 플러그인을 추가해주세요!

#### 사용방법:
```java
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
			return;-
		}
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
}
```

#### ExampleCMD.class:
```java
public class ExampleCMD implements Command {
	
	/**
	 * 명령어 실행 구문
	 */
	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		// TODO Auto-generated method stub
		sender.sendMessage("Example!");
		return true;
	}
	
	/**
	 * 명령어 이름
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "examplecmd";
	}
	
	/**
	 * 명령어 설명
	 */
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "CommandAPI example Command.";
	}
	
	/**
	 * 명령어 사용 방법
	 */
	@Override
	public String getUsageMessage() {
		// TODO Auto-generated method stub
		return "/examplecmd";
	}
	
	/**
	 * 명령어 권한
	 */
	@Override
	public String getPermission() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 추가 명령어
	 */
	@Override
	public List<String> getAliases() {
		// TODO Auto-generated method stub
		return new ArrayList<>();
	}

}

```
