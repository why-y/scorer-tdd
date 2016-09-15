package ch.gry.scorer;

public class Match extends ScoreUnit {

	private Match(final Player firstServer, final Player firstReturner) {
		super(firstServer, firstReturner);
	}
	
	public static Match create(final Player firstServer, final Player firstReturner) {
		Match match = new Match(firstServer, firstReturner);
		return match;
	}
	
	public String getScore() {
		return String.format("%d:%d", noOfWonSetsOf(getServer()), noOfWonSetsOf(getReturner()));
	}

	private long noOfWonSetsOf(Player player) {
		return scoreSequence.stream().filter(p -> p==player).count();
	}
	
	@Override
	public boolean isWonBy(Player player) {
		return false;
	}
}
