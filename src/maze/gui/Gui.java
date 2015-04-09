package maze.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

import maze.logic.Maze;

@SuppressWarnings("serial")
public class Gui extends JFrame{

	private JPanel panel;
	
	public Gui(){
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
		setPreferredSize(new Dimension(500, 500));
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
	public void interectiveBuilder() {
		if(panel != null){
			remove(panel);
		}
		setFocusTraversalKeysEnabled(false);
		panel = new MazeCostomBuilder(1,1,11);
		getContentPane().add(panel);
		pack();
		panel.requestFocus();
	}
}
