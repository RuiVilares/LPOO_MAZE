package maze.logic;

import java.util.Random;

/**
 * 
 * Builder class to launch game with our settings.
 * 
 * @author Diogo Trindade
 * @author Rui Vilares
 * 
 */

public class Builder {
	/** Default size of a random board */
	private static int size = 11;
	
	/** Random board option */
	private boolean random;
	
	/** Dragon Behaviour */
	private Dragon.Behaviour dragonMode;
	
	/** Dragon spit fire mode */
	private boolean spitFire;

	/**
	 * Constructs and initializes a builder.
	 * 
	 * @param random
	 * 			This option indicate if the board is randomly generated or not
	 */
	
	public Builder(int random) {
		this.spitFire = false;
		this.dragonMode = Dragon.Behaviour.Idle;
		if (random == 1){
			size = 10;
			this.random = false;
			}
		else
			this.random = true;
	}
	
	/**
	 * Set dragon mode.
	 * 
	 * @param optionD
	 * 			This option indicate if the dragon behaviour is Idle, random or sleep 
	 */
	
	public void setDragonMode(int optionD) {
		switch (optionD) {
		case 1:
			this.dragonMode = Dragon.Behaviour.Idle;
			break;
		case 2:
			this.dragonMode = Dragon.Behaviour.Random;
			break;
		case 3:
			this.dragonMode = Dragon.Behaviour.Sleep;
			break;
		default:
			break;
		};
	}
	
	/**
	 * Set dragon spit fire.
	 * 
	 * @param optionS
	 * 			This option indicate if the dragon spit fire or not. 
	 */
	
	public void setDragonSpitFire(int optionS) {
		if (optionS == 1)
			this.spitFire = true;
		else
			this.spitFire = false;
	}
	
	/**
	 * Get dragon spit fire.
	 * 
	 * @return true if the dragon spit fire, false otherwise. 
	 * 			
	 */
	
	public boolean getDragonSpitFire() {
			return this.spitFire;
	}
	
	/**
	 * Get random mode.
	 * 
	 * @return true if the random board was selected, false otherwise. 
	 * 			
	 */
	
	public boolean getRandom(){
		return this.random;
	};

	/**
	 * Function to create the board.
	 * 
	 * @return Board with user settings. 
	 * 			
	 */
	
	public Board createBoard() {
		if (!random)
			return new Board();
		else
			return new Board(size);
	}

	/**
	 * Function to create the hero.
	 * 
	 * @param board 
	 * 			Actual game board
	 * 
	 * @return Hero in default position or Hero in a randomly position. 
	 * 			
	 */
	
	public Hero createHero(Board board) {
		if (!random)
			return new Hero(1, 1);
		else {
			return generateHero(board);
		}
	}

	/**
	 * Function to generate a randomly position to the hero.
	 * 
	 * @param board 
	 * 			Actual game board
	 * 
	 * @return Hero in a randomly position. 
	 * 			
	 */
	
	private Hero generateHero(Board board) {
		Random r = new Random();
		int x = 0;
		int y = 0;
		do {
			x = r.nextInt(board.getSize());
			y = r.nextInt(board.getSize());
		} while (board.getCell(x, y) == 'X');
		return new Hero(x, y);

	}

	/**
	 * Function to create the sword.
	 * 
	 * @param board 
	 * 			Actual game board
	 * @param hero 
	 * 			Actual game hero
	 * 
	 * @return Sword in default position or Sword in a randomly position. 
	 * 			
	 */
	
	public Sword createSword(Board board, Hero hero) {
		if (!random)
			return new Sword(1, 8);
		else {
			return generateSword(board, hero);
		}
	}

	/**
	 * Function to generate a randomly position to the sword.
	 * 
	 * @param board 
	 * 			Actual game board
	 * @param hero 
	 * 			Actual game hero
	 * 
	 * @return Sword in a randomly position. 
	 * 			
	 */
	
	private Sword generateSword(Board board, Hero hero) {
		Random r = new Random();
		Sword sword;
		int x = 0;
		int y = 0;
		do {
			x = r.nextInt(board.getSize());
			y = r.nextInt(board.getSize());
			sword = new Sword(x, y);
		} while (board.getCell(x, y) == 'X' || sword.equals(hero));
		return sword;
	}
	
	
	/**
	 * Function to create the shield.
	 * 
	 * @param board 
	 * 			Actual game board
	 * @param hero 
	 * 			Actual game hero
	 * 
	 * @return Shield in a randomly position. 
	 * 			
	 */
	
	public Shield createShield(Board board, Hero hero) {
		Random r = new Random();
		Shield shield;
		int x = 0;
		int y = 0;
		do {
			x = r.nextInt(board.getSize());
			y = r.nextInt(board.getSize());
			shield = new Shield(x, y);
		} while (board.getCell(x, y) == 'X' || shield.equals(hero));
		return shield;
	}
	
	/**
	 * Function to create the exit.
	 * 
	 * @param board 
	 * 			Actual game board
	 * 
	 * @return Exit in default position or Sword in a randomly position. 
	 * 			
	 */
	
	public Exit createExit(Board board) {
		if (!random)
			return new Exit(9, 5);
		else {
			return generateExit(board);
		}
	}

	/**
	 * Function to generate a randomly position to the exit.
	 * 
	 * @param board 
	 * 			Actual game board
	 * 
	 * @return Exit in a randomly position. 
	 * 			
	 */
	
	private Exit generateExit(Board board) {
		Random r = new Random();
		int x = 0;
		int y = 0;
		do {
			x = r.nextInt(board.getSize());
			y = r.nextInt(board.getSize());
		} while (!isAtBorder(x, y) || !accesible(board,x,y));
		return new Exit(x, y);
	}

	/**
	 * Function to verify board walls around position.
	 * 
	 * @param board 
	 * 			Actual game board
	 * @param x 
	 * 			X position
	 * @param y 
	 * 			Y position
	 * 
	 * @return false if the position has a wall around, true otherwise. 
	 * 			
	 */
	
	private boolean accesible(Board board, int x, int y) {
		if(x == 0 && board.getCell(x+1,y) == 'X'){
			return false;
		}
		else if(x == size - 1 && board.getCell(x-1,y) == 'X'){
			return false;
		}
		if(y == 0 && board.getCell(x,y+1) == 'X'){
			return false;
		}
		else if(y == size - 1 && board.getCell(x,y-1) == 'X'){
			return false;
		}
		else
			return true;
	}
	
	
	/**
	 * Function to verify that exit isn't in corners.
	 * 
	 * @param x 
	 * 			X position
	 * @param y 
	 * 			Y position
	 * 
	 * @return true if the position has in a corner, false otherwise. 
	 * 			
	 */
	private boolean isAtBorder(int x, int y) {
		return (((x == 0 || x == size - 1) && y != 0 && y != size - 1) 
				|| ((y == 0 || y == size -1)
				&& x != 0 && x != size - 1));
	}

	/**
	 * Function to create the dragon.
	 * 
	 * @param board 
	 * 			Actual game board
	 * @param hero 
	 * 			Actual game hero
	 * 
	 * @return Dragon in default position or Dragon in a randomly position. 
	 * 			
	 */
	
	public Dragon createDragon(Board board, Hero hero) {
		if (!random)
			return new Dragon(1, 3, dragonMode, false);
		else {
			return generateDragon(board, hero);
		}
	}

	/**
	 * Function to generate a randomly position to the dragon.
	 * 
	 * @param board 
	 * 			Actual game board
	 * @param hero 
	 * 			Actual game hero
	 * 
	 * @return Dragon in a randomly position. 
	 * 			
	 */
	
	private Dragon generateDragon(Board board, Hero hero) {
		Random r = new Random();
		int x = 0;
		int y = 0;
		Dragon dragon;
		do {
			x = r.nextInt(board.getSize());
			y = r.nextInt(board.getSize());
			dragon = new Dragon(x, y, dragonMode, spitFire);
		} while (board.getCell(x, y) == 'X' || dragon.equals(hero));
		return dragon;
	}
	
	
	/**
	 * Function to create the dart.
	 * 
	 * @param board 
	 * 			Actual game board 
	 * 
	 * @return Dart in default position or Dart in a randomly position. 
	 * 			
	 */
	
	
	public Dart createDart(Board board) {
		if (!random)
			return new Dart(1,2);
		else {
			return generateDart(board);
		}
	}

	/**
	 * Function to generate a randomly position to the dart.
	 * 
	 * @param board 
	 * 			Actual game board
	 * 
	 * @return Dart in a randomly position. 
	 * 			
	 */
	
	private Dart generateDart(Board board) {
		Random r = new Random();
		int x = 0;
		int y = 0;
		Dart dart;
		do {
			x = r.nextInt(board.getSize());
			y = r.nextInt(board.getSize());
			dart = new Dart(x, y);
		} while (board.getCell(x, y) == 'X');
		return dart;
	}
}
