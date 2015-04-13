package maze.logic;

/**
 * 
 * Class that defines the shield.
 * 
 * @author Diogo Trindade
 * @author Rui Vilares
 * 
 */

@SuppressWarnings("serial")
public class Shield extends Piece {

	/**
	 * Constructs and initializes a shield.
	 * 
	 * @param x 
	 * 			X position
	 * @param y 
	 * 			Y position
	 * 
	 */
	
	public Shield(int x, int y) {
		super(x, y);
		this.desc = 'O';
	}
	
	/**
	 * Draws shield to String.
	 * 
	 * @return String with a shield
	 * 
	 */	
	
	public String toString(){
		return "" + desc;
	}
}
