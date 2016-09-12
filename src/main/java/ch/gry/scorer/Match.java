package ch.gry.scorer;

import java.util.ArrayList;
import java.util.List;

public class Match {

	private List<Player> setSequence;
	private Player[] players = new Player[2];
	
	private Match() {
		setSequence = new ArrayList<>();
	}
	
	public static Match create(final Player firstServer, final Player firstReturner) {
		Match match = new Match();
		match.players[0] = firstServer;
		match.players[1] = firstReturner;
		return match;
	}
	
	public String getScore() {
		return String.format("%d:%d", noOfWonSetsOf(players[0]), noOfWonSetsOf(players[1]));
	}

	private long noOfWonSetsOf(Player player) {
		return setSequence.stream().filter(p -> p==player).count();
	}

	public void scoreSetFor(Player player) {
		setSequence.add(player);
	}
	
}
