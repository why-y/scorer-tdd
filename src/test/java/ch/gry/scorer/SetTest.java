package ch.gry.scorer;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import static ch.gry.scorer.Set.Mode.*;


public class SetTest {
	
	Set testSet;
	
	@Before
	public void setUp() {
	}

	@Test
	public void createSet() throws Exception {
		testSet = Set.create("A", "B", WITHOUT_TIEBREAK);
		
	}
}
