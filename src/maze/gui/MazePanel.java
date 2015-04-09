package maze.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import maze.logic.Maze;

@SuppressWarnings("serial")
public class MazePanel extends JPanel{
	
	private static BufferedImage wall;
	private static BufferedImage door;
	private static BufferedImage grass;
	private static BufferedImage hero;
	private static BufferedImage dragon;
	private static BufferedImage sword;
	private static BufferedImage armedHero;
	private static BufferedImage sleepingDragon;
	private static BufferedImage darts;
	private static BufferedImage shield;
	private static BufferedImage heroShield;
	private static BufferedImage heroFullyArmed;
	private Maze maze;
	private GameListener listener;
	
	
	public MazePanel(Maze maze){
		this.maze = maze;
		
		setLayout(new GridLayout(11,11));
		setBackground(Color.BLACK);
		setFocusable(true);
		try{
			wall = ImageIO.read(new File("res/Box.jpg"));
			door = ImageIO.read(new File("res/Door.jpg"));
			grass = ImageIO.read(new File("res/Grass.jpg"));
			hero = ImageIO.read(new File("res/Hero.jpg"));
			dragon  = ImageIO.read(new File("res/Dragon.jpg"));
			sword = ImageIO.read(new File("res/Sword.jpg"));
			armedHero = ImageIO.read(new File("res/HeroSword.jpg"));
			sleepingDragon = ImageIO.read(new File("res/DragonSleep.jpg"));
			darts = ImageIO.read(new File("res/Darts.jpg"));
			shield = ImageIO.read(new File("res/Shield.jpg"));
			heroShield = ImageIO.read(new File("res/HeroShield.jpg"));
			heroFullyArmed = ImageIO.read(new File("res/HeroShieldSword.jpg"));
			
		}
		catch(IOException e){
			JOptionPane.showMessageDialog(this, e.getMessage());
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
			case 'd':
				g.drawImage(sleepingDragon, x1, y1, x2, y2, 0, 0, sleepingDragon.getWidth(), sleepingDragon.getHeight(), null);
				break;
			case 'O':
				g.drawImage(shield, x1, y1, x2, y2, 0, 0, shield.getWidth(), shield.getHeight(), null);
				break;
			case '/':
				g.drawImage(darts, x1, y1, x2, y2, 0, 0, darts.getWidth(), darts.getHeight(), null);
				break;
			case 'U':
				g.drawImage(heroFullyArmed, x1, y1, x2, y2, 0, 0, heroFullyArmed.getWidth(), heroFullyArmed.getHeight(), null);
				break;
			case 'Q':
				g.drawImage(heroShield, x1, y1, x2, y2, 0, 0, heroShield.getWidth(), heroShield.getHeight(), null);
				break;
			case '\n':
				x1 = 0;
				x2 = width/11;
				y1 += height/11;
				y2 += height/11;
				break;
			default:
				g.drawImage(dragon, x1, y1, x2, y2, 0, 0, dragon.getWidth(), dragon.getHeight(), null);
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
	public ArrayList<BufferedImage> getImages() {
		ArrayList<BufferedImage> imagens = new ArrayList<BufferedImage>();
		imagens.add(wall);
		imagens.add(door);
		imagens.add(grass);
		imagens.add(dragon);
		imagens.add(hero);
		imagens.add(shield);
		imagens.add(sword);
		return imagens;
	}
}
