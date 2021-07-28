import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.stage.Stage;
import javafx.geometry.*;
import javafx.scene.image.*;


public class DataMiners extends Application {   
	//starting characters, name, atk, def, explor, hp, dmg attack, support atk, exploration atk
	PartyMember caulder = new PartyMember("Caulder", 3, 5, 10, 20, "Endure", "Heal", "Map");
	PartyMember co = new PartyMember("CO", 5, 5, 5, 25, "Fire!", "Cover!", "Capture!");
	PartyMember mars = new PartyMember("Mars", 8, 6, 3, 20, "Slash", "War Cry", "Scale");
	PartyMember ascii = new PartyMember("ASCII", 10, 8, 1, 18, "Attack Enemy", "Use Shield", "Go There");
	PartyMember professormoney = new PartyMember("ProfessorMoney", 1, 1, 3, 30, "Coin Cannon", "Make it Rain", "First Class");
	PartyMember p1 = caulder;
	PartyMember p2 = co;
	PartyMember p3 = mars;
	PartyMember p4 = ascii;
	
	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {  
		TabPane tabPane = new TabPane();	
		
		MenuBar menuBar = new MenuBar();

		Menu menuTabs = new Menu("Party");
		menuBar.getMenus().addAll(menuTabs);

		//items box
		/*		
		MenuItem menuItemOther = new MenuItem("Items");
		menuTabs.getItems().add(menuItemOther);

		menuItemOther.setOnAction(e -> {
			GridPane gp2 = new GridPane();
			
			Label itemText = new Label("apple");
			gp2.add(itemText,0,0);
			
			Scene scene2 = new Scene(gp2,306,100);
			
			Stage newWindow = new Stage();
			newWindow.setTitle("Tax");
			newWindow.setScene(scene2);
			
			newWindow.show();
		});
		*/
		
		MenuItem menuItemP1 = new MenuItem("Party Member Slot 1");
		menuTabs.getItems().add(menuItemP1);
		
		menuItemP1.setOnAction(e -> partyWindow(p1));
		
		MenuItem menuItemP2 = new MenuItem("Party Member Slot 2");
		menuTabs.getItems().add(menuItemP2);
		
		menuItemP2.setOnAction(e -> partyWindow(p2));
		
		MenuItem menuItemP3 = new MenuItem("Party Member Slot 3");
		menuTabs.getItems().add(menuItemP3);
		
		menuItemP3.setOnAction(e -> partyWindow(p3));
		
		MenuItem menuItemP4 = new MenuItem("Party Member Slot 4");
		menuTabs.getItems().add(menuItemP4);
		
		menuItemP4.setOnAction(e -> partyWindow(p4));
		
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
		
		ImageView p1FacePlate = new ImageView(p.getFP());
		
		gp2.add(p1FacePlate,0,0);
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

