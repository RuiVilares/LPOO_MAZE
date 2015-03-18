package maze.logic;


public class Hero extends Piece {
	private boolean dead;
	private boolean armed;
	private boolean protection;
	private int nDarts;

	public Hero(int x, int y){
		super(x,y);
		this.dead = false;
		this.protection = false;
		this.armed = false;
		this.desc = 'H';
		this.nDarts=0;
	}
	
	public boolean getArmed(){
		return armed;
	}
	
	public void setArmed(){
		this.armed=true;
		if(!protection)
			this.desc = 'A';
		else
			desc = 'U';
	}

	public boolean getDead() {
		return dead;
	}

	public void setDead() {
		this.dead = true;
	}
	public boolean isProtection() {
		return protection;
	}

	public void setProtection() {
		this.protection = true;
		if(armed){
			desc = 'U';
		}
		else
			desc = 'Q';
	}
	public String toString(){
		return ""+desc;
	}
	
	public void incDarts(){
		this.nDarts++;
	}
	
	public void decDarts(){
		this.nDarts--;
	}
	
	public int getDarts(){
		return this.nDarts;
	}
}
