import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.geometry.*;
import javafx.scene.image.*;
import java.util.*;

//Jordan Ashe X-X-2021

/*
	TO DO:
	-INTRO: (DONE)
		-SPRITES (MORE OR LESS DONE)
		-TEXT (FIRST SCENE DONE)
		-TRANSITION TO FIRST TOWN (DONE)
	-TOWNS:
		-SPRITES (6/6) (DONE)
		-TEXT (1/5)
		-MAP FUNCTIONALITY (DONE)
		-ABILITY TO CHANGE PARTY MEMBERS (DONE)
	-MAP:
		-SPRITES (for the demo, we are done)
		-TEXT (done)
		-ABILITY INTERACTION (sadly will not do for now, maybe if there is extra time)
		-COMBAT INTERACTION
		-DUNGEONS
			-SPRITES (3/10) (done for the demo)
			-EVENTS (DONE)
			-MAP INTERACTION (DONE)
	-COMBAT:
		-SPRITES FOR ENEMIES (DONE)
		-SPRITES FOR CHARACTERS (9/20) --Kook is making one, hound him.
		-ITEM FUNCIONALITY (DONE)
		-TEXT FOR HOW COMBAT IS GOING (DONE)
		-ABILITY INTERACTION (DONE)
	-STORY: (KINDA DONE)
		-YEAH YOU SHOULD PROBABLY WRITE THAT LMAO (OK KINDA DONE)
	-OTHER TAB:
		-QUEST TAB (DONE)
		-SETTINGS TAB (MIGHT DELETE IDK)
		-EVENT LOG (DONE)
		-MANUAL (DONE)
	-LOAD AND SAVE:
		-Save AND Load BUTTON
		-GAME OVER FUNCTIONALITY
*/

public class DataMiners extends Application {   
	Random rand = new Random();
	static PartyMember[] pTable = new PartyMember[4];
	ArrayList<PartyMember> charUnlocked = new ArrayList<PartyMember>();
	static Enemy[] eCombatTable = new Enemy[3];
	
	//starting characters, name, atk, def, explor, hp, dmg attack, support atk, exploration atk
	PartyMember caulder = new PartyMember("Caulder", 3, 5, 10, 20, "Endure", "Heal", "Map");
	PartyMember co = new PartyMember("CO", 5, 5, 5, 25, "Fire!", "Cover!", "Capture!");
	PartyMember mars = new PartyMember("Mars", 8, 6, 3, 20, "Slash", "War Cry", "Scale");
	PartyMember ascii = new PartyMember("ASCII", 10, 2, 1, 20, "Use Sword", "Use Shield", "Go There");
	
	//other party members
	PartyMember kyzu = new PartyMember("Kyzu", 15, 2, 10, 10, "Kōgeki", "Hashiru", "En'eki");
	PartyMember melody = new PartyMember("Melody", 25, 25, 25, 50, "Rocket", "Kindness", "Wander");
	PartyMember antt = new PartyMember("Antt", 10, 9, 13, 30, "Strength", "Build", "Burrow");
	PartyMember steve = new PartyMember("Steve", 18, 2, 10, 10, "Wrench Bash", "Repair", "Tinker");
	PartyMember stockholm = new PartyMember("Stockholm", 15, 2, 10, 10, "Tennis", "White Bread", "Subway");
	PartyMember gatorboy = new PartyMember("GatorBoy", 15, 2, 10, 10, "Hyuk!", "Yah!", "Eep!");
	PartyMember professorMoney = new PartyMember("ProfessorMoney", 20, 10, 4, 20, "Coin Gun", "Cash Flow", "Bribe");
	
	//enemies, name, atk, def, hp
	static ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
	Enemy blank = new Enemy("blank",0,0,0);
	
	Enemy slimyCode = new Enemy("SlimyCode",6,2,5);
	Enemy viralOfficer = new Enemy("ViralOfficer",12,4,15);
	Enemy hauntedCode = new Enemy("HauntedCode",9,1,12);
	Enemy navyWindCO = new Enemy("NavyWindCO",6,3,13);
	Enemy blargo = new Enemy("Blargo",7,10,20);
	Enemy firewall = new Enemy("FireWall",12,6,17);
	Enemy bandit = new Enemy("Bandit",10,8,20);
	Enemy browserGremlin = new Enemy("BrowserGremlin",15,8,15);
	Enemy cryptoMiner = new Enemy("CryptoMiner",9,6,20);
	Enemy glitchedOfficer = new Enemy("GlitchedOfficer",6,3,13);
	Enemy glitchKnight = new Enemy("GlitchKnight",6,3,13);
	Enemy glitchtopus = new Enemy("Glitchtopus",6,3,13);
	Enemy glitchedvillvillvillee = new Enemy("GlitchedVillVillVillee",6,3,13);
	Enemy invasiveSpacer = new Enemy("InvasiveSpacer",6,3,13);
	Enemy looseTerminal = new Enemy("LooseTerminal",6,3,13);
	Enemy mab = new Enemy("MildlyAnnoyedBird",6,3,13);
	Enemy taxman = new Enemy("Taxman",6,3,13);
	Enemy theCube = new Enemy("TheCube",6,3,13);
	Enemy weatherman = new Enemy("WeatherMan",6,3,13);
	Enemy webSurfer = new Enemy("WebSurfer",6,3,13);
	Enemy wildone = new Enemy("WildOne",6,3,13);
	
	//dungeon
	Dungeon trashBin = new Dungeon("TrashBin",3);
	
	//various variables
	static ArrayList<Item> itemsOnPerson = new ArrayList<Item>();
	static String mode = "intro";
	static String tempMode = "";
	String cTown = "ugpu";
	String cTownName = "UGPU";
	static String cQuest = "N/A";
	String cQuestInfo = "N/A";
	MenuBar menuBarCombat = new MenuBar();
	MenuBar menuBarTown = new MenuBar();
	MenuBar menuBarMap = new MenuBar();
	Menu menuParty = new Menu("Party");
	Menu menuItems = new Menu("Items");
	Menu menuOther = new Menu("Other Tab");
	Menu menuAbout = new Menu("About");
	GridPane town = new GridPane();
	GridPane combatGP = new GridPane();
	VBox mainVBox = new VBox();
	VBox dungeonVBox = new VBox();
	static HBox hboxEnemies = new HBox();
	StackPane mapSP = new StackPane();
	StackPane combatSP = new StackPane();
	Label textForCutscene1 = new Label("This, is a computer. ");
	Label textForCutscene2 = new Label("It may be exactly like yours, or it may not.");
	Label textForCutscene3 = new Label("");
	static ImageView cEnemyPic1;
	static ImageView cEnemyPic2;
	static ImageView cEnemyPic3;
	ImageView picForCutscene = new ImageView(new Image("/images/cutscenes/intro-1.png"));
	ImageView picForTown = new ImageView(new Image("/images/locations/" + cTown + ".png"));
	ImageView picForDungeon = new ImageView(new Image("/images/locations/" + cTown + ".png"));
	Button btnIntro = new Button("Next...");
	Button btnVisit = new Button("Visit");
	Button btnVisitDungeon = new Button("Start");
	Button btnVisitEvent = new Button("Next...");
	Button btnShop = new Button("Shop");
	Button btnMap = new Button("Map");
	Button btnQuest = new Button("Quest");
	Button btnGO = new Button("Load Last Save");
	Button btnAcceptQuest = new Button("Accept");
	int textIntro = 0;
	int currentTurn=0;
	boolean visitGPUFirst = false;
	static boolean quest1complete = false;
	static boolean quest1reward = false;
	static boolean quest2complete = false;
	static boolean quest2reward = false;
	boolean shopUnlocked = false;
	static boolean inCombat = false;
	static boolean inDungeon = false;
	TextArea eventLog = new TextArea("This is where events will pile up as you play:");
	TextArea questLog = new TextArea(cQuest + "\n" + cQuestInfo);
	TextArea combatLog = new TextArea("Initiated Combat!");
	
	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {  
		pTable[0] = caulder;
		charUnlocked.add(caulder);
		
		enemyList.add(blank);
		enemyList.add(slimyCode);
		enemyList.add(hauntedCode);
		enemyList.add(viralOfficer);
		enemyList.add(navyWindCO);
		enemyList.add(blargo);
		enemyList.add(firewall);
		enemyList.add(bandit);
		enemyList.add(browserGremlin);
		enemyList.add(cryptoMiner);
		
		itemsOnPerson.add(new Item("HealingPotion",false,0,10));
		
		
		
		//party tab
		MenuItem menuItemChange = new MenuItem("Change Party Members");
		menuParty.getItems().add(menuItemChange);
		
		menuItemChange.setOnAction(e -> {
			if (mode=="town"){
				changeParty();
			}
			else{
				VBox vItem = new VBox();
				Label errorLabel = new Label("You can only change in a town!");
				Button btnMyBad = new Button("My bad.");
				Stage errorWindow = new Stage();
				
				btnMyBad.setOnAction(p -> {
					errorWindow.close();
				});
				
				vItem.getChildren().addAll(errorLabel,btnMyBad);
				vItem.setAlignment(Pos.TOP_CENTER);
				
				Scene sceneI = new Scene(vItem,200,60);
				errorWindow.setTitle("Party Error");
				errorWindow.setResizable(false);
				errorWindow.setAlwaysOnTop(true);
				errorWindow.setScene(sceneI);
				errorWindow.show();
			}
		});
		
		MenuItem menuItemP1 = new MenuItem("Party Member Slot 1");
		menuParty.getItems().add(menuItemP1);
		
		menuItemP1.setOnAction(e -> partyWindow(pTable[0], 0));
		
		MenuItem menuItemP2 = new MenuItem("Party Member Slot 2");
		menuParty.getItems().add(menuItemP2);
		
		menuItemP2.setOnAction(e -> partyWindow(pTable[1], 1));
		
		MenuItem menuItemP3 = new MenuItem("Party Member Slot 3");
		menuParty.getItems().add(menuItemP3);
		
		menuItemP3.setOnAction(e -> partyWindow(pTable[2], 2));
				
		MenuItem menuItemP4 = new MenuItem("Party Member Slot 4");
		menuParty.getItems().add(menuItemP4);
		
		menuItemP4.setOnAction(e -> partyWindow(pTable[3], 3));
		
		MenuItem menuItemPAll = new MenuItem("Open all Party Member Slots");
		menuParty.getItems().add(menuItemPAll);
		
		menuItemPAll.setOnAction(e -> {
			partyWindow(pTable[0], 0);
			partyWindow(pTable[1], 1);
			partyWindow(pTable[2], 2);
			partyWindow(pTable[3], 3);
		});
		
		//-------------------COMBAT-------------------
		combatLog.setEditable(false);
		combatLog.setStyle("-fx-opacity: 1; -fx-font-size: 24px;");
		hboxEnemies.setAlignment(Pos.CENTER);
		
		
		//-------------------DUNGEON------------------
		btnVisitDungeon.setOnAction(e -> {
			switch (cTown) {
				case "trashbin":
					trashBin.tempEvents=trashBin.events;
					break;
			}
			mode="indungeon";
			modeMachine();
		});
		
		btnVisitEvent.setOnAction(e -> {
			switch (cTown) {
				case "trashbin":
					Dungeon.event(trashBin);
					break;
			}
			modeMachine();
		});
		
		//-------------------MAP----------------------
		ImageView mapBack = new ImageView(new Image("/images/map.PNG"));
		
		Pane mapPane = new Pane();
		mapPane.setPrefSize(700,670);
		
		//locations init
		ImageView ugpuMap = new ImageView(new Image("/images/locations/map/ugpu.PNG"));
		ugpuMap.relocate(8,64);
		ImageView hardDriveMap = new ImageView(new Image("/images/locations/map/harddriveton.PNG"));
		hardDriveMap.relocate(8,236);
		ImageView newBoardCityMap = new ImageView(new Image("/images/locations/map/newboardcity.PNG"));
		newBoardCityMap.relocate(300,64);
		ImageView ramopolisMap = new ImageView(new Image("/images/locations/map/ramopolis.PNG"));
		ramopolisMap.relocate(620,580);
		
		//dungeons init
		ImageView trashBinMap = new ImageView(new Image("/images/locations/map/trashbin.PNG"));
		trashBinMap.relocate(8,100);
		
		//plr on map
		ImageView plrMap = new ImageView(new Image("/images/partyMembers/" + caulder.fileName + "/map.PNG"));
		plrMap.relocate(8,32);
		
		//move plr
		mapPane.setOnMouseClicked(e -> {
			if (e.getY() > 76 && e.getY() < 628){
				plrMap.relocate(e.getX()-16,e.getY()-24);
				
				if (rand.nextInt(5)==1 && mode=="map"){
					int battle = rand.nextInt(3);
					combat2(battle);
					mode="combat";
					modeMachine();
				}
			}
		});
		
		//towns
		ugpuMap.setOnMouseClicked(e -> {
			cTownName="UGPU";
			eventLog.setText("\n-Arrived in United Graphics Processing Corporation" + eventLog.getText());
			cTown="ugpu";
			mode="town";
			modeMachine();
		});
		
		hardDriveMap.setOnMouseClicked(e -> {
			cTownName="HardDriveton";
			eventLog.setText("-Arrived in HardDriveton\n" + eventLog.getText());
			cTown="harddriveton";
			mode="town";
			modeMachine();
		});
		
		newBoardCityMap.setOnMouseClicked(e -> {
			cTownName="NewBoardCity";
			eventLog.setText("-Arrived in NewBoardCity\n" + eventLog.getText());
			cTown="newboardcity";
			mode="town";
			modeMachine();
		});
		
		ramopolisMap.setOnMouseClicked(e -> {
			VBox vItem = new VBox();
			Label errorLabel = new Label("No availble in the demo, sorry!");
			Button btnMyBad = new Button("My bad.");
			Stage errorWindow = new Stage();
			
			btnMyBad.setOnAction(p -> {
				errorWindow.close();
			});
			
			vItem.getChildren().addAll(errorLabel,btnMyBad);
			vItem.setAlignment(Pos.TOP_CENTER);
			
			Scene sceneI = new Scene(vItem,250,60);
			errorWindow.setTitle("Not for Demo");
			errorWindow.setResizable(false);
			errorWindow.setAlwaysOnTop(true);
			errorWindow.setScene(sceneI);
			errorWindow.show();
		});
		
		//dungeons
		trashBinMap.setOnMouseClicked(e -> {
			cTown="trashbin";
			cTownName="The Trash Bin";
			eventLog.setText("-Arrived in the TrashBin dungeon!\n" + eventLog.getText());
			mode="dungeon";
			modeMachine();
		});
		
		mapPane.getChildren().addAll(ugpuMap,trashBinMap,hardDriveMap,newBoardCityMap,ramopolisMap,plrMap);
		mapSP.getChildren().addAll(mapBack,mapPane);
		
		
		//-------------------EXTRAS TAB---------------
		//extras tab and about tab
		eventLog.setText("\n-Booted the program." + eventLog.getText());
		eventLog.setEditable(false);
		eventLog.setStyle("-fx-opacity: 1;");
		questLog.setEditable(false);
		questLog.setStyle("-fx-opacity: 1;");
		
		MenuItem menuItemOther = new MenuItem("Open Others Tab");
		MenuItem menuItemAbout = new MenuItem("About this Game");
		menuItemOther.setOnAction(e -> otherWindow());
		menuItemAbout.setOnAction(e-> aboutWindow());
		menuOther.getItems().add(menuItemOther);
		menuAbout.getItems().add(menuItemAbout);
		menuBarCombat.getMenus().addAll(menuParty,menuItems,menuAbout);
		menuBarTown.getMenus().addAll(menuParty,menuItems,menuOther,menuAbout);
		menuBarMap.getMenus().addAll(menuParty,menuOther,menuAbout);
		
		//items tab
		MenuItem menuItemUseFirst = new MenuItem("Use First Item");
		menuItems.getItems().add(menuItemUseFirst);

		menuItemUseFirst.setOnAction(e -> {
			//use the first usable item in the players inventory
			Item temp;
			
			for (int i=0; i<itemsOnPerson.size(); i++){
				if (itemsOnPerson.get(i).isKey == false){
					//USE THE ITEM
					temp = itemsOnPerson.get(i);
					Item.useItem(temp);
					itemsOnPerson.remove(itemsOnPerson.get(i));
					break;
				}
			}
		});
		
		MenuItem menuItemItems = new MenuItem("Items");
		menuItems.getItems().add(menuItemItems);

		menuItemItems.setOnAction(e -> itemWindow());
		
		//-------------------TOWN---------------------
		btnVisit.setOnAction(e -> {
			visit(cTown);
		});
		
		btnShop.setOnAction(e -> {
			if (shopUnlocked==true){
				shop(cTown);
			}
			else{
				VBox vItem = new VBox();
				Label errorLabel = new Label("No one seems to be here...?");
				Button btnMyBad = new Button("Huh?");
				Stage errorWindow = new Stage();
				
				btnMyBad.setOnAction(p -> {
					errorWindow.close();
				});
				
				vItem.getChildren().addAll(errorLabel,btnMyBad);
				vItem.setAlignment(Pos.TOP_CENTER);
				
				Scene sceneI = new Scene(vItem,250,60);
				errorWindow.setTitle("Shop?");
				errorWindow.setResizable(false);
				errorWindow.setAlwaysOnTop(true);
				errorWindow.setScene(sceneI);
				errorWindow.show();
			}
		});
		
		btnMap.setOnAction(e->{
			if (charUnlocked.contains(co)){
				mode="map";
				modeMachine();
			}
			else{
				VBox vItem = new VBox();
				Label errorLabel = new Label("You may wanna go Visit the town first!");
				Button btnMyBad = new Button("My bad.");
				Stage errorWindow = new Stage();
				
				btnMyBad.setOnAction(p -> {
					errorWindow.close();
				});
				
				vItem.getChildren().addAll(errorLabel,btnMyBad);
				vItem.setAlignment(Pos.TOP_CENTER);
				
				Scene sceneI = new Scene(vItem,250,60);
				errorWindow.setTitle("Map Error");
				errorWindow.setResizable(false);
				errorWindow.setAlwaysOnTop(true);
				errorWindow.setScene(sceneI);
				errorWindow.show();
			}
		});
		
		btnQuest.setOnAction(e->{
			quest(cTown);
		});
		
		town.setPadding(new Insets(10, 10, 10, 10));
		
		//-------------------INTRO SCENE--------------
		btnIntro.setOnAction(e -> {
			advanceText();
			modeMachine();
		});
		
		//-------------------TITLE--------------------
		ImageView menulogo = new ImageView(new Image("/images/menulogo.png"));
		
		Button btnStart = new Button("Start");
		btnStart.setOnAction(e -> modeMachine());
		
		
		//Start program
		mainVBox.getChildren().addAll(menulogo,btnStart);
		
		mainVBox.setBackground(new Background(new BackgroundFill(Color.HONEYDEW, CornerRadii.EMPTY, Insets.EMPTY)));
		mainVBox.setAlignment(Pos.TOP_CENTER);
		
		Scene scene = new Scene(mainVBox, 700, 670);
		
		primaryStage.setOnCloseRequest(e -> {
			Platform.exit();
			System.exit(0);
		});
		
		primaryStage.setTitle("DataMiners"); // Set the window title
		primaryStage.setResizable(false); // bah screw resizing!
		primaryStage.setScene(scene); // Place the scene in the window
		primaryStage.show(); // Display the window
	}
	
	/**
	 * The main method is only needed for the IDE with limited
	 * JavaFX support. Not needed for running from the command line.
	 * line.
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
	public void modeMachine(){
		mainVBox.getChildren().clear();
		switch (mode) {
			case "intro":
				mainVBox.getChildren().addAll(picForCutscene,textForCutscene1,textForCutscene2,textForCutscene3,btnIntro);
				break;
			case "combat":
				menuBarCombat.getMenus().removeAll(menuParty,menuItems,menuAbout);
				menuBarCombat.getMenus().addAll(menuParty,menuItems,menuAbout);
				
				combatSP.getChildren().clear();
				ImageView battleback = new ImageView(new Image(getBack()));
				combatSP.getChildren().addAll(battleback,combatGP);
				
				cEnemyPic1.setVisible(true);
				cEnemyPic2.setVisible(true);
				cEnemyPic3.setVisible(true);
				
				currentTurn=0;
				
				combatLog = new TextArea("It is now " + pTable[0].name + "'s Turn!\nInitallized Combat!");
				combatLog.setEditable(false);
				combatLog.setPrefColumnCount(25);
				combatLog.setStyle("-fx-opacity: 1; -fx-font-size: 1.5em;");
				
				for (int i=1; i<enemyList.size(); i++){
					enemyList.get(i).chp = enemyList.get(i).mhp;
					enemyList.get(i).ko = false;
				}
				
				combatGP.getChildren().clear();
				combatGP.add(hboxEnemies,0,0);
				combatGP.add(combatLog,0,1);
				combatGP.setVgap(4);
				combatGP.setHgap(4);
				combatGP.setAlignment(Pos.BOTTOM_CENTER);
				
				mainVBox.setBackground(new Background(new BackgroundFill(Color.CRIMSON, CornerRadii.EMPTY, Insets.EMPTY)));
				mainVBox.getChildren().addAll(menuBarCombat,combatSP);
				break;
			case "town":
				town.getChildren().clear();
				
				Text townName = new Text(cTownName);
				townName.setFont(new Font(20.0));
				
				picForTown = new ImageView(new Image("/images/locations/" + cTown + ".png"));
				
				town.add(picForTown,0,0);
				town.add(new Text(townInfo(cTown)),0,1);
				town.add(townName,1,0);
				town.add(btnVisit,1,2);
				town.add(btnQuest,0,2);
				town.add(btnShop,1,3);
				town.add(btnMap,1,4);
				town.setVgap(4);
				town.setHgap(4);
				
				if (charUnlocked.contains(co)){
					for (int i=0; i<=3; i++) {
						pTable[i].restoreStats();
						if (pTable[i].isStageShowing()) {
							partyWindow(pTable[i], i);
						}
					}
				}
				
				menuBarTown.getMenus().removeAll(menuParty,menuItems,menuOther,menuAbout);
				menuBarTown.getMenus().addAll(menuParty,menuItems,menuOther,menuAbout);
				mainVBox.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, CornerRadii.EMPTY, Insets.EMPTY)));
				if (visitGPUFirst){
					mainVBox.getChildren().addAll(menuBarTown,town);
				}
				else{
					mainVBox.getChildren().addAll(menuBarTown, new Text("Check the Others Tab for a Manual on how to play!"),town);
				}
				break;
			case "visit":
				mainVBox.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
				mainVBox.getChildren().addAll(picForCutscene,textForCutscene1,textForCutscene2,textForCutscene3,btnIntro);
				break;
			case "map":
				menuBarMap.getMenus().removeAll(menuParty,menuOther,menuAbout);
				menuBarMap.getMenus().addAll(menuParty,menuOther,menuAbout);	
				mainVBox.getChildren().addAll(menuBarMap, mapSP);
				break;
			case "dungeon":
				dungeonVBox.getChildren().clear();
				
				Text dungeonName = new Text(cTownName);
				dungeonName.setFont(new Font(20.0));
				
				picForDungeon = new ImageView(new Image("/images/locations/" + cTown + ".png"));
				
				dungeonVBox.getChildren().addAll(picForDungeon, dungeonName, new Text(townInfo(cTown)), btnVisitDungeon, btnMap);
				dungeonVBox.setAlignment(Pos.CENTER);
				dungeonVBox.setSpacing(10.0);
				
				menuBarTown.getMenus().removeAll(menuParty,menuItems,menuOther,menuAbout);
				menuBarTown.getMenus().addAll(menuParty,menuItems,menuOther,menuAbout);
				mainVBox.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, CornerRadii.EMPTY, Insets.EMPTY)));
				mainVBox.getChildren().addAll(menuBarTown,dungeonVBox);
				break;
			case "indungeon":
				dungeonVBox.getChildren().clear();
				
				Text dungeonName2 = new Text(cTownName);
				dungeonName2.setFont(new Font(20.0));
				
				Color dungeonColor = Color.WHITESMOKE;
				
				switch (cTown) {
					case "trashbin":
						dungeonColor = Color.BISQUE;
						break;
					case "documents":
						dungeonColor = Color.CRIMSON;
						break;
				}
				
				picForDungeon = new ImageView(new Image("/images/locations/" + cTown + ".png"));
				
				dungeonVBox.getChildren().addAll(picForDungeon, dungeonName2, new Text("You are exploring...\nCue Next Event:"), btnVisitEvent, new Text("Or give up and go home."),btnMap);
				dungeonVBox.setAlignment(Pos.CENTER);
				dungeonVBox.setSpacing(10.0);
				
				menuBarTown.getMenus().removeAll(menuParty,menuItems,menuOther,menuAbout);
				menuBarTown.getMenus().addAll(menuParty,menuItems,menuOther,menuAbout);
				mainVBox.setBackground(new Background(new BackgroundFill(dungeonColor, CornerRadii.EMPTY, Insets.EMPTY)));
				mainVBox.getChildren().addAll(menuBarTown,dungeonVBox);
				break;
			case "dungeonReward":
				dungeonVBox.getChildren().clear();
				
				Text dungeonName3 = new Text(cTownName + "'s Rewards:");
				dungeonName3.setFont(new Font(20.0));
				
				Color dungeonColor2 = Color.GOLD;
				
				Item reward = new Item("Apple",false,0,10);
				
				switch (cTown) {
					case "trashbin":
						reward = new Item("OldTextDocument", false, 2, 5);
						if (cQuest.equals("Dig Through Trash")){
							reward = new Item("CryptoMiner", true, 0, 0);
						}
						break;
				}
				
				String stat = "HP";
				
				switch (reward.stat) {
					case 1:
						stat="Attack";
						break;
					case 2:
						stat="Defense";
						break;
				}
				
				picForDungeon = new ImageView(reward.itemSpr);
				
				Text rewardText = new Text("Your reward is a(n) " + reward.name + ".\nIt increases your " + stat + " by " + reward.statAmount + ".");
				
				if (reward.name.equals("CryptoMiner")){
					rewardText.setText("Your reward is a CrytoMiner.\nIt's a key Item!");
				}
				
				dungeonVBox.getChildren().addAll(dungeonName3, picForDungeon, new Text("Fantastic job!"), rewardText,btnMap);
				itemsOnPerson.add(reward);
				dungeonVBox.setAlignment(Pos.CENTER);
				dungeonVBox.setSpacing(10.0);
				
				menuBarTown.getMenus().removeAll(menuParty,menuItems,menuOther,menuAbout);
				menuBarTown.getMenus().addAll(menuParty,menuItems,menuOther,menuAbout);
				mainVBox.setBackground(new Background(new BackgroundFill(dungeonColor2, CornerRadii.EMPTY, Insets.EMPTY)));
				mainVBox.getChildren().addAll(menuBarTown,dungeonVBox);
				break;
			case "gameover":
				mainVBox.getChildren().addAll(new ImageView(new Image("/images/game over.png")), btnGO);
				break;
		}
	}
	
	
	
	//preset encounters, like on the map
	static void combat2(int random){
		switch (random){
			case 0:
				combat(3, 2, 4);
				break;
			case 1:
				combat(3, 4, 1);
				break;
			case 2:
				combat(4, 1, 2);
				break;
			case 3:
				combat(1, 5, 1);
				break;
			case 4:
				combat(2, 3, 6);
				break;
		}
	}
	
	static void combat(int e1, int e2, int e3){
		Enemy temp1 = enemyList.get(e1);
		Enemy temp2 = enemyList.get(e2);
		Enemy temp3 = enemyList.get(e3);
		
		eCombatTable[0] = temp1;
		eCombatTable[1] = temp2;
		eCombatTable[2] = temp3;
		
		tempMode = mode;
		mode = "combat";
		
		cEnemyPic1 = new ImageView(temp1.btleSpr);
		cEnemyPic2 = new ImageView(temp2.btleSpr);
		cEnemyPic3 = new ImageView(temp3.btleSpr);
		
		hboxEnemies.getChildren().clear();
		hboxEnemies.getChildren().addAll(cEnemyPic1, cEnemyPic2, cEnemyPic3);
	}
	
	void visit(String currentTown){
		switch (currentTown) {
			case "ugpu":
				if (!visitGPUFirst){
					textIntro=17;	
				}
				else if (!charUnlocked.contains(kyzu)){
					textIntro=37;
				}
				else {
					textIntro=44;
				}
				mode="visit";
				advanceText();
				modeMachine();
				break;
			case "harddriveton":
				if (!charUnlocked.contains(antt)){
					textIntro=46;
				}
				else {
					textIntro=44;
				}
				mode="visit";
				advanceText();
				modeMachine();
				break;
		}
		
		eventLog.setText("\n-Visited " + cTownName + "'s Center." + eventLog.getText());
	}
	
	void quest(String currentTown){
		GridPane gpQ = new GridPane();
		Stage newWindow = new Stage();
		
		switch (currentTown) {
			case "ugpu":
				if (quest1complete == false){
					Text taQuest = new Text("Descend Below - Grandma Calculator:\n\tThey say below the town there is a virus that has taken root of\nthe old storages down there.\n\nI guess what I am asking is easy to follow, but could\nyou be a dearie and slay that nasty and mean Viral Officer?\n\n(Defeat one Viral Officer in the TrashBin Dungeon)");
					
					if (cQuest=="Descend Below"){
						taQuest.setText("This quest is currently in progress!");
					}
					
					gpQ.add(taQuest,0,1);
					
					btnAcceptQuest.setOnAction(e -> {
						cQuest = "Descend Below";
						cQuestInfo = "Below the town, in the TrashBin Dungeon, a Viral Officer has set up camp!";
						questLog.setText(cQuest + "\n" + cQuestInfo);
						taQuest.setText("This quest is currently in progress!");
					});
					
					gpQ.add(btnAcceptQuest,0,2);
				}
				else{
					if (quest1reward == true){
						gpQ.add(new Label("No Quest Availble:\n\tSorry, but you have already completed the quest here."),0,1);
					}
					else {
						gpQ.add(new Label("Descend Below - Grandma Calculator:\n\tThank you so much!\nNow that mean old Viral Officer will be on his way!\nHere, it isn't much, but take this.'"),0,1);
						quest1reward = true;
						cQuest = "";
						cQuestInfo = "";
						questLog.setText("N/A\nN/A");
						itemsOnPerson.add(new Item("GrannysNumberSoup", false, 0, 30));
					}
				}
				break;
			case "harddriveton":
				if (quest2complete == false){
					Text taQuest = new Text("Dig Through Trash - Professor Money:\n\tYou never talked to me, ok?\n\n\tI am an operative who mines Crytocurrency down in the depths below \n\n\tUGPU. One of my CryptoMiners has escaped. I need you to bring them \n\tback to me.\n\n\t(Find a CryptoMiner in the TrashBin Dungeon)");
					
					if (cQuest=="Dig Through Trash"){
						taQuest.setText("This quest is currently in progress!");
					}
					
					gpQ.add(taQuest,0,1);
					
					btnAcceptQuest.setOnAction(e -> {
						cQuest = "Dig Through Trash";
						cQuestInfo = "In the TrashBin Dungeon, Professor Money lost his CryptoMiner!";
						questLog.setText(cQuest + "\n" + cQuestInfo);
						taQuest.setText("This quest is currently in progress!");
					});
					
					gpQ.add(btnAcceptQuest,0,2);
				}
				else{
					if (quest2reward == true){
						gpQ.add(new Label("No Quest Availble:\n\tSorry, but you have already completed the quest here."),0,1);
					}
					else {
						gpQ.add(new Label("Dig Through Trash - Professor Money:\n\tThank you.\n\tNow I can freely assist you in your fight against that virus.\n\tLet me tag along.\n\n\t(Professor Money join the fight against the virus!)"),0,1);
						quest2reward = true;
						cQuest = "";
						cQuestInfo = "";
						questLog.setText("N/A\nN/A");
						charUnlocked.add(professorMoney);
					}
				}
				break;
		}
		
		gpQ.setPadding(new Insets(10, 10, 10, 10));
		gpQ.setBackground(new Background(new BackgroundFill(Color.LIGHTGOLDENRODYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
		
		Scene sceneQ = new Scene(gpQ,496,480);
		
		newWindow.setTitle("Availible Quests - " + cTownName);
		newWindow.setResizable(false);
		newWindow.setScene(sceneQ);
		newWindow.show();
		eventLog.setText("\n-Visited " + cTownName + "'s Quest Board." + eventLog.getText());
	}
	
	void shop(String currentTown){
		
	}
	
	public void advanceText(){
		textIntro++;
		switch (textIntro) {
			case 1:
				textForCutscene1.setText("The important part is...");
				textForCutscene2.setText("that if this is the outside of your computer,");
				textForCutscene3.setText("");
				break;
			case 2:
				textForCutscene1.setText("How does the computer exactly work?");
				textForCutscene2.setText("");
				textForCutscene3.setText("");
				break;
			case 3:
				textForCutscene1.setText("Is it magic?");
				textForCutscene2.setText("Does your computer manage electricity into");
				textForCutscene3.setText("inner components???");
				break;
			case 4:
				textForCutscene1.setText("I like to believe there are li'l' creatures in there.");
				textForCutscene2.setText("Like a tiny guy who does the GPS and stuff,");
				textForCutscene3.setText("you know?");
				break;
			case 5:
				textForCutscene1.setText("All doing the hard work for you.");
				textForCutscene2.setText("");
				textForCutscene3.setText("...Probably. ");
				break;
			case 6:
				textForCutscene1.setText("When they are on the job, atleast.");
				textForCutscene2.setText("Actually that would explain why my PC has");
				textForCutscene3.setText("been running slow lately-");
				break;
			case 7:
				textForCutscene1.setText("You know, another strange thought, ");
				textForCutscene2.setText("what happens when a virus gets in?");
				textForCutscene3.setText("How do they fight it?");
				picForCutscene = new ImageView(new Image("/images/cutscenes/intro-2.png"));
				break;
			case 8:
				textForCutscene1.setText("(Elsewhere... ");
				textForCutscene2.setText("Inside of your computer...)");
				textForCutscene3.setText("");
				mainVBox.setBackground(new Background(new BackgroundFill(Color.LIGHTCYAN, CornerRadii.EMPTY, Insets.EMPTY)));
				picForCutscene = new ImageView(new Image("/images/cutscenes/black.png"));
				break;
			case 9:
				textForCutscene1.setText("Hey!");
				textForCutscene2.setText("What's going on here?!");
				textForCutscene3.setText("");
				picForCutscene = new ImageView(new Image("/images/cutscenes/intro-3.png"));
				break;
			case 10:
				textForCutscene1.setText("All routes to The RAMopolis district");
				textForCutscene2.setText("have been closed due to the");
				textForCutscene3.setText("virus emergency.");
				picForCutscene = new ImageView(new Image("/images/cutscenes/intro-4.png"));
				break;
			case 11:
				textForCutscene1.setText("W-what?");
				textForCutscene2.setText("That's right next to my home,");
				textForCutscene3.setText("Coolantia! How did this happen?");
				picForCutscene = new ImageView(new Image("/images/cutscenes/intro-3.png"));
				break;
			case 12:
				textForCutscene1.setText("We are looking into that,");
				textForCutscene2.setText("sir. We apologize for the");
				textForCutscene3.setText("inconvience this may cause.");
				picForCutscene = new ImageView(new Image("/images/cutscenes/intro-4.png"));
				break;
			case 13:
				textForCutscene1.setText("Sigh...");
				textForCutscene2.setText("This is rough.");
				textForCutscene3.setText("Is there anything I can do?");
				picForCutscene = new ImageView(new Image("/images/cutscenes/intro-3.png"));
				break;
			case 14:
				textForCutscene1.setText("There's a meeting going on");
				textForCutscene2.setText("at the United Graphic Proccessing Unit's Capital.");
				textForCutscene3.setText("Everyone is welcome.");
				picForCutscene = new ImageView(new Image("/images/cutscenes/intro-4.png"));
				break;
			case 15:
				textForCutscene1.setText("Plus I don't think we have");
				textForCutscene2.setText("a representive from Coolantia.");
				textForCutscene3.setText("");
				break;
			case 16:
				textForCutscene1.setText("Alright, I'll be there.");
				textForCutscene2.setText("");
				textForCutscene3.setText("");
				picForCutscene = new ImageView(new Image("/images/cutscenes/intro-3.png"));
				break;
			case 17:
				mode = "town";
				break;
			case 18:
				textForCutscene1.setText("I've arrived. What's going on?");
				textForCutscene2.setText("");
				textForCutscene3.setText("");
				picForCutscene = new ImageView(new Image("/images/cutscenes/intro-3.png"));
				break;
			case 19:
				textForCutscene1.setText("This virus is outrageous!");
				textForCutscene2.setText("It's torn apart my home!");
				textForCutscene3.setText("");
				picForCutscene = new ImageView(new Image("/images/cutscenes/intro-5.png"));
				break;
			case 20:
				textForCutscene1.setText("Sir, we are trying our best.");
				textForCutscene2.setText("All of our fail-safes are");
				textForCutscene3.setText("failing badly.");
				picForCutscene = new ImageView(new Image("/images/cutscenes/intro-6.png"));
				break;
			case 21:
				textForCutscene1.setText("For being a Commanding Officer");
				textForCutscene2.setText("Your army sure seems to be");
				textForCutscene3.setText("doing a poor job.");
				picForCutscene = new ImageView(new Image("/images/cutscenes/intro-5.png"));
				break;
			case 22:
				textForCutscene1.setText("I think we just need to take");
				textForCutscene2.setText("it chill. What can this new virus");
				textForCutscene3.setText("do? Is it anything to worry about?");
				picForCutscene = new ImageView(new Image("/images/cutscenes/intro-7.png"));
				break;
			case 23:
				textForCutscene1.setText("Sir! This virus has the ability");
				textForCutscene2.setText("to turn people into strange");
				textForCutscene3.setText("amalgamations of code!");
				picForCutscene = new ImageView(new Image("/images/cutscenes/intro-10.png"));
				break;
			case 24:
				textForCutscene1.setText("I see.");
				textForCutscene2.setText("Who is helping clear it out?");
				textForCutscene3.setText("");
				picForCutscene = new ImageView(new Image("/images/cutscenes/intro-7.png"));
				break;
			case 25:
				textForCutscene1.setText("Not many, sir.");
				textForCutscene2.setText("A lot of the important faces have");
				textForCutscene3.setText("to keep the user interface running!");
				picForCutscene = new ImageView(new Image("/images/cutscenes/intro-10.png"));
				break;
			case 26:
				textForCutscene1.setText("Not good.");
				textForCutscene2.setText("We have to get everyone to help, then.");
				textForCutscene3.setText("All hands on deck.");
				picForCutscene = new ImageView(new Image("/images/cutscenes/intro-7.png"));
				break;
			case 27:
				textForCutscene1.setText("I'm Caulder. I work deep down in the");
				textForCutscene2.setText("Coolant Mines in Coolantia.");
				textForCutscene3.setText("");
				picForCutscene = new ImageView(new Image("/images/cutscenes/intro-7.png"));
				break;
			case 28:
				textForCutscene1.setText("I'm CO. I am the Commanding Officer");
				textForCutscene2.setText("of the Anti-Virus Brigade hired");
				textForCutscene3.setText("out from Red Constellation.");
				picForCutscene = new ImageView(new Image("/images/cutscenes/intro-10.png"));
				break;
			case 29:
				textForCutscene1.setText("Red Constellation?");
				textForCutscene2.setText("I have not heard of such a kingdom.");
				textForCutscene3.setText("");
				picForCutscene = new ImageView(new Image("/images/cutscenes/intro-5.png"));
				break;
			case 30:
				textForCutscene1.setText("Well according to our records,");
				textForCutscene2.setText("it's been a long time since you last");
				textForCutscene3.setText("were used, Mars. That may be why.");
				picForCutscene = new ImageView(new Image("/images/cutscenes/intro-6.png"));
				break;
			case 31:
				textForCutscene1.setText("I hail from the kingdom of Ecra of the Burning");
				textForCutscene2.setText("Mark RPG. I was last played last year");
				textForCutscene3.setText("when the player finished the game...");
				picForCutscene = new ImageView(new Image("/images/cutscenes/intro-5.png"));
				break;
			case 32:
				textForCutscene1.setText("*You feel inclined to join these");
				textForCutscene2.setText("misadventurers. They may need it.");
				textForCutscene3.setText("Badly.");
				picForCutscene = new ImageView(new Image("/images/cutscenes/intro-8.png"));
				break;
			case 33:
				textForCutscene1.setText(">Introduce yourself as ASCII,");
				textForCutscene2.setText("the trainer of the Keyboard District's");
				textForCutscene3.setText("new recruits.");
				picForCutscene = new ImageView(new Image("/images/cutscenes/intro-8.png"));
				break;
			case 34:
				textForCutscene1.setText("I've never seen you before.");
				textForCutscene2.setText("But you seem like a formidable ally");
				textForCutscene3.setText("and we need all the help we can get.");
				picForCutscene = new ImageView(new Image("/images/cutscenes/intro-9.png"));
				break;
			case 35:
				textForCutscene1.setText("*They seem to accept you");
				textForCutscene2.setText("and your clearly superior");
				textForCutscene3.setText("combat abilities.");
				picForCutscene = new ImageView(new Image("/images/cutscenes/intro-8.png"));
				break;
			case 36:
				textForCutscene1.setText(">Go to Town");
				textForCutscene2.setText("");
				textForCutscene3.setText("");
				picForCutscene = new ImageView(new Image("/images/cutscenes/intro-8.png"));
				break;
			case 37:
				pTable[1] = co;
				pTable[2] = mars;
				pTable[3] = ascii;
				charUnlocked.add(co);
				charUnlocked.add(mars);
				charUnlocked.add(ascii);
				eventLog.setText("\n-" + co.name + " joined against the virus!" + eventLog.getText());
				eventLog.setText("\n-" + mars.name + " joined against the virus!" + eventLog.getText());
				eventLog.setText("\n-" + ascii.name + " joined against the virus!" + eventLog.getText());
				visitGPUFirst=true;
				mode="town";
				break;
			case 38:
				textForCutscene1.setText("Yā nē!");
				textForCutscene2.setText("Anata wa sono yakkaina uirusu");
				textForCutscene3.setText("o torinozoku no o tetsudatte imasu ka?");
				picForCutscene = new ImageView(new Image("/images/cutscenes/visit-2.png"));
				break;
			case 39:
				textForCutscene1.setText("Um, excuse me?");
				textForCutscene2.setText("I am deeply sorry, but where I am");
				textForCutscene3.setText("from I don't speak that language.");
				picForCutscene = new ImageView(new Image("/images/cutscenes/visit-1.png"));
				break;
			case 40:
				textForCutscene1.setText("Sono uirusu ga kite irai,");
				textForCutscene2.setText("watashi no tantei gēmu wa");
				textForCutscene3.setText("konran jōtai ni arimashita!");
				picForCutscene = new ImageView(new Image("/images/cutscenes/visit-2.png"));
				break;
			case 41:
				textForCutscene1.setText("Huh?");
				textForCutscene2.setText("Do you wanna join us?");
				textForCutscene3.setText("");
				picForCutscene = new ImageView(new Image("/images/cutscenes/visit-1.png"));
				break;
			case 42:
				textForCutscene1.setText("Caulder daisuki!");
				textForCutscene2.setText("");
				textForCutscene3.setText("");
				picForCutscene = new ImageView(new Image("/images/cutscenes/visit-2.png"));
				break;
			case 43:
				textForCutscene1.setText("Alright then!");
				textForCutscene2.setText("I'll take that as a yes!");
				textForCutscene3.setText("(Kyzu joins the fight against the virus!)");
				picForCutscene = new ImageView(new Image("/images/cutscenes/visit-1.png"));
				break;
			case 44:
				charUnlocked.add(kyzu);
				eventLog.setText("\n-" + kyzu.name + " joined against the virus!" + eventLog.getText());
				mode="town";
				break;
			case 45:
				textForCutscene1.setText("(Nothing seems to be going on...)");
				textForCutscene2.setText("");
				textForCutscene3.setText("");
				picForCutscene = new ImageView(new Image("/images/cutscenes/black.png"));
				break;
			case 46:
				mode="town";
				break;
			case 47:
				textForCutscene1.setText("Yee!");
				textForCutscene2.setText("Stay Away!");
				textForCutscene3.setText("I'm just a computer bug!");
				picForCutscene = new ImageView(new Image("/images/cutscenes/visit-3.png"));
				break;
			case 48:
				textForCutscene1.setText("Huh?");
				textForCutscene2.setText("Aww look at you!");
				textForCutscene3.setText("You're pretty cute!");
				picForCutscene = new ImageView(new Image("/images/cutscenes/visit-4.png"));
				break;
			case 49:
				textForCutscene1.setText("Would you like to come with me?");
				textForCutscene2.setText("We are killing that nasty virus down");
				textForCutscene3.setText("in the Ramopolis area!");
				picForCutscene = new ImageView(new Image("/images/cutscenes/visit-4.png"));
				break;
			case 50:
				textForCutscene1.setText("Ooo~");
				textForCutscene2.setText("I would love to!");
				textForCutscene3.setText("I'm a lot stronger than I look!");
				picForCutscene = new ImageView(new Image("/images/cutscenes/visit-3.png"));
				break;
			case 51:
				charUnlocked.add(antt);
				eventLog.setText("\n-" + antt.name + " joined against the virus!" + eventLog.getText());
				mode="town";
				break;
		}
	}
	
	public void itemWindow(){
		GridPane gpI = new GridPane();
		Stage newWindow = new Stage();
		
		int row = 0;
		
		for (int i=0; i<itemsOnPerson.size(); i++){
			Item item = itemsOnPerson.get(i);
			Label itemText = new Label(item.name);
			ImageView itemImage = new ImageView(item.itemSpr);
			itemImage.setOnMousePressed(a -> {
				Item.useItem(item);
				if (item.isKey==false)
					itemsOnPerson.remove(item);
	
				newWindow.close();
				itemWindow();
			});
			
			if (i % 5 == 0 && i > 0){
				row+=2;
				gpI.add(itemImage,i%5,row);
				gpI.add(itemText,i%5,row+1);
			}
			else{
				gpI.add(itemImage,i%5,row);
				gpI.add(itemText,i%5,row+1);
			}
		}
		gpI.setPadding(new Insets(10, 10, 10, 10));
		gpI.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		
		Scene sceneI = new Scene(gpI,496,480);
		
		newWindow.setTitle("Items");
		newWindow.setResizable(false);
		newWindow.setScene(sceneI);
		newWindow.show();
	}
	
	public void partyWindow(PartyMember p,int id){
		GridPane gp2 = new GridPane();
		
		Button bA1 = new Button(p.atk1);
		
		bA1.setOnAction(b -> {
			if(mode=="combat") {
				//register attack
				if (currentTurn==id){
					runAttack(0);
					p.pWindow.setWidth(p.pWindow.getWidth() + 0.001);
				}
				else {
					combatLog.setText("It is not your turn, please wait.\n" + combatLog.getText());
				}
			}
		});
		
		Button bA2 = new Button(p.atk2);
		
		bA2.setOnAction(b -> {
			if(mode=="combat") {
				//register attack
				if (currentTurn==id){
					runAttack(1);
					p.pWindow.setWidth(p.pWindow.getWidth() + 0.001);
				}
				else {
					combatLog.setText("It is not your turn, please wait.\n" + combatLog.getText());
				}
			}
		});
		
		Button bA3 = new Button(p.atk3);
		
		bA3.setOnAction(b -> {
			if(mode=="combat") {
				//register attack
				if (currentTurn==id){
					runAttack(2);
					p.pWindow.setWidth(p.pWindow.getWidth() + 0.001);
				}
				else {
					combatLog.setText("It is not your turn, please wait.\n" + combatLog.getText());
				}
			}
		});
		
		ImageView pFacePlate = new ImageView(p.getFP());
		
		Text t1 = new Text("HP: \t" + p.chp + "/" + p.mhp);
		Text t2 = new Text("ATK: \t" + p.atk);
		Text t3 = new Text("DEF: \t" + p.def);
		Text t4 = new Text("Explr: \t" + p.explr);
		
		gp2.add(pFacePlate,0,0);
		gp2.add(bA1,1,2);
		gp2.add(bA2,1,3);
		//gp2.add(bA3,1,4);
		gp2.add(t1,0,1);
		gp2.add(t2,0,2);
		gp2.add(t3,0,3);
		gp2.add(t4,0,4);
		gp2.setVgap(10);
		gp2.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		gp2.setPadding(new Insets(10, 10, 10, 10));
		
		Scene scene2 = new Scene(gp2,230,300);
		
		p.pWindow.setTitle(p.name);
		p.pWindow.setScene(scene2);
		p.pWindow.setResizable(false);
		p.pWindow.show();
	}
	
	public void otherWindow(){
		//VBox vbox2 = new VBox();
		
		TextArea ta1 = new TextArea("DataMiners is a somewhat complicated game.\nThe goal is to take your party of 4 (or more if you find people willing to join\nyour cause ;] ) and take down the virus that has recently got onto your \ncomputer.\n\nGENERAL CONTROLS:\n Party Tab - This tab has the windows for each party member you have\nactive. It also has the CHANGE command, which allows you \nto change your party members as you see fit. Just click on their\nprofile picture and select where you want them to be. \n\nNOTE: YOU CAN ONLY CHANGE PARTY MEMBERS IN A TOWN.\n\nTOWN CONTROLS:\nVisit - visit the town's center to see if there is anything of use there.\nShop - Visit the town's shop and buy things!\nMap - Open the town's map and access a new location!\n\nITEM CONTROLS:\nTo access an item, simply click on it. \nAlternatively, you can press the 'use first item' button,\nwhich will use the first usable item.\n\nYou have a max of 25 items, and some items cannot be used.\n\nMAP CONTROLS:\nMoving in the map is very easy, simply click somewhere to move. \nIf you hit one of the brown tiles, you'll enter a town! \nIf you enter a red tile, you'll enter a dungeon!\n\nBe careful however, as you can enter\ncombat while travelling!\n\nCOMBAT CONTROLS:\nCombat in DataMiners is turn based.\nMeaning that you go, then the respective enemy goes, and then back to\nyou. To attack, you want to open the party member's\nwindow and then select the attack you would like to use.\n\nDon't forget to use items!\n\nTHANK YOU for playing DataMiners, have a nice day!");
		ta1.setEditable(false);
		ta1.setStyle("-fx-opacity: 1;");
		Tab howToPlayTab = new Tab("Manual",ta1);
		Tab questTab = new Tab("Current Quest",questLog);
		//Tab settingsTab = new Tab("Settings");
		Tab recentTalkTab = new Tab("Recent Text",eventLog);
		TabPane tb = new TabPane(questTab,recentTalkTab,howToPlayTab);
		
		tb.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		tb.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		
		//vbox2.getChildren().addAll(tb);
		
		Scene sceneO = new Scene(tb,496,200);
		Stage newWindow = new Stage();
		newWindow.setTitle("Other");
		newWindow.setResizable(false);
		newWindow.setScene(sceneO);
		newWindow.show();
	}
	
	public void aboutWindow(){
		GridPane gpA = new GridPane();
		
		gpA.add(new Label("Jordan Ashe"),0,0);
		gpA.add(new Label("X-X-21"),0,1);
		
		gpA.setPadding(new Insets(10, 10, 10, 10));
		gpA.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		
		Scene sceneA = new Scene(gpA,100,80);
		Stage newWindow = new Stage();
		newWindow.setTitle("About");
		newWindow.setResizable(false);
		newWindow.setScene(sceneA);
		newWindow.show();
	}
	
	public void changeParty(){
		GridPane gpCP = new GridPane();
		
		int row = 0;
		
		for (int i=0; i<charUnlocked.size(); i++){
			PartyMember pm = charUnlocked.get(i);
			Label pmText = new Label(pm.name);
			ImageView pmImage = new ImageView(pm.facePlateSpr);
			pmImage.setOnMousePressed(a -> {
				putInParty(pm);
			});
			
			if (i % 4 == 0 && i > 0){
				row+=2;
				gpCP.add(pmImage,i%4,row);
				gpCP.add(pmText,i%4,row+1);
			}
			else{
				gpCP.add(pmImage,i%4,row);
				gpCP.add(pmText,i%4,row+1);
			}
		}
		
		gpCP.setPadding(new Insets(10, 10, 10, 10));
		gpCP.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		
		Scene sceneCP = new Scene(gpCP,496,480);
		Stage newWindow = new Stage();
		newWindow.setTitle("Change Party");
		newWindow.setResizable(false);
		newWindow.setScene(sceneCP);
		newWindow.show();
	}
	
	void runAttack(int attackMode){
		GridPane gpChoose = new GridPane();
		Stage chooseWindow = new Stage();
		Button btnP1;
		Button btnP2;
		Button btnP3;
		Button btnP4;
		
		if (attackMode==0){
			btnP1 = new Button(eCombatTable[0].name);
			btnP2 = new Button(eCombatTable[1].name);
			btnP3 = new Button(eCombatTable[2].name);
			btnP4 = new Button(eCombatTable[2].name);
		}
		else{
			btnP1 = new Button(pTable[0].name);
			btnP2 = new Button(pTable[1].name);
			btnP3 = new Button(pTable[2].name);
			btnP4 = new Button(pTable[3].name);
		}
		
		
		btnP1.setOnAction(e -> {
			attack(attackMode, 0);
			chooseWindow.close();
		});
		btnP2.setOnAction(e -> {
			attack(attackMode, 1);
			chooseWindow.close();
		});
		btnP3.setOnAction(e -> {
			attack(attackMode, 2);
			chooseWindow.close();
		});
		btnP4.setOnAction(e -> {
			attack(attackMode, 3);
			chooseWindow.close();
		});
		
		gpChoose.add(new Label("Who will you Target?"),0,0);
		if (attackMode==0){
			if (!btnP1.getText().equals("blank") && eCombatTable[0].isKO()==false)
				gpChoose.add(btnP1,0,1);
				
			if (!btnP2.getText().equals("blank") && eCombatTable[1].isKO()==false)
				gpChoose.add(btnP2,0,2);
			
			if (!btnP3.getText().equals("blank") && eCombatTable[2].isKO()==false)
				gpChoose.add(btnP3,0,3);
		}
		else{
			gpChoose.add(btnP1,0,1);
			gpChoose.add(btnP2,0,2);
			gpChoose.add(btnP3,0,3);
			gpChoose.add(btnP4,0,4);
		}
		
		gpChoose.setPadding(new Insets(10, 10, 10, 10));
		gpChoose.setBackground(new Background(new BackgroundFill(Color.LEMONCHIFFON, CornerRadii.EMPTY, Insets.EMPTY)));
		
		Scene sceneI = new Scene(gpChoose,320,150);

		chooseWindow.setTitle("Choose Target");
		chooseWindow.setResizable(false);
		chooseWindow.setAlwaysOnTop(true);
		chooseWindow.setScene(sceneI);
		chooseWindow.show();
	}
	
	void attack(int attackMode,int target){
		switch(attackMode){
			case 0:
				int tempAtk = pTable[currentTurn].atk-eCombatTable[target].def;
				if (tempAtk<0)
					tempAtk=0;
				
				combatLog.setText(pTable[currentTurn].name + " Dealt " + tempAtk + " damage to " + eCombatTable[target].name + "!\n" + combatLog.getText());
				eCombatTable[target].statsDown(0, pTable[currentTurn].atk);
				if (eCombatTable[target].isKO()){
					combatLog.setText(eCombatTable[target].name + " was knocked out!\n" + combatLog.getText());
					switch (target) {
						case 0:
							cEnemyPic1.setVisible(false);
							break;
						case 1:
							cEnemyPic2.setVisible(false);
							break;
						case 2:
							cEnemyPic3.setVisible(false);
							break;
					}
				}
				break;
			case 1:
				combatLog.setText(pTable[currentTurn].name + " healed " + pTable[currentTurn].explr + " damage to " + pTable[target].name + "!\n" + combatLog.getText());
				pTable[target].statsUp(0, pTable[currentTurn].explr);
				
				partyWindow(pTable[target], target);
				break;
			case 2:
				combatLog.setText(pTable[currentTurn].name + " Dealt " + pTable[currentTurn].atk + " damage to " + eCombatTable[target].name + "!\n" + combatLog.getText());
				eCombatTable[target].statsDown(0, pTable[currentTurn].atk);
				if (eCombatTable[target].isKO()){
					combatLog.setText(eCombatTable[target].name + " was knocked out!\n" + combatLog.getText());
					switch (target) {
						case 0:
							cEnemyPic1.setVisible(false);
							break;
						case 1:
							cEnemyPic2.setVisible(false);
							break;
						case 2:
							cEnemyPic3.setVisible(false);
							break;
					}
				}
				break;
		}
		if (currentTurn<=2){
			//run enemy, next turn
			if(!(eCombatTable[currentTurn].isKO()) && (eCombatTable[currentTurn] != blank)){
				int variable = rand.nextInt(4); 
				pTable[variable].statsDown(0, eCombatTable[currentTurn].atk);
				
				int tempAtk = eCombatTable[currentTurn].atk-pTable[variable].def;
				
				if (tempAtk<0)
					tempAtk=0;
				
				combatLog.setText(eCombatTable[currentTurn].name + " Dealt " + tempAtk + " to " + pTable[variable].name + "!\n" +combatLog.getText());
				
				if (pTable[variable].isKO()){
					combatLog.setText(pTable[variable].name + " was knocked out!\n"+combatLog.getText());
				}
				
				partyWindow(pTable[variable], variable);
			}
			
			currentTurn++;
			
			while (pTable[currentTurn].isKO()){
				combatLog.setText(pTable[currentTurn].name + " is still knocked out!\n" + combatLog.getText());
				
				if(currentTurn!=3){
					if(!(eCombatTable[currentTurn].isKO()) && (eCombatTable[currentTurn] != blank)){
						int variable = rand.nextInt(4); 
						pTable[variable].statsDown(0, eCombatTable[currentTurn].atk);
						
						int tempAtk = eCombatTable[currentTurn].atk-pTable[variable].def;
						
						if (tempAtk<0)
							tempAtk=0;
						
						combatLog.setText(eCombatTable[currentTurn].name + " Dealt " + tempAtk + " to " + pTable[variable].name + "!\n" + combatLog.getText());
						
						if (pTable[variable].isKO()){
							combatLog.setText(pTable[variable].name + " was knocked out!\n" + combatLog.getText());
						}
						
						partyWindow(pTable[variable], variable);
					}
					currentTurn++;
				}
				else{
					currentTurn=0;
				}
			}
			
			combatLog.setText("It is now " + pTable[currentTurn].name + "'s Turn!\n" + combatLog.getText());
		}
		else{
			currentTurn=0;
			
			combatLog.setText("It is now " + pTable[currentTurn].name + "'s Turn!\n" + combatLog.getText());
		}
		
		
		//if the party won or not
		if (eCombatTable[0].isKO() && eCombatTable[1].isKO() && eCombatTable[2].isKO()) {
			mode=tempMode;
			modeMachine();
		} 
		else if (pTable[0].isKO() && pTable[1].isKO() && pTable[2].isKO() && pTable[3].isKO()){
			mode="game over";
			modeMachine();
		}
	}
	
	public String townInfo(String townName){
		if (townName.equals("ugpu")){
			return "This town is known for their exports of graphic tiles. \nThey are very friendly once you get to know them, \n and they are the least affected by the virus of the towns\non your computer.";
		}
		else if (townName.equals("harddriveton")){
			return "HardDriveTon is most known for their hording. \nThey collect everything you can find and then some. \nIf you want somemthing, you will to do some begging and bargining for it.";
		}
		else if (townName.equals("trashbin")){
			return "A dungeon crawling with lower level enemies that have been scrapped. \nMight be something of worth down there.";
		}
		else if (townName.equals("newboardcity")){
			return "This Town was recently build as of this year.\nIt was constructed from the ruins of the prevous town, Board City.\n It's a vast and massive city with people from all over the computer.";
		}
		else{
			return "yeah.";
		}
	}
	
	String getBack(){
		if (tempMode=="map"){
			return "/images/battlebacks/map.png";
		}
		else if (tempMode=="dungeonReward"){
			return "/images/battlebacks/dungeonReward.png";
		}
		else if (tempMode=="indungeon"){
			switch (cTown) {
				case "trashbin":
					return "/images/battlebacks/trashbin.png";
				default:
					return "/images/battlebacks/blank.png";
			}
		}
		return "/images/battlebacks/blank.png";
	}	
	
	public void putInParty(PartyMember pm){
		if (pTable[0] != pm && pTable[1] != pm && pTable[2] != pm && pTable[3] != pm){
			GridPane gpParty = new GridPane();
			Stage changePartyWindow = new Stage();
			
			Button btnP1 = new Button("Slot 1");
			Button btnP2 = new Button("Slot 2");
			Button btnP3 = new Button("Slot 3");
			Button btnP4 = new Button("Slot 4");
			
			btnP1.setOnAction(e -> {
				double temp1 = pTable[0].pWindow.getX();
				double temp2 = pTable[0].pWindow.getY();
				pTable[0].pWindow.close();
				pTable[0] = pm;
				pTable[0].pWindow.setX(temp1);
				pTable[0].pWindow.setY(temp2);
				partyWindow(pTable[0], 0);
				changePartyWindow.close();
			});
			btnP2.setOnAction(e -> {
				double temp1 = pTable[1].pWindow.getX();
				double temp2 = pTable[1].pWindow.getY();
				pTable[1].pWindow.close();
				pTable[1] = pm;
				pTable[1].pWindow.setX(temp1);
				pTable[1].pWindow.setY(temp2);
				partyWindow(pTable[1], 1);
				changePartyWindow.close();
			});
			btnP3.setOnAction(e -> {
				double temp1 = pTable[2].pWindow.getX();
				double temp2 = pTable[2].pWindow.getY();
				pTable[2].pWindow.close();
				pTable[2] = pm;
				pTable[2].pWindow.setX(temp1);
				pTable[2].pWindow.setY(temp2);
				partyWindow(pTable[2], 2);
				changePartyWindow.close();
			});
			btnP4.setOnAction(e -> {
				double temp1 = pTable[3].pWindow.getX();
				double temp2 = pTable[3].pWindow.getY();
				pTable[3].pWindow.close();
				pTable[3] = pm;
				pTable[3].pWindow.setX(temp1);
				pTable[3].pWindow.setY(temp2);
				partyWindow(pTable[3], 3);
				changePartyWindow.close();
			});
			
			gpParty.add(new Label("Which slot would you like to place them?"),0,0);
			gpParty.add(new Label(pm.name + "\nHP: \t\t" + pm.chp + "/" + pm.mhp),0,1);
			gpParty.add(new Label("ATK: \t" + pm.atk),0,2);
			gpParty.add(new Label("DEF: \t" + pm.def),0,3);
			gpParty.add(new Label("Explr: \t" + pm.explr),0,4);
			gpParty.add(btnP1,0,5);
			gpParty.add(new Label(pTable[0].name),1,5);
			gpParty.add(btnP2,0,6);
			gpParty.add(new Label(pTable[1].name),1,6);
			gpParty.add(btnP3,0,7);
			gpParty.add(new Label(pTable[2].name),1,7);
			gpParty.add(btnP4,0,8);
			gpParty.add(new Label(pTable[3].name),1,8);
			
			gpParty.add(new Label("Note: You may have to refresh their party slot."),0,9);
			
			gpParty.setPadding(new Insets(10, 10, 10, 10));
			gpParty.setBackground(new Background(new BackgroundFill(Color.LEMONCHIFFON, CornerRadii.EMPTY, Insets.EMPTY)));
			
			Scene sceneP = new Scene(gpParty,360,320);

			changePartyWindow.setTitle("Place them where?");
			changePartyWindow.setResizable(false);
			changePartyWindow.setAlwaysOnTop(true);
			changePartyWindow.setScene(sceneP);
			changePartyWindow.show();
		}
		else{
			VBox cpItem = new VBox();
			Label errorLabel = new Label("They are already in the party!");
			Button btnMyBad = new Button("My bad.");
			Stage changePartyWindow = new Stage();
			
			btnMyBad.setOnAction(e -> {
				changePartyWindow.close();
			});
			
			cpItem.getChildren().addAll(errorLabel,btnMyBad);
			cpItem.setAlignment(Pos.TOP_CENTER);
			
			Scene sceneCP = new Scene(cpItem,200,60);
			changePartyWindow.setTitle("Already in Party Error");
			changePartyWindow.setResizable(false);
			changePartyWindow.setAlwaysOnTop(true);
			changePartyWindow.setScene(sceneCP);
			changePartyWindow.show();
		}
	}
	
	static class Dungeon {
		static Random randomizer = new Random();
		String name;
		String fileName;
		int events;
		Image placeSpr;
		int tempEvents;
		
		Dungeon(String newName, int maxEvents){
			this.name = newName;
			this.fileName = newName.toLowerCase().trim();
			this.events = maxEvents;
			this.placeSpr = new Image("/images/locations/map/" + this.fileName + ".png");
			this.tempEvents = this.events;
		}
		
		//utrigger event (DO LATER)
		static void event(Dungeon d){
			if (d.tempEvents>0){
				GridPane gpEvent = new GridPane();
				Stage eventWindow = new Stage();
				int event = randomizer.nextInt(5);
				Label tempLabel1 = new Label("You were Ambushed!");
				Label tempLabel2 = new Label();
				
				switch (d.name) {
					case "TrashBin":
						switch (event) {
							case 0:
								if(DataMiners.pTable[0].explr+DataMiners.pTable[1].explr+DataMiners.pTable[2].explr+DataMiners.pTable[3].explr > 40){
									tempLabel1 = new Label("Do you Wish to Encounter:");
									tempLabel2 = new Label("\tStray Code");
								}
								else{
									eventWindow.close();
									DataMiners.combat(1,2,0);
								}
								break;
							case 1:
								tempLabel1 = new Label("You found a HealthPotion!");
								tempLabel2 = new Label("\tWill you take it?");
								break;
							case 2:
								tempLabel1 = new Label("You found a AttackFruit!");
								tempLabel2 = new Label("\tWill you take it?");
								break;
							case 3:
								if (DataMiners.pTable[0].explr+DataMiners.pTable[1].explr+DataMiners.pTable[2].explr+DataMiners.pTable[3].explr > 50){
									tempLabel1 = new Label("Do you Wish to Encounter:");
									tempLabel2 = new Label("\tNavyWindCO and Co.");
								}
								else{
									eventWindow.close();
									DataMiners.combat(1,4,2);
								}
								break;
							case 4:
								if (DataMiners.pTable[0].explr+DataMiners.pTable[1].explr+DataMiners.pTable[2].explr+DataMiners.pTable[3].explr > 60){
									tempLabel1 = new Label("Do you Wish to Encounter:");
									tempLabel2 = new Label("\tHaunted Code");
								}
								else{
									eventWindow.close();
									DataMiners.combat(2,0,0);
								}
								break;
						}		
						break;
				}
				
				Button btnP1 = new Button("Yes!");
				Button btnP2 = new Button("No!");
				
				btnP1.setOnAction(e -> {
					switch (event) {
						case 0:
							DataMiners.combat(1,2,0);
							break;
						case 1:
							DataMiners.itemsOnPerson.add(new Item("HealingPotion",false,0,10));
							break;
						case 2:
							DataMiners.itemsOnPerson.add(new Item("AttackFruit",false,1,5));
							break;
						case 3:
							DataMiners.combat(1,4,2);
							break;
						case 4:
							DataMiners.combat(2,0,0);
							break;
							
					}
					
					eventWindow.close();
				});
				
				btnP2.setOnAction(e -> eventWindow.close());
				
				gpEvent.add(tempLabel1,0,0);
				gpEvent.add(tempLabel2,0,1);
				if (mode!="combat"){
					gpEvent.add(btnP1,0,2);
					gpEvent.add(btnP2,0,3);
				}
				else{
					gpEvent.add(btnP2,0,2);
				}
				
				gpEvent.setPadding(new Insets(10, 10, 10, 10));
				gpEvent.setBackground(new Background(new BackgroundFill(Color.LEMONCHIFFON, CornerRadii.EMPTY, Insets.EMPTY)));
				
				Scene sceneE = new Scene(gpEvent,320,150);
				
				eventWindow.setOnCloseRequest(e -> e.consume());
				
				d.tempEvents-=1;
				
				eventWindow.setTitle("Event:");
				eventWindow.setResizable(false);
				eventWindow.setAlwaysOnTop(true);
				eventWindow.setScene(sceneE);
				eventWindow.show();
			}
			else{
				/*
				register quests:
				dungeon names:
				-The Deep Web
				-The Center of the Virus
				-The Terminal
				-The Music Folder
				-The Trash Bin
				-The Depths of the Settings
				-The Documents Pile
				-The Gaming Center
				-The Downloads Folder
				-The Cloud
				*/
				switch(DataMiners.cQuest){
					case "Descend Below":
						combat(2, 3, 1);
						DataMiners.quest1complete = true;
						tempMode="dungeonReward";
						break;
					case "Dig Through Trash":
						combat(0, 9, 0);
						DataMiners.quest2complete = true;
						tempMode="dungeonReward";
						break;
					default:
						mode="dungeonReward";
				}
			}
		}
	}
}