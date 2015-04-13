package maze.logic;

import java.io.Serializable;

/**
 * 
 * Class that defines a piece of the maze.
 * 
 * @author Diogo Trindade
 * @author Rui Vilares
 * 
 */

@SuppressWarnings("serial")
public class Piece implements Serializable{
	/** X position */
	private int x;
	
	/** Y position */
	private int y;
	
	/** Char that describe the piece */
	char desc;
	
	/**
	 * Constructs and initializes a piece.
	 * 
	 * @param x 
	 * 			X position
	 * @param y 
	 * 			Y position
	 * 
	 */
	
	public Piece(int x, int y){
		this.x=x;
		this.y=y;
	}
	
	/**
	 * Get X position of a piece. 
	 * 
	 * @return X position
	 * 
	 */
	
	public int getX(){
		return x;
	}
	
	/**
	 * Get Y position of a piece. 
	 * 
	 * @return Y position
	 * 
	 */
	
	public int getY(){
		return y;
	}
	
	/**
	 * Put a piece in a new x position. 
	 * 
	 * @param x
	 * 			new x position
	 * 
	 */
	
	public void setX(int x){
		this.x=x;
	}
	
	/**
	 * Put a piece in a new y position. 
	 * 
	 * @param y
	 * 			new y position
	 * 
	 */
	
	public void setY(int y){
		this.y=y;
	}
	
	/**
	 * Increments x position. 
	 */
	
	public void incX(){
		this.x+=1;
	}
	
	/**
	 * Increments y position. 
	 */
	
	public void incY(){
		this.y+=1;
	}
	
	/**
	 * Decrements x position. 
	 */
	
	public void decX(){
		this.x-=1;
	}
	
	/**
	 * Decrements y position. 
	 */
	
	public void decY(){
		this.y-=1;
	}
	
	/**
	 * Checks if two pieces are in the same position 
	 * 
	 * @return true if pieces are in the same position, false if pieces are in different positions.
	 * 
	 */
	public boolean equals(Object piece){
		return piece != null && piece instanceof Piece && x == ((Piece)piece).x && y == ((Piece)piece).y;
	}
}
