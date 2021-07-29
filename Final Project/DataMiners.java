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


public class DataMiners extends Application {   
	//starting characters, name, atk, def, explor, hp, dmg attack, support atk, exploration atk
	PartyMember[] pTable = new PartyMember[4];
	ArrayList<PartyMember> charUnlocked = new ArrayList<PartyMember>();
	PartyMember caulder = new PartyMember("Caulder", 3, 5, 10, 20, "Endure", "Heal", "Map");
	PartyMember co = new PartyMember("CO", 5, 5, 5, 25, "Fire!", "Cover!", "Capture!");
	PartyMember mars = new PartyMember("Mars", 8, 6, 3, 20, "Slash", "War Cry", "Scale");
	PartyMember ascii = new PartyMember("ASCII", 10, 2, 1, 20, "Use Sword", "Use Shield", "Go There");
	ArrayList<Item> itemsOnPerson = new ArrayList<Item>();
	
	
	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {  
		pTable[0] = caulder;
		pTable[1] = co;
		pTable[2] = mars;
		pTable[3] = ascii;
		itemsOnPerson.add(new Item("HealingPotion",false,1,10));
		
		TabPane tabPane = new TabPane();	
		
		MenuBar menuBar = new MenuBar();

		Menu menuParty = new Menu("Party");
		Menu menuItems = new Menu("Items");
		menuBar.getMenus().addAll(menuParty,menuItems);

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
		
		Button btnStart = new Button("Start");
		
		VBox vbox = new VBox();
		vbox.getChildren().addAll(menuBar);
		
		Scene scene = new Scene(vbox, 350, 300);  
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

	public void partyWindow(PartyMember p){
		
		GridPane gp2 = new GridPane();
		
		Button bA1 = new Button(p.atk1);
		
		bA1.setOnAction(b -> {
			System.out.println("Youch!");
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
}

