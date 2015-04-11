package maze.logic;

/**
 * 
 * Class that defines the darts.
 * 
 * @author Diogo Trindade
 * @author Rui Vilares
 * 
 */

@SuppressWarnings("serial")
public class Dart extends Piece{
	/** Dart is taked now? */
	boolean taked;

	/**
	 * Constructs and initializes a dart.
	 * 
	 * @param x 
	 * 			X position
	 * @param y 
	 * 			Y position
	 * 
	 */
	
	public Dart(int x, int y) {
		super(x, y);
		this.desc='/';
		this.taked=false;
	}
	
	/**
	 * Draws dart to String.
	 * 
	 * @return String with a dart
	 * 
	 */	
	
	public String toString(){
		return ""+desc;
	}
	
	/**
	 * Verify if dart is already taked. 
	 * 
	 * @return true if was already taked, false otherwise
	 * 
	 */
	
	public boolean getTaked(){
		return taked;
	}
	
	
	/**
	 * Put dart taked
	 */
	
	public void setTaked(){
		this.taked = true;
	}
	
}
