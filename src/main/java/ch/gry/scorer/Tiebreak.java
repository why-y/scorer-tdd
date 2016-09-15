package ch.gry.scorer;

/**
 * Created by gry on 15.09.16.
 */
public class Tiebreak extends ScoreUnit{

    private Tiebreak(final Player firstServer, final Player firstReturner) {
        super(firstServer, firstReturner);
    }

    public static Tiebreak create(final Player firstServer, final Player firstReturner) {
        return new Tiebreak(firstServer, firstReturner);
    }

    @Override
    public boolean isWonBy(Player player) {
        return false;
    }
}