import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.*;


public class DataMiners extends Application {   
	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {   
		TabPane tabPane = new TabPane();
		
		//starting characters
		PartyMember caulder = new PartyMember();
		
		MenuBar menuBar = new MenuBar();

		Menu menuTabs = new Menu("Tabs");
		menuBar.getMenus().addAll(menuTabs);
		
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
		
		MenuItem menuItemP1 = new MenuItem("Party Member 1");
		menuTabs.getItems().add(menuItemP1);
		
		menuItemP1.setOnAction(e -> {
			GridPane gp2 = new GridPane();
			
			Button bA1 = new Button("Jump");
			
			bA1.setOnAction(b -> {
				System.out.println("Youch!");
			});
			
			gp2.add(bA1,0,1);
			
			Scene scene2 = new Scene(gp2,306,100);
			
			Stage newWindow = new Stage();
			newWindow.setTitle("Tax");
			newWindow.setScene(scene2);
			
			newWindow.show();
		});
		
		VBox vbox = new VBox();
		vbox.getChildren().addAll(menuBar);
		
		Scene scene = new Scene(vbox, 350, 300);  
		primaryStage.setTitle("Meal Order!"); // Set the window title
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
	
	
}

