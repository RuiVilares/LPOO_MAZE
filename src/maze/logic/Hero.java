package maze.logic;


public class Hero extends Piece {
	private boolean dead;
	private boolean armed;
	
	public Hero(int x, int y){
		super(x,y);
		this.dead = false;
		this.armed = false;
		this.desc = 'H';
	}
	
	public boolean getArmed(){
		return armed;
	}
	
	public void setArmed(){
		this.armed=true;
		this.desc = 'A';
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
