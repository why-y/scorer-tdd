package ch.gry.scorer.match;
import ch.gry.scorer.AlreadyTerminatedException;
import ch.gry.scorer.Player;
import ch.gry.scorer.ScoreUnit;
import ch.gry.scorer.set.Set;
import ch.gry.scorer.set.SetBuilder;

import static ch.gry.scorer.match.Length.*;
import static ch.gry.scorer.match.Mode.*;

public class Match extends ScoreUnit {

	Length length = BEST_OF_THREE;
	Mode mode = ALL_SETS_WITH_TIEBREAKS;

	private Set currentSet;

	protected Match(final Player firstServer, final Player firstReturner) {
		super(firstServer, firstReturner);
		currentSet = new SetBuilder(firstServer, firstReturner).build();
	}
	
	public void setCurrentSet(final Set currentSet) {
		this.currentSet = currentSet;
	}

	@Override
	public String getScore() {
		return String.format("%d:%d", getScoreCount(getServer()), getScoreCount(getReturner()));
	}

	@Override
	public boolean isWonBy(Player player) {
		return getScoreCount(player)==length.getNoOfSetsToWin();
	}

	public Set getCurrentSet() {
		return currentSet;
	}

	public ScoreUnit getCurrentGame() {
		return getCurrentSet().getCurrentGame();
	}

	public String getFullScore() {
		return String.format("%s; %s; %s", getScore(), getCurrentSet().getScore(), getCurrentGame().getScore());
	}

	public void rallyWonBy(final Player player) {
		if(isTerminated())
			throw new AlreadyTerminatedException("This match has already been terminated! No more rallies accepted.");
		Set currentSet = getCurrentSet();
		currentSet.rallyWonBy(player);
		if(currentSet.isWonBy(player)) {
			scoreFor(player);
			currentSet.reset();
		}
	}
}
