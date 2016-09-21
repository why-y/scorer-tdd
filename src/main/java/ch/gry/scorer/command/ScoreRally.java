package ch.gry.scorer.command;

import ch.gry.scorer.ScoreUnit;
import ch.gry.scorer.rally.Rally;

public class ScoreRally implements Command {
	private ScoreUnit scoreUnit;
	private Rally rally;
	
	public ScoreRally(final ScoreUnit scoreUnit, final Rally rally) {
		this.scoreUnit = scoreUnit;
		this.rally = rally;
	}
	
	public ScoreUnit getScoreUnit() {
		return this.scoreUnit;
	}
	
	public Rally getRally() {
		return this.rally;
	}
	
	@Override
	public void execute() {
		scoreUnit.score(rally);
	}

}
