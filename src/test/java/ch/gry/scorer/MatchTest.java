package ch.gry.scorer;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;


public class MatchTest {
	
	private Player tom;
	private Player pat;

	@Before
	public void setUp() {
		tom =  Player.create("Tom");
		pat = Player.create("Pat");
	}

	@Test
	public void createMatch() throws Exception {
		Match testMatch = Match.create(tom, pat);
		assertThat(testMatch, is(not(nullValue())));
	}
	
	@Test
	public void initial_score_is_0_0() throws Exception {
		Match testMatch = Match.create(tom, pat);
		assertThat(testMatch.getScore(), is(equalTo("0:0")));
	}
	
	@Test
	public void tom_leads_1_0() throws Exception {
		Match testMatch = Match.create(tom, pat);
		testMatch.scoreFor(tom);
		assertThat(testMatch.getScore(), is(equalTo("1:0")));
	}
	
	@Test
	public void tom_wins_a_best_of_3_with_2_1() throws Exception {
		Match testMatch = Match.create(tom, pat);
		testMatch.scoreFor(pat);
		testMatch.scoreFor(tom);
		testMatch.scoreFor(tom);
		assertThat(testMatch.getScore(), is(equalTo("Match Tom")));
	}
	
	@Test(expected = AlreadyTerminatedException.class)
	public void dont_let_score_on_terminated_match() throws Exception {
		Match testMatch = Match.create(tom, pat);
		testMatch.scoreFor(tom);
		testMatch.scoreFor(tom);
		testMatch.scoreFor(tom);	
	}
	
	@Test
	@Ignore
	public void allow_2_2_for_best_of_five_matches() throws Exception {
		Match testMatch = Match.create(tom, pat);
		testMatch.scoreFor(tom);
		testMatch.scoreFor(tom);
		testMatch.scoreFor(pat);
		testMatch.scoreFor(pat);
		assertThat(testMatch.getScore(), is(equalTo("2:2")));		
	}
}
