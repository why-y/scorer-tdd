package ch.gry.scorer.event;

import java.time.Duration;

import ch.gry.scorer.Player;

public class RallyTerminatedEvent {
	
	private Player rallyWinner;
	private int  shots;
	private Duration duration;
	private Type type;
	
	public RallyTerminatedEvent(final Player rallyWinner) {
		this.rallyWinner = rallyWinner;
	}
	
	public Player getRallyWinner() {
		return rallyWinner;
	}
	
	public int getShots() {
		return shots;
	}
	
	public void setShots(int shots) {
		this.shots = shots;
	}
	
	public Duration getDuration() {
		return duration;
	}
	
	public void setDuration(Duration duration) {
		this.duration = duration;
	}
	
	public Type getType() {
		return type;
	}
	
	public void setType(Type type) {
		this.type = type;
	}

}
