package maze.logic;
import maze.cli.*;

public class StateMachine {
	private Maze maze;
	private Interface io;
	
	public StateMachine(){
		io = new Interface();
		io.clearSrc();
		
		int gameMode = io.gameMode();
		if(gameMode == 1)
			maze = new Maze(gameMode, 1, 2, 1);
		else
			maze = new Maze(gameMode, io.dragonMode(), io.dragonSpitFire(), io.dragonSize());
		
		char cmd;
		do {
			io.clearSrc();
			io.printString(maze + "");
			cmd = io.readChar();
			maze.update(cmd);

		} while (!maze.isDone());	
		
		io.clearSrc();
		if (!maze.getHero().getDead()) {
			io.printWinningMessage();
		} else {
			io.printLoosingMessage();
		}
	}
}
