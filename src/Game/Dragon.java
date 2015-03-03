package Game;

public class Dragon extends Piece {

	private boolean died;

	public Dragon(int x, int y) {
		super(x, y);
		this.died = false;
	}

	public boolean getDied() {
		return died;
	}

	public void setDied() {
		this.died = true;
	}

}
