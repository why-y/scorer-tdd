package ch.gry.scorer.match;

import static org.junit.Assert.*;

import ch.gry.scorer.AlreadyTerminatedException;
import ch.gry.scorer.Player;
import ch.gry.scorer.match.Match;
import ch.gry.scorer.match.MatchBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static ch.gry.scorer.match.Length.*;

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
		Match testMatch = new Match(tom, pat);
		assertThat(testMatch, is(not(nullValue())));
	}
	
	@Test
	public void initial_score_is_0_0() throws Exception {
		Match testMatch = new Match(tom, pat);
		assertThat(testMatch.getScore(), is(equalTo("0:0")));
	}
	
	@Test
	public void tom_leads_1_0() throws Exception {
		Match testMatch = new Match(tom, pat);
		testMatch.scoreFor(tom);
		assertThat(testMatch.getScore(), is(equalTo("1:0")));
	}
	
	@Test
	public void tom_wins_a_best_of_3_with_2_1() throws Exception {
		Match testMatch = new Match(tom, pat);
		testMatch.scoreFor(pat);
		testMatch.scoreFor(tom);
		testMatch.scoreFor(tom);
		assertThat(testMatch.getScore(), is(equalTo("Match Tom")));
	}
	
	@Test(expected = AlreadyTerminatedException.class)
	public void disallow_score_on_a_terminated_match() throws Exception {
		Match testMatch = new Match(tom, pat);
		testMatch.scoreFor(tom);
		testMatch.scoreFor(tom);
		testMatch.scoreFor(tom);	
	}
	
	@Test
	public void score_is_2_2_for_best_of_five_match() throws Exception {
		Match testMatch = new MatchBuilder(tom, pat).length(BEST_OF_FIVE).build();
		testMatch.scoreFor(tom);
		testMatch.scoreFor(tom);
		testMatch.scoreFor(pat);
		testMatch.scoreFor(pat);
		assertThat(testMatch.getScore(), is(equalTo("2:2")));		
	}

	@Test
	public void pat_wins_a_best_of_5_match_with_2_3() throws Exception {
		Match testMatch = new MatchBuilder(tom, pat).length(BEST_OF_FIVE).build();
		testMatch.scoreFor(pat);
		testMatch.scoreFor(tom);
		testMatch.scoreFor(pat);
		testMatch.scoreFor(tom);
		testMatch.scoreFor(pat);
		assertThat(testMatch.getScore(), is(equalTo("Match Pat")));
	}
}