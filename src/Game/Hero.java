package Game;

public class Hero extends Piece {
	private boolean armed;
	
	public Hero(int x, int y){
		super(x,y);
		this.armed = false;
	}
	
	public boolean getArmed(){
		return armed;
	}
	
	public void setArmed(){
		this.armed=true;
	}
}
