package ch.gry.scorer;
import static ch.gry.scorer.Set.Mode.WITHOUT_TIEBREAK;

import java.util.ArrayList;
import java.util.List;

public class Set {
	enum Mode{WITH_TIEBREAK, WITHOUT_TIEBREAK}
	private Mode mode =  WITHOUT_TIEBREAK;
	private Player[] players = new Player[2];
	private List<Player> gameSequence;
	
	private Set() {
		gameSequence = new ArrayList<>();
	}
	
	public static Set create(Player firstServer, Player firstReturner, Mode setMode) {
		Set set = new Set();
		set.setServer(firstServer);
		set.setReturner(firstReturner);
		set.mode = setMode;
		return set;
	}

	private void setServer(Player player) { 
		players[0] = player; 
	}
	
	private Player getServer() { 
		return players[0]; 
	}
	
	private void setReturner(Player player) { 
		players[1] = player; 
	}
	
	private Player getReturner() {
		return players[1];
	}

	private long gamesOf(Player player) {
		return gameSequence.stream().filter(p -> p==player).count();
	}

	public String getScore() {
		return String.format("%d:%d", gamesOf(getServer()), gamesOf(getReturner())); 
	}

	public void gameFor(Player player) {
		gameSequence.add(player);
	}
}
