package maze.logic;

import java.util.Random;

public class Builder {
	private static int size = 11;
	private boolean random;
	private Dragon.Behaviour dragonMode;
	private boolean spitFire;

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

	public void setDragonSpitFire(int optionS) {
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
			return new Board();
		else
			return new Board(size);
	}

	public Hero createHero(Board board) {
		if (!random)
			return new Hero(1, 1);
		else {
			return generateHero(board);
		}
	}

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

	public Sword createSword(Board board, Hero hero) {
		if (!random)
			return new Sword(1, 8);
		else {
			return generateSword(board, hero);
		}
	}

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

	public Exit createExit(Board board) {
		if (!random)
			return new Exit(9, 5);
		else {
			return generateExit(board);
		}
	}

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

	private boolean isAtBorder(int x, int y) {
		return (((x == 0 || x == size - 1) && y != 0 && y != size - 1) 
				|| ((y == 0 || y == size -1)
				&& x != 0 && x != size - 1));
	}

	public Dragon createDragon(Board board, Hero hero) {
		if (!random)
			return new Dragon(1, 3, dragonMode, false);
		else {
			return generateDragon(board, hero);
		}
	}

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
	
	public Dart createDart(Board board) {
		if (!random)
			return new Dart(1,2);
		else {
			return generateDart(board);
		}
	}

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
