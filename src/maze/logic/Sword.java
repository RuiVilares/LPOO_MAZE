package maze.logic;

/**
 * 
 * Class that defines the sword.
 * 
 * @author Diogo Trindade
 * @author Rui Vilares
 * 
 */

@SuppressWarnings("serial")
public class Sword extends Piece {
	
	/**
	 * Constructs and initializes a sword.
	 * 
	 * @param x 
	 * 			X position
	 * @param y 
	 * 			Y position
	 * 
	 */
	
	public Sword(int x, int y){
		super(x, y);
		this.desc='E';
	}
	
	/**
	 * Draws sword to String.
	 * 
	 * @return String with a sword
	 * 
	 */	
	
	public String toString(){
		return ""+desc;
	}
	
}
