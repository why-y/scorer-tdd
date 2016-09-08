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
		if(isDeuce()) return "Deuce";
		for (Player player : Player.values()) {
			if(isAdvantageFor(player)) 
				return String.format("Advantage %s", player.getName());
			if(isWonBy(player)) 
				return String.format("Game %s", player.getName());			
		}
		return String.format("%d:%d", getPointsOf(SERVER), getPointsOf(RETURNER));
	}
	
	public void reset() {
		rallySequence.clear();
	}
	
	public void withdrawLastRally() {
		rallySequence.removeIf(p -> rallySequence.indexOf(p)==rallySequence.size()-1);
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
	
	private boolean isWonBy(Player player) {
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
		return isWonBy(SERVER) || isWonBy(RETURNER);
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
