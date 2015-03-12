package maze.logic;

import java.util.Random;
import maze.cli.*;

public class Maze {
	private boolean done;
	private Builder builder;
	private Board board;
	private Dragon dragon;
	private Exit exit;
	private Hero hero;
	private Sword sword;
	private Interface io;

	public Maze() {
		done = false;
		io = new Interface();
		io.clearSrc();
		builder = new Builder(io.gameMode());
		if (builder.getRandom()) {
			builder.setDragonMode(io.dragonMode());
			builder.setDragonSpitFire(io.dragonSpitFire());
		}
		board = builder.createBoard();
		hero = builder.createHero(board);
		exit = builder.createExit(board);
		sword = builder.createSword(board, hero);
		dragon = builder.createDragon(board, hero);

		char cmd;
		do {
			io.clearSrc();
			io.printString(this.toString());
			cmd = io.readChar();
			update(cmd);

		} while (!done);
		io.clearSrc();
		if (!hero.getDead()) {
			io.printWinningMessage();
		} else {
			io.printLoosingMessage();
		}
	}

	public String toString() {
		String maze = "";
		for (int i = 0; i < board.getSize(); i++) {
			for (int j = 0; j < board.getSize(); j++) {
				if (i == hero.getY() && j == hero.getX()) {
					maze += hero + " ";
				} else if (i == exit.getY() && j == exit.getX()) {
					maze += exit + " ";
				} else if (i == dragon.getY() && j == dragon.getX()
						&& !dragon.getDead()) {
					if (dragon.equals(sword) && !hero.getArmed()) {
						if (dragon.getSleeping())
							maze += "F ";
						else
							maze += "f ";
					} else
						maze += dragon + " ";
				} else if (i == sword.getY() && j == sword.getX()
						&& !hero.getArmed() && !dragon.equals(sword)) {
					maze += sword + " ";
				} else {
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
		checkDragon();
		checkIfDone();
	}

	private boolean checkDragonColision(int dif) {
		int i;
		if (hero.getX() - dragon.getX() <= dif
				&& Math.abs(hero.getY() - dragon.getY()) == 0 && hero.getX() - dragon.getX() >= 0) {
			i = dragon.getX();
			do {
				if (board.getCell(i, hero.getY()) == 'X'){
					return false;
				}
				i++;
			} while(i<hero.getX());
			return true;
		}
		if (dragon.getX() - hero.getX() <= dif
				&& Math.abs(hero.getY() - dragon.getY()) == 0 && dragon.getX() - hero.getX() >= 0) {
			i = hero.getX();
			do {
				if (board.getCell(i, hero.getY()) == 'X')
				{
					return false;
				}
				i++;
			} while(i<dragon.getX());
			return true;
		}
		if (Math.abs(hero.getX() - dragon.getX()) == 0
				&& hero.getY() - dragon.getY() <= dif && hero.getY() - dragon.getY() >= 0) {
			i = dragon.getY();
			do {
				if (board.getCell(hero.getX(), i) == 'X')
				{
					return false;
				}
				i++;
			} while(i<hero.getY());
			return true;
		}
		if (Math.abs(hero.getX() - dragon.getX()) == 0
				&& dragon.getY() - hero.getY() <= dif && dragon.getY() - hero.getY() >= 0) {
			i = hero.getY();
			do {
				if (board.getCell(hero.getX(), i) == 'X')
				{
					return false;
				}
				i++;
			} while(i<dragon.getY());
			return true;
		}
		return false;
	}

	private void checkDragon() {
		int dif;
		if (dragon.getSpitFire())
			dif = 3;
		else
			dif = 1;
		if (checkDragonColision(dif)) {
			if (hero.getArmed() && !dragon.getSpitFire()) {
				dragon.setDead();
			} else {
				if (!dragon.getSleeping())
					hero.setDead();
			}
		}
	}

	private void checkIfDone() {
		if (hero.getDead() || (hero.equals(exit) && dragon.getDead())) {
			done = true;
		}
	}

	private void checkArmedStatus() {
		if (!hero.getArmed() && hero.equals(sword))
			hero.setArmed();
	}

	private void updateDragon() {
		if (dragon.canMove()) {
			updateDragonPos();
		}
		if (dragon.canSleep())
			if (dragon.getSleeping())
				dragon.update();

			else
				dragon.sleepingMachine();
	}

	private void updateDragonPos() {
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
			if (board.getCell(dragon.getX() + x, dragon.getY() + y) == ' ') {
				dragon.setX(dragon.getX() + x);
				dragon.setY(dragon.getY() + y);
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
					.equals(exit)) || (hero.equals(exit) && !dragon.getDead())) {
				hero.incY();
			}
			break;
		case 'd':
			hero.incX();
			if ((board.getCell(hero.getX(), hero.getY()) == 'X' && !hero
					.equals(exit)) || (hero.equals(exit) && !dragon.getDead())) {
				hero.decX();
			}
			break;
		case 's':
			hero.incY();
			if ((board.getCell(hero.getX(), hero.getY()) == 'X' && !hero
					.equals(exit)) || (hero.equals(exit) && !dragon.getDead())) {
				hero.decY();
			}
			break;
		case 'a':
			hero.decX();
			if ((board.getCell(hero.getX(), hero.getY()) == 'X' && !hero
					.equals(exit)) || (hero.equals(exit) && !dragon.getDead())) {
				hero.incX();
			}
			break;
		default:
		}
	}
}
