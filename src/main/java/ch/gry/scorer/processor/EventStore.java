package ch.gry.scorer.processor;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import ch.gry.scorer.ScoreUnit;
import ch.gry.scorer.command.Command;
import ch.gry.scorer.command.ScoreRally;
import ch.gry.scorer.event.EventHandler;

public class EventStore {
	
	private List<EventHandler> registeredHandlers;
	
	private Map<Date, Command> commandSequence = new HashMap<Date, Command>();
	
	public void push(final Command command) {
		commandSequence.put(new Date(), command);
		notifyHandlers(command);
	}
	
	private void notifyHandlers(final Command command) {
		registeredHandlers.stream().forEach(h -> h.handle(command) );
	}

	public Stream<Command> replayAll() {
		return commandSequence.values().stream();
	}
	
	public Stream<ScoreRally> replayEventsFor(final ScoreUnit scoreUnit) {
		return replayAll()
				.filter(c -> (c instanceof ScoreRally))
				.map(c -> (ScoreRally)c);
	}

	public void register(final EventHandler handler) {
		registeredHandlers.add(handler);
	}

	public void unRegister(final EventHandler handler) {
		registeredHandlers.removeIf(h -> registeredHandlers.contains(h));
	}

}
