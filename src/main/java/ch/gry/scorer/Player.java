package ch.gry.scorer;

public enum Player {

	SERVER("the server"), RETURNER("the returner");
	
	private String name;
	
	private Player(String name) {
		setName(name);
	}
	
	public void setName(String name) {
		this.name = name;		
	}
	
	public String getName() {
		return this.name;
	}
}
