package maze.gui;

import java.awt.event.KeyEvent;

public class Keys {
	private int keyUp = KeyEvent.VK_UP;
	private int keyDown;
	private int keyLeft;
	private int keyRight;
	private int specialKey;
	
	public Keys(){
		keyDown = KeyEvent.VK_DOWN;
		keyLeft = KeyEvent.VK_LEFT;
		keyRight = KeyEvent.VK_RIGHT;
		specialKey = KeyEvent.VK_ALT;
	}

	public int getKeyUp() {
		return keyUp;
	}

	public int getKeyDown() {
		return keyDown;
	}

	public int getKeyLeft() {
		return keyLeft;
	}

	public int getKeyRight() {
		return keyRight;
	}

	public int getSpecialKey() {
		return specialKey;
	}

	public void setKeyUp(int keyUp) {
		this.keyUp = keyUp;
	}

	public void setKeyDown(int keyDown) {
		this.keyDown = keyDown;
	}

	public void setKeyLeft(int keyLeft) {
		this.keyLeft = keyLeft;
	}

	public void setKeyRight(int keyRight) {
		this.keyRight = keyRight;
	}

	public void setSpecialKey(int specialKey) {
		this.specialKey = specialKey;
	}
	
	public void loadKeys(String path){
		
	}
	public void saveKeys(String path){
		
	}
	

}
