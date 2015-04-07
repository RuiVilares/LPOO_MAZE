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

import maze.logic.Maze;

@SuppressWarnings("serial")
public class MazePanel extends JPanel{
	
	private BufferedImage wall;
	private BufferedImage door;
	private BufferedImage grass;
	private BufferedImage hero;
	private BufferedImage dragon;
	private BufferedImage sword;
	private BufferedImage armedHero;
	private Maze maze;
	private GameListener listener;
	
	public MazePanel(Maze maze){
		this.maze = maze;
		
		setLayout(new GridLayout(11,11));
		setBackground(Color.BLACK);
		setFocusable(true);
		try{
			wall = ImageIO.read(new File("res/muro.png"));
			door = ImageIO.read(new File("res/door.png"));
			grass = ImageIO.read(new File("res/relva.png"));
			hero = ImageIO.read(new File("res/hero.png"));
			dragon  = ImageIO.read(new File("res/dragon.png"));
			sword = ImageIO.read(new File("res/sword.png"));
			armedHero = ImageIO.read(new File("res/armedHero.png"));
		}
		catch(IOException e){
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}
	public void updateMaze(char cmd){
		maze.update(cmd);
		if(maze.isDone()){
			listener.gameDone(new GameEvent("done",0));;
		}
		repaint();
	}

	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		String mazeString = maze + "";
		int height = getHeight();
		int width = getWidth();
		int x1 = 0;
		int x2 = width/11;
		int y1 = 0;
		int y2 = height/11;
		for(int i = 0; i < mazeString.length(); i++){
			switch(mazeString.charAt(i)){
			case 'X':
				g.drawImage(wall, x1, y1, x2, y2, 0, 0, wall.getWidth(), wall.getHeight(), null);
				break;
			case ' ':
				g.drawImage(grass, x1, y1, x2, y2, 0, 0, grass.getWidth(), grass.getHeight(), null);
				break;
			case 'H':
				g.drawImage(hero, x1, y1, x2, y2, 0, 0, hero.getWidth(), hero.getHeight(), null);
				break;
			case 'D':
				g.drawImage(dragon, x1, y1, x2, y2, 0, 0, dragon.getWidth(), dragon.getHeight(), null);
				break;
			case 'E':
				g.drawImage(sword, x1, y1, x2, y2, 0, 0, sword.getWidth(), sword.getHeight(), null);
				break;
			case 'S':
				g.drawImage(door, x1, y1, x2, y2, 0, 0, door.getWidth(), door.getHeight(), null);
				break;
			case 'A':
				g.drawImage(armedHero, x1, y1, x2, y2, 0, 0, armedHero.getWidth(), armedHero.getHeight(), null);
				break;
			case '\n':
				x1 = 0;
				x2 = width/11;
				y1 += height/11;
				y2 += height/11;
				break;
			default:
				g.drawImage(grass, x1, y1, x2, y2, 0, 0, grass.getWidth(), grass.getHeight(), null);
				break;
			}
			if(mazeString.charAt(i) != '\n'){
				x1 += width/11;
				x2 += width/11;
			}
		}
	}
	
	public void addGameListner(GameListener gameListener) {
		this.listener = gameListener;
		
	}
	public void saveGameStatus() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.showSaveDialog(getParent());
		ObjectOutputStream os = null;
		try{
			os = new ObjectOutputStream(new FileOutputStream(fileChooser.getSelectedFile() + ".dat"));
			os.writeObject(maze);
			os.close();
		}
		catch(IOException e){
		}
	}

}
