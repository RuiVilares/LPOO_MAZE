package maze.logic;

import java.util.Scanner;

public class Game {
	public static void main(String[] args) {
		Scanner reader; 
		int optionG, optionD;
		do{
		System.out.print("Choose game mode: \n\n"
				+ "1. Classic Maze\n"
				+ "2. Random Maze\n\n"
				+ "> ");
		reader = new Scanner(System.in);
		optionG = reader.nextInt();
		} while (optionG < 1 || optionG > 2);
		do {
			System.out.print("\n\nChoose dragon mode: \n\n" + "1. Igle\n"
					+ "2. Random Moviment\n" + "3. Sleeping dragon\n\n" + "> ");
			reader = new Scanner(System.in);
			optionD = reader.nextInt();
		} while (optionD < 1 || optionD > 3);
		if (optionG == 1){
			if (optionD == 1)
				new Maze(Dragon.Behaviour.Idle);
			if (optionD == 2)
				new Maze(Dragon.Behaviour.Random);
			if (optionD == 3)
				new Maze(Dragon.Behaviour.Sleep);
		}
		else
		{
			if (optionD == 1)
				new MazeGenerator(Dragon.Behaviour.Idle);
			if (optionD == 2)
				new MazeGenerator(Dragon.Behaviour.Random);
			if (optionD == 3)
				new MazeGenerator(Dragon.Behaviour.Sleep);
		}
		reader.close();
	}
}
