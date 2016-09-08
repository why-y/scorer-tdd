package ch.gry.scorer;

import java.util.ArrayList;
import java.util.List;

public class Game {

	private static final String CANNOT_SCORE_TO_A_TERMINATED_GAME = "Cannot score for a terminated game!";

	private Game() {}
	
	private Player [] players = new Player[2];
	private List<Player> rallySequence;
	
	
	public static Game create(final Player server, final Player returner) {
		Game game = new Game();
		game.setServer(server);
		game.setReturner(returner);
		game.rallySequence = new ArrayList<>();
		return game;
	}
	
	private void setServer(Player player) { 
		players[0] = player; 
	}
	
	private Player getServer() { 
		return players[0]; 
	}
	
	private void setReturner(Player player) { 
		players[1] = player; 
	}
	
	private Player getReturner() { 
		return players[1]; 
	}
	
	public String getScore() {
		if(isDeuce()) return "Deuce";
		for (Player player : players) {
			if(isAdvantageFor(player)) 
				return String.format("Advantage %s", player.getName());
			if(isWonBy(player)) 
				return String.format("Game %s", player.getName());			
		}
		return String.format("%d:%d", getPointsOf(getServer()), getPointsOf(getReturner()));
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
		long serverRallies = getRalliesCount(getServer());
		return serverRallies>=3 && serverRallies==getRalliesCount(getReturner());
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
		return player==getServer() ? getReturner() : getServer();
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
		return isWonBy(getServer()) || isWonBy(getReturner());
	}

	@Override
	public String toString() {
		return String.format("%s(%d):%s(%d) score: %s -> RallyHistory: %s", 
				getServer().getName(), 
				getRalliesCount(getServer()),
				getReturner().getName(), 
				getRalliesCount(getReturner()),
				getScore(),
				rallySequence);
	}

}
