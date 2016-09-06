package ch.gry.scorer;

import static org.junit.Assert.*;

import org.junit.Test;

public class ScorerTest {

	@Test
	public void createInstance() {
		Scorer scorer = Scorer.create("A", "B");
	}

}
