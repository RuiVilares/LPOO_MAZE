package maze.logic;

public class Piece {
	private int x;
	private int y;
	char desc;
	
	public Piece(int x, int y){
		this.x=x;
		this.y=y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setX(int x){
		this.x=x;
	}
	
	public void setY(int y){
		this.y=y;
	}
	
	public void incX(){
		this.x+=1;
	}
	
	public void incY(){
		this.y+=1;
	}
	
	public void decX(){
		this.x-=1;
	}
	
	public void decY(){
		this.y-=1;
	}
	public boolean equals(Object piece){
		return piece != null && piece instanceof Piece && x == ((Piece)piece).x && y == ((Piece)piece).y;
	}
}
