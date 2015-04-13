package maze.gui;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import maze.logic.Maze;


@SuppressWarnings("serial")
public class MainMenu extends JPanel {
	private Gui frame;
	private BufferedImage backGround; 
	private static final JButton loadGame = new JButton("Carregar Jogo");
	private static final JButton newGame = new JButton("Novo Jogo");
	private static final JButton settings = new JButton("Definicoes");
	private static final JButton exit = new JButton("Sair");
	
	public MainMenu(final Gui frame){
		this.frame = frame;
		try {
			backGround = ImageIO.read(new File("res/Main.jpg"));
		} catch (IOException e1) {
			
		}
		
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
				frame.saveKeys();
				System.exit(0);
		    }

		});
		add(exit);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backGround, 0, 0, getWidth(), getHeight(), 0, 0, backGround.getWidth(), backGround.getHeight(), null);
	}
	private void loadGame(){
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Game Files (*.game)", "game");
		fileChooser.setFileFilter(filter);
		fileChooser.showOpenDialog(getParent());
		Maze maze = null;
		ObjectInputStream is = null;
		try {
			File file = fileChooser.getSelectedFile();
			if(file != null){
				is = new ObjectInputStream(new FileInputStream(file));
				maze = (Maze) is.readObject();
				is.close();
			}
		}
		catch (IOException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(frame,"ERROR");
		}
		if(maze != null){
			frame.maze(maze);
		}
		
	}
}
