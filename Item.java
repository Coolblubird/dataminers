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

public class Item {
	String name;
	String fileName;
	boolean isKey;
	int stat;
	int statAmount;
	Image itemSpr;
	
	Item(String newName, boolean isItKey, int newStat, int newStatAmount){
		this.name = newName;
		this.fileName = newName.toLowerCase().trim();
		this.isKey = isItKey;
		if (isKey==false){
			this.stat = newStat;
			this.statAmount = newStatAmount;
		}
		this.itemSpr = new Image("/images/items/" + this.fileName + ".PNG");
	}
	
	//use the item
	static void useItem(Item item){
		if (item.isKey==false){
			GridPane gpItem = new GridPane();
			Stage itemWindow = new Stage();
			
			Button btnP1 = new Button(DataMiners.pTable[0].name);
			Button btnP2 = new Button(DataMiners.pTable[1].name);
			Button btnP3 = new Button(DataMiners.pTable[2].name);
			Button btnP4 = new Button(DataMiners.pTable[3].name);
			
			btnP1.setOnAction(e -> {
				DataMiners.pTable[0].statsUp(item.stat,item.statAmount);
				itemWindow.close();
			});
			btnP2.setOnAction(e -> {
				DataMiners.pTable[1].statsUp(item.stat,item.statAmount);
				itemWindow.close();
			});
			btnP3.setOnAction(e -> {
				DataMiners.pTable[2].statsUp(item.stat,item.statAmount);
				itemWindow.close();
			});
			btnP4.setOnAction(e -> {
				DataMiners.pTable[3].statsUp(item.stat,item.statAmount);
				itemWindow.close();
			});
			
			gpItem.add(new Label("Use the " + item.name + " on which Party Member?"),0,0);
			gpItem.add(btnP1,0,1);
			gpItem.add(new Label(DataMiners.pTable[0].chp + "/" + DataMiners.pTable[0].mhp),1,1);
			gpItem.add(btnP2,0,2);
			gpItem.add(new Label(DataMiners.pTable[1].chp + "/" + DataMiners.pTable[1].mhp),1,2);
			gpItem.add(btnP3,0,3);
			gpItem.add(new Label(DataMiners.pTable[2].chp + "/" + DataMiners.pTable[2].mhp),1,3);
			gpItem.add(btnP4,0,4);
			gpItem.add(new Label(DataMiners.pTable[3].chp + "/" + DataMiners.pTable[3].mhp),1,4);
			
			gpItem.setPadding(new Insets(10, 10, 10, 10));
			gpItem.setBackground(new Background(new BackgroundFill(Color.LEMONCHIFFON, CornerRadii.EMPTY, Insets.EMPTY)));
			
			Scene sceneI = new Scene(gpItem,320,150);
			
			itemWindow.setOnCloseRequest(e -> e.consume());

			itemWindow.setTitle("Items");
			itemWindow.setResizable(false);
			itemWindow.setAlwaysOnTop(true);
			itemWindow.setScene(sceneI);
			itemWindow.show();
		}
		else{
			VBox vItem = new VBox();
			Label errorLabel = new Label("This Item is a key Item!");
			Button btnMyBad = new Button("My bad.");
			Stage itemWindow = new Stage();
			
			btnMyBad.setOnAction(e -> {
				itemWindow.close();
			});
			
			vItem.getChildren().addAll(errorLabel,btnMyBad);
			vItem.setAlignment(Pos.TOP_CENTER);
			
			Scene sceneI = new Scene(vItem,180,60);
			itemWindow.setTitle("Key Item Error");
			itemWindow.setResizable(false);
			itemWindow.setAlwaysOnTop(true);
			itemWindow.setScene(sceneI);
			itemWindow.show();
		}
	}
}