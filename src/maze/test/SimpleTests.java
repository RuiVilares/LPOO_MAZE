package maze.test;

import maze.logic.Maze;
import maze.logic.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class SimpleTests {

	@Test
	public void MoveHeroSuccessTest()
	{
		Maze maze = new Maze(1,0,0,0);
		Hero h = maze.getHero();
		assertEquals(1, h.getX());
		assertEquals(1, h.getY());
		
		maze.update('d');
		assertEquals(2, h.getX());
		assertEquals(1, h.getY());
	}
	
	@Test
	public void MoveHeroFailureTest()
	{
		Maze maze = new Maze(1,1,1,1);
		Hero h = maze.getHero();
		assertEquals(1, h.getX());
		assertEquals(1, h.getY());
		
		maze.update('w');
		assertEquals(1, h.getX());
		assertEquals(1, h.getY());
	}
	
	@Test
    public void EquipSwordTest()
    {
		Maze maze = new Maze(1,1,1,1);
		Hero h = maze.getHero();
		
        assertFalse(h.getArmed());

        char[] movements = {
                'd', 'd', 'd',
                's', 's', 's', 's',
                'a', 'a', 'a',
                's', 's', 's'
        };

        for (char cmd : movements)
        {
        	maze.update(cmd);
        }

        assertTrue(h.getArmed());
    }
	
	@Test
    public void DragonKillHero()
    {
		Maze maze = new Maze(1,1,1,1);
		Hero h = maze.getHero();
		assertFalse(h.getDead());
		maze.update('s');
        assertTrue(h.getDead());
    }
	
	@Test
    public void HeroKillDragonTest()
    {
		Maze maze = new Maze(1,1,1,1);
		Hero h = maze.getHero();
		h.setArmed();
		maze.update('s');
        assertTrue(maze.getDragons().get(0).getDead());
    }
	@Test
    public void EquipSwordKillDragonAndExitTest()
    {
		Maze maze = new Maze(1,1,1,1);
		Hero h = maze.getHero();
        char[] movements = {
        		'd', 'd', 'd',
                's', 's', 's', 's',
                'a', 'a', 'a',
                's', 's', 's',
                'w', 'w', 'w', 'w', // dragon killed
                's',
                'd', 'd', 'd', 'd', 'd',
                's', 's', 's',
                'd', 'd',
                'w', 'w', 'w',
                'd' // exited
        };

        for (char cmd : movements)
        {
            maze.update(cmd);
        }
        assertTrue(!h.getDead());
        assertTrue(maze.isDone());
    }
	
    @Test
    public void MoveToExitWithoutDragonKilledOrSwordTest()
    {
    	Maze maze = new Maze(1,1,1,1);
		Hero h = maze.getHero();
        char[] movements = {
                'd', 'd', 'd', 'd', 'd', 'd', 'd',
                's', 's', 's', 's',
                'd'
        };

        for (char cmd : movements)
        {
            maze.update(cmd);
        }

        assertTrue(!h.getDead());
        assertFalse(maze.isDone());
    }
    
    @Test
    public void PrintTest()
    {
    	Maze maze = new Maze(1,1,1,1);
    	String compare = ""+maze;
    	String expected = "Armed: false  Shield: true  Number of Darts: 0\n"+
    			"Dragons: 1/1  SpitFire: false  CanSleep: false  CanMove: false  Sleeping: false\n"+
    			"X X X X X X X X X X \n"+
    			"X Q               X \n"+
    			"X   X X   X   X   X \n"+
    			"X D X X   X   X   X \n"+
    			"X   X X   X   X   X \n"+
    			"X             X   S \n"+
    			"X   X X   X   X   X \n"+
    			"X   X X   X   X   X \n"+
    			"X E X X           X \n"+
    			"X X X X X X X X X X \n";
    	assertEquals(expected, compare);
    }
    
    @Test
    public void TesteRandomSleepWith3Dragons() 
    {
    	Maze maze = new Maze(2,3,1,3);
    	assertEquals(3, maze.getDragons().size());
    	assertTrue(maze.getDragons().get(0).getSpitFire());
    	assertTrue(maze.getDragons().get(0).canSleep());
    	assertTrue(maze.getDragons().get(0).canMove());
    	maze.update('a');
    }
	
}
