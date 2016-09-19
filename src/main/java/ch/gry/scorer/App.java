package ch.gry.scorer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static ch.gry.scorer.match.Length.*;
import ch.gry.scorer.match.Match;
import ch.gry.scorer.match.MatchBuilder;

public class App {

	private static Random randomizer = new Random(System.currentTimeMillis());
	private static List<Player> rallySequence = new ArrayList<>();
	
	public static void main(String[] args) {
		Player harry = Player.create("Harry");
		Player eric = Player.create("Eric");	
		Match match = new MatchBuilder(harry, eric).length(BEST_OF_FIVE).build();
		Player[] players = new Player[]{harry, eric};
		Player rallyWinner = null;
		while(!match.isTerminated()) {
			rallyWinner = getRandomPlayer(players);
			rallySequence.add(rallyWinner);
			match.rallyWonBy(rallyWinner);
			System.out.println(match.getFullScore());
		}
		System.out.println(String.format("Game, Set and Match: %s", rallyWinner.getName()));
		System.out.println(String.format("  Rallies by %s: %d, Rallies by %s: %d, Total rallies: %d",
				harry.getName(), rallyCountOf(harry),
				eric.getName(), rallyCountOf(eric),
				rallySequence.size()
				));
	}
	
	private static Player getRandomPlayer(Player[] players) {
		return players[randomizer.nextInt(players.length)];
	}
	
	private static long rallyCountOf(final Player player) {
		return rallySequence.stream().filter(p -> p==player).count();
	}

}
