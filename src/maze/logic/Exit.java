package maze.logic;


@SuppressWarnings("serial")
public class Exit extends Piece {
	
	public Exit(int x, int y){
		super(x, y);
		this.desc = 'S';
	}
	public String toString(){
		return ""+desc;
	}
	
}
