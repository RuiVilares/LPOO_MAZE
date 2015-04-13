package maze.gui;

import javax.swing.*;

import maze.logic.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;


@SuppressWarnings("serial")
public class GamePanel extends JPanel{
	
	private MazePanel mazePanel;
	private JPanel statusPanel;
	private JTextArea status;
	public GamePanel(final Gui frame, Maze maze){

		addKeyListener(new KeyListener(){
		    public void keyPressed(KeyEvent e) {
		    	boolean special;
		    	if(Gui.getKeys().getSpecialKey() == KeyEvent.VK_ALT){
		    		special = e.isAltDown();
		    	}
		    	else{
		    		special = e.isControlDown();
		    	}
		    	if(e.getKeyCode() == Gui.getKeys().getKeyUp()){
		    		if(!special)
		    			mazePanel.updateMaze('w');
		    		else
		    			mazePanel.updateMaze('i');
		    	}
		    	else if(e.getKeyCode() == Gui.getKeys().getKeyRight()){
		    		if(!special)
		    			mazePanel.updateMaze('d');
		    		else
		    			mazePanel.updateMaze('l');
		    	}
		    	else if(e.getKeyCode() == Gui.getKeys().getKeyDown()){
		    		if(!special)
		    			mazePanel.updateMaze('s');
		    		else
		    			mazePanel.updateMaze('k');
		    	}
		    	else if(e.getKeyCode() == Gui.getKeys().getKeyLeft()){
		    		if(!special)
		    			mazePanel.updateMaze('a');
		    		else
		    			mazePanel.updateMaze('j');
		    	}
		    	status.setText(mazePanel.getMaze().statusToString());
		    	
		    }

		    public void keyReleased(KeyEvent e) {
		    	
		    }
		    public void keyTyped(KeyEvent e) {
		    	
		    }
			
		});
		setLayout(new BorderLayout());
		setFocusable(true);
		
		mazePanel = new MazePanel(maze);
		mazePanel.addGameListner(new GameListener(){

			@Override
			public void gameDone(GameEvent e) {
				
				if(e.getSource().equals("win"))
					frame.end(true);
				else
					frame.end(false);
				
			}
			
		});
		
		statusPanel = new JPanel();
		statusPanel.setLayout(new BorderLayout());
		statusPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		statusPanel.setBackground(Color.BLACK);
		JButton mainMenu = new JButton("Menu Principal");
		JButton newGame = new JButton("Novo Jogo");
		JButton saveGame = new JButton("Gravar Jogo");
		JButton definitions = new JButton("Definicoes");
		
		
		mainMenu.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.mainMenu();
			}
		});
		newGame.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.definitions();
			}
		});
		saveGame.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				mazePanel.saveGameStatus();
			}
		});
		definitions.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.settings();
				requestFocus();
			}
		});
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBackground(Color.BLACK);
		statusPanel.add(buttonsPanel, BorderLayout.NORTH);
		GroupLayout gl_buttonsPanel = new GroupLayout(buttonsPanel);
		gl_buttonsPanel.setHorizontalGroup(
			gl_buttonsPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(mainMenu,GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
				.addComponent(newGame, GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
				.addComponent(saveGame, GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
				.addComponent(definitions, GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
		);
		gl_buttonsPanel.setVerticalGroup(
			gl_buttonsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_buttonsPanel.createSequentialGroup()
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(mainMenu)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(newGame)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(saveGame)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(definitions)
					.addContainerGap())
		);
		buttonsPanel.setLayout(gl_buttonsPanel);
		
		status = new JTextArea(mazePanel.getMaze().statusToString());
		status.setForeground(Color.WHITE);
		status.setBackground(Color.BLACK);
		status.setEditable(false);
		
		statusPanel.add(status, BorderLayout.SOUTH);
		
		add(statusPanel, BorderLayout.EAST);
		add(mazePanel, BorderLayout.CENTER);
	};
	
}
