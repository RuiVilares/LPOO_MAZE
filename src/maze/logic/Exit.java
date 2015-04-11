package maze.logic;

/**
 * 
 * Class that defines the exit.
 * 
 * @author Diogo Trindade
 * @author Rui Vilares
 * 
 */

@SuppressWarnings("serial")
public class Exit extends Piece {
	
	/**
	 * Constructs and initializes a exit.
	 * 
	 * @param x 
	 * 			X position
	 * @param y 
	 * 			Y position
	 * 
	 */
	
	public Exit(int x, int y){
		super(x, y);
		this.desc = 'S';
	}
	
	/**
	 * Draws exit to String.
	 * 
	 * @return String with a exit
	 * 
	 */	
	
	public String toString(){
		return ""+desc;
	}
	
}
