package ch.gry.scorer;

public class Player {

	private String name;
	
	private Player() {
	}

	public static Player create(String name) {
		Player player = new Player();
		player.setName(name);
		return player;
	}
	
	public void setName(String name) {
		this.name = name;		
	}
	
	public String getName() {
		return this.name;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}
