package maze.gui;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import maze.logic.Maze;

import javax.swing.*;

public class Gui extends JFrame implements KeyListener, ComponentListener{

	private static final long serialVersionUID = 3010908532613150542L;
	private JPanel panel;
	
	public Gui(){
		this.panel = null;
		this.setTitle("Labirinto");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setVisible(true);
	}
	
	public void getDefinitions(){
		if(getKeyListeners().length != 0){
			this.removeKeyListener(this);
		}
		if(getComponentListeners().length != 0){
			this.removeComponentListener(this);
		}
		if(panel != null){
			remove(panel);
		}
		setResizable(true);
		panel = new Definitions(this);
		add(panel);
		pack();
	}
	
	public void maze(Maze maze){
        addKeyListener(this);
        addComponentListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
		remove(panel);
		panel = new MazeFrame(maze,this);
		((MazeFrame) panel).drawMaze();
		add(panel);
		pack();
	}

    public void keyPressed(KeyEvent e) {
    	switch(e.getKeyCode()){
    	case KeyEvent.VK_UP:
    		((MazeFrame) panel).updateMaze('w');
    		break;
    	case KeyEvent.VK_RIGHT:
    		((MazeFrame) panel).updateMaze('d');
    		break;
    	case KeyEvent.VK_DOWN:
    		((MazeFrame) panel).updateMaze('s');
    		break;
    	case KeyEvent.VK_LEFT:
    		((MazeFrame) panel).updateMaze('a');
    		break;
    	}
    	
    }

    public void keyReleased(KeyEvent e) {
    	
    }
    public void keyTyped(KeyEvent e) {
    	
    }

	public void componentResized(ComponentEvent e) {
		
	}

	public void componentMoved(ComponentEvent e) {
		((MazeFrame) panel).resize();
	}

	public void componentShown(ComponentEvent e) {
		
	}

	public void componentHidden(ComponentEvent e) {
		
	}
}
