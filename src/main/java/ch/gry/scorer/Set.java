package ch.gry.scorer;
import static ch.gry.scorer.Set.Mode.WITHOUT_TIEBREAK;

public class Set extends ScoreUnit{
	enum Mode{WITH_TIEBREAK, WITHOUT_TIEBREAK}
	private Mode mode =  WITHOUT_TIEBREAK;
	
	private Set(final Player firstServer, final Player firstReturner) {
		super(firstServer, firstReturner);
	}
	
	public static Set create(final Player firstServer, final Player firstReturner, Mode setMode) {
		Set set = new Set(firstServer, firstReturner);
		set.mode = setMode;
		return set;
	}

	private long gamesOf(Player player) {
		return scoreSequence.stream().filter(p -> p==player).count();
	}

	public String getScore() {
		for (Player player: getPlayers()) {
			if(isWonBy(player))
				return String.format("Set %s", player.getName());
		}
		return String.format("%d:%d", gamesOf(getServer()), gamesOf(getReturner()));
	}

	@Override
	public boolean isWonBy(Player player) {
		return gamesOf(player)==6 && isAtLeastTwoGamesAhead(player) ||
				gamesOf(player)>6 && (mode==WITHOUT_TIEBREAK ? isAtLeastTwoGamesAhead(player) : isAtLeastOneGameAhead(player));
	}

	private boolean isAtLeastOneGameAhead(final Player player) {
		return gamesOf(player) - gamesOf(opponentOf(player)) >= 1;
	}

	private boolean isAtLeastTwoGamesAhead(final Player player) {
		return gamesOf(player) - gamesOf(opponentOf(player)) >= 2;
	}

	@Override
	public String toString() {
		return String.format("%s vs. %s: %s", getServer().getName(), getReturner().getName(), getScore());
	}
}
