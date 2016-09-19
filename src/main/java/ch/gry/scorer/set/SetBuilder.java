package ch.gry.scorer.set;

import static ch.gry.scorer.set.Mode.*;

import ch.gry.scorer.Player;
import ch.gry.scorer.set.Mode;

public class SetBuilder {

    private Player firstServer, firstReturner;
    private Mode mode;

	public SetBuilder(final Player firstServer, final Player firstReturner) {
        this.firstServer = firstServer;
        this.firstReturner = firstReturner;
        this.mode = WITH_TIEBREAK;
	}
	
	public SetBuilder mode(Mode mode) {
		this.mode = mode;
		return this;
	}
	
	public Set build() {
		Set set = new Set(firstServer, firstReturner);
		set.mode = this.mode;
		return set;
	}
}
