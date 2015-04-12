package maze.gui;

import javax.swing.*;

import maze.logic.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


@SuppressWarnings("serial")
public class GamePanel extends JPanel implements KeyListener{
	
	private MazePanel mazePanel;
	private JPanel statusPanel;
	private JTextArea status;
	
	public GamePanel(final Gui frame, Maze maze){
		
		addKeyListener(this);
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
		JButton newGame = new JButton("Novo Jogo");
		JButton saveGame = new JButton("Gravar Jogo");
		
		newGame.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getDefinitions();
			}
		});
		saveGame.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				mazePanel.saveGameStatus();
			}
		});
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.add(newGame);
		buttonsPanel.add(saveGame);
		buttonsPanel.setBackground(Color.BLACK);
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
		statusPanel.add(buttonsPanel, BorderLayout.NORTH);
		
		status = new JTextArea(mazePanel.getMaze().stateToString());
		status.setForeground(Color.WHITE);
		status.setBackground(Color.BLACK);
		status.setEditable(false);
		
		statusPanel.add(status, BorderLayout.SOUTH);
		
		add(statusPanel, BorderLayout.EAST);
		add(mazePanel, BorderLayout.CENTER);
	};
    public void keyPressed(KeyEvent e) {
    	switch(e.getKeyCode()){
    	case KeyEvent.VK_UP:
    		if(!e.isAltDown())
    			mazePanel.updateMaze('w');
    		else
    			mazePanel.updateMaze('i');
    		break;
    	case KeyEvent.VK_RIGHT:
    		if(!e.isAltDown())
    			mazePanel.updateMaze('d');
    		else
    			mazePanel.updateMaze('l');
    		break;
    	case KeyEvent.VK_DOWN:
    		if(!e.isAltDown())
    			mazePanel.updateMaze('s');
    		else
    			mazePanel.updateMaze('k');
    		break;
    	case KeyEvent.VK_LEFT:
    		if(!e.isAltDown())
    			mazePanel.updateMaze('a');
    		else
    			mazePanel.updateMaze('j');
    		break;
    	}
    	status.setText(mazePanel.getMaze().stateToString());
    	
    }

    public void keyReleased(KeyEvent e) {
    	
    }
    public void keyTyped(KeyEvent e) {
    	
    }
}
