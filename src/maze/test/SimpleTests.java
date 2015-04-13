package maze.test;

import maze.logic.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class SimpleTests {

	@Test
	public void MoveHeroSuccessTest()
	{
		Maze maze = new Maze();
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
		Maze maze = new Maze();
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
		Maze maze = new Maze();
		Hero h = maze.getHero();
		
        assertFalse(h.isArmed());

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

        assertTrue(h.isArmed());
    }
	
	@Test
    public void DragonKillHero()
    {
		Maze maze = new Maze();
		Hero h = maze.getHero();
		assertFalse(h.isDead());
		maze.update('s');
        assertTrue(h.isDead());
    }
	
	@Test
    public void HeroKillDragonTest()
    {
		Maze maze = new Maze();
		Hero h = maze.getHero();
		h.setArmed();
		maze.update('s');
        assertTrue(maze.getDragons().size() == 0);
    }
	@Test
    public void EquipSwordKillDragonAndExitTest()
    {
		Maze maze = new Maze();
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
        assertTrue(!h.isDead());
        assertTrue(maze.isDone());
    }
	
    @Test
    public void MoveToExitWithoutDragonKilledOrSwordTest()
    {
    	Maze maze = new Maze();
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

        assertTrue(!h.isDead());
        assertFalse(maze.isDone());
    }
    
    @Test
    public void PrintTest()
    {
    	Maze maze = new Maze();
    	String compare = ""+maze;
    	String expected =
    			"XXXXXXXXXX\n"+
    			"XH       X\n"+
    			"X XX X X X\n"+
    			"XDXX X X X\n"+
    			"X XX X X X\n"+
    			"X      X S\n"+
    			"X XX X X X\n"+
    			"X XX X X X\n"+
    			"XEXX     X\n"+
    			"XXXXXXXXXX\n";
    	assertEquals(expected, compare);
    }
    
    @Test
    public void TesteRandomSleepWith3Dragons() 
    {
    	Maze maze = new Maze(11,3,1,3,0);
    	assertEquals(3, maze.getDragons().size());
    	assertTrue(maze.getDragons().get(0).spitsFire());
    	assertTrue(maze.getDragons().get(0).canSleep());
    	assertTrue(maze.getDragons().get(0).canMove());
    	maze.update('a');
    }
	
}
