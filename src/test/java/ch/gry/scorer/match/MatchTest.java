package ch.gry.scorer.match;

import static ch.gry.scorer.match.Length.BEST_OF_FIVE;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import ch.gry.scorer.AlreadyTerminatedException;
import ch.gry.scorer.Player;
import ch.gry.scorer.ScoreUnit;
import ch.gry.scorer.set.Set;

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
		assertThat(testMatch.getScore(), is(equalTo("2:1")));
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
		assertThat(testMatch.getScore(), is(equalTo("2:3")));
	}

	@Test
	public void test_get_current_set() throws Exception {
		Match testMatch = new MatchBuilder(tom, pat).build();
		Set	currentSet = testMatch.getCurrentSet();
		assertThat(currentSet, is(notNullValue()));
	}

	@Test
	public void test_get_current_game() throws Exception {
		Match testMatch = new MatchBuilder(tom, pat).build();
		ScoreUnit currentGame = testMatch.getCurrentGame();
		assertThat(currentGame, is(notNullValue()));
	}

	@Test
	public void test_a_complete_match() throws Exception {
		Match testMatch = new MatchBuilder(tom, pat).build();
		
		testMatch.rallyWonBy(tom);
		testMatch.rallyWonBy(tom);
		testMatch.rallyWonBy(tom);
		testMatch.rallyWonBy(tom);
		
		testMatch.rallyWonBy(tom);
		testMatch.rallyWonBy(tom);
		testMatch.rallyWonBy(tom);
		testMatch.rallyWonBy(tom);
		
		testMatch.rallyWonBy(tom);
		testMatch.rallyWonBy(tom);
		testMatch.rallyWonBy(tom);
		testMatch.rallyWonBy(tom);
		
		testMatch.rallyWonBy(tom);
		testMatch.rallyWonBy(tom);
		testMatch.rallyWonBy(tom);
		testMatch.rallyWonBy(tom);
		
		testMatch.rallyWonBy(tom);
		testMatch.rallyWonBy(tom);
		testMatch.rallyWonBy(tom);
		testMatch.rallyWonBy(tom);
		
		testMatch.rallyWonBy(tom);
		testMatch.rallyWonBy(tom);
		testMatch.rallyWonBy(tom);
		testMatch.rallyWonBy(tom);
		
		
		testMatch.rallyWonBy(tom);
		testMatch.rallyWonBy(tom);
		testMatch.rallyWonBy(tom);
		testMatch.rallyWonBy(tom);
		
		testMatch.rallyWonBy(tom);
		testMatch.rallyWonBy(tom);
		testMatch.rallyWonBy(tom);
		testMatch.rallyWonBy(tom);
		
		testMatch.rallyWonBy(tom);
		testMatch.rallyWonBy(tom);
		assertThat(testMatch.getFullScore(), is(equalTo("1:0; 2:0; 30:0")));
		testMatch.rallyWonBy(tom);
		testMatch.rallyWonBy(tom);
		
		testMatch.rallyWonBy(tom);
		testMatch.rallyWonBy(tom);
		testMatch.rallyWonBy(tom);
		testMatch.rallyWonBy(tom);
		
		testMatch.rallyWonBy(tom);
		testMatch.rallyWonBy(tom);
		testMatch.rallyWonBy(tom);
		testMatch.rallyWonBy(tom);
		
		testMatch.rallyWonBy(tom);
		testMatch.rallyWonBy(tom);
		testMatch.rallyWonBy(tom);
		testMatch.rallyWonBy(tom);
		
		assertThat(testMatch.getFullScore(), is(equalTo("2:0; 0:0; 0:0")));
		
	}
}
