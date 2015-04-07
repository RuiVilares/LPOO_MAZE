package maze.logic;


@SuppressWarnings("serial")
public class Sword extends Piece {
	
	
	public Sword(int x, int y){
		super(x, y);
		this.desc='E';
	}
	public String toString(){
		return ""+desc;
	}
	
}
