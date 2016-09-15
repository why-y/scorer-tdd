package ch.gry.scorer.match;
import ch.gry.scorer.Player;
import ch.gry.scorer.ScoreUnit;
import static ch.gry.scorer.match.Length.*;
import static ch.gry.scorer.match.Mode.*;

public class Match extends ScoreUnit {

	protected Length length = BEST_OF_THREE;
	protected Mode mode = WITH_TIEBREAKS;

	public Match(final Player firstServer, final Player firstReturner) {
		super(firstServer, firstReturner);
	}

	public String getScore() {
		for (Player player : getPlayers()) {
			if(isWonBy(player))
				return String.format("Match %s", player.getName());
		}
		return String.format("%d:%d", getScoreCount(getServer()), getScoreCount(getReturner()));
	}

	@Override
	public boolean isWonBy(Player player) {
		return getScoreCount(player)==length.getNoOfSetsToWin();
	}
}
