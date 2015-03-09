package maze.logic;

import java.util.Random;

import maze.cli.*;

public class MazeGenerator extends Maze{
	private static final int SIZE = 11;
	public MazeGenerator()
	{
		super();
	}
	public MazeGenerator(Dragon.Behaviour dragonMode) {
		done = false;
		board = new Board(SIZE);
		io = new Interface();
		generateHero();
		generateDragon(dragonMode);
		generateExit();
		generateSword();
		char cmd;
		do{
			io.printString(this.toString());
            cmd = io.readChar();
            update(cmd);

		}while (!done);
		if(!hero.getDead()){
			io.printWinningMessage();
		}
		else{
			io.printLoosingMessage();
		}
	}

	private void generateHero() {
		Random r = new Random();
		int x = 0;
		int y = 0;
		do{
			x = r.nextInt(board.getSize());
			y = r.nextInt(board.getSize());
		}while(board.getCell(x, y) == 'X');
		hero = new Hero(x,y);
		
	}
	
	private void generateDragon(Dragon.Behaviour dragonMode) {
		Random r = new Random();
		int x = 0;
		int y = 0;
		do{
			x = r.nextInt(board.getSize());
			y = r.nextInt(board.getSize());
			dragon = new Dragon(x,y,dragonMode);
		}while(board.getCell(x, y) == 'X' || dragon.equals(hero));
	}
	
	private void generateSword() {
		Random r = new Random();
		int x = 0;
		int y = 0;
		do{
			x = r.nextInt(board.getSize());
			y = r.nextInt(board.getSize());
			sword = new Sword(x,y);
		}while(board.getCell(x, y) == 'X' || sword.equals(hero));
	}
	
	private void generateExit() {
		Random r = new Random();
		int x = 0;
		int y = 0;
		do{
			x = r.nextInt(board.getSize());
			y = r.nextInt(board.getSize());
			exit = new Exit(x,y);
		}while(!(((x == 0 || x == board.getSize()-1)&& y != 0 && y != board.getSize()-1) || 
				((y == 0 || y == board.getSize()-1) && x != 0 && x != board.getSize()-1)));
	}
}
