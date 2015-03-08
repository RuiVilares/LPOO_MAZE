package data_structures;

public class Pair {
	private int x,y;
	
	public boolean copy(Object p2){
		if(p2 != null && p2 instanceof Pair){
			x = ((Pair)p2).x;
			y = ((Pair)p2).y;
		}
		return false;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Pair(int x, int y){
		this.x=x;
		this.y=y;
	}
}
