package maze.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * 
 * Class that defines the main maze logic.
 * 
 * @author Diogo Trindade
 * @author Rui Vilares
 * 
 */

@SuppressWarnings("serial")
public class Maze implements Serializable{
	/** Represents maze termination */
	private boolean done;
	
	/** Builder used to create a game with our settings */
	private transient Builder builder;
	
	/** Board created by the builder */
	private Board board;
	
	/** List of dragons in the board */
	private ArrayList<Dragon> dragons;
	
	/** List of darts in the board */
	private ArrayList<Dart> darts;
	
	/** Represents a exit piece */
	private Exit exit;
	
	/** Represents a exit piece */
	private Hero hero;
	
	/** Represents a sword piece */
	private Sword sword;
	
	/** Represents a shield piece */
	private Shield shield;
	
	/**
	 * Constructs and initializes a null maze.
	 */
	
	public Maze(){
		done = false;
		
		dragons = new ArrayList<Dragon>();
		darts = new ArrayList<Dart>();
		
		builder = new Builder(1);
		builder.setDragonMode(1);
		builder.setDragonSpitFire(2);
		
		
		board = builder.createBoard();
		hero = builder.createHero(board);
		exit = builder.createExit(board);
		sword = builder.createSword(board, hero);
		dragons.add(builder.createDragon(board, hero));
		shield = null;
		hero.noProtectionNeeded();
	}
	
	/**
	 * Constructs and initializes maze with a specific board.
	 * 
	 * @param dragonMode
	 * 			Dragon mode (idle, move, sleep)
	 * @param dragonSpitFire
	 * 			Spit Fire mode active or not
	 * @param array
	 * 			specific maze
	 */
	
	public Maze(int dragonMode, int dragonSpitFire, char [][] array){
		done = false;
		shield = null;
		exit = new Exit(-1,-1);
		hero = new Hero (-1,-1);
		sword = new Sword(-1,-1);
		char[][] maze = array;
		boolean spitsFire = true;
		Dragon.Behaviour behavior = Dragon.Behaviour.Idle;
		
		if(dragonSpitFire == 2){
			spitsFire = false;
		}
		
		if(dragonMode == 2){
			behavior = Dragon.Behaviour.Random;
		}
		else if(dragonMode == 3){
			behavior = Dragon.Behaviour.Sleep;
		}
		
		dragons = new ArrayList<Dragon>();
		darts = new ArrayList<Dart>();
		for(int i = 0; i < array.length; i++){
			for(int j = 0; j < array.length; j++){
				switch(maze[i][j]){
				case 'H':
					hero = new Hero(j,i);
					maze[i][j] = ' ';
					break;
				case 'E':
					sword = new Sword(j,i);
					maze[i][j] = ' ';
					break;
				case 'S':
					exit = new Exit(j,i);
					maze[i][j] = ' ';
					break;
				case 'O':
					shield = new Shield(j,i);
					maze[i][j] = ' ';
					break;
				case 'D':
					dragons.add(new Dragon(i,j,behavior,spitsFire));
					maze[i][j] = ' ';
					break;
				case '/':
					darts.add(new Dart(i,j));
					maze[i][j] = ' ';
					break;
				default:
				}
			}
		}
		board = new Board(maze);
	}

	/**
	 * Constructs and initializes maze with several dragons.
	 * 
	 * @param dragonMode
	 * 			Dragon mode (idle, move, sleep)
	 * @param dragonSpitFire
	 * 			Spit Fire mode active or not
	 * @param nDragons
	 * 			number of dragons that we pretend
	 */
	
	public Maze(int dragonMode, int dragonSpitFire, int nDragons) {
		done = false;
		
		dragons = new ArrayList<Dragon>();
		darts = new ArrayList<Dart>();
		builder = new Builder(2);
		
		builder.setDragonMode(dragonMode);
		builder.setDragonSpitFire(dragonSpitFire);
		
		board = builder.createBoard();
		hero = builder.createHero(board);
		exit = builder.createExit(board);
		sword = builder.createSword(board, hero);
		shield = builder.createShield(board, hero);
		
		if(!builder.getDragonSpitFire()){
			hero.noProtectionNeeded();
		}
		do {
			dragons.add(builder.createDragon(board, hero));
			darts.add(builder.createDart(board));
			nDragons--;
		} while (nDragons > 0);
	}

	/**
	 * Draws complete maze.
	 * 
	 * @return String with every maze.
	 * 
	 */	
	
	public String toString() {
		String maze = "";
		int dragonIndex;
		int dartIndex;
		for (int i = 0; i < board.getSize(); i++) {
			for (int j = 0; j < board.getSize(); j++) {
				dragonIndex = checkForAliveDragonCell(j, i);
				dartIndex = checkForDarts(j, i);
				if (i == hero.getY() && j == hero.getX()) {
					maze += hero + "";
				} else if (i == exit.getY() && j == exit.getX()) {
					maze += exit + "";
				} else if (dragonIndex != -1) {
					if (dragons.get(dragonIndex).equals(sword)
							&& !hero.isArmed()) {
						if (!dragons.get(dragonIndex).isSleeping())
							maze += "F";
						else
							maze += "f";
					} else if (dragons.get(dragonIndex).equals(shield)
							&& !hero.isProtection()) {
						if (!dragons.get(dragonIndex).isSleeping())
							maze += "M";
						else
							maze += "m";
					} else
						maze += dragons.get(dragonIndex) + "";
					//
				} else if (dartIndex != -1) {
					if (!darts.get(dartIndex).getTaked())
						maze += darts.get(dartIndex) + "";
					else
						maze += " ";
					//
				} else if (i == sword.getY() && j == sword.getX()
						&& !hero.isArmed() && !swordEqualsAnyDragon()) {
					maze += sword + "";
				} else if (shield != null && i == shield.getY() && j == shield.getX()
						&& !hero.isProtection() && !shieldEqualsAnyDragon()) {
					maze += shield + "";
				} else {
					maze += board.getCell(j, i) + "";
				}
			}
			maze += "\n";
		}
		return maze;
	}

	/**
	 * Draws game state to String.
	 * 
	 * @return String with actual game state
	 * 
	 */	
	
	public String stateToString() {
		String def = "Armed: " + hero.isArmed() + "  Shield: "
				+ hero.isProtection() + "  Number of Darts: " + hero.getDarts()
				+ "\n" + "Dragons: " + dragons.size() + "/" + dragons.size()
				+ "  SpitFire: " + dragons.get(0).spitsFire()
				+ "  CanSleep: " + dragons.get(0).canSleep() + "  CanMove: "
				+ dragons.get(0).canMove() + "  Sleeping: "
				+ dragons.get(0).isSleeping() + "\n";
		return def;
	}

	/**
	 * Update game.
	 * 
	 * @param cmd
	 * 			command received thought the user  
	 * 
	 */		
	
	public void update(char cmd) {
		updateHero(cmd);
		throwDarts(cmd);
		updateDragon();
		checkDarts();
		checkArmedStatus();
		checkProtectionStatus();
		checkDragons();
		checkIfDone();
	}

	/**
	 * Check for a live dragon in a determinate cell
	 * 
	 * @param x
	 * 			X position
	 * @param y
	 * 			Y position
	 * 
	 * @return index of dragon in the cell or -1 in case of it doesn't exist
	 * 
	 */	
	
	private int checkForAliveDragonCell(int x, int y) {
		for (int i = 0; i < dragons.size(); i++) {
			if (dragons.get(i).getX() == x && dragons.get(i).getY() == y)
				return i;
		}
		return -1;
	}

	/**
	 * Check for a dart in a determinate cell
	 * 
	 * @param x
	 * 			X position
	 * @param y
	 * 			Y position
	 * 
	 * @return index of dart in the cell or -1 in case of it doesn't exist
	 * 
	 */	
	
	private int checkForDarts(int x, int y) {
		for (int i = 0; i < darts.size(); i++) {
			if (darts.get(i).getX() == x && darts.get(i).getY() == y)
				return i;
		}
		return -1;
	}

	/**
	 * Check dragons and sword in the same position
	 * 
	 * @return true in case of overlapping, false otherwise
	 * 
	 */	
	
	private boolean swordEqualsAnyDragon() {
		for (int i = 0; i < dragons.size(); i++) {
			if (sword.equals(dragons.get(i)))
				return true;
		}
		return false;
	}

	/**
	 * Check dragons and shield in the same position
	 * 
	 * @return true in case of overlapping, false otherwise
	 * 
	 */	
	
	private boolean shieldEqualsAnyDragon() {
		for (int i = 0; i < dragons.size(); i++) {
			if (shield.equals(dragons.get(i)))
				return true;
		}
		return false;
	}

	/**
	 * Function to kill dragons in determinate cell
	 * 
	 * @param x
	 * 			X position
	 * @param y
	 * 			Y position
	 * 
	 * @return true if any dragon was killed, false otherwise
	 * 
	 */	
	
	private boolean killDragonsInCell(int x, int y) {
		for (int i = 0; i < dragons.size(); i++) {
			if (dragons.get(i).getX() == x && dragons.get(i).getY() == y) {
				dragons.remove(i);
				return true;
			}
		}
		return false;
	}

	/**
	 * Auxiliary function to check hero and dragon collision
	 * 
	 * @param diff
	 * 			difference with hero and dragon collision
	 * @param index
	 * 			dragon index in dragons list
	 * 
	 * @return true if the dragon kill the hero, false otherwise
	 * 
	 */	

	private boolean checkDragonColision(int dif, int index) {
		int i;
		if (hero.getX() - dragons.get(index).getX() <= dif
				&& Math.abs(hero.getY() - dragons.get(index).getY()) == 0
				&& hero.getX() - dragons.get(index).getX() >= 0) {
			i = dragons.get(index).getX();
			do {
				if (board.getCell(i, hero.getY()) == 'X') {
					return false;
				}
				i++;
			} while (i < hero.getX());
			return true;
		}
		if (dragons.get(index).getX() - hero.getX() <= dif
				&& Math.abs(hero.getY() - dragons.get(index).getY()) == 0
				&& dragons.get(index).getX() - hero.getX() >= 0) {
			i = hero.getX();
			do {
				if (board.getCell(i, hero.getY()) == 'X') {
					return false;
				}
				i++;
			} while (i < dragons.get(index).getX());
			return true;
		}
		if (Math.abs(hero.getX() - dragons.get(index).getX()) == 0
				&& hero.getY() - dragons.get(index).getY() <= dif
				&& hero.getY() - dragons.get(index).getY() >= 0) {
			i = dragons.get(index).getY();
			do {
				if (board.getCell(hero.getX(), i) == 'X') {
					return false;
				}
				i++;
			} while (i < hero.getY());
			return true;
		}
		if (Math.abs(hero.getX() - dragons.get(index).getX()) == 0
				&& dragons.get(index).getY() - hero.getY() <= dif
				&& dragons.get(index).getY() - hero.getY() >= 0) {
			i = hero.getY();
			do {
				if (board.getCell(hero.getX(), i) == 'X') {
					return false;
				}
				i++;
			} while (i < dragons.get(index).getY());
			return true;
		}
		return false;
	}

	/**
	 * Function to update hero and dragon state
	 */	
	
	private void checkDragons() {
		for (int i = 0; i < dragons.size(); i++) {
			int dif;
			if (dragons.get(i).spitsFire())
				dif = 3;
			else
				dif = 1;
			if (!dragons.get(i).isSleeping() && !hero.isProtection()) {
				if (checkDragonColision(dif, i))
					hero.setDead();
			} else if (checkDragonColision(1, i)) {
					if (hero.isArmed())
						dragons.remove(i);
					else
						hero.setDead();
			}
		}
	}

	/**
	 * Function to check the end of the game
	 */	
	
	private void checkIfDone() {
		if (hero.isDead() || (hero.equals(exit) && dragons.size() == 0)) {
			done = true;
		}
	}
	
	/**
	 * Function to analyze hero and sword position. Turns hero armed
	 */	
	
	private void checkArmedStatus() {
		if (!hero.isArmed() && hero.equals(sword))
			hero.setArmed();
	}

	/**
	 * Function to analyze hero and shield position. Turns hero protected
	 */
	
	private void checkProtectionStatus() {
		if (!hero.isProtection() && hero.equals(shield))
			hero.setProtection();
	}

	/**
	 * Function to analyze hero and darts position. Hero catch the dart
	 */
	
	private void checkDarts() {
		for (int i = 0; i < darts.size(); i++) {
			if (darts.get(i).equals(hero) && !darts.get(i).getTaked()) {
				darts.get(i).setTaked();
				hero.incDarts();
			}
		}
	}

	/**
	 * Function to update every dragons.
	 */
	
	private void updateDragon() {
		for (int i = 0; i < dragons.size(); i++) {
			if (dragons.get(i).canMove()) {
				updateDragonPos(i);
			}
			if (dragons.get(i).canSleep())
				if (dragons.get(i).isSleeping())
					dragons.get(i).update();

				else
					dragons.get(i).sleepingMachine();
		}
	}
	
	/**
	 * Function to update a dragon position.
	 * 
	 * @param i
	 * 			index of dragons list  
	 * 
	 */
	
	private void updateDragonPos(int i) {
		int x = 0;
		int y = 0;
		Random r = new Random();
		int dir;
		do {
			dir = r.nextInt(4);
			switch (dir) {
			case 0:
				y = -1;
				break;
			case 1:
				x = 1;
				break;
			case 2:
				y = 1;
				break;
			case 3:
				x = -1;
				break;
			}
			if (board.getCell(dragons.get(i).getX() + x, dragons.get(i).getY()
					+ y) == ' ') {
				dragons.get(i).setX(dragons.get(i).getX() + x);
				dragons.get(i).setY(dragons.get(i).getY() + y);
				break;
			}
			x = 0;
			y = 0;
		} while (true);
	}

	/**
	 * Function to update hero position and status.
	 * 
	 * @param cmd
	 * 			char with a direction received throw the user
	 * 
	 */
	
	private void updateHero(char cmd) {
		switch (cmd) {
		case 'w':
			hero.decY();
			if ((board.getCell(hero.getX(), hero.getY()) == 'X' && !hero
					.equals(exit)) || (hero.equals(exit) && dragons.size() != 0)) {
				hero.incY();
			}
			break;
		case 'd':
			hero.incX();
			if ((board.getCell(hero.getX(), hero.getY()) == 'X' && !hero
					.equals(exit)) || (hero.equals(exit) && dragons.size() != 0)) {
				hero.decX();
			}
			break;
		case 's':
			hero.incY();
			if ((board.getCell(hero.getX(), hero.getY()) == 'X' && !hero
					.equals(exit)) || (hero.equals(exit) && dragons.size() != 0)) {
				hero.decY();
			}
			break;
		case 'a':
			hero.decX();
			if ((board.getCell(hero.getX(), hero.getY()) == 'X' && !hero
					.equals(exit)) || (hero.equals(exit) && dragons.size() != 0)) {
				hero.incX();
			}
			break;
		default:
		}
	}

	/**
	 * Function to launch the darts.
	 * 
	 * @param cmd
	 * 			char with a direction received throw the user
	 * 
	 */
	
	private void throwDarts(char cmd) {
		int x = hero.getX(), y = hero.getY();
		if (hero.getDarts() != 0) {
			switch (cmd) {
			case 'i':
				hero.decDarts();
				do {
					System.out.println("Y: " + y);
					if (killDragonsInCell(x, y))
						break;
					y--;
				} while (board.getCell(x, y) != 'X');
				break;
			case 'l':
				hero.decDarts();
				do {
					System.out.println("X: " + x);
					if (killDragonsInCell(x, y))
						break;
					x++;
				} while (board.getCell(x, y) != 'X');
				break;
			case 'j':
				hero.decDarts();
				do {
					System.out.println("X: " + x);
					if (killDragonsInCell(x, y))
						break;
					x--;
				} while (board.getCell(x, y) != 'X');
				break;
			case 'k':
				hero.decDarts();
				do {
					System.out.println("Y: " + y);
					if (killDragonsInCell(x, y))
						break;
					y++;
				} while (board.getCell(x, y) != 'X');
				break;
			default:
			}
		}
	}

	/**
	 * Function to check done state.
	 * 
	 * @return done flag 
	 * 
	 */
	
	public boolean isDone() {
		return done;
	}

	/**
	 * Get hero.
	 * 
	 * @return hero
	 * 
	 */
	
	public Hero getHero() {
		return hero;
	}
	
	/**
	 * Get dragons arrayList.
	 * 
	 * @return array with the dragons
	 * 
	 */
	
	public ArrayList<Dragon> getDragons(){
		return dragons;
	}
}
