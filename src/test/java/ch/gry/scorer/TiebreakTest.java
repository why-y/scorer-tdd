package ch.gry.scorer;

import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by gry on 15.09.16.
 */
public class TiebreakTest {

    private Tiebreak testTiebreak;
    private Player firstServer;
    private Player firstReturner;

    @Before
    public void setUp() {
        firstServer =  Player.create("Tom");
        firstReturner = Player.create("Pat");
        testTiebreak = Tiebreak.create(firstServer, firstReturner);
    }

    @Test
    public void createInstance() {
        assertThat(testTiebreak, not(nullValue()));
    }

    @Test
    public void initial_score_is_0_0() {
        assertThat(testTiebreak.getScore(), is(equalTo("0:0")));
    }

    @Test
    public void tom_leads_4_2() {
        scoreXTimesFor(firstServer, 4);
        scoreXTimesFor(firstReturner, 2);
        assertThat(testTiebreak.getScore(), is(equalTo("4:2")));
    }

    private void scoreXTimesFor(Player player, int times) throws AlreadyTerminatedException {
        IntStream.range(0,times).forEach(i -> testTiebreak.scoreFor(player));
    }
}
