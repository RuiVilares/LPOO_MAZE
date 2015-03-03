package Game;

public class Piece {
	private int x;
	private int y;
	
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
		this.x=this.x++;
	}
	
	public void incY(){
		this.y=this.y++;
	}
	
	public void decX(){
		this.x=this.x--;
	}
	
	public void decY(){
		this.y=this.y--;
	}
}
