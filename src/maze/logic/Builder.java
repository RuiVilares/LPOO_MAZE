package maze.logic;

import java.util.Random;

public class Builder {
	private static final int size = 11;
	private boolean random;
	private Dragon.Behaviour dragonMode;
	
	public Builder(int random, int dragonMode){
		if(random == 1)
			this.random = false;
		else
			this.random = true;
		switch(dragonMode){
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
	public Board createBoard(){
		if(!random)
			return new Board();
		else
			return new Board(size);
	}
	
	public Hero createHero(Board board){
		if(!random)
			return new Hero(1, 1);
		else{
			return generateHero(board);
		}
	}
	private Hero generateHero(Board board) {
		Random r = new Random();
		int x = 0;
		int y = 0;
		do{
			x = r.nextInt(board.getSize());
			y = r.nextInt(board.getSize());
		}while(board.getCell(x, y) == 'X');
		return new Hero(x,y);
		
	}
	
	public Sword createSword(Board board, Hero hero){
		if(!random)
			return new Sword(1, 8);
		else{
			return generateSword(board, hero);
		}
	}
	private Sword generateSword(Board board, Hero hero) {
		Random r = new Random();
		Sword sword;
		int x = 0;
		int y = 0;
		do{
			x = r.nextInt(board.getSize());
			y = r.nextInt(board.getSize());
			sword = new Sword(x,y);
		}while(board.getCell(x, y) == 'X' || sword.equals(hero));
		return sword;
	}
	public Exit createExit(Board board){
		if(!random)
			return new Exit(9, 5);
		else{
			return generateExit(board);
		}
	}
	private Exit generateExit(Board board) {
		Random r = new Random();
		int x = 0;
		int y = 0;
		do{
			x = r.nextInt(board.getSize());
			y = r.nextInt(board.getSize());
		}while(!(((x == 0 || x == board.getSize()-1)&& y != 0 && y != board.getSize()-1) || 
				((y == 0 || y == board.getSize()-1) && x != 0 && x != board.getSize()-1)));
		return new Exit(x,y);
	}
	public Dragon createDragon(Board board, Hero hero){
		if(!random)
			return new Dragon(1, 3, dragonMode);
		else{
			return generateDragon(board,hero);
		}
	}
	private Dragon generateDragon(Board board,Hero hero) {
		Random r = new Random();
		int x = 0;
		int y = 0;
		Dragon dragon;
		do{
			x = r.nextInt(board.getSize());
			y = r.nextInt(board.getSize());
			dragon = new Dragon(x,y,dragonMode);
		}while(board.getCell(x, y) == 'X' || dragon.equals(hero));
		return dragon;
	}
}
