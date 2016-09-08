package ch.gry.scorer;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static ch.gry.scorer.Player.*;

public class ScorerTest {

	@Test
	public void createInstance() {
		Scorer scorer = Scorer.create("A", "B");
		assertThat(scorer, not(nullValue()));
	}
	
	@Test
	public void initial_score_is_0_0() {
		Scorer scorer = Scorer.create("A", "B");
		assertThat(scorer.getScore(), is(equalTo("0:0")));		
	}
	
	@Test
	public void test_15_0() throws Exception {
		Scorer scorer = Scorer.create("A", "B");
		scorer.scoreFor(SERVER);
		assertThat(scorer.getScore(), is(equalTo("15:0")));		
	}
	
	@Test
	public void test_15_15() throws Exception {
		Scorer scorer = Scorer.create("A", "B");
		scorer.scoreFor(SERVER);
		scorer.scoreFor(RETURNER);
		assertThat(scorer.getScore(), is(equalTo("15:15")));		
	}

	@Test
	public void test_30_15() throws Exception {
		Scorer scorer = Scorer.create("A", "B");
		scorer.scoreFor(SERVER);
		scorer.scoreFor(RETURNER);
		scorer.scoreFor(SERVER);
		assertThat(scorer.getScore(), is(equalTo("30:15")));		
	}

	@Test
	public void test_30_30() throws Exception {
		Scorer scorer = Scorer.create("A", "B");
		scorer.scoreFor(SERVER);
		scorer.scoreFor(RETURNER);
		scorer.scoreFor(RETURNER);
		scorer.scoreFor(SERVER);
		assertThat(scorer.getScore(), is(equalTo("30:30")));		
	}

	@Test
	public void test_30_40() throws Exception {
		Scorer scorer = Scorer.create("A", "B");
		scorer.scoreFor(SERVER);
		scorer.scoreFor(RETURNER);
		scorer.scoreFor(RETURNER);
		scorer.scoreFor(SERVER);
		scorer.scoreFor(RETURNER);
		assertThat(scorer.getScore(), is(equalTo("30:40")));		
	}

	@Test
	public void test_0_40() throws Exception {
		Scorer scorer = Scorer.create("A", "B");
		scorer.scoreFor(RETURNER);
		scorer.scoreFor(RETURNER);
		scorer.scoreFor(RETURNER);
		assertThat(scorer.getScore(), is(equalTo("0:40")));		
	}
	
	@Test
	public void test_deuce() throws Exception {
		Scorer scorer = Scorer.create("A", "B");
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
		Scorer scorer = Scorer.create("A", "B");
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
		Scorer scorer = Scorer.create("A", "B");
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
		Scorer scorer = Scorer.create("A", "B");
		scorer.scoreFor(RETURNER);
		scorer.scoreFor(RETURNER);
		scorer.scoreFor(RETURNER);
		scorer.scoreFor(RETURNER);
		assertThat(scorer.getScore(), is(equalTo("Game B")));		
	}

	@Test
	public void test_game_server_after_deuce() throws Exception {
		Scorer scorer = Scorer.create("A", "B");
		System.out.println(scorer);
		scorer.scoreFor(SERVER);
		System.out.println(scorer);
		scorer.scoreFor(SERVER);
		System.out.println(scorer);
		scorer.scoreFor(SERVER);
		System.out.println(scorer);
		scorer.scoreFor(RETURNER);
		System.out.println(scorer);
		scorer.scoreFor(RETURNER);
		System.out.println(scorer);
		scorer.scoreFor(RETURNER);
		System.out.println(scorer);
		// deuce
		scorer.scoreFor(SERVER);
		System.out.println(scorer);
		scorer.scoreFor(RETURNER);
		System.out.println(scorer);
		// deuce
		scorer.scoreFor(SERVER);
		System.out.println(scorer);
		scorer.scoreFor(SERVER);
		System.out.println(scorer);
		assertThat(scorer.getScore(), is(equalTo("Game A")));		
	}

}
