package maze.logic;


public class Dragon extends Piece {

	private boolean dead;

	public Dragon(int x, int y) {
		super(x, y);
		this.dead = false;
		this.desc = 'D';
	}

	public boolean getDead() {
		return dead;
	}

	public void setDead() {
		this.dead = true;
	}
	public String toString(){
		return ""+desc;
	}

}
