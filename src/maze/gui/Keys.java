package maze.gui;

import java.awt.event.KeyEvent;
import java.io.Serializable;

@SuppressWarnings("serial")
public class Keys implements Serializable{
	private int keyUp;
	private int keyDown;
	private int keyLeft;
	private int keyRight;
	private int specialKey;
	
	public Keys(){
		keyUp = KeyEvent.VK_UP;
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
		if(keyUp != keyDown && keyUp != keyLeft && keyUp != keyRight && keyUp != specialKey)
			this.keyUp = keyUp;
	}

	public void setKeyDown(int keyDown) {
		if(keyDown != keyUp && keyDown != keyLeft && keyDown != keyRight && keyDown != specialKey)
			this.keyDown = keyDown;
	}

	public void setKeyLeft(int keyLeft) {
		if(keyLeft != keyUp && keyLeft != keyDown && keyLeft != keyRight && keyLeft != specialKey)
			this.keyLeft = keyLeft;
	}

	public void setKeyRight(int keyRight) {
		if(keyRight != keyUp && keyRight != keyDown && keyRight != keyLeft && keyRight != specialKey)
			this.keyRight = keyRight;
	}

	public void setSpecialKey(int specialKey) {
		if(specialKey == KeyEvent.VK_ALT || specialKey == KeyEvent.VK_CONTROL)
			this.specialKey = specialKey;
	}
	
	

}
