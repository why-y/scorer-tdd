package ch.gry.scorer.event;

import ch.gry.scorer.command.Command;

public interface EventHandler {
	public void handle(Command command);
}
