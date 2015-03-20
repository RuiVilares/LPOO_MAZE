package maze.logic;


public class Hero extends Piece {
	private boolean dead;
	private boolean armed;
	private boolean protection;
	private boolean protectionNeed;
	private int nDarts;

	public Hero(int x, int y){
		super(x,y);
		this.dead = false;
		this.protection = false;
		this.protectionNeed = true;
		this.armed = false;
		this.desc = 'H';
		this.nDarts=0;
	}
	
	public boolean isArmed(){
		return armed;
	}
	
	public void setArmed(){
		this.armed=true;
		if(!protection || !protectionNeed)
			this.desc = 'A';
		else
			desc = 'U';
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead() {
		dead = true;
	}
	public boolean isProtection() {
		return protection;
	}
	public void noProtectionNeeded(){
		protection = true;
		protectionNeed = false;
	}

	public void setProtection() {
		protection = true;
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
