package ch.gry.scorer.event;

import java.time.Duration;

import ch.gry.scorer.Player;

public class RallyTerminatedEventBuilder {

	private Player rallyWinner;
	private int  shots;
	private Duration duration;
	private Type type;
	
	public RallyTerminatedEventBuilder(final Player rallyWinner) {
		this.rallyWinner = rallyWinner;
	}
	
	public RallyTerminatedEventBuilder shots(int noOfShots) {
		this.shots = noOfShots;
		return this;
	}
	
	public RallyTerminatedEventBuilder duration(final Duration duration) {
		this.duration = duration;
		return this;
	}
	
	public RallyTerminatedEventBuilder type(Type type) {
		this.type = type;
		return this;
	}
	
	public RallyTerminatedEvent build() {
		RallyTerminatedEvent event = new RallyTerminatedEvent(rallyWinner);
		event.setShots(shots);
		event.setDuration(duration);
		event.setType(type);
		return event;
	}
}
