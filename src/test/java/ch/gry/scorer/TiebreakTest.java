package ch.gry.scorer;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
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

}
