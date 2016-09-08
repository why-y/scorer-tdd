package ch.gry.scorer;

import static ch.gry.scorer.Set.Mode.WITHOUT_TIEBREAK;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
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
	public void test_1_0() throws Exception {
		testSet.gameFor(firstServer);
		assertThat(testSet.getScore(), is(equalTo("1:0")));
	}
}
