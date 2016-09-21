package ch.gry.scorer.rally;

import java.time.Duration;

import ch.gry.scorer.Player;

public class RallyBuilder {
	
	private Player winner;
	private int  shots;
	private Duration duration;
	private Type type;
	
	public RallyBuilder(final Player winner) {
		this.winner = winner;
	}
	
	public RallyBuilder shots(int noOfShots) {
		this.shots = noOfShots;
		return this;
	}
	
	public RallyBuilder duration(final Duration duration) {
		this.duration = duration;
		return this;
	}
	
	public RallyBuilder type(Type type) {
		this.type = type;
		return this;
	}
	
	public Rally build() {
		Rally rally = new Rally(winner);
		rally.setShots(shots);
		rally.setDuration(duration);
		rally.setType(type);
		return rally;
	}
}
