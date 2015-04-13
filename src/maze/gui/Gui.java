package maze.gui;

import java.awt.*;
import java.awt.event.HierarchyBoundsListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.*;

import maze.logic.Maze;

@SuppressWarnings("serial")
public class Gui extends JFrame{

	private JPanel panel;
	private static Keys keys;
	
	public Gui(){
		panel = null;
		
		setLocationRelativeTo(null);
		setTitle("Labirinto");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setResizable(true);
	    addWindowListener(new WindowAdapter() {
	        public void windowClosing(WindowEvent e) {
	            saveKeys();
	        }
	    });
	    setSize(600, 500);
	    getContentPane().addHierarchyBoundsListener(new HierarchyBoundsListener(){
	    	 
            @Override
            public void ancestorMoved(HierarchyEvent e) {           
            }
            @Override
            public void ancestorResized(HierarchyEvent e) {
            	setPreferredSize(new Dimension(getWidth(), getHeight()));
                 
            }     
        });
	    setVisible(true);
	    
	    loadKeys();
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

	public void loadKeys(){
		ObjectInputStream is = null;
		try {
		  is = new ObjectInputStream(new FileInputStream("settings.stgs"));
		  keys = (Keys) is.readObject();
		  is.close();
		}
		catch (IOException | ClassNotFoundException e) {
			keys =  new Keys();
		}
		
	}
	public void saveKeys(){
		ObjectOutputStream os = null;
		try{
			os = new ObjectOutputStream(new FileOutputStream("settings.stgs"));
			os.writeObject(keys);
			os.close();
		}
		catch(IOException e){
		}
	}

	
}
