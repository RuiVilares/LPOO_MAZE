package maze.logic;


@SuppressWarnings("serial")
public class Exit extends Piece {
	
	public Exit(int x, int y){
		super(x, y);
		this.desc = 'S';
	}
	public String toString(){
		return ""+desc;
	}
	
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

	public boolean isAtBorder(int size) {
		return (((getX() == 0 || getX() == size - 1) && getY() != 0 && getY() != size - 1) 
				|| ((getY() == 0 || getY() == size -1)
				&& getX() != 0 && getX() != size - 1));
	}
}
