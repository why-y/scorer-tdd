package ch.gry.scorer;

import static ch.gry.scorer.Set.Mode.WITHOUT_TIEBREAK;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;


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
		assertThat(firstServer, is(not(nullValue())));
		assertThat(firstReturner, is(not(nullValue())));
		assertThat(testSet, is(not(nullValue())));
	}
	
	@Test
	public void get_initial_score() throws Exception {
		assertThat(testSet.getScore(), is(equalTo("0:0")));
	}

	@Test
	public void test_score_1_0() throws Exception {
		testSet.gameFor(firstServer);
		assertThat(testSet.getScore(), is(equalTo("1:0")));
	}

	@Test
	public void test_score_6_6() throws Exception {
		scoreXTimesFor(firstServer, 5);
		scoreXTimesFor(firstReturner, 5);
		testSet.gameFor(firstServer);
		testSet.gameFor(firstReturner);
		assertThat(testSet.getScore(), is(equalTo("6:6")));
	}

	@Test
	public void pat_won_3_6() throws Exception {
		scoreXTimesFor(firstServer, 3);
		scoreXTimesFor(firstReturner, 6);
		assertThat(testSet.getScore(), is(equalTo("Set Pat")));
	}

	@Test
	public void tom_won_7_5() throws Exception {
		scoreXTimesFor(firstServer, 5);
		scoreXTimesFor(firstReturner, 5);
		scoreXTimesFor(firstServer, 2);
		assertThat(testSet.getScore(), is(equalTo("Set Tom")));
	}

	@Test(expected = SetOverException.class)
	public void disallow_score_on_terminated_set() throws Exception {
		scoreXTimesFor(firstServer, 7);
	}




	private void scoreXTimesFor(Player player, int times) throws  SetOverException {
		IntStream.range(0,times).forEach(i -> testSet.gameFor(player));
	}
}
