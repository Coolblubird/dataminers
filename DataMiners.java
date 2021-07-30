import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.stage.Stage;
import javafx.geometry.*;
import javafx.scene.image.*;
import java.util.*;

/*
	TO DO:
	-INTRO:
		-SPRITES
		-TEXT
		-TRANSITION TO FIRST TOWN
	-TOWNS:
		-SPRITES (3/6) (Viral Center, Motherboard, and the Storage device)
		-TEXT (0/5)
		-MAP FUNCTIONALITY 
	-MAP:
		-SPRITES
		-TEXT
		-ABILITY INTERACTION
		-COMBAT STARTING
	-COMBAT:
		-SPRITES FOR ENEMIES (10/20)
		-SPRITES FOR CHARACTERS (5/20)
		-TEXT FOR HOW COMBAT IS GOING
		-ABILITY INTERACTION
	-STORY:
		-YEAH YOU SHOULD PROBABLY WRITE THAT LMAO
*/

public class DataMiners extends Application {   
	//starting characters, name, atk, def, explor, hp, dmg attack, support atk, exploration atk
	PartyMember[] pTable = new PartyMember[4];
	ArrayList<PartyMember> charUnlocked = new ArrayList<PartyMember>();
	PartyMember caulder = new PartyMember("Caulder", 3, 5, 10, 20, "Endure", "Heal", "Map");
	PartyMember co = new PartyMember("CO", 5, 5, 5, 25, "Fire!", "Cover!", "Capture!");
	PartyMember mars = new PartyMember("Mars", 8, 6, 3, 20, "Slash", "War Cry", "Scale");
	PartyMember ascii = new PartyMember("ASCII", 10, 2, 1, 20, "Use Sword", "Use Shield", "Go There");
	ArrayList<Item> itemsOnPerson = new ArrayList<Item>();
	String mode = "intro";
	String cTown = "gpc";
	MenuBar menuBarCombat = new MenuBar();
	GridPane town = new GridPane();
	VBox vbox = new VBox();
	Label textForCutscene1 = new Label("This, is a computer. ");
	Label textForCutscene2 = new Label("It may be exactly like yours, or it may not.");
	Label textForCutscene3 = new Label("");
	ImageView picForCutscene = new ImageView(new Image("/images/cutscenes/intro-1.png"));
	Button btnIntro = new Button("Next...");
	int textIntro = 0;
	
	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {  
		pTable[0] = caulder;
		pTable[1] = co;
		pTable[2] = mars;
		pTable[3] = ascii;
		charUnlocked.add(caulder);
		charUnlocked.add(co);
		charUnlocked.add(mars);
		charUnlocked.add(ascii);
		itemsOnPerson.add(new Item("HealingPotion",false,1,10));
		
		
		//-----------------COMBAT------------------
		Menu menuParty = new Menu("Party");
		Menu menuItems = new Menu("Items");
		menuBarCombat.getMenus().addAll(menuParty,menuItems);

		//items tab
		MenuItem menuItemUseFirst = new MenuItem("Use First Item");
		menuItems.getItems().add(menuItemUseFirst);

		menuItemUseFirst.setOnAction(e -> {
			//use the first usable item in the players inventory
		});
		
		MenuItem menuItemOther = new MenuItem("Items");
		menuItems.getItems().add(menuItemOther);

		menuItemOther.setOnAction(e -> {
			GridPane gpI = new GridPane();
			
			int row = 0;
			
			for (int i=0; i<itemsOnPerson.size(); i++){
				Label itemText = new Label(itemsOnPerson.get(i).name);
				ImageView itemImage = new ImageView(itemsOnPerson.get(i).itemSpr);
				
				
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
			
			Stage newWindow = new Stage();
			newWindow.setTitle("Items");
			newWindow.setResizable(false);
			newWindow.setScene(sceneI);
			newWindow.show();
		});
		
		//party tab
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
		
		//-------------------INTRO SCENE--------------
		btnIntro.setOnAction(e -> {
			advanceText();
			modeMachine();
		});
		
		//-------------------TITLE--------------------
		ImageView menulogo = new ImageView(new Image("/images/menulogo.png"));
		
		Button btnStart = new Button("Start");
		btnStart.setOnAction(e -> modeMachine());
		
		vbox.getChildren().addAll(menulogo,btnStart);
		
		vbox.setBackground(new Background(new BackgroundFill(Color.HONEYDEW, CornerRadii.EMPTY, Insets.EMPTY)));
		vbox.setAlignment(Pos.TOP_CENTER);
		
		Scene scene = new Scene(vbox, 350, 340);  
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
		vbox.getChildren().clear();
		switch (mode) {
			case "intro":
				vbox.getChildren().addAll(picForCutscene,textForCutscene1,textForCutscene2,textForCutscene3,btnIntro);
				break;
			case "combat":
				vbox.getChildren().addAll(menuBarCombat);
				break;
			case "town":
				vbox.getChildren().addAll(town);
				break;
		}
	}
	
	
	public void partyWindow(PartyMember p){
		
		GridPane gp2 = new GridPane();
		
		Button bA1 = new Button(p.atk1);
		
		bA1.setOnAction(b -> {
			System.out.println(bA1.getText());
		});
		
		Button bA2 = new Button(p.atk2);
		
		bA2.setOnAction(b -> {
			System.out.println("Oof!");
		});
		
		Button bA3 = new Button(p.atk3);
		
		bA3.setOnAction(b -> {
			System.out.println("Ow!");
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
		
		Stage newWindow = new Stage();
		newWindow.setTitle(p.name);
		newWindow.setScene(scene2);
		newWindow.setResizable(false);
		newWindow.show();
	}
	
	void registerButton(String ability){
		switch (mode) {
			case "combat":
				//register attack
				break;
			case "town":
				//register map movement
				break;
		}
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
				textForCutscene2.setText("at the Graphics Processing Center.");
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
		}
	}
}

