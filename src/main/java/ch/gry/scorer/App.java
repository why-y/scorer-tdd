package ch.gry.scorer;

public class App {

	public static void main(String[] args) {
		// play a long tight game
		Player server = Player.create("Harry");
		Player returner = Player.create("Eric");		
		Game game = Game.create(server, returner);
		try {
			game.scoreFor(server); System.out.println(game);
			game.scoreFor(server); System.out.println(game);
			game.scoreFor(server); System.out.println(game);
			game.scoreFor(returner); System.out.println(game);
			game.scoreFor(returner); System.out.println(game);
			game.scoreFor(returner); System.out.println(game);
			game.scoreFor(returner); System.out.println(game);
			game.scoreFor(server); System.out.println(game);
			game.scoreFor(server); System.out.println(game);
			game.scoreFor(returner); System.out.println(game);
			game.scoreFor(returner); System.out.println(game);
			game.scoreFor(returner); System.out.println(game);
		}
		catch(AlreadyTerminatedException e) {
			System.err.println(e);
		}
	}

}
