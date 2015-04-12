package maze.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

import maze.logic.Maze;

@SuppressWarnings("serial")
public class Gui extends JFrame{

	private JPanel panel;
	
	public Gui(){
		setPreferredSize(new Dimension(500, 500));
		panel = null;
		setLocationRelativeTo(null);
		setTitle("Labirinto");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setResizable(true);
	    setVisible(true);
	    mainMenu();
	}
	
	public void getDefinitions(){
		if(panel != null){
			remove(panel);
		}
		panel = new Definitions(this);
		getContentPane().add(panel);
		pack();
	}
	public void mainMenu(){
		if(panel != null){
			remove(panel);
		}
		panel = new MainMenu(this);
		getContentPane().add(panel);
		pack();
	}
	
	public void maze(Maze maze){
        setLayout(new BorderLayout());
		setFocusTraversalKeysEnabled(false);
		remove(panel);
		panel = new GamePanel(this,maze);
		getContentPane().add(panel, BorderLayout.CENTER);
		pack();
		panel.requestFocus();
	}

	public void end(boolean winner) {
		if(panel != null){
			remove(panel);
		}
		setFocusTraversalKeysEnabled(false);
		panel = new EndPane(winner,this);
		getContentPane().add(panel);
		pack();
		panel.requestFocus();
	}
	public void interactiveBuilder(int dragonMode,int spitsFire, int size) {
		if(panel != null){
			remove(panel);
		}
		setFocusTraversalKeysEnabled(false);
		panel = new MazeCustomPanel(dragonMode, spitsFire, size, this);
		getContentPane().add(panel);
		pack();
		panel.requestFocus();
	}
}
