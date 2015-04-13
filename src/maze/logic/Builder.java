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
	private final int size;
	
	/** Random board option */
	private boolean random;
	
	/** Dragon Behavior */
	private Dragon.Behavior dragonMode;
	
	/** Dragon spit fire mode */
	private boolean spitFire;
	
	/** Board Object */
	private Board board;
	
	/** Exit Object */
	private Exit exit;
	
	/** Hero Object */
	private Hero hero;
	
	/** Sword Object */
	private Sword sword;
	
	/** Shield Object */
	private Shield shield;
	
	/**
	 * Constructs and initializes a builder for classic mode.
	 * 
	 */
	public Builder() {
		this.spitFire = false;
		this.dragonMode = Dragon.Behavior.Idle;
		this.size = 10;
		this.random = false;
	}
	/**
	 * Constructs and initializes a builder for random mode.
	 * 
	 */
	public Builder(int optionD, int optionS, int size) {
		this.random = true;
		this.size = size;
		switch (optionD) {
		case 1:
			this.dragonMode = Dragon.Behavior.Idle;
			break;
		case 2:
			this.dragonMode = Dragon.Behavior.Random;
			break;
		case 3:
			this.dragonMode = Dragon.Behavior.Sleep;
			break;
		default:
			break;
		};
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
	 * @return true if the random mode was selected, false otherwise. 
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
			board = new Board();
			
		else
			board =  new Board(size);
		return board;
	}
	/**
	 * Function to create the hero.
	 * 
	 * 
	 * @return Hero in default position or Hero in a random position. 
	 * 			
	 */
	public Hero createHero() {
		if (!random)
			hero = new Hero(1, 1);
		else {
			hero = generateHero();
		}
		return hero;
	}

	/**
	 * Function to generate a random position to the hero.
	 * 
	 * 
	 * @return Hero in a random position. 
	 * 			
	 */
	private Hero generateHero() {
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
	 * 
	 * @return Sword in default position or Sword in a random position. 
	 * 			
	 */
	public Sword createSword() {
		if (!random)
			sword = new Sword(1, 8);
		else {
			sword = generateSword();
		}
		return sword;
	}

	/**
	 * Function to generate a random position to the sword.
	 * 
	 * 
	 * @return Sword in a random position. 
	 * 			
	 */
	private Sword generateSword() {
		Random r = new Random();
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
	 * 
	 * @return Shield in a random position. 
	 * 			
	 */
	public Shield createShield() {
		Random r = new Random();
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
	 * 
	 * @return Exit in default position or Sword in a random position. 
	 * 			
	 */
	public Exit createExit() {
		if (!random)
			exit = new Exit(9, 5);
		else {
			exit = generateExit();
		}
		return exit;
	}

	/**
	 * Function to generate a random position to the exit.
	 * 
	 * @return Exit in a random position. 
	 * 			
	 */
	private Exit generateExit() {
		Random r = new Random();
		int x = 0;
		int y = 0;
		do {
			x = r.nextInt(board.getSize());
			y = r.nextInt(board.getSize());
			exit = new Exit(x, y);
		} while (!exit.isAtBorder(board.getSize()) || !exit.accesible(board));
		return exit;
	}

	/**
	 * Function to create the dragon.
	 * 
	 * 
	 * @return Dragon in default position or Dragon in a random position. 
	 * 			
	 */
	public Dragon createDragon() {
		if (!random)
			return new Dragon(1, 3, dragonMode, false);
		else {
			return generateDragon();
		}
	}

	/**
	 * Function to generate a random position to the dragon.
	 * 
	 * @return Dragon in a random position. 
	 * 			
	 */
	private Dragon generateDragon() {
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
	 * 
	 * @return Dart in default position or Dart in a random position. 
	 * 			
	 */
	public Dart createDart() {
		if (!random)
			return new Dart(1,2);
		else {
			return generateDart();
		}
	}

	/**
	 * Function to generate a random position to the dart.
	 * 
	 * 
	 * @return Dart in a random position. 
	 * 			
	 */
	private Dart generateDart() {
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
	public boolean getSleep() {
		if(dragonMode == Dragon.Behavior.Sleep){
			return true;
		}
		else{
			return false;
		}
	}
	public boolean canMove() {
		if(dragonMode == Dragon.Behavior.Idle){
			return false;
		}
		else{
			return true;
		}
	}
}
