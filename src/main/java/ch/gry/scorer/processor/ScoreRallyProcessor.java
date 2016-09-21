package ch.gry.scorer.processor;

import ch.gry.scorer.command.ScoreRally;

public class ScoreRallyProcessor extends CommandProcessor<ScoreRally> {

	@Override
	public boolean accepts(final ScoreRally command) {
		return isRallyWinnerPartOfScoreUnit(command);
	}
	
	private boolean isRallyWinnerPartOfScoreUnit(final ScoreRally command) {
		return command.getRally() != null
				&& command.getRally().getRallyWinner() != null
				&& command.getScoreUnit() != null
				&& (command.getScoreUnit().getServer() == command.getRally().getRallyWinner()
					|| command.getScoreUnit().getReturner() == command.getRally().getRallyWinner());
	}

	@Override
	public void process(ScoreRally command) {
		super.handle(command);
	}

}
