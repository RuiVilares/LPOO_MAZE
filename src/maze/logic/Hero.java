package maze.logic;

/**
 * 
 * Class that defines the hero.
 * 
 * @author Diogo Trindade
 * @author Rui Vilares
 * 
 */

@SuppressWarnings("serial")
public class Hero extends Piece {
	/** Hero is dead? */
	private boolean dead;
	
	/** Hero has sword? */
	private boolean armed;
	
	/** Hero has shield? */
	private boolean protection;
	
	/** Hero need protection? */
	private boolean protectionNeed;
	
	/** Number of darts */
	private int nDarts;

	/**
	 * Constructs and initializes a hero.
	 * 
	 * @param x 
	 * 			X position
	 * @param y 
	 * 			Y position
	 * 
	 */
	
	public Hero(int x, int y){
		super(x,y);
		this.dead = false;
		this.protection = false;
		this.protectionNeed = true;
		this.armed = false;
		this.desc = 'H';
		this.nDarts=0;
	}
	
	/**
	 * Show if hero has sword
	 * 
	 * @return true Hero has sword, false hero hasn't sword. 
	 * 
	 */
	
	public boolean isArmed(){
		return armed;
	}
	
	
	/**
	 * Set hero armed
	 */
	
	public void setArmed(){
		this.armed=true;
		if(!protection || !protectionNeed)
			this.desc = 'A';
		else
			desc = 'U';
	}

	/**
	 * Show if hero was dead
	 * 
	 * @return true Hero was dead, false hero wasn't dead. 
	 * 
	 */
	
	public boolean isDead() {
		return dead;
	}

	/**
	 * Set hero dead
	 */
	
	public void setDead() {
		dead = true;
	}
	
	/**
	 * Show if hero was protected
	 * 
	 * @return true Hero was protected, false hero wasn't protected. 
	 * 
	 */
	
	public boolean isProtection() {
		return protection;
	}
	
	/**
	 * Put hero with no protection needed
	 */
	
	public void noProtectionNeeded(){
		protection = true;
		protectionNeed = false;
	}
	
	/**
	 * Put hero with shield 
	 */

	public void setProtection() {
		protection = true;
		if(armed){
			desc = 'U';
		}
		else
			desc = 'Q';
	}
	
	/**
	 * Draws hero to String.
	 * 
	 * @return String with a hero
	 * 
	 */	
	
	public String toString(){
		return ""+desc;
	}
	
	/**
	 * Increments number of darts.
	 */	
	
	public void incDarts(){
		this.nDarts++;
	}
	
	/**
	 * Decrements number of darts.
	 */	
	
	public void decDarts(){
		this.nDarts--;
	}
	
	/**
	 * Get number of darts.
	 * 
	 * @return number of darts
	 * 
	 */	
	
	public int getDarts(){
		return this.nDarts;
	}

	public boolean isProtectionNeed() {
		return protectionNeed;
	}
}
