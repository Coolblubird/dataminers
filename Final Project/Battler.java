interface Battler{

	void statsUp(int a, int b);
	void statsDown(int a, int b);
}

class PartyMember implements Battler{
	String name;
	String fileName;
	int atk;
	int def;
	int mhp;
	int chp;
	int explr;
	
	PartyMember(String newName, int newAtk, int newDef, int newExplr, int newMhp){
		this.name = newName;
		this.fileName = newName.toLowerCase();
		this.atk = newAtk;
		this.def = newDef;
		this.explr = newExplr;
		this.mhp = newMhp;
		this.chp = newMhp;
	}
		
	double statsUp(int stat, int amount){
		switch (stat) {
			case 1:
				this.chp+=amount;
				break;
			case 2:
				this.atk+=amount;
				break;
			case 3:
				this.def+=amount;
				break;
		}	
	}
		
	double statsDown(int stat, int amount){
		switch (stat) {
			case 1:
				this.chp-=amount;
				break;
			case 2:
				this.atk-=amount;
				break;
			case 3:
				this.def-=amount;
				break;
		}	
	}
}

class Enemy implements Battler{
	String name;
	String fileName;
	int atk;
	int def;
	int hp;
	
	Enemy(String newName, int newAtk, int newDef){
		this.name = newName;
		this.fileName = newName.toLowerCase();
		this.atk = newAtk;
		this.def = newDef;
	}
}