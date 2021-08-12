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
			int event = ((int)Math.random())*5;
			
			switch (d.name) {
				case "trashbin":
					switch (event) {
						case 0:
							if (DataMiners.pTable[0].explr+DataMiners.pTable[0].explr+DataMiners.pTable[0].explr+DataMiners.pTable[0].explr > 40){
								gpEvent.add(new Label("Do you Wish to Encounter:"),0,0);
								gpEvent.add(new Label("\tStray Code"),0,1);
							}
							else{
								DataMiners.combat(1,2,1,0);
								eventWindow.close();
							}
							break;
						case 1:
							gpEvent.add(new Label("You found a HealthPotion!"),0,0);
							gpEvent.add(new Label("\tWill you take it?"),0,1);
							break;
						case 2:
							gpEvent.add(new Label("You found an AttackFruit!"),0,0);
							gpEvent.add(new Label("\tWill you take it?"),0,1);
							break;
						case 3:
							if (DataMiners.pTable[0].explr+DataMiners.pTable[0].explr+DataMiners.pTable[0].explr+DataMiners.pTable[0].explr > 40){
								gpEvent.add(new Label("Do you Wish to Encounter:"),0,0);
								gpEvent.add(new Label("\tNavyWindCO and co."),0,1);
							}
							else{
								DataMiners.combat(1,2,1,0);
								eventWindow.close();
							}
							break;
						case 4:
							if (DataMiners.pTable[0].explr+DataMiners.pTable[0].explr+DataMiners.pTable[0].explr+DataMiners.pTable[0].explr > 40){
								gpEvent.add(new Label("Do you Wish to Encounter:"),0,0);
								gpEvent.add(new Label("\tHauntedCode"),0,1);
							}
							else{
								DataMiners.combat(1,2,1,0);
								eventWindow.close();
							}
							break;
					}
					break;
			}
			
			Button btnP1 = new Button("Yes");
			Button btnP2 = new Button("No");
			
			btnP1.setOnAction(e -> {
				switch (event) {
					case 0:
						DataMiners.combat(1,2,1,0);
						break;
					case 1:
						DataMiners.itemsOnPerson.add(new Item("HealingPotion",false,1,10));
						break;
					case 2:
						DataMiners.itemsOnPerson.add(new Item("AttackFruit",false,2,5));
						break;
					case 3:
						DataMiners.combat(1,2,1,0);
						break;
					case 4:
						DataMiners.combat(1,2,1,0);
						break;
						
				}
				eventWindow.close();
			});
			btnP2.setOnAction(e -> {
				eventWindow.close();
			});
			
			gpEvent.add(btnP1,0,2);
			gpEvent.add(btnP2,0,3);
			
			gpEvent.setPadding(new Insets(10, 10, 10, 10));
			gpEvent.setBackground(new Background(new BackgroundFill(Color.LEMONCHIFFON, CornerRadii.EMPTY, Insets.EMPTY)));
			
			Scene sceneE = new Scene(gpEvent,320,150);
			
			eventWindow.setOnCloseRequest(e -> e.consume());

			eventWindow.setTitle("Items");
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
			switch(d.name){
				
			}
		}
	}
}