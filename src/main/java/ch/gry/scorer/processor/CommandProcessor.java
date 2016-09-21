package ch.gry.scorer.processor;

import ch.gry.scorer.command.Command;

abstract public class CommandProcessor<T extends Command> {
	
	private EventStore eventStore;
	
	public void setEventStore(final EventStore eventStore) {
		this.eventStore = eventStore;
	}
	
	public abstract void process(final T command);
	
	public abstract boolean accepts(final T command);
	
	protected void handle(final T command) {
		eventStore.push(command);
	}
}
