package ch.gry.scorer;

import java.util.ArrayList;
import java.util.List;
import static ch.gry.scorer.Player.*;

public class Game {
	
	private static final String CANNOT_SCORE_TO_A_TERMINATED_GAME = "Cannot score for a terminated game!";

	private Game() {}
	
	private String serverName;
	private String returnerName;
	private List<Player> rallyHistory;
	
	
	public static Game create(String serverName, String returnerName) {
		Game scorer = new Game();
		scorer.serverName = serverName;
		scorer.returnerName = returnerName;
		scorer.rallyHistory = new ArrayList<>();
		return scorer;
	}
	
	public String getScore() {
		if(isDeuce()) {
			return "Deuce";
		}
		else if(isAdvantageFor(SERVER)) {
			return String.format("Advantage %s", serverName);
		}
		else if(isAdvantageFor(RETURNER)) {
			return String.format("Advantage %s", returnerName);
		}
		else if(isGameWonBy(SERVER)) {
			return String.format("Game %s", serverName);
		}
		else if(isGameWonBy(RETURNER)) {
			return String.format("Game %s", returnerName);
		}
		else {
			return String.format("%d:%d", getPointsOf(SERVER), getPointsOf(RETURNER));
		}
	}
	
	public void reset() {
		rallyHistory.clear();
	}
	
	public void withdrawLastRally() {
		if(rallyHistory.isEmpty()) {
			System.err.println("No rally played so far. Cannot be whithdrawn!");
		}
		rallyHistory.remove(rallyHistory.size()-1);
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
		return rallyHistory.stream().filter(p -> p==player).count();
	}

	public void scoreFor(Player scorer) throws GameOverException {
		if(gameOver()) 
			throw new GameOverException(CANNOT_SCORE_TO_A_TERMINATED_GAME);
		rallyHistory.add(scorer);
	}
	
	private boolean gameOver() {
		return isGameWonBy(SERVER) || isGameWonBy(RETURNER);
	}

	@Override
	public String toString() {
		return String.format("%s(%d):%s(%d) score: %s -> RallyHistory: %s", 
				serverName, 
				getRalliesCount(SERVER),
				returnerName, 
				getRalliesCount(RETURNER),
				getScore(),
				rallyHistory);
	}

}
