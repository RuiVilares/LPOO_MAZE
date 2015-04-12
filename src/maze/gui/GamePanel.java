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
		statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.PAGE_AXIS));
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
		
		statusPanel.add(newGame);
		statusPanel.add(saveGame);
		//String status = mazePanel.getMaze().stateToString();
		
		add(statusPanel, BorderLayout.EAST);
		add(mazePanel, BorderLayout.CENTER);
	};
    public void keyPressed(KeyEvent e) {
    	switch(e.getKeyCode()){
    	case KeyEvent.VK_UP:
    		mazePanel.updateMaze('w');
    		break;
    	case KeyEvent.VK_RIGHT:
    		mazePanel.updateMaze('d');
    		break;
    	case KeyEvent.VK_DOWN:
    		mazePanel.updateMaze('s');
    		break;
    	case KeyEvent.VK_LEFT:
    		mazePanel.updateMaze('a');
    		break;
    	}
    	
    }

    public void keyReleased(KeyEvent e) {
    	
    }
    public void keyTyped(KeyEvent e) {
    	
    }
}
