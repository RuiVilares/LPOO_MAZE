package maze.logic;

import java.util.ArrayList;
import java.util.Random;


public class Maze {
	private boolean done;
	private Builder builder;
	private Board board;
	private ArrayList<Dragon> dragons;
	private Exit exit;
	private Hero hero;
	private Sword sword;
	private Shield shield;
	
	public Maze(int gameMode, int dragonMode, int dragonSpitFire , int nDragons) {
		done = false;
		dragons = new ArrayList<Dragon>();
		
		
		builder = new Builder(gameMode);
		if (builder.getRandom()) {
			builder.setDragonMode(dragonMode);
			builder.setDragonSpitFire(dragonSpitFire);
		}
		board = builder.createBoard();
		hero = builder.createHero(board);
		exit = builder.createExit(board);
		sword = builder.createSword(board, hero);
		shield = builder.createShield(board, hero);
		do{
			dragons.add(builder.createDragon(board, hero));
			nDragons--;
		}while(nDragons > 0);
	}

	public String toString() {
		String maze = "";
		int dragonIndex;
		for (int i = 0; i < board.getSize(); i++) {
			for (int j = 0; j < board.getSize(); j++) {
				dragonIndex = checkForAliveDragonCell(j,i);
				if (i == hero.getY() && j == hero.getX()) {
					maze += hero + " ";
				} else if (i == exit.getY() && j == exit.getX()) {
					maze += exit + " ";
				} else if (dragonIndex != -1) {
					if (dragons.get(dragonIndex).equals(sword) && !hero.getArmed()) {
						if (!dragons.get(dragonIndex).getSleeping())
							maze += "F ";
						else
							maze += "f ";
					}
					else if(dragons.get(dragonIndex).equals(shield) && !hero.isProtection()){
						if (!dragons.get(dragonIndex).getSleeping())
							maze += "M ";
						else
							maze += "m ";
					}
					else
						maze += dragons.get(dragonIndex) + " ";
				} 
				else if (i == sword.getY() && j == sword.getX()
						&& !hero.getArmed() && !swordEqualsAnyDragon()) {
					maze += sword + " ";
					} 
				else if (i == shield.getY() && j == shield.getX()
						&& !hero.isProtection() && !shieldEqualsAnyDragon()) {
					maze += shield + " ";
					}
				else {
					maze += board.getCell(j, i) + " ";
				}
			}
			maze += "\n";
		}
		return maze;
	}

	public void update(char cmd) {
		updateHero(cmd);
		updateDragon();
		checkArmedStatus();
		checkProtectionStatus();
		checkDragons();
		checkIfDone();
	}

	private int checkForAliveDragonCell(int x, int y) {
		for(int i = 0; i < dragons.size(); i++){
			if(dragons.get(i).getX() == x && dragons.get(i).getY() == y && !dragons.get(i).getDead())
				return i;
		}
		return -1;
	}

	private boolean swordEqualsAnyDragon() {
		for(int i = 0; i < dragons.size(); i++){
			if(sword.equals(dragons.get(i)))
				return true;
		}
		return false;
	}
	private boolean shieldEqualsAnyDragon() {
		for(int i = 0; i < dragons.size(); i++){
			if(shield.equals(dragons.get(i)))
				return true;
		}
		return false;
	}
	private boolean allDragonsDead() {
		for(int i = 0; i < dragons.size(); i++){
			if(!dragons.get(i).getDead()){
				return false;
			}
		}
		return true;
	}
	private boolean checkDragonColision(int dif, int index) {
		int min = 0, max = 0, fix = 0;
		if (Math.abs(hero.getX() - dragons.get(index).getX()) <= dif
				&& Math.abs(hero.getY() - dragons.get(index).getY()) == 0) {
			if (hero.getX() - dragons.get(index).getX() >= 0) {
				min = dragons.get(index).getX();
				max = hero.getX();
			} else {
				min = hero.getX();
				max = dragons.get(index).getX();
			}
			fix = hero.getY();
		}
		if (Math.abs(hero.getX() - dragons.get(index).getX()) == 0
				&& Math.abs(hero.getY() - dragons.get(index).getY()) <= dif) {
			if (hero.getY() - dragons.get(index).getY() >= 0) {
				min = dragons.get(index).getY();
				max = hero.getY();
			} else {
				min = hero.getY();
				max = dragons.get(index).getY();
			}
			fix = hero.getX();
		}
		do {
			if (board.getCell(min, fix) == 'X') {
				return false;
			}
			min++;
		} while (min < max);
		return true;
	}

	private void checkDragons() {
		for(int i = 0; i < dragons.size(); i++){
			int dif;
			if (dragons.get(i).getSpitFire())
				dif = 3;
			else
				dif = 1;
			if (checkDragonColision(dif, i)) {
				if (hero.getArmed() && !dragons.get(i).getSpitFire()) {
					dragons.get(i).setDead();
				} else {
					if (!dragons.get(i).getSleeping() && !hero.isProtection())
						hero.setDead();
				}
			}
		}
	}

	private void checkIfDone() {
		if (hero.getDead() || (hero.equals(exit) && allDragonsDead())) {
			done = true;
		}
	}

	private void checkArmedStatus() {
		if (!hero.getArmed() && hero.equals(sword))
			hero.setArmed();
	}
	private void checkProtectionStatus() {
		if (!hero.isProtection() && hero.equals(shield))
			hero.setProtection();
	}

	private void updateDragon() {
		for(int i = 0; i < dragons.size(); i++){
			if (dragons.get(i).canMove()) {
				updateDragonPos(i);
			}
			if (dragons.get(i).canSleep())
				if (dragons.get(i).getSleeping())
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
			if (board.getCell(dragons.get(i).getX() + x, dragons.get(i).getY() + y) == ' ') {
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
					.equals(exit)) || (hero.equals(exit) && !allDragonsDead())) {
				hero.incY();
			}
			break;
		case 'd':
			hero.incX();
			if ((board.getCell(hero.getX(), hero.getY()) == 'X' && !hero
					.equals(exit)) || (hero.equals(exit) && !allDragonsDead())) {
				hero.decX();
			}
			break;
		case 's':
			hero.incY();
			if ((board.getCell(hero.getX(), hero.getY()) == 'X' && !hero
					.equals(exit)) || (hero.equals(exit) && !allDragonsDead())) {
				hero.decY();
			}
			break;
		case 'a':
			hero.decX();
			if ((board.getCell(hero.getX(), hero.getY()) == 'X' && !hero
					.equals(exit)) || (hero.equals(exit) && !allDragonsDead())) {
				hero.incX();
			}
			break;
		default:
		}
	}
	public boolean isDone() {
		return done;
	}

	public Hero getHero() {
		return hero;
	}
}
