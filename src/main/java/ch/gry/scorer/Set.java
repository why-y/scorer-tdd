package ch.gry.scorer;
import static ch.gry.scorer.Set.Mode.*;

import java.util.ArrayList;
import java.util.Arrays;
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
		set.players[0] = firstServer;
		set.players[1] = firstReturner;
		set.mode = setMode;
		return set;
	}

	private long gamesOf(Player player) {
		return gameSequence.stream().filter(p -> p==player).count();
	}

	public String getScore() {
		for (Player player: players) {
			if(isWonBy(player))
				return String.format("Set %s", player.getName());
		}
		return String.format("%d:%d", gamesOf(players[0]), gamesOf(players[1]));
	}

	private boolean isWonBy(final Player player) {
		return gamesOf(player)==6 && isAtLeastTwoGamesAhead(player) ||
				gamesOf(player)>6 && (mode==WITHOUT_TIEBREAK ? isAtLeastTwoGamesAhead(player) : isAtLeastOneGameAhead(player));
	}

	private boolean isAtLeastOneGameAhead(final Player player) {
		return gamesOf(player) - gamesOf(other(player)) >= 1;
	}

	private boolean isAtLeastTwoGamesAhead(final Player player) {
		return gamesOf(player) - gamesOf(other(player)) >= 2;
	}

	private Player other(final Player player) {
		return player==players[0] ? players[1] : players[0];
	}

	public void gameFor(Player player) {
		if (isSetOver())
			throw new SetOverException("Cannot score a game on a terminated Set!");
		gameSequence.add(player);
	}

	private boolean isSetOver(){
		return Arrays.stream(players).anyMatch(player -> isWonBy(player));
	}
	
	public void reset() {
		gameSequence.clear();
	}

	@Override
	public String toString() {
		return String.format("%s vs. %s: %s", players[0].getName(), players[1].getName(), getScore());
	}
}
