package maze.logic;

@SuppressWarnings("serial")
public class Shield extends Piece {

	public Shield(int x, int y) {
		super(x, y);
		this.desc = 'O';
	}
	
	public String toString(){
		return "" + desc;
	}
}
