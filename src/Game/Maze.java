package Game;

import java.util.Scanner;

public class Maze {
	private Board board;
	private Dragon dragon;
	private Exit exit;
	private Hero hero;
	private Sword sword;
	
	public Maze() {
		board = new Board();
		dragon = new Dragon(3, 1);
		exit = new Exit(5, 9);
		hero = new Hero(1, 1);
		sword = new Sword(8, 1);
		char cmd;
		Scanner reader;
		do{
			board.draw();
			System.out.print("> ");
			reader = new Scanner(System.in);
            cmd = reader.next().charAt(0);

		}while (board.getDone());
		reader.close();
	}
	
	
}
