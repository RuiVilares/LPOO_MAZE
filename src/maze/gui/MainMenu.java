package maze.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.*;

import maze.logic.Maze;


@SuppressWarnings("serial")
public class MainMenu extends JPanel {
	private Gui frame;
	private static final JButton loadGame = new JButton("Carregar Jogo");
	private static final JButton newGame = new JButton("Novo Jogo");
	private static final JButton settings = new JButton("Definicoes");
	private static final JButton exit = new JButton("Sair");
	
	public MainMenu(final Gui frame){
		this.frame = frame;
		newGame.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
		    {
				frame.definitions();
		    }

		});
		add(newGame);
		loadGame.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
		    {
				loadGame();
		    }

		});
		add(loadGame);
		settings.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
		    {
				frame.settings();
		    }

		});
		add(settings);
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
		    {
				System.exit(0);
		    }

		});
		add(exit);
	}
	private void loadGame(){
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.showOpenDialog(getParent());
		Maze maze = null;
		ObjectInputStream is = null;
		try {
		  is = new ObjectInputStream(new FileInputStream(fileChooser.getSelectedFile()));
		  maze = (Maze) is.readObject();
		  is.close();
		}
		catch (IOException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(frame,"ERROR");
		}
		frame.maze(maze);
		
	}
}
