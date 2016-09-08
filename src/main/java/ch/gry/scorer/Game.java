package ch.gry.scorer;

import java.util.ArrayList;
import java.util.List;
import static ch.gry.scorer.Player.*;

public class Game {
	
	private static final String CANNOT_SCORE_TO_A_TERMINATED_GAME = "Cannot score for a terminated game!";

	private Game() {}
	
	private Player server = SERVER;
	private Player returner = RETURNER;
	private List<Player> rallySequence;
	
	
	public static Game create(String serverName, String returnerName) {
		Game game = new Game();
		game.server.setName(serverName);
		game.returner.setName(returnerName);
		game.rallySequence = new ArrayList<>();
		return game;
	}
	
	public String getScore() {
		if(isDeuce()) {
			return "Deuce";
		}
		else if(isAdvantageFor(SERVER)) {
			return String.format("Advantage %s", server.getName());
		}
		else if(isAdvantageFor(RETURNER)) {
			return String.format("Advantage %s", returner.getName());
		}
		else if(isGameWonBy(SERVER)) {
			return String.format("Game %s", server.getName());
		}
		else if(isGameWonBy(RETURNER)) {
			return String.format("Game %s", returner.getName());
		}
		else {
			return String.format("%d:%d", getPointsOf(SERVER), getPointsOf(RETURNER));
		}
	}
	
	public void reset() {
		rallySequence.clear();
	}
	
	public void withdrawLastRally() {
		if(rallySequence.isEmpty()) {
			System.err.println("No rally played so far. Cannot be whithdrawn!");
		}
		rallySequence.remove(rallySequence.size()-1);
	}
	
	private long getPointsOf(Player player) {
		long playerRallies = getRalliesCount(player);
		return playerRallies<3 ? playerRallies*15 : 10 + playerRallies*10;
	}

	private boolean isDeuce() {
		long serverRallies = getRalliesCount(SERVER);
		return serverRallies>=3 && serverRallies==getRalliesCount(RETURNER);
	}
	
	private boolean isAdvantageFor(Player player) {
		long playerRallies = getRalliesCount(player);
		return playerRallies>=4 && isOneRallyAhead(player);
	}
	
	private boolean isGameWonBy(Player player) {
		return getRalliesCount(player)>3 && isAtLeastTwoRalliesAhead(player);
	}

	private boolean isOneRallyAhead(Player player) {
		return getRalliesCount(player)-getRalliesCount(oponentOf(player)) == 1;
	}

	private Player oponentOf(Player player) {
		return player==SERVER ? RETURNER : SERVER;
	}

	private boolean isAtLeastTwoRalliesAhead(Player player) {
		return getRalliesCount(player)-getRalliesCount(oponentOf(player)) >= 2;
	}

	private long getRalliesCount(Player player) {
		return rallySequence.stream().filter(p -> p==player).count();
	}

	public void scoreFor(Player scorer) throws GameOverException {
		if(gameOver()) 
			throw new GameOverException(CANNOT_SCORE_TO_A_TERMINATED_GAME);
		rallySequence.add(scorer);
	}
	
	private boolean gameOver() {
		return isGameWonBy(SERVER) || isGameWonBy(RETURNER);
	}

	@Override
	public String toString() {
		return String.format("%s(%d):%s(%d) score: %s -> RallyHistory: %s", 
				server.getName(), 
				getRalliesCount(SERVER),
				returner.getName(), 
				getRalliesCount(RETURNER),
				getScore(),
				rallySequence);
	}

}
