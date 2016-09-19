package ch.gry.scorer;

import java.util.Random;

import ch.gry.scorer.match.Match;
import ch.gry.scorer.match.MatchBuilder;

public class App {

	private static Random randomizer = new Random(System.currentTimeMillis());
	
	public static void main(String[] args) {
		// play a long tight game
		Player harry = Player.create("Harry");
		Player eric = Player.create("Eric");	
		Match match = new MatchBuilder(harry, eric).build();
		Player[] players = new Player[]{harry, eric};
		while(!match.isTerminated()) {
			match.rallyWonBy(getRandomPlayer(players));
			System.out.println(match.getFullScore());
		}
	}
	
	private static Player getRandomPlayer(Player[] players) {
		return players[randomizer.nextInt(players.length)];
	}

}
