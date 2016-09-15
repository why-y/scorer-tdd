package ch.gry.scorer;

import static ch.gry.scorer.ScoreUnit.ServerOrReturner.RETURNER;
import static ch.gry.scorer.ScoreUnit.ServerOrReturner.SERVER;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ScoreUnit {
	
	/**
	 * We want to know who <br>
	 * - starts to serve in a Set (SERVER) <br>
	 * - starts to serve in a Tiebreak (SEVER) <br>
	 * - serves in a Game (SERVER) <br>
	 * In each case the other player is the RETURNER
	 */
	enum ServerOrReturner {SERVER, RETURNER};
	
	protected List<Player> scoreSequence;
	private Map<ServerOrReturner, Player> players;
	
	protected ScoreUnit(final Player server, final Player returner) {
		scoreSequence = new ArrayList<>();
		players = new HashMap<>();
		players.put(SERVER, server);
		players.put(RETURNER, returner);
	}

	final protected boolean isTerminated() {
		return players.values().stream().anyMatch(player -> isWonBy(player));
	};
	
	abstract public boolean isWonBy(final Player player);
	
	final protected Collection<Player> getPlayers() {
		return players.values();
	}
	
	final protected Player getServer() {
		return players.get(SERVER);
	}

	final protected Player getReturner() {
		return players.get(RETURNER);
	}
	
	final protected Player opponentOf(final Player player) {
		return player==getServer() ? getReturner() : getServer();
	}

	final protected boolean isEvenScore() {
		return getScoreCount(getServer()) == getScoreCount(getReturner());
	}
	
	final protected boolean isOneScorePointAhead(final Player player) {
		return getScoreCount(player)-getScoreCount(opponentOf(player)) == 1;
	}
	
	final protected boolean isAtLeastOneScorePointsAhead(final Player player) {
		return getScoreCount(player)-getScoreCount(opponentOf(player)) >= 1;
	}
	
	final protected boolean isAtLeastTwoScorePointsAhead(final Player player) {
		return getScoreCount(player)-getScoreCount(opponentOf(player)) >= 2;
	}
	
	final protected long getScoreCount(Player player) {
		return scoreSequence.stream().filter(p -> p==player).count();
	}

	public void scoreFor(final Player player) {
		if(isTerminated())
			throw new AlreadyTerminatedException(String.format("This %s has already been terminated. Thus it's not allowed to be scored!", getClass().getSimpleName()));
		scoreSequence.add(player);
	}

	public void undoLastScore() {
		if(!scoreSequence.isEmpty())
			scoreSequence.remove(scoreSequence.size()-1);
	}
	
	public void reset() {
		scoreSequence.clear();
	}
}
