package zombicide.item;

import java.util.Map;

import zombicide.item.equipment.Equipment;
import zombicide.item.weapons.Weapon;


/**
 * enum  ItemType
 */
public enum ItemType {
	
	/** Weapons*/
	
		/** Crowbar */
	CROWBAR,
		/** AXE */
	AXE,
		/** CHAINSAW */
	CHAINSAW,
		/** GUN */
	GUN,
		/** CARABINE */
	CARABINE,
	
	/** Equipment*/
	
		/** FIRST_AID_KIT */
	FIRST_AID_KIT,
		/** GLASSES */
	GLASSES,
		/** MAP_CARD */
	MAP_CARD,
		/** MASTER_KEY */
	MASTER_KEY;
	
	/**
	 * Create Item
	 * @param type of the item to initialize 
	 * @return weapon 
	 *  */
	public Item createItem(ItemType type) {
		Item res = null;
		for(ItemType w :ItemType.values()) {
			if(w==ItemType.CROWBAR) {
				res= new Weapon("crowbar", 0, 1, 4, true);
			}
			else if (w==ItemType.AXE) {
				res= new Weapon("axe", 0, 2, 4, true);
			}
			else if(w==ItemType.CHAINSAW) {
				res= new Weapon("chainsaw", 0, 3, 5, true);
			}
			else if(w== ItemType.GUN) {
				res= new Weapon("gun", 1, 1, 4, false);
			}
			else if (w==ItemType.CARABINE) {
				res= new Weapon("carabine", 3, 1, 4, false) ;
			}
			else if(w== ItemType.FIRST_AID_KIT) {
				res= new Item("FirstAidKit");
			} 
			else if(w== ItemType.GLASSES) {
				res= new Item("Glasses");
			}
			else if (w== ItemType.MAP_CARD) {
				res= new Item("MapCard");
			}
			else if (w== ItemType.MASTER_KEY) {
				res= new Item("MasterKey");
			}
			
		}
		return res;
	}
}
