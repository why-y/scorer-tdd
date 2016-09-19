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
    public void rallyWonBy(final Player player) {
    	scoreFor(player);
    }
    
    @Override
    public String getScore() {
        for (Player player: getPlayers()) {
            if(isWonBy(player))
                return String.format("Tiebreak %s", player.getName());
        }
        return String.format("%d:%d", getScoreCount(getServer()), getScoreCount(getReturner()));
    }

    @Override
    public boolean isWonBy(Player player) {
        return getScoreCount(player)>=7 && isAtLeastTwoScorePointsAhead(player);
    }
}
