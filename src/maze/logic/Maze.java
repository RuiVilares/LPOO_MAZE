package maze.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

@SuppressWarnings("serial")
public class Maze implements Serializable{
	private boolean done;
	private transient Builder builder;
	private Board board;
	private ArrayList<Dragon> dragons;
	private ArrayList<Dart> darts;
	private Exit exit;
	private Hero hero;
	private Sword sword;
	private Shield shield;
	
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

	private int checkForAliveDragonCell(int x, int y) {
		for (int i = 0; i < dragons.size(); i++) {
			if (dragons.get(i).getX() == x && dragons.get(i).getY() == y)
				return i;
		}
		return -1;
	}

	private int checkForDarts(int x, int y) {
		for (int i = 0; i < darts.size(); i++) {
			if (darts.get(i).getX() == x && darts.get(i).getY() == y)
				return i;
		}
		return -1;
	}

	private boolean swordEqualsAnyDragon() {
		for (int i = 0; i < dragons.size(); i++) {
			if (sword.equals(dragons.get(i)))
				return true;
		}
		return false;
	}

	private boolean shieldEqualsAnyDragon() {
		for (int i = 0; i < dragons.size(); i++) {
			if (shield.equals(dragons.get(i)))
				return true;
		}
		return false;
	}

	private boolean killDragonsInCell(int x, int y) {
		for (int i = 0; i < dragons.size(); i++) {
			if (dragons.get(i).getX() == x && dragons.get(i).getY() == y) {
				dragons.remove(i);
				return true;
			}
		}
		return false;
	}



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

	private void checkIfDone() {
		if (hero.isDead() || (hero.equals(exit) && dragons.size() == 0)) {
			done = true;
		}
	}

	private void checkArmedStatus() {
		if (!hero.isArmed() && hero.equals(sword))
			hero.setArmed();
	}

	private void checkProtectionStatus() {
		if (!hero.isProtection() && hero.equals(shield))
			hero.setProtection();
	}

	private void checkDarts() {
		for (int i = 0; i < darts.size(); i++) {
			if (darts.get(i).equals(hero) && !darts.get(i).getTaked()) {
				darts.get(i).setTaked();
				hero.incDarts();
			}
		}
	}

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

	public boolean isDone() {
		return done;
	}

	public Hero getHero() {
		return hero;
	}
	
	public ArrayList<Dragon> getDragons(){
		return dragons;
	}
}
