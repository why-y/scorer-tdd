package ch.gry.scorer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class GameTest {
	
	private Game testGame;
	private Player server;
	private Player returner;
	
	@Before
	public void createGame() {
		server = Player.create("A");
		returner = Player.create("B");
		testGame = Game.create(server, returner);
	}
	

	@Test
	public void createInstance() {
		assertThat(testGame, not(nullValue()));
	}
	
	@Test
	public void initial_score_is_0_0() {
		assertThat(testGame.getScore(), is(equalTo("0:0")));		
	}
	
	@Test
	public void test_15_0() throws Exception {
		testGame.scoreFor(server);
		assertThat(testGame.getScore(), is(equalTo("15:0")));		
	}
	
	@Test
	public void test_15_15() throws Exception {
		testGame.scoreFor(server);
		testGame.scoreFor(returner);
		assertThat(testGame.getScore(), is(equalTo("15:15")));		
	}

	@Test
	public void test_30_15() throws Exception {
		testGame.scoreFor(server);
		testGame.scoreFor(returner);
		testGame.scoreFor(server);
		assertThat(testGame.getScore(), is(equalTo("30:15")));		
	}

	@Test
	public void test_30_30() throws Exception {
		testGame.scoreFor(server);
		testGame.scoreFor(returner);
		testGame.scoreFor(returner);
		testGame.scoreFor(server);
		assertThat(testGame.getScore(), is(equalTo("30:30")));		
	}

	@Test
	public void test_30_40() throws Exception {
		testGame.scoreFor(server);
		testGame.scoreFor(returner);
		testGame.scoreFor(returner);
		testGame.scoreFor(server);
		testGame.scoreFor(returner);
		assertThat(testGame.getScore(), is(equalTo("30:40")));		
	}

	@Test
	public void test_0_40() throws Exception {
		testGame.scoreFor(returner);
		testGame.scoreFor(returner);
		testGame.scoreFor(returner);
		assertThat(testGame.getScore(), is(equalTo("0:40")));		
	}
	
	@Test
	public void test_deuce() throws Exception {
		testGame.scoreFor(server);
		testGame.scoreFor(returner);
		testGame.scoreFor(returner);
		testGame.scoreFor(server);
		testGame.scoreFor(server);
		testGame.scoreFor(returner);
		assertThat(testGame.getScore(), is(equalTo("Deuce")));		
	}

	@Test
	public void test_advantage_server() throws Exception {
		testGame.scoreFor(server);
		testGame.scoreFor(server);
		testGame.scoreFor(server);
		testGame.scoreFor(returner);
		testGame.scoreFor(returner);
		testGame.scoreFor(returner);
		testGame.scoreFor(server);
		assertThat(testGame.getScore(), is(equalTo("Advantage A")));		
	}

	@Test
	public void test_advantage_returner() throws Exception {
		testGame.scoreFor(server);
		testGame.scoreFor(server);
		testGame.scoreFor(server);
		testGame.scoreFor(returner);
		testGame.scoreFor(returner);
		testGame.scoreFor(returner);
		testGame.scoreFor(returner);
		assertThat(testGame.getScore(), is(equalTo("Advantage B")));		
	}

	@Test
	public void test_game_returner() throws Exception {
		testGame.scoreFor(returner);
		testGame.scoreFor(returner);
		testGame.scoreFor(returner);
		testGame.scoreFor(returner);
		assertThat(testGame.getScore(), is(equalTo("Game B")));		
	}

	@Test
	public void test_game_server_after_deuce() throws Exception {
		testGame.scoreFor(server);
		testGame.scoreFor(server);
		testGame.scoreFor(server);
		testGame.scoreFor(returner);
		testGame.scoreFor(returner);
		testGame.scoreFor(returner);
		// deuce
		testGame.scoreFor(server);
		testGame.scoreFor(returner);
		// deuce
		testGame.scoreFor(server);
		testGame.scoreFor(server);
		assertThat(testGame.getScore(), is(equalTo("Game A")));		
	}
	
	@Test (expected = GameOverException.class)
	public void exc_when_score_to_terminated_game() throws Exception {
		testGame.scoreFor(server);
		testGame.scoreFor(server);
		testGame.scoreFor(server);
		testGame.scoreFor(server);
		// game server
		testGame.scoreFor(server);
	}
	
	@Test
	public void withdraw_last_rally() throws Exception {
		testGame.scoreFor(server);
		testGame.withdrawLastRally();
		assertThat(testGame.getScore(), is(equalTo("0:0")));		
	}
	
	@Test
	public void withdraw_with_no_rallies() throws Exception {
		testGame.withdrawLastRally();
		assertThat(testGame.getScore(), is(equalTo("0:0")));		
	}
	
	@Test
	public void reset_game() throws Exception {
		testGame.scoreFor(server);
		testGame.scoreFor(returner);
		testGame.reset();		
		assertThat(testGame.getScore(), is(equalTo("0:0")));		
	}

}
