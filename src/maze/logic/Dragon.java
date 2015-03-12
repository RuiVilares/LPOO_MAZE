package maze.logic;

import java.util.Random;

public class Dragon extends Piece {

	public enum Behaviour
    {
        Idle,

        Random,

        Sleep
    }
	private boolean spitFire;
	private boolean dead;
	private boolean sleeping;
	private int sleepTime;
	private final Behaviour mode;
	
	

	public Dragon(int x, int y, Behaviour mode, boolean spitFire) {
		super(x, y);
		
		this.dead = false;
		this.sleeping = false;
		this.desc = 'D';
		this.mode = mode;
		this.spitFire = spitFire;
		
	}
	

	public boolean getDead() {
		return dead;
	}
	
	public boolean getSleeping() {
		return sleeping;
	}
	
	public boolean getSpitFire(){
		return spitFire;
	}
	
	public boolean canMove() {
		if (mode == Behaviour.Idle || sleeping)
			return false;
		else
			return true;
	}
	
	public boolean canSleep()
    {
        if (mode != Behaviour.Sleep)
            return false;
        else
        	return true;
    }

	public void setDead() {
		this.dead = true;
	}
	
	public void setDesc(char desc) {
		this.desc = desc;
	}
	
	public void setSleeping(int time) {
		this.sleeping = true;
		this.sleepTime = time;
		this.desc = 'd';
	}

	
	public void wakeUp()
    {
        sleeping = false;
        sleepTime = 0;
        this.desc = 'D';
    }
	
	public void update()
    {
        sleepTime--;
        if (sleepTime == 0)
        	wakeUp();
    }
	
	public void sleepingMachine()
    {
		int prob;
		Random r = new Random();
		prob = r.nextInt(60)+1;
		if (prob%10 == 0)
			setSleeping(prob/10);
    }
	
	public String toString(){
		return ""+desc;
	}

}
