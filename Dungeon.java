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

public class Dungeon {
	String name;
	String fileName;
	int events;
	Image itemSpr;
	int tempEvents;
	
	Dungeon(String newName, int maxEvents){
		this.name = newName;
		this.fileName = newName.toLowerCase().trim();
		this.events = maxEvents;
		this.itemSpr = new Image("/images/items/" + this.fileName + ".PNG");
		this.tempEvents = this.events;
	}
	
	//utrigger event (DO LATER)
	static void event(Dungeon d){
		if (this.events>0){
			GridPane gpEvent = new GridPane();
			Stage eventWindow = new Stage();
			int event = Math.random()*10;
			
			switch (d.name) {
				case "trashbin":
					switch (event) {
						case 0:
							if (DataMiners.pTable[0].explr+DataMiners.pTable[0].explr+DataMiners.pTable[0].explr+DataMiners.pTable[0].explr > 40){
								gpEvent.add(new Label("Do you Wish to Encounter:"),0,0);
								gpEvent.add(new Label("\tEnemies"),0,0);
							}
							else{
								DataMiners.combat()
							}
							break;
					}
					break;
			}
			
			Button btnP1 = new Button("Yes");
			Button btnP2 = new Button("No");
			
			btnP1.setOnAction(e -> {
				
				eventWindow.close();
			});
			btnP2.setOnAction(e -> {
				
				eventWindow.close();
			});
			
			gpEvent.setPadding(new Insets(10, 10, 10, 10));
			gpEvent.setBackground(new Background(new BackgroundFill(Color.LEMONCHIFFON, CornerRadii.EMPTY, Insets.EMPTY)));
			
			Scene sceneE = new Scene(gpEvent,320,150);
			
			eventWindow.setOnCloseRequest(e -> e.consume());

			eventWindow.setTitle("Items");
			eventWindow.setResizable(false);
			eventWindow.setAlwaysOnTop(true);
			eventWindow.setScene(sceneI);
			eventWindow.show();
			
			
		}
		else{
			/*
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
			switch(d.name){
				
			}
		}
	}
}