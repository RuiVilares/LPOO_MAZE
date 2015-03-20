package maze.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import maze.logic.Maze;

public class MazeFrame extends JPanel{
	

	private static final long serialVersionUID = 7838575124151753641L;
	private ImageIcon wall;
	private ImageIcon door;
	private ImageIcon grass;
	private ImageIcon hero;
	private ImageIcon dragon;
	private ImageIcon sword;
	private ImageIcon armedHero;
	private Maze maze;
	private Gui frame;
	
	public MazeFrame(Maze maze, Gui frame){
		this.frame = frame;
		this.maze = maze;
		
		setLayout(new GridLayout(11,11));
		setBackground(Color.BLACK);
		
		wall = new ImageIcon(getClass().getResource("../../res/muro.png"));
		door = new ImageIcon(getClass().getResource("../../res/door.png"));
		grass = new ImageIcon(getClass().getResource("../../res/relva.png"));
		hero = new ImageIcon(getClass().getResource("../../res/hero.png"));
		dragon  = new ImageIcon(getClass().getResource("../../res/dragon.png"));
		sword = new ImageIcon(getClass().getResource("../../res/sword.png"));
		armedHero = new ImageIcon(getClass().getResource("../../res/armedHero.png"));
	}
	public void updateMaze(char cmd){
		maze.update(cmd);
		System.out.print(maze);
		if(maze.isDone()){
			frame.getDefinitions();
		}
		drawMaze();
	}
	
	public void drawMaze(){
		removeAll();
		String mazeString = maze + "";
		for(int i = 0; i < mazeString.length(); i++){
			switch(mazeString.charAt(i)){
			case 'X':
				JLabel wallLabel = new JLabel(wall);
				add(wallLabel);
				break;
			case ' ':
				JLabel grassLabel = new JLabel(grass);
				add(grassLabel);
				break;
			case 'H':
				JLabel heroLabel = new JLabel(hero);
				add(heroLabel);
				break;
			case 'D':
				JLabel dragonLabel = new JLabel(dragon);
				add(dragonLabel);
				break;
			case 'E':
				JLabel swordLabel = new JLabel(sword);
				add(swordLabel);
				break;
			case 'S':
				JLabel exitLabel = new JLabel(door);
				add(exitLabel);
				break;
			case 'A':
				JLabel armedHeroLabel = new JLabel(armedHero);
				add(armedHeroLabel);
				break;
			case '\n':
				break;
			default:
				JLabel label = new JLabel(grass);
				add(label);
				break;
			}
		}
		revalidate();
		repaint();
	}
	

}
