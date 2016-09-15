package ch.gry.scorer;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;


public class MatchTest {
	
	private Match testMatch;
	private Player firstServer;
	private Player firstReturner;

	@Before
	public void setUp() {
		firstServer =  Player.create("Tom");
		firstReturner = Player.create("Pat");
		testMatch = Match.create(firstServer, firstReturner);
	}

	@Test
	public void createMatch() throws Exception {
		assertThat(testMatch, is(not(nullValue())));
	}
	
	@Test
	public void initial_score_is_0_0() throws Exception {
		assertThat(testMatch.getScore(), is(equalTo("0:0")));
	}
	
	@Test
	public void tom_leads_1_0() throws Exception {
		testMatch.scoreFor(firstServer);
		assertThat(testMatch.getScore(), is(equalTo("1:0")));
	}
}
