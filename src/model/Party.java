package model;

public abstract class Party {
	
	public static boolean hasLimit() {
		if (constants.Party.PARTY_LIMIT == 0) {
			return false;
		}
		
		return true;
	}
}
