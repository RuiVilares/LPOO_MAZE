package maze.logic;

import java.util.Random;


/**
 * 
 * Class that defines the dragon.
 * 
 * @author Diogo Trindade
 * @author Rui Vilares
 * 
 */


@SuppressWarnings("serial")
public class Dragon extends Piece {

	/**
	 * Represents a Dragon behaviour
	 */
	
	public enum Behaviour
    {
        Idle,

        Random,

        Sleep
    }
	
	/** Dragon spit fire mode */
	private boolean spitFire;
	
	/** Dragon is sleeping now? */
	private boolean sleeping;
	
	/** Sleep countdown. */
	private int sleepTime;
	
	/** The behaviour. */
	private final Behaviour mode;
	
	
	/**
	 * Constructs and initializes a dragon.
	 * 
	 * @param x 
	 * 			X position
	 * @param y 
	 * 			Y position
	 * @param mode 
	 * 			Dragon behaviour
	 * @param spitFire 
	 * 			Dragon spit fire option
	 * 
	 */
	
	public Dragon(int x, int y, Behaviour mode, boolean spitFire) {
		super(x, y);
	
		this.sleeping = false;
		this.desc = 'D';
		this.mode = mode;
		this.spitFire = spitFire;
		
	}
	

	/**
	 * Show if dragon is sleeping
	 * 
	 * @return true Dragon is sleeping, false Dragon isn't sleeping. 
	 * 
	 */
	
	public boolean isSleeping() {
		return sleeping;
	}
	
	/**
	 * Show if dragon spit fire
	 * 
	 * @return true Dragon spits fire, false Dragon doesn't spit fire. 
	 * 
	 */
	
	public boolean spitsFire(){
		return spitFire;
	}
	
	/**
	 * Show if dragon can move
	 * 
	 * @return true Dragon can move, false Dragon can't move. 
	 * 
	 */
	
	public boolean canMove() {
		if (mode == Behaviour.Idle || sleeping)
			return false;
		else
			return true;
	}
	
	/**
	 * Show if dragon can sleep
	 * 
	 * @return true Dragon can sleep, false Dragon can't sleep. 
	 * 
	 */
	
	public boolean canSleep()
    {
        if (mode != Behaviour.Sleep)
            return false;
        else
        	return true;
    }
	
	/**
	 * Change dragon description
	 * 
	 * @param desc 
	 * 			Char with a dragon description mode
	 * 
	 */
	
	public void setDesc(char desc) {
		this.desc = desc;
	}
	
	/**
	 * Put dragon sleeping
	 * 
	 * @param time 
	 * 			Sleeping time
	 * 
	 */
	
	public void setSleeping(int time) {
		this.sleeping = true;
		this.sleepTime = time;
		this.desc = 'd';
	}

	
	/**
	 * Wake up dragon 
	 */
	
	public void wakeUp()
    {
        sleeping = false;
        sleepTime = 0;
        this.desc = 'D';
    }
	
	/**
	 * Update dragon sleep time  
	 */
	
	public void update()
    {
        sleepTime--;
        if (sleepTime == 0)
        	wakeUp();
    }
	
	/**
	 * Function used to put dragon sleeping 
	 */
	
	public void sleepingMachine()
    {
		int prob;
		Random r = new Random();
		prob = r.nextInt(60)+1;
		if (prob%10 == 0)
			setSleeping(prob/10);
    }
	
	/**
	 * Draws dragon to String.
	 * 
	 * @return String with a dragon
	 * 
	 */	
	
	public String toString(){
		return ""+desc;
	}

}
