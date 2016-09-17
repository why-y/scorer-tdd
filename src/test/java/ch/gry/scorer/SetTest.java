package ch.gry.scorer;

import static ch.gry.scorer.Set.Mode.WITHOUT_TIEBREAK;
import static ch.gry.scorer.Set.Mode.WITH_TIEBREAK;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;


public class SetTest {
	
	private Set testSet;
	private Player firstServer;
	private Player firstReturner;

	@Before
	public void setUp() {
		firstServer =  Player.create("Tom");
		firstReturner = Player.create("Pat");
		testSet = Set.create(firstServer, firstReturner, WITHOUT_TIEBREAK);
	}

	@Test
	public void createSet() throws Exception {
		assertThat(testSet, is(not(nullValue())));
	}
	
	@Test
	public void get_initial_score() throws Exception {
		assertThat(testSet.getScore(), is(equalTo("0:0")));
	}

	@Test
	public void test_score_1_0() throws Exception {
		testSet.scoreFor(firstServer);
		assertThat(testSet.getScore(), is(equalTo("1:0")));
	}

	@Test
	public void test_score_6_6() throws Exception {
		scoreXTimesFor(firstServer, 5);
		scoreXTimesFor(firstReturner, 5);
		testSet.scoreFor(firstServer);
		testSet.scoreFor(firstReturner);
		assertThat(testSet.getScore(), is(equalTo("6:6")));
	}

	@Test
	public void pat_won_3_6() throws Exception {
		scoreXTimesFor(firstServer, 3);
		scoreXTimesFor(firstReturner, 6);
		assertThat(testSet.getScore(), is(equalTo("3:6")));
		assertThat(testSet.isWonBy(firstReturner), is(true));
	}

	@Test
	public void tom_won_7_5() throws Exception {
		scoreXTimesFor(firstServer, 5);
		scoreXTimesFor(firstReturner, 5);
		scoreXTimesFor(firstServer, 2);
		assertThat(testSet.getScore(), is(equalTo("7:5")));
		assertThat(testSet.isWonBy(firstServer), is(true));
	}

	@Test(expected = AlreadyTerminatedException.class)
	public void disallow_score_on_terminated_set() throws Exception {
		scoreXTimesFor(firstServer, 7);
	}

	@Test
	public void pat_won_7_9() throws Exception {
		scoreXTimesFor(firstServer, 5);
		scoreXTimesFor(firstReturner, 6);
		scoreXTimesFor(firstServer, 2);
		scoreXTimesFor(firstReturner, 3);
		assertThat(testSet.getScore(), is(equalTo("7:9")));
		assertThat(testSet.isWonBy(firstReturner), is(true));
	}

	@Test
	public void won_in_tiebreak() throws Exception {
		Player mark = Player.create("Mark");
		Player paul = Player.create("Paul");
		testSet = Set.create(mark, paul, WITH_TIEBREAK);
		scoreXTimesFor(mark, 5);
		scoreXTimesFor(paul, 6);
		scoreXTimesFor(mark, 2);
		assertThat(testSet.getScore(), is(equalTo("7:6")));
		assertThat(testSet.isWonBy(mark), is(true));
	}

	@Test(expected = AlreadyTerminatedException.class)
	public void disallow_score_on_terminated_tiebreaker() throws Exception {
		Player mark = Player.create("Mark");
		Player paul = Player.create("Paul");
		testSet = Set.create(mark, paul, WITH_TIEBREAK);
		scoreXTimesFor(mark, 5);
		scoreXTimesFor(paul, 6);
		scoreXTimesFor(mark, 2);
		testSet.scoreFor(paul);
	}

	@Test
	public void test_0_0_after_reset() {
		scoreXTimesFor(firstServer, 5);
		scoreXTimesFor(firstReturner, 2);
		testSet.reset();
		assertThat(testSet.getScore(), is(equalTo("0:0")));
	}

	@Test
	public void test_get_current_game() throws Exception {
		Game currentGame = testSet.getCurrentGame();
		assertThat(currentGame, is(notNullValue()));
	}

	private void scoreXTimesFor(Player player, int times) throws AlreadyTerminatedException {
		IntStream.range(0,times).forEach(i -> testSet.scoreFor(player));
	}
}
