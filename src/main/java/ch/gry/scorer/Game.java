package ch.gry.scorer;

public class Game extends ScoreUnit {

	private Game(final Player server, final Player returner) {
		super(server, returner);
	}
	
	public static Game create(final Player server, final Player returner) {
		Game game = new Game(server, returner);
		return game;
	}
	
	
	public String getScore() {
		if(isDeuce()) return "Deuce";
		for (Player player : players.values()) {
			if(isAdvantageFor(player)) 
				return String.format("Advantage %s", player.getName());
			if(isWonBy(player)) 
				return String.format("Game %s", player.getName());			
		}
		return String.format("%d:%d", getPointsOf(getServer()), getPointsOf(getReturner()));
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
	
	@Override
	public boolean isWonBy(Player player) {
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
		return scoreSequence.stream().filter(p -> p==player).count();
	}

	@Override
	public String toString() {
		return String.format("%s(%d):%s(%d) score: %s -> RallyHistory: %s", 
				getServer().getName(), 
				getRalliesCount(getServer()),
				getReturner().getName(), 
				getRalliesCount(getReturner()),
				getScore(),
				scoreSequence);
	}

}
