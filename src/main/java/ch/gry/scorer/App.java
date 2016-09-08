package ch.gry.scorer;
import static ch.gry.scorer.Player.*;

public class App {

	public static void main(String[] args) {
		// play a long tight game
		Game game = Game.create("Harry", "Eric");
		try {
			game.scoreFor(SERVER); System.out.println(game);
			game.scoreFor(SERVER); System.out.println(game);
			game.scoreFor(SERVER); System.out.println(game);
			game.scoreFor(RETURNER); System.out.println(game);
			game.scoreFor(RETURNER); System.out.println(game);
			game.scoreFor(RETURNER); System.out.println(game);
			game.scoreFor(RETURNER); System.out.println(game);
			game.scoreFor(SERVER); System.out.println(game);
			game.scoreFor(SERVER); System.out.println(game);
			game.scoreFor(RETURNER); System.out.println(game);
			game.scoreFor(RETURNER); System.out.println(game);
			game.scoreFor(RETURNER); System.out.println(game);
		}
		catch(GameOverException e) {
			System.err.println(e);
		}
	}

}
