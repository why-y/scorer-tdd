package ch.gry.scorer.match;

/**
 * Created by gry on 15.09.16.
 */
public enum Length {
    BEST_OF_THREE(2),
    BEST_OF_FIVE(3);

    private int noOfSetsToWin;

    Length(int n) {
        noOfSetsToWin = n;
    }

    public int getNoOfSetsToWin() {
        return noOfSetsToWin;
    }
}
