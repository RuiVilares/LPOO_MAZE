package maze.logic;

import java.util.Random;

public class Builder {
	private final int size;
	private boolean random;
	private Dragon.Behaviour dragonMode;
	private boolean spitFire;
	private Board board;
	private Exit exit;
	private Hero hero;
	private Sword sword;
	private Shield shield;
	
	public Builder() {
		this.spitFire = false;
		this.dragonMode = Dragon.Behaviour.Idle;
		this.size = 10;
		this.random = false;
	}
	public Builder(int optionD, int optionS, int size) {
		this.random = true;
		this.size = size;
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
		
		if (optionS == 1)
			this.spitFire = true;
		else
			this.spitFire = false;
	}
	
	public boolean getDragonSpitFire() {
			return this.spitFire;
	}
	
	public boolean getRandom(){
		return this.random;
	};

	public Board createBoard() {
		if (!random)
			board = new Board();
			
		else
			board =  new Board(size);
		return board;
	}

	public Hero createHero() {
		if (!random)
			hero = new Hero(1, 1);
		else {
			hero = generateHero();
		}
		return hero;
	}

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

	public Sword createSword() {
		if (!random)
			sword = new Sword(1, 8);
		else {
			sword = generateSword();
		}
		return sword;
	}

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

	public Exit createExit() {
		if (!random)
			exit = new Exit(9, 5);
		else {
			exit = generateExit();
		}
		return exit;
	}

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

	public Dragon createDragon() {
		if (!random)
			return new Dragon(1, 3, dragonMode, false);
		else {
			return generateDragon();
		}
	}

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
	
	public Dart createDart() {
		if (!random)
			return new Dart(1,2);
		else {
			return generateDart();
		}
	}

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
		if(dragonMode == Dragon.Behaviour.Sleep){
			return true;
		}
		else{
			return false;
		}
	}
	public boolean canMove() {
		if(dragonMode == Dragon.Behaviour.Idle){
			return false;
		}
		else{
			return true;
		}
	}
}
