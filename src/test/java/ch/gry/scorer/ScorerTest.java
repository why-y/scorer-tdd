package ch.gry.scorer;

import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.Before;

import static org.hamcrest.CoreMatchers.*;
import static ch.gry.scorer.Player.*;

public class ScorerTest {
	
	private Game scorer;
	
	@Before
	public void createScorer() {
		scorer = Game.create("A", "B");
	}
	

	@Test
	public void createInstance() {
		assertThat(scorer, not(nullValue()));
	}
	
	@Test
	public void initial_score_is_0_0() {
		assertThat(scorer.getScore(), is(equalTo("0:0")));		
	}
	
	@Test
	public void test_15_0() throws Exception {
		scorer.scoreFor(SERVER);
		assertThat(scorer.getScore(), is(equalTo("15:0")));		
	}
	
	@Test
	public void test_15_15() throws Exception {
		scorer.scoreFor(SERVER);
		scorer.scoreFor(RETURNER);
		assertThat(scorer.getScore(), is(equalTo("15:15")));		
	}

	@Test
	public void test_30_15() throws Exception {
		scorer.scoreFor(SERVER);
		scorer.scoreFor(RETURNER);
		scorer.scoreFor(SERVER);
		assertThat(scorer.getScore(), is(equalTo("30:15")));		
	}

	@Test
	public void test_30_30() throws Exception {
		scorer.scoreFor(SERVER);
		scorer.scoreFor(RETURNER);
		scorer.scoreFor(RETURNER);
		scorer.scoreFor(SERVER);
		assertThat(scorer.getScore(), is(equalTo("30:30")));		
	}

	@Test
	public void test_30_40() throws Exception {
		scorer.scoreFor(SERVER);
		scorer.scoreFor(RETURNER);
		scorer.scoreFor(RETURNER);
		scorer.scoreFor(SERVER);
		scorer.scoreFor(RETURNER);
		assertThat(scorer.getScore(), is(equalTo("30:40")));		
	}

	@Test
	public void test_0_40() throws Exception {
		scorer.scoreFor(RETURNER);
		scorer.scoreFor(RETURNER);
		scorer.scoreFor(RETURNER);
		assertThat(scorer.getScore(), is(equalTo("0:40")));		
	}
	
	@Test
	public void test_deuce() throws Exception {
		scorer.scoreFor(SERVER);
		scorer.scoreFor(RETURNER);
		scorer.scoreFor(RETURNER);
		scorer.scoreFor(SERVER);
		scorer.scoreFor(SERVER);
		scorer.scoreFor(RETURNER);
		assertThat(scorer.getScore(), is(equalTo("Deuce")));		
	}

	@Test
	public void test_advantage_server() throws Exception {
		scorer.scoreFor(SERVER);
		scorer.scoreFor(SERVER);
		scorer.scoreFor(SERVER);
		scorer.scoreFor(RETURNER);
		scorer.scoreFor(RETURNER);
		scorer.scoreFor(RETURNER);
		scorer.scoreFor(SERVER);
		assertThat(scorer.getScore(), is(equalTo("Advantage A")));		
	}

	@Test
	public void test_advantage_returner() throws Exception {
		scorer.scoreFor(SERVER);
		scorer.scoreFor(SERVER);
		scorer.scoreFor(SERVER);
		scorer.scoreFor(RETURNER);
		scorer.scoreFor(RETURNER);
		scorer.scoreFor(RETURNER);
		scorer.scoreFor(RETURNER);
		assertThat(scorer.getScore(), is(equalTo("Advantage B")));		
	}

	@Test
	public void test_game_returner() throws Exception {
		scorer.scoreFor(RETURNER);
		scorer.scoreFor(RETURNER);
		scorer.scoreFor(RETURNER);
		scorer.scoreFor(RETURNER);
		assertThat(scorer.getScore(), is(equalTo("Game B")));		
	}

	@Test
	public void test_game_server_after_deuce() throws Exception {
		scorer.scoreFor(SERVER);
		scorer.scoreFor(SERVER);
		scorer.scoreFor(SERVER);
		scorer.scoreFor(RETURNER);
		scorer.scoreFor(RETURNER);
		scorer.scoreFor(RETURNER);
		// deuce
		scorer.scoreFor(SERVER);
		scorer.scoreFor(RETURNER);
		// deuce
		scorer.scoreFor(SERVER);
		scorer.scoreFor(SERVER);
		assertThat(scorer.getScore(), is(equalTo("Game A")));		
	}
	
	@Test (expected = GameOverException.class)
	public void exc_when_score_to_terminated_game() throws Exception {
		scorer.scoreFor(SERVER);
		scorer.scoreFor(SERVER);
		scorer.scoreFor(SERVER);
		scorer.scoreFor(SERVER);
		// game server
		scorer.scoreFor(SERVER);
	}

}
