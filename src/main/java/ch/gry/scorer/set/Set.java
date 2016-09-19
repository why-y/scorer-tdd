package ch.gry.scorer.set;
import static ch.gry.scorer.set.Mode.WITHOUT_TIEBREAK;

import ch.gry.scorer.Game;
import ch.gry.scorer.Player;
import ch.gry.scorer.ScoreUnit;
import ch.gry.scorer.Tiebreak;

public class Set extends ScoreUnit{
	
	protected Mode mode =  WITHOUT_TIEBREAK;

	private ScoreUnit currentGame;

	public Set(final Player firstServer, final Player firstReturner) {
		super(firstServer, firstReturner);
		currentGame = Game.create(firstServer, firstReturner);
	}

	public ScoreUnit getCurrentGame() {
		return currentGame;
	}

	private long gamesOf(Player player) {
		return scoreSequence.stream().filter(p -> p==player).count();
	}

	@Override
	public String getScore() {
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

	public void rallyWonBy(final Player player) {
		currentGame.rallyWonBy(player);
		if(currentGame.isWonBy(player)) {
			scoreFor(player);
			currentGame = setupNextGame();
		}
	}
	
	private ScoreUnit setupNextGame() {
		Player newServer = currentGame.getReturner();
		Player newReturner = currentGame.getServer();
		return (this.mode==Mode.WITH_TIEBREAK && getScoreCount(getServer())==6 && isEvenScore())  ? 
				Tiebreak.create(newServer, newReturner) :
				Game.create(newServer, newReturner);
	}
}
