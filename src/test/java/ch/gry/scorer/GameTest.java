package ch.gry.scorer;

import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.Before;

import static org.hamcrest.CoreMatchers.*;
import static ch.gry.scorer.Player.*;

public class GameTest {
	
	private Game testGame;
	
	@Before
	public void createScorer() {
		testGame = Game.create("A", "B");
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
		testGame.scoreFor(SERVER);
		assertThat(testGame.getScore(), is(equalTo("15:0")));		
	}
	
	@Test
	public void test_15_15() throws Exception {
		testGame.scoreFor(SERVER);
		testGame.scoreFor(RETURNER);
		assertThat(testGame.getScore(), is(equalTo("15:15")));		
	}

	@Test
	public void test_30_15() throws Exception {
		testGame.scoreFor(SERVER);
		testGame.scoreFor(RETURNER);
		testGame.scoreFor(SERVER);
		assertThat(testGame.getScore(), is(equalTo("30:15")));		
	}

	@Test
	public void test_30_30() throws Exception {
		testGame.scoreFor(SERVER);
		testGame.scoreFor(RETURNER);
		testGame.scoreFor(RETURNER);
		testGame.scoreFor(SERVER);
		assertThat(testGame.getScore(), is(equalTo("30:30")));		
	}

	@Test
	public void test_30_40() throws Exception {
		testGame.scoreFor(SERVER);
		testGame.scoreFor(RETURNER);
		testGame.scoreFor(RETURNER);
		testGame.scoreFor(SERVER);
		testGame.scoreFor(RETURNER);
		assertThat(testGame.getScore(), is(equalTo("30:40")));		
	}

	@Test
	public void test_0_40() throws Exception {
		testGame.scoreFor(RETURNER);
		testGame.scoreFor(RETURNER);
		testGame.scoreFor(RETURNER);
		assertThat(testGame.getScore(), is(equalTo("0:40")));		
	}
	
	@Test
	public void test_deuce() throws Exception {
		testGame.scoreFor(SERVER);
		testGame.scoreFor(RETURNER);
		testGame.scoreFor(RETURNER);
		testGame.scoreFor(SERVER);
		testGame.scoreFor(SERVER);
		testGame.scoreFor(RETURNER);
		assertThat(testGame.getScore(), is(equalTo("Deuce")));		
	}

	@Test
	public void test_advantage_server() throws Exception {
		testGame.scoreFor(SERVER);
		testGame.scoreFor(SERVER);
		testGame.scoreFor(SERVER);
		testGame.scoreFor(RETURNER);
		testGame.scoreFor(RETURNER);
		testGame.scoreFor(RETURNER);
		testGame.scoreFor(SERVER);
		assertThat(testGame.getScore(), is(equalTo("Advantage A")));		
	}

	@Test
	public void test_advantage_returner() throws Exception {
		testGame.scoreFor(SERVER);
		testGame.scoreFor(SERVER);
		testGame.scoreFor(SERVER);
		testGame.scoreFor(RETURNER);
		testGame.scoreFor(RETURNER);
		testGame.scoreFor(RETURNER);
		testGame.scoreFor(RETURNER);
		assertThat(testGame.getScore(), is(equalTo("Advantage B")));		
	}

	@Test
	public void test_game_returner() throws Exception {
		testGame.scoreFor(RETURNER);
		testGame.scoreFor(RETURNER);
		testGame.scoreFor(RETURNER);
		testGame.scoreFor(RETURNER);
		assertThat(testGame.getScore(), is(equalTo("Game B")));		
	}

	@Test
	public void test_game_server_after_deuce() throws Exception {
		testGame.scoreFor(SERVER);
		testGame.scoreFor(SERVER);
		testGame.scoreFor(SERVER);
		testGame.scoreFor(RETURNER);
		testGame.scoreFor(RETURNER);
		testGame.scoreFor(RETURNER);
		// deuce
		testGame.scoreFor(SERVER);
		testGame.scoreFor(RETURNER);
		// deuce
		testGame.scoreFor(SERVER);
		testGame.scoreFor(SERVER);
		assertThat(testGame.getScore(), is(equalTo("Game A")));		
	}
	
	@Test (expected = GameOverException.class)
	public void exc_when_score_to_terminated_game() throws Exception {
		testGame.scoreFor(SERVER);
		testGame.scoreFor(SERVER);
		testGame.scoreFor(SERVER);
		testGame.scoreFor(SERVER);
		// game server
		testGame.scoreFor(SERVER);
	}
	
	@Test
	public void withdraw_last_rally() throws Exception {
		testGame.scoreFor(SERVER);
		testGame.withdrawLastRally();
		assertThat(testGame.getScore(), is(equalTo("0:0")));		
	}
	
	@Test
	public void reset_game() throws Exception {
		testGame.scoreFor(SERVER);
		testGame.scoreFor(RETURNER);
		testGame.reset();		
		assertThat(testGame.getScore(), is(equalTo("0:0")));		
	}

}
