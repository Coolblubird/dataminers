import java.util.*;
import javafx.scene.image.Image;

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
	int atk;
	int def;
	int mhp;
	int chp;
	int explr;
	Image facePlateSpr;
	Image overWorldSpr;
	boolean ko = false;
	
	PartyMember(String newName, int newAtk, int newDef, int newExplr, int newMhp, String newATK1, String newATK2, String newATK3){
		this.name = newName;
		this.fileName = newName.toLowerCase().trim();
		this.atk = newAtk;
		this.def = newDef;
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
			case 1:
				if (this.chp+amount < this.mhp)
					this.chp+=amount;
				else 
					this.chp=this.mhp;
				break;
			case 2:
				this.atk+=amount;
				break;
			case 3:
				this.def+=amount;
				break;
		}	
	}
		
	@Override
	public void statsDown(int stat, int amount){
		switch (stat) {
			case 1:
				if (this.chp-amount > 0)
					this.chp-=amount;
				else 
					this.chp=0;
					this.ko=true;
				break;
			case 2:
				this.atk-=amount;
				break;
			case 3:
				this.def-=amount;
				break;
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
		this.atk = newAtk;
		this.def = newDef;
		this.mhp = newMHP;
		this.chp = newMHP;
		this.btleSpr = new Image("/images/enemies/" + this.fileName + ".PNG");
	}
	
	@Override
	public void statsUp(int stat, int amount){
		switch (stat) {
			case 1:
				if (this.chp+amount < this.mhp)
					this.chp+=amount;
				else 
					this.chp=this.mhp;
				break;
			case 2:
				this.atk+=amount;
				break;
			case 3:
				this.def+=amount;
				break;
		}	
	}
		
	@Override
	public void statsDown(int stat, int amount){
		switch (stat) {
			case 1:
				if (this.chp-amount > 0)
					this.chp-=amount;
				else 
					this.chp=0;
					this.ko=true;
				break;
			case 2:
				this.atk-=amount;
				break;
			case 3:
				this.def-=amount;
				break;
		}	
	}
	
	boolean isKO(){
		return ko;
	}
}