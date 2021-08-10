import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.stage.Stage;
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
		-MAP FUNCTIONALITY 
		-ABILITY TO CHANGE PARTY MEMBERS (DONE)
	-MAP:
		-SPRITES
		-TEXT
		-ABILITY INTERACTION
		-COMBAT STARTING
		-DUNGEONS
			-SPRITES (0/10)
			-EVENTS
			-MAP INTERACTION
	-COMBAT:
		-SPRITES FOR ENEMIES (DONE)
		-SPRITES FOR CHARACTERS (9/20) --Kook is making one, hound him.
		-ITEM FUNCIONALITY
		-TEXT FOR HOW COMBAT IS GOING
		-ABILITY INTERACTION
	-STORY: (KINDA DONE)
		-YEAH YOU SHOULD PROBABLY WRITE THAT LMAO (OK KINDA DONE)
	-OTHER TAB:
		-QUEST TAB (DONE)
		-SETTINGS TAB (MIGHT DELETE IDK)
		-EVENT LOG (DONE)
		-MANUAL (FINISH THE MAP AND COMBAT ONES PLS THANKS)
*/

public class DataMiners extends Application {   
	static PartyMember[] pTable = new PartyMember[4];
	ArrayList<PartyMember> charUnlocked = new ArrayList<PartyMember>();
	static Enemy[] eCombatTable = new Enemy[4];
	
	//starting characters, name, atk, def, explor, hp, dmg attack, support atk, exploration atk
	PartyMember caulder = new PartyMember("Caulder", 3, 5, 10, 20, "Endure", "Heal", "Map");
	PartyMember co = new PartyMember("CO", 5, 5, 5, 25, "Fire!", "Cover!", "Capture!");
	PartyMember mars = new PartyMember("Mars", 8, 6, 3, 20, "Slash", "War Cry", "Scale");
	PartyMember ascii = new PartyMember("ASCII", 10, 2, 1, 20, "Use Sword", "Use Shield", "Go There");
	
	//other party members
	PartyMember kyzu = new PartyMember("Kyzu", 15, 2, 10, 10, "Kōgeki", "Hashiru", "En'eki");
	
	//enemies, name, atk, def, hp
	ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
	Enemy blank = new Enemy("blank",0,0,0);
	
	//UGPU enemies
	Enemy slimyCode = new Enemy("SlimyCode",2,2,5);
	Enemy viralOfficer = new Enemy("ViralOfficer",4,7,15);
	Enemy hauntedCode = new Enemy("HauntedCode",6,1,7);
	Enemy navyWindCO = new Enemy("NavyWindCO",1,5,10);
	
	
	static ArrayList<Item> itemsOnPerson = new ArrayList<Item>();
	String mode = "intro";
	String cTown = "ugpu";
	String cTownName = "UGPU";
	String cQuest = "N/A";
	String cQuestInfo = "N/A";
	String cQuestEnemy = "N/A";
	MenuBar menuBarCombat = new MenuBar();
	MenuBar menuBarTown = new MenuBar();
	GridPane town = new GridPane();
	VBox mainVBox = new VBox();
	HBox hboxEnemies = new HBox();
	Label textForCutscene1 = new Label("This, is a computer. ");
	Label textForCutscene2 = new Label("It may be exactly like yours, or it may not.");
	Label textForCutscene3 = new Label("");
	ImageView picForCutscene = new ImageView(new Image("/images/cutscenes/intro-1.png"));
	ImageView picForTown = new ImageView(new Image("/images/locations/" + cTown + ".png"));
	Button btnIntro = new Button("Next...");
	Button btnVisit = new Button("Visit");
	Button btnShop = new Button("Shop");
	Button btnMap = new Button("Map");
	Button btnQuest = new Button("Quest");
	Button btnAcceptQuest = new Button("Accept");
	int textIntro = 0;
	boolean visitGPUFirst = false;
	boolean quest1complete = false;
	static boolean inCombat = false;
	static boolean pTurn = true;
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
		
		itemsOnPerson.add(new Item("HealingPotion",false,1,10));
		
		eventLog.setText(eventLog.getText() + "\n-Booted the program.");
		eventLog.setEditable(false);
		eventLog.setStyle("-fx-opacity: 1;");
		questLog.setEditable(false);
		questLog.setStyle("-fx-opacity: 1;");
		
		//-----------------COMBAT------------------
		Menu menuParty = new Menu("Party");
		Menu menuItems = new Menu("Items");
		Menu menuOther = new Menu("Other Tab");
		Menu menuAbout = new Menu("About");
		MenuItem menuItemOther = new MenuItem("Open Others Tab");
		MenuItem menuItemAbout = new MenuItem("About this Game");
		menuItemOther.setOnAction(e -> otherWindow());
		menuItemAbout.setOnAction(e-> aboutWindow());
		menuOther.getItems().add(menuItemOther);
		menuAbout.getItems().add(menuItemAbout);
		
		menuBarCombat.getMenus().addAll(menuParty,menuItems);
		menuBarTown.getMenus().addAll(menuParty,menuItems,menuOther,menuAbout);

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
		
		//party tab
		MenuItem menuItemChange = new MenuItem("Change Party Members");
		menuParty.getItems().add(menuItemChange);
		
		menuItemChange.setOnAction(e -> changeParty());
		
		MenuItem menuItemP1 = new MenuItem("Party Member Slot 1");
		menuParty.getItems().add(menuItemP1);
		
		menuItemP1.setOnAction(e -> partyWindow(pTable[0]));
		
		MenuItem menuItemP2 = new MenuItem("Party Member Slot 2");
		menuParty.getItems().add(menuItemP2);
		
		menuItemP2.setOnAction(e -> partyWindow(pTable[1]));
		
		MenuItem menuItemP3 = new MenuItem("Party Member Slot 3");
		menuParty.getItems().add(menuItemP3);
		
		menuItemP3.setOnAction(e -> partyWindow(pTable[2]));
				
		MenuItem menuItemP4 = new MenuItem("Party Member Slot 4");
		menuParty.getItems().add(menuItemP4);
		
		menuItemP4.setOnAction(e -> partyWindow(pTable[3]));
		
		//-------------------MAP----------------------
		
		//-------------------EXTRAS TAB---------------
		MenuItem menuItemItems = new MenuItem("Items");
		menuItems.getItems().add(menuItemItems);

		menuItemItems.setOnAction(e -> itemWindow());
		
		
		
		//-------------------TOWN---------------------
		btnVisit.setOnAction(e -> {
			visit(cTown);
		});
		
		btnShop.setOnAction(e -> {
			shop(cTown);
		});
		
		btnMap.setOnAction(e->{
			mode="map";
			modeMachine();
		});
		
		btnQuest.setOnAction(e->{
			quest(cTown);
		});
		
		Label townName = new Label(cTownName);
		townName.setFont(new Font(20.0));
		
		town.add(picForTown,0,0);
		town.add(new Label("Where to, sir?"),0,1);
		town.add(townName,1,0);
		town.add(btnVisit,1,1);
		town.add(btnQuest,0,2);
		town.add(btnShop,1,2);
		town.add(btnMap,1,3);
		town.setVgap(4);
		town.setHgap(4);
		town.setPadding(new Insets(0, 10, 10, 10));
		
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
		
		Scene scene = new Scene(mainVBox, 350, 340);
		
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
				mainVBox.getChildren().addAll(menuBarCombat,hboxEnemies,combatLog);
				break;
			case "town":		
				mainVBox.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, CornerRadii.EMPTY, Insets.EMPTY)));
				picForTown = new ImageView(new Image("/images/locations/" + cTown + ".png"));
				mainVBox.getChildren().addAll(menuBarTown,town);
				break;
			case "visit":
				mainVBox.getChildren().addAll(picForCutscene,textForCutscene1,textForCutscene2,textForCutscene3,btnIntro);
				break;
		}
	}
	
	void registerButton(String ability){
		switch (mode) {
			case "combat":
				//register attack
				if (pTurn==true){
					int tempValue = 0;
					switch (ability) {
						//attack abilities
						case "Endure":
						case "Fire!":
						case "Slash":
						case "Use Sword":
						case "Kōgeki":
							
							break;
						//Def abilities
						case "Heal":
						case "Cover!":
						case "War Cry":
						case "Use Shield":
						case "Hashiru":
						
							break;
						//Explore abilities
						case "Map":
						case "Capture!":
						case "Scale":
						case "Go There":
						case "En'eki":
						
							break;
					}
				}
				else {
					combatLog.setText(combatLog.getText() + "It is not your turn, please wait.");
				}
				break;
			case "town":
				//register map movement
				break;
		}
	}
	
	void combat(int e1, int e2, int e3, int e4){
		mode="combat";
		Enemy temp1 = enemyList.get(e1);
		Enemy temp2 = enemyList.get(e2);
		Enemy temp3 = enemyList.get(e3);
		Enemy temp4 = enemyList.get(e4);
		
		hboxEnemies.getChildren().addAll(new ImageView(temp1.btleSpr),new ImageView(temp2.btleSpr),new ImageView(temp3.btleSpr),new ImageView(temp4.btleSpr));
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
		}
		
		eventLog.setText(eventLog.getText() + "\n-Visited " + cTownName + "'s Center.");
	}
	
	void quest(String currentTown){
		GridPane gpQ = new GridPane();
		Stage newWindow = new Stage();
		
		switch (currentTown) {
			case "ugpu":
				if (quest1complete == false){
					gpQ.add(new Label("Descend Below - Grandma Calculator:\n\tThey say below the town there is a virus that has taken root of\nthe old storages down there.\n\nI guess what I am asking is easy to follow, but could\nyou be a dearie and slay that nasty and mean Viral Officer?\n\n(Defeat one Viral Officer in the Trash Bin Dungeon)"),0,1);
					
					btnAcceptQuest.setOnAction(e -> {
						cQuest = "Descend Below";
						cQuestInfo = "Below the town, in the Cellar Dungeon, a Viral Officer has set up camp!";
						cQuestEnemy = "viralofficer";
						questLog.setText(cQuest + "\n" + cQuestInfo);
					});
					
					gpQ.add(btnAcceptQuest,0,2);
				}
				else{
					gpQ.add(new Label("No Quest Availble:\n\tSorry, but you have already completed the quest here."),0,1);
				}
		}
		
		gpQ.setPadding(new Insets(10, 10, 10, 10));
		gpQ.setBackground(new Background(new BackgroundFill(Color.LIGHTGOLDENRODYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
		
		Scene sceneQ = new Scene(gpQ,496,480);
		
		newWindow.setTitle("Availible Quests - " + cTownName);
		newWindow.setResizable(false);
		newWindow.setScene(sceneQ);
		newWindow.show();
		eventLog.setText(eventLog.getText() + "\n-Visited " + cTownName + "'s Quest Board.");
	}
	
	void shop(String currentTown){
		
	}
	
	public void advanceText(){
		textIntro++;
		switch (textIntro) {
			case 1:
				textForCutscene1.setText("The important part is...");
				textForCutscene2.setText("that if this is the inside of your computer,");
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
				eventLog.setText(eventLog.getText() + "\n-" + co.name + " joined against the virus!");
				eventLog.setText(eventLog.getText() + "\n-" + mars.name + " joined against the virus!");
				eventLog.setText(eventLog.getText() + "\n-" + ascii.name + " joined against the virus!");
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
				eventLog.setText(eventLog.getText() + "\n-" + kyzu.name + " joined against the virus!");
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
	
	public void partyWindow(PartyMember p){
		GridPane gp2 = new GridPane();
		
		Button bA1 = new Button(p.atk1);
		
		bA1.setOnAction(b -> {
			registerButton(p.atk1);
		});
		
		Button bA2 = new Button(p.atk2);
		
		bA2.setOnAction(b -> {
			registerButton(p.atk2);
		});
		
		Button bA3 = new Button(p.atk3);
		
		bA3.setOnAction(b -> {
			registerButton(p.atk3);	
		});
		
		ImageView pFacePlate = new ImageView(p.getFP());
		
		gp2.add(pFacePlate,0,0);
		gp2.add(bA1,1,2);
		gp2.add(bA2,1,3);
		gp2.add(bA3,1,4);
		gp2.add(new Label("HP: \t" + p.chp + "/" + p.mhp),0,1);
		gp2.add(new Label("ATK: \t" + p.atk),0,2);
		gp2.add(new Label("DEF: \t" + p.def),0,3);
		gp2.add(new Label("Explr: \t" + p.explr),0,4);
		gp2.setVgap(10);
		gp2.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		gp2.setPadding(new Insets(10, 10, 10, 10));
		
		Scene scene2 = new Scene(gp2,230,300);
		
		Stage pWindow = new Stage();
		pWindow.setTitle(p.name);
		pWindow.setScene(scene2);
		pWindow.setResizable(false);
		pWindow.show();
	}
	
	public void otherWindow(){
		//VBox vbox2 = new VBox();
		
		TextArea ta1 = new TextArea("DataMiners is a somewhat complicated game.\nThe goal is to take your party of 4 (or more if you find people willing to join\nyour cause ;] ) and take down the virus that has recently got onto your \ncomputer.\n\nGENERAL CONTROLS:\n Party Tab - This tab has the windows for each party member you have\nactive. It also has the CHANGE command, which allows you \nto change your party members as you see fit. Just click on their\nprofile picture and select where you want them to be. \n\nNOTE: YOU CAN ONLY CHANGE PARTY MEMBERS IN A TOWN.\n\nTOWN CONTROLS:\nVisit - visit the town's center to see if there is anything of use there.\nShop - Visit the town's shop and buy things!\nMap - Open the town's map and access a new location!\n\nITEM CONTROLS:\nTo access an item, simply click on it. \nAlternatively, you can press the 'use first item' button,\nwhich will use the first usable item.\n\nYou have a max of 25 items, and some items cannot be used.\n\nMAP CONTROLS:\nthis is where the map controls will go.\n\nCOMBAT CONTROLS:\nThis is where the combat controls will go.\n\nThank you for playing DataMiners, have a nice day!");
		ta1.setEditable(false);
		ta1.setStyle("-fx-opacity: 1;");
		Tab howToPlayTab = new Tab("Manual",ta1);
		Tab questTab = new Tab("Current Quest",questLog);
		Tab settingsTab = new Tab("Settings");
		Tab recentTalkTab = new Tab("Recent Text",eventLog);
		TabPane tb = new TabPane(questTab,recentTalkTab,howToPlayTab,settingsTab);
		
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
				PartyMember.putInParty(pm);
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
		newWindow.setTitle("About");
		newWindow.setResizable(false);
		newWindow.setScene(sceneCP);
		newWindow.show();
	}
}

