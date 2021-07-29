import java.util.*;
import javafx.scene.image.Image;

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
		this.stat = newStat;
		this.statAmount = newStatAmount;
		this.itemSpr = new Image("/images/items/" + this.fileName + ".PNG");
	}
}