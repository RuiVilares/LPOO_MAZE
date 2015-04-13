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
	/**
	 * Checks if there is no wall near the door.
	 * 
	 * @return Either true if the hero can reach the door and false otherwise. 
	 * 
	 */
	public boolean accesible(Board board) {
		if(getX() == 0 && board.getCell(getX()+1,getY()) == 'X'){
			return false;
		}
		else if(getX() == board.getSize() - 1 && board.getCell(getX()-1,getY()) == 'X'){
			return false;
		}
		if(getY() == 0 && board.getCell(getX(),getY()+1) == 'X'){
			return false;
		}
		else if(getY() == board.getSize() - 1 && board.getCell(getX(),getY()-1) == 'X'){
			return false;
		}
		else
			return true;
	}
	/**
	 * Checks if the door is on the border of the board.
	 * 
	 * @return Either true if the door is on the border of the board and false otherwise. 
	 * 
	 */
	public boolean isAtBorder(int size) {
		return (((getX() == 0 || getX() == size - 1) && getY() != 0 && getY() != size - 1) 
				|| ((getY() == 0 || getY() == size -1)
				&& getX() != 0 && getX() != size - 1));
	}
}
