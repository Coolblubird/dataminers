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
import java.security.cert.*;

interface Battler{
	void statsUp(int a, int b);
	void statsDown(int a, int b);
}

class PartyMember implements Battler{
	String name;
	String fileName;
	String atk1;
	String atk2;
	String atk3;
	int rAtk;
	int rDef;
	int rExplr;
	int atk;
	int def;
	int mhp;
	int chp;
	int explr;
	Image facePlateSpr;
	Image overWorldSpr;
	boolean ko = false;
	Stage pWindow = new Stage();
	
	PartyMember(String newName, int newAtk, int newDef, int newExplr, int newMhp, String newATK1, String newATK2, String newATK3){
		this.name = newName;
		this.fileName = newName.toLowerCase().trim();
		this.rAtk = newAtk;
		this.atk = newAtk;
		this.rDef = newDef;
		this.def = newDef;
		this.rExplr = newExplr;
		this.explr = newExplr;
		this.mhp = newMhp;
		this.chp = newMhp;
		this.facePlateSpr = new Image("/images/partyMembers/" + this.fileName + "/facePlate.PNG");
		this.overWorldSpr = new Image("/images/partyMembers/" + this.fileName + "/overWorld.PNG");
		this.atk1 = newATK1;
		this.atk2 = newATK2;
		this.atk3 = newATK3;
	}
		
	@Override
	public void statsUp(int stat, int amount){
		switch (stat) {
			case 0:
				if (this.chp+amount < this.mhp)
					this.chp+=amount;
				else 
					this.chp=this.mhp;
				break;
			case 1:
				this.atk+=amount;
				break;
			case 2:
				this.def+=amount;
				break;
		}	
	}
		
	@Override
	public void statsDown(int stat, int amount){
		switch (stat) {
			case 0:
				if (this.chp-amount+this.def > 0){
					amount-=this.def;
					if (amount>0){
						this.chp-=amount;
					}
				}
				else {
					this.chp=0;
					this.ko=true;
				}
				break;
			case 1:
				this.atk-=amount;
				break;
			case 2:
				this.def-=amount;
				break;
		}	
	}
	
	public static void putInParty(PartyMember pm){
		if (DataMiners.pTable[0] != pm && DataMiners.pTable[1] != pm && DataMiners.pTable[2] != pm && DataMiners.pTable[3] != pm){
			GridPane gpParty = new GridPane();
			Stage changePartyWindow = new Stage();
			
			Button btnP1 = new Button("Slot 1");
			Button btnP2 = new Button("Slot 2");
			Button btnP3 = new Button("Slot 3");
			Button btnP4 = new Button("Slot 4");
			
			btnP1.setOnAction(e -> {
				DataMiners.pTable[0] = pm;
				changePartyWindow.close();
			});
			btnP2.setOnAction(e -> {
				DataMiners.pTable[1] = pm;
				changePartyWindow.close();
			});
			btnP3.setOnAction(e -> {
				DataMiners.pTable[2] = pm;
				changePartyWindow.close();
			});
			btnP4.setOnAction(e -> {
				DataMiners.pTable[3] = pm;
				changePartyWindow.close();
			});
			
			gpParty.add(new Label("Which slot would you like to place them?"),0,0);
			gpParty.add(new Label(pm.name + "\nHP: \t\t" + pm.chp + "/" + pm.mhp),0,1);
			gpParty.add(new Label("ATK: \t" + pm.atk),0,2);
			gpParty.add(new Label("DEF: \t" + pm.def),0,3);
			gpParty.add(new Label("Explr: \t" + pm.explr),0,4);
			gpParty.add(btnP1,0,5);
			gpParty.add(new Label(DataMiners.pTable[0].name),1,5);
			gpParty.add(btnP2,0,6);
			gpParty.add(new Label(DataMiners.pTable[1].name),1,6);
			gpParty.add(btnP3,0,7);
			gpParty.add(new Label(DataMiners.pTable[2].name),1,7);
			gpParty.add(btnP4,0,8);
			gpParty.add(new Label(DataMiners.pTable[3].name),1,8);
			
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
	
	boolean isKO(){
		return this.ko;
	}
		
	Image getFP(){
		return this.facePlateSpr;
	}
		
	Image getOW(){
		return this.overWorldSpr;
	}
	
	boolean isStageShowing(){
		return pWindow.isShowing();
	}
	
	void restoreStats(){
		this.chp=this.mhp;
		this.atk = this.rAtk;
		this.def = this.rDef;
		this.explr = this.rExplr;
	}
}

class Enemy implements Battler{
	String name;
	String fileName;
	int atk;
	int def;
	int chp;
	int mhp;
	Image btleSpr;
	boolean ko = false;
	
	Enemy(String newName, int newAtk, int newDef,int newMHP){
		this.name = newName;
		this.fileName = newName.toLowerCase().trim();
		if (this.fileName.equals("blank")){
			ko=true;
		}
		this.atk = newAtk;
		this.def = newDef;
		this.mhp = newMHP;
		this.chp = newMHP;
		this.btleSpr = new Image("/images/enemies/" + this.fileName + ".PNG");
	}
	
	@Override
	public void statsUp(int stat, int amount){
		switch (stat) {
			case 0:
				if (this.chp+amount < this.mhp)
					this.chp+=amount;
				else 
					this.chp=this.mhp;
				break;
			case 1:
				this.atk+=amount;
				break;
			case 2:
				this.def+=amount;
				break;
		}	
	}
		
	@Override
	public void statsDown(int stat, int amount){
		switch (stat) {
			case 0:
				if (this.chp-amount+this.def > 0){
					amount-=this.def;
					if (amount>0){
						this.chp-=amount;
					}
				}
				else {
					this.chp=0;
					this.ko=true;
				}
				break;
			case 1:
				this.atk-=amount;
				break;
			case 2:
				this.def-=amount;
				break;
		}	
	}
	
	boolean isKO(){
		return ko;
	}
}