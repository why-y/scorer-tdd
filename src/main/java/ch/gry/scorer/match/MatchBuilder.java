package ch.gry.scorer.match;

import ch.gry.scorer.Player;
import ch.gry.scorer.Set;

import static ch.gry.scorer.match.Length.*;
import static ch.gry.scorer.match.Mode.*;

/**
 * Created by gry on 15.09.16.
 */
public class MatchBuilder {

    private Player firstServer, firstReturner;
    private Length length = BEST_OF_THREE;
    private Mode mode = WITH_TIEBREAKS;

    public MatchBuilder(final Player firstServer, final Player firstReturner) {
        this.firstServer = firstServer;
        this.firstReturner = firstReturner;
    }

    public MatchBuilder length(Length length) {
        this.length = length;
        return this;
    }

    public MatchBuilder mode(Mode mode) {
        this.mode = mode;
        return this;
    }

    public Match build() {
        Match match = new Match(firstServer, firstReturner);
        match.length = this.length;
        match.mode = this.mode;
        match.sets.add(Set.create(firstServer, firstReturner,
                match.mode==WITHOUT_TIEBREAK ? Set.Mode.WITHOUT_TIEBREAK : Set.Mode.WITH_TIEBREAK));
        return match;
    }
}
