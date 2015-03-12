package maze.logic;

public class Darts extends Piece{

	public Darts(int x, int y) {
		super(x, y);
		this.desc=30;
	}
	
	public String toString(){
		return ""+desc;
	}
	
}
