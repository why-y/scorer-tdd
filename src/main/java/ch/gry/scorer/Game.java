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
		for (Player player : getPlayers()) {
			if(isAdvantageFor(player)) 
				return String.format("Advantage %s", player.getName());
			if(isWonBy(player)) 
				return String.format("Game %s", player.getName());			
		}
		return String.format("%d:%d", getPointsOf(getServer()), getPointsOf(getReturner()));
	}
	
	private long getPointsOf(Player player) {
		long playerRallies = getScoreCount(player);
		return playerRallies<3 ? playerRallies*15 : 10 + playerRallies*10;
	}

	private boolean isDeuce() {
		return getScoreCount(getServer()) >=3 && isEvenScore();
	}
	
	private boolean isAdvantageFor(Player player) {
		return getScoreCount(player)>=4 && isOneScorePointAhead(player);
	}
	
	@Override
	public boolean isWonBy(Player player) {
		return getScoreCount(player)>3 && isAtLeastTwoScorePointsAhead(player);
	}

	@Override
	public String toString() {
		return String.format("%s(%d):%s(%d) score: %s -> RallyHistory: %s", 
				getServer().getName(), 
				getScoreCount(getServer()),
				getReturner().getName(), 
				getScoreCount(getReturner()),
				getScore(),
				scoreSequence);
	}

}
