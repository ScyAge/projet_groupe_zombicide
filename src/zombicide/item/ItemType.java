package zombicide.item;

import zombicide.item.equipment.Equipment;
import zombicide.item.weapons.Weapon;


public enum ItemType {
	CROWBAR,
	AXE,
	CHAINSAW,
	GUN,
	CARABINE,
	FIRST_AID_KIT,
	GLASSES,
	MAP_CARD,
	MASTER_KEY,
	HEALINGVIAL;
	
	/**
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
			else {
				res = new Item("HealingVial");
			}
			
		}
		return res;
	}
	
	
}
