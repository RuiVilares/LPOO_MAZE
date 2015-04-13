package maze.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import maze.logic.Maze;

@SuppressWarnings("serial")
public class MazePanel extends JPanel{
	
	private static BufferedImage sprite;
	private Maze maze;
	private GameListener listener;
	
	public MazePanel(Maze maze){
		this.maze = maze;
		setLayout(new GridLayout(maze.getSize(),maze.getSize()));
		setBackground(Color.BLACK);
		setFocusable(true);
		try{
			sprite = ImageIO.read(new File("res/Sprite.jpg"));
			
		}
		catch(IOException e){
			JOptionPane.showMessageDialog(this, "Sprite file is missing...");
			System.exit(0);
		}
		
		
	}
	public void updateMaze(char cmd){
		maze.update(cmd);
		if(maze.isDone()){
			if(!maze.getHero().isDead())
				listener.gameDone(new GameEvent("win",0));
			else
				listener.gameDone(new GameEvent("lose",0));
		}
		repaint();
	}

	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		String mazeString = maze + "";
		int size = maze.getSize();
		int height = getHeight();
		int width = getWidth();
		int x1 = 0;
		int x2 = width/size;
		int y1 = 0;
		int y2 = height/size;
		int imageHeight = sprite.getHeight();
		int imageWidth = sprite.getWidth();
		for(int i = 0; i < mazeString.length(); i++){
			switch(mazeString.charAt(i)){
			case 'X':
				g.drawImage(sprite, x1, y1, x2, y2, 0, imageHeight/2, imageWidth/6, imageHeight, null);
				break;
			case ' ':
				g.drawImage(sprite, x1, y1, x2, y2, 5*imageWidth/6, imageHeight/2, imageWidth, imageHeight, null);
				break;
			case 'H':
				g.drawImage(sprite, x1, y1, x2, y2, 0, 0, imageWidth/6, imageHeight/2, null);
				break;
			case 'D':
				g.drawImage(sprite, x1, y1, x2, y2, imageWidth/2, imageHeight/2, 2*imageWidth/3, imageHeight, null);
				break;
			case 'E':
				g.drawImage(sprite, x1, y1, x2, y2, 5*imageWidth/6, 0, imageWidth, imageHeight/2, null);
				break;
			case 'S':
				g.drawImage(sprite, x1, y1, x2, y2, imageWidth/6, imageHeight/2, imageWidth/3, imageHeight, null);
				break;
			case 'A':
				g.drawImage(sprite, x1, y1, x2, y2, imageWidth/2, 0, 2*imageWidth/3, imageHeight/2, null);
				break;
			case 'd':
				g.drawImage(sprite, x1, y1, x2, y2, imageWidth/3, imageHeight/2, imageWidth/2, imageHeight, null);
				break;
			case 'O':
				g.drawImage(sprite, x1, y1, x2, y2, 2*imageWidth/3, 0, 5*imageWidth/6, imageHeight/2, null);
				break;
			case '/':
				g.drawImage(sprite, x1, y1, x2, y2, 2*imageWidth/3, imageHeight/2, 5*imageWidth/6, imageHeight, null);
				break;
			case 'U':
				g.drawImage(sprite, x1, y1, x2, y2, imageWidth/3, 0, imageWidth/2, imageHeight/2, null);
				break;
			case 'Q':
				g.drawImage(sprite, x1, y1, x2, y2, imageWidth/6, 0, imageWidth/3, imageHeight/2, null);
				break;
			case '\n':
				x1 = 0;
				x2 = width/size;
				y1 += height/size;
				y2 += height/size;
				break;
			default:
				g.drawImage(sprite, x1, y1, x2, y2, imageWidth/2, imageHeight/2, 2*imageWidth/3, imageHeight, null);
				break;
			}
			if(mazeString.charAt(i) != '\n'){
				x1 += width/size;
				x2 += width/size;
			}
		}
	}
	
	public void addGameListner(GameListener gameListener) {
		this.listener = gameListener;
		
	}
	public void saveGameStatus() {
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter gameFilter = new FileNameExtensionFilter("Game Files (*.game)", "game");
		fileChooser.addChoosableFileFilter(gameFilter);
		fileChooser.setFileFilter(gameFilter);
		fileChooser.showSaveDialog(getParent());
		ObjectOutputStream os = null;
		try{
			if(fileChooser.getSelectedFile() != null){
				String file = fileChooser.getSelectedFile()+"";
				if(!file.endsWith(".game")){
					file += ".game";
				}
				os = new ObjectOutputStream(new FileOutputStream(file));
				os.writeObject(maze);
				os.close();
			}
		}
		catch(IOException e){
		}
	}

	public void setMaze(Maze maze) {
		this.maze = maze;
		repaint();
	}
	public Maze getMaze() {
		return maze;
	}
}
