package maze.logic;

import java.util.Random;

public class Dragon extends Piece {

	public enum Behaviour
    {
        Idle,

        Random,

        Sleep
    }
	
	private boolean dead;
	private boolean sleeping;
	private int sleepTime;
	private final Behaviour mode;
	
	

	public Dragon(int x, int y, Behaviour mode) {
		super(x, y);
		
		this.dead = false;
		this.desc = 'D';
		this.mode = mode;
		
	}
	

	public boolean getDead() {
		return dead;
	}
	
	public boolean getSleeping() {
		return sleeping;
	}
	
	public boolean canMove() {
		{
			System.out.println("sleep: "+sleepTime+"\n");
	        if (mode == Behaviour.Idle)
	            return false;
	        if (sleeping)
	            return false;
	        return true;
	    }
	}
	
	public boolean CanSleep()
    {
        if (mode != Behaviour.Sleep)
            return false;
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
		System.out.println("prob: "+prob+"\n");
		if (prob%10 == 0)
			setSleeping(prob/10);
    }
	
	public String toString(){
		return ""+desc;
	}

}
