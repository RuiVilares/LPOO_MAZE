package maze.cli;

import java.util.Scanner;

import maze.logic.Maze;

public class Cli {
	private Scanner reader;

	public Cli(){
		Maze maze = null;
		reader = new Scanner(System.in);
		clearSrc();

		int gameMode = gameMode();
		if(gameMode == 1)
			maze = new Maze();
		else if(gameMode == 3){
			System.exit(0);
		}
		else
			maze = new Maze(size(),dragonMode(), dragonSpitFire(), dragonSize(), dartsSize());

		char cmd;
		do {
			clearSrc();
			printString(maze.statusToString());
			printString(maze + "");
			cmd = readChar();
			maze.update(cmd);

		} while (!maze.isDone());	

		clearSrc();
		if (!maze.getHero().isDead()) {
			printWinningMessage();
		} else {
			printLoosingMessage();
		}
		new Cli();
	}

	public int size() {
		int size;
		do {
			System.out.print("\n\nChoose a odd number to the size of the maze (from 11 to 31): \n\n" + "> ");
			reader = new Scanner(System.in);
			size = reader.nextInt();
		} while (size < 11 || size > 32 || size%2 ==0 );
		return size;
	}

	private int dartsSize() {
		int optionD;
		do {
			System.out.print("\n\nChoose numbre of darts: \n\n" + "> ");
			reader = new Scanner(System.in);
			try{
				optionD = reader.nextInt();
			}
			catch(Exception e){
				optionD = 100;
			}
		} while (optionD < 1 || optionD > 5);
		return optionD;
	}

	public int gameMode(){
		int optionG;
		do{
			System.out.print("Choose game mode: \n\n"
					+ "1. Classic Maze\n"
					+ "2. Random Maze\n"
					+ "3. Quit\n\n"
					+ "> ");
			reader = new Scanner(System.in);
			try{
				optionG = reader.nextInt();
			}
			catch(Exception e){
				optionG = 100;
			}
		} while (optionG < 1 || optionG > 3);
		return optionG;
	}

	public int dragonMode(){
		int optionD;
		do {
			System.out.print("\n\nChoose dragon mode: \n\n" + "1. Igle\n"
					+ "2. Random Moviment\n" + "3. Sleeping dragon\n\n" + "> ");
			reader = new Scanner(System.in);
			try{
				optionD = reader.nextInt();
			}
			catch(Exception e){
				optionD = 100;
			}
		} while (optionD < 1 || optionD > 3);
		return optionD;
	}

	public int dragonSpitFire(){
		int option;
		do {
			System.out.print("\n\nCan the dragon spit fire: \n\n" + "1. Yes\n"
					+ "2. No\n\n" + "> ");
			reader = new Scanner(System.in);
			try{
				option = reader.nextInt();
			}
			catch(Exception e){
				option = 100;
			}
		} while (option < 1 || option > 2);
		return option;
	}

	public int dragonSize(){
		int optionD;
		do {
			System.out.print("\n\nChoose numbre of dragons: \n\n" + "> ");
			reader = new Scanner(System.in);
			try{
				optionD = reader.nextInt();
			}
			catch(Exception e){
				optionD = 100;
			}

		} while (optionD < 1 || optionD > 5);
		return optionD;
	}

	public char readChar(){
		char read;
		System.out.print("> ");
		read = reader.next().charAt(0);
		return read;
	}
	public void finalize(){
		reader.close();
	}
	public void printString(String str){
		System.out.print(str);
	}
	public void printWinningMessage(){
		System.out.println("\t\t\t\t YOU WIN !!!!!! \n \n \n \n \n \n \n \n \n");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
		}
	}
	public void printLoosingMessage(){
		System.out.println("\t\t\t\t YOU LOSE !!!!!! \n \n \n \n \n \n \n \n \n");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
		}
	}
	public void clearSrc(){
		for(int i = 0; i < 50; i++){
			System.out.println();
		}
	}
}
