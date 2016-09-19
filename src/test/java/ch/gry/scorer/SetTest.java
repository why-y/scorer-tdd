package ch.gry.scorer;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import static ch.gry.scorer.set.Mode.*;
import ch.gry.scorer.set.Set;
import ch.gry.scorer.set.SetBuilder;

import java.util.stream.IntStream;


public class SetTest {
	
	private static final int NUM_OF_RALLIES_TO_WIN_A_GAME = 4;
	private static final int NUM_OF_RALLIES_TO_WIN_A_TIEBREAK = 7;
	private Set testSet;
	private Player tom;
	private Player pat;

	@Before
	public void setUp() {
		tom =  Player.create("Tom");
		pat = Player.create("Pat");
		testSet = new SetBuilder(tom, pat).build();
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
		scoreRalliesFor(tom, NUM_OF_RALLIES_TO_WIN_A_GAME);
		assertThat(testSet.getScore(), is(equalTo("1:0")));
	}

	@Test
	public void test_score_6_6() throws Exception {
		scoreRalliesFor(tom, 5 * NUM_OF_RALLIES_TO_WIN_A_GAME);
		scoreRalliesFor(pat, 5 * NUM_OF_RALLIES_TO_WIN_A_GAME);
		scoreRalliesFor(tom, NUM_OF_RALLIES_TO_WIN_A_GAME);
		scoreRalliesFor(pat, NUM_OF_RALLIES_TO_WIN_A_GAME);
		assertThat(testSet.getScore(), is(equalTo("6:6")));
	}

	@Test
	public void pat_won_3_6() throws Exception {
		scoreRalliesFor(tom, 3 * NUM_OF_RALLIES_TO_WIN_A_GAME);
		scoreRalliesFor(pat, 6 * NUM_OF_RALLIES_TO_WIN_A_GAME);
		assertThat(testSet.getScore(), is(equalTo("3:6")));
		assertThat(testSet.isWonBy(pat), is(true));
	}

	@Test
	public void tom_won_7_5() throws Exception {
		scoreRalliesFor(tom, 5 * NUM_OF_RALLIES_TO_WIN_A_GAME);
		scoreRalliesFor(pat, 5 * NUM_OF_RALLIES_TO_WIN_A_GAME);
		scoreRalliesFor(tom, 2 * NUM_OF_RALLIES_TO_WIN_A_GAME);
		assertThat(testSet.getScore(), is(equalTo("7:5")));
		assertThat(testSet.isWonBy(tom), is(true));
	}

	@Test(expected = AlreadyTerminatedException.class)
	public void disallow_score_on_terminated_set() throws Exception {
		scoreRalliesFor(tom, 7 * NUM_OF_RALLIES_TO_WIN_A_GAME);
	}

	@Test
	public void paul_won_7_9() throws Exception {
		Player mark = Player.create("Mark");
		Player paul = Player.create("Paul");
		testSet = new SetBuilder(mark, paul).mode(WITHOUT_TIEBREAK).build();
		scoreRalliesFor(mark, 5 * NUM_OF_RALLIES_TO_WIN_A_GAME);
		scoreRalliesFor(paul, 6 * NUM_OF_RALLIES_TO_WIN_A_GAME);
		scoreRalliesFor(mark, 2 * NUM_OF_RALLIES_TO_WIN_A_GAME);
		scoreRalliesFor(paul, 3 * NUM_OF_RALLIES_TO_WIN_A_GAME);
		assertThat(testSet.getScore(), is(equalTo("7:9")));
		assertThat(testSet.isWonBy(paul), is(true));
	}

	@Test
	public void force_to_tiebreak() throws Exception {
		scoreRalliesFor(tom, 5 * NUM_OF_RALLIES_TO_WIN_A_GAME);
		scoreRalliesFor(pat, 6 * NUM_OF_RALLIES_TO_WIN_A_GAME);
		scoreRalliesFor(tom, NUM_OF_RALLIES_TO_WIN_A_GAME);
		ScoreUnit tiebreak = testSet.getCurrentGame();
		assertThat(tiebreak, is(instanceOf(Tiebreak.class)));
	}

	@Test
	public void won_in_tiebreak() throws Exception {
		scoreRalliesFor(tom, 5 * NUM_OF_RALLIES_TO_WIN_A_GAME);
		scoreRalliesFor(pat, 6 * NUM_OF_RALLIES_TO_WIN_A_GAME);
		scoreRalliesFor(tom, NUM_OF_RALLIES_TO_WIN_A_GAME);
		scoreRalliesFor(tom, NUM_OF_RALLIES_TO_WIN_A_TIEBREAK);
		assertThat(testSet.getScore(), is(equalTo("7:6")));
		assertThat(testSet.isWonBy(tom), is(true));
	}

	@Test
	public void test_0_0_after_reset() {
		scoreRalliesFor(tom, 5 * NUM_OF_RALLIES_TO_WIN_A_GAME);
		scoreRalliesFor(pat, 2 * NUM_OF_RALLIES_TO_WIN_A_GAME);
		testSet.reset();
		assertThat(testSet.getScore(), is(equalTo("0:0")));
	}

	@Test
	public void test_get_current_game() throws Exception {
		ScoreUnit currentGame = testSet.getCurrentGame();
		assertThat(currentGame, is(notNullValue()));
	}

	private void scoreRalliesFor(final Player player, int numOfRallies) {
		IntStream.range(0, numOfRallies).forEach(i -> testSet.rallyWonBy(player));	
	}
	
}
