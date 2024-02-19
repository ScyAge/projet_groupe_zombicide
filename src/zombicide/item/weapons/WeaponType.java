package zombicide.item.weapons;

public enum WeaponType {
	CROWBAR,
	AXE,
	CHAINSAW,
	GUN,
	CARABINE;
	
	
	/**
	 * @param type of the weapon to initialize 
	 * @return weapon 
	 *  */
	public Weapon createWeapon(WeaponType type) {
		Weapon res = null;
		for(WeaponType w :WeaponType.values()) {
			if(w==WeaponType.CROWBAR) {
				res= new Weapon("crowbar", 0, 1, 4, true, w);
			}
			else if (w==WeaponType.AXE) {
				res= new Weapon("axe", 0, 2, 4, true, w);
			}
			else if(w==WeaponType.CHAINSAW) {
				res= new Weapon("chainsaw", 0, 3, 5, true, w);
			}
			else if(w== WeaponType.GUN) {
				res= new Weapon("gun", 1, 1, 4, false, w);
			}
			else {
				res= new Weapon("carabine", 3, 1, 4, false,  w) ;
			}
		}
		return res;
	}
}
