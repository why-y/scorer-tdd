package ch.gry.scorer;
import static ch.gry.scorer.Set.Mode.*;

public class Set {
	enum Mode{WITH_TIEBREAK, WITHOUT_TIEBREAK}
	private Mode mode =  WITHOUT_TIEBREAK;
	
	private Set() {
	}
	
	public static Set create(String firstServerName, String firstReturnerName, Mode setMode) {
		return null;
	}
}
