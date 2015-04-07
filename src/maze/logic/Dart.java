package maze.logic;

@SuppressWarnings("serial")
public class Dart extends Piece{
	boolean taked;

	public Dart(int x, int y) {
		super(x, y);
		this.desc='/';
		this.taked=false;
	}
	
	public String toString(){
		return ""+desc;
	}
	
	public boolean getTaked(){
		return taked;
	}
	
	public void setTaked(){
		this.taked = true;
	}
	
}
