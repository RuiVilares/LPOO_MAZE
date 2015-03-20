package maze.cli;

import java.util.Scanner;

import maze.gui.Gui;
import maze.logic.Maze;

public class Cli {
	private Scanner reader;
	
	public Cli(){
		Maze maze;
		reader = new Scanner(System.in);
		clearSrc();
		Gui gui = new Gui();
		
		gui.getDefinitions();
		int gameMode = gameMode();
		if(gameMode == 1)
			 maze = new Maze();
		else
			maze = new Maze(dragonMode(), dragonSpitFire(), dragonSize());
		
		char cmd;
		do {
			clearSrc();
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
	}
	
	public int gameMode(){
		int optionG;
		do{
		System.out.print("Choose game mode: \n\n"
				+ "1. Classic Maze\n"
				+ "2. Random Maze\n\n"
				+ "> ");
		reader = new Scanner(System.in);
		optionG = reader.nextInt();
		} while (optionG < 1 || optionG > 2);
		return optionG;
	}
	
	public int dragonMode(){
		int optionD;
		do {
			System.out.print("\n\nChoose dragon mode: \n\n" + "1. Igle\n"
					+ "2. Random Moviment\n" + "3. Sleeping dragon\n\n" + "> ");
			reader = new Scanner(System.in);
			optionD = reader.nextInt();
		} while (optionD < 1 || optionD > 3);
		return optionD;
	}
	
	public int dragonSpitFire(){
		int option;
		do {
			System.out.print("\n\nCan the dragon spit fire: \n\n" + "1. Yes\n"
					+ "2. No\n\n" + "> ");
			reader = new Scanner(System.in);
			option = reader.nextInt();
		} while (option < 1 || option > 2);
		return option;
	}
	
	public int dragonSize(){
		int optionD;
		do {
			System.out.print("\n\nChoose numbre of dragons: \n\n" + "> ");
			reader = new Scanner(System.in);
			optionD = reader.nextInt();
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
	}
	public void printLoosingMessage(){
		System.out.println("\t\t\t\t YOU LOSE !!!!!! \n \n \n \n \n \n \n \n \n");
	}
	public void clearSrc(){
		for(int i = 0; i < 50; i++){
			System.out.println();
		}
	}
}
