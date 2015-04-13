package maze.gui;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import maze.logic.Maze;

@SuppressWarnings("serial")
public class Gui extends JFrame{

	private JPanel panel;
	private static Keys keys;
	
	public Gui(){
		setPreferredSize(new Dimension(600, 500));
		panel = null;
		
		setLocationRelativeTo(null);
		setTitle("Labirinto");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    addWindowListener(new WindowAdapter() {
	        public void windowClosing(WindowEvent e) {
	            getKeys().saveKeys();
	        }
	    });
	    setResizable(true);
	    setVisible(true);
	    
	    keys = new Keys();
	    keys.loadKeys();
	    mainMenu();
	}
	
	public void definitions(){
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
	
	public void settings(){
		new SettingsDialog(getKeys());
	}

	public static Keys getKeys() {
		return keys;
	}

	public static void setKeys(Keys keys) {
		Gui.keys = keys;
	}

	
}
