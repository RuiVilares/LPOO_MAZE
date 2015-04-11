package maze.logic;

import maze.cli.Cli;
import maze.gui.Gui;

/**
 * 
 * Class that starts the application.
 * 
 * @author Diogo Trindade
 * @author Rui Vilares
 * 
 */

public class Game {
	
	/**
	 * Main function.
	 */
	
	public static void main(String[] args) {
		new Gui();
		new Cli();
	}
}
