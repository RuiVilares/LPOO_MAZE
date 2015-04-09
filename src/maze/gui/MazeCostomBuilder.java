package maze.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;



import maze.logic.Maze;

@SuppressWarnings("serial")
public class MazeCostomBuilder extends JPanel {
	
	static final JButton wall = new JButton("Parede");
	static final JButton grass = new JButton("Relva");
	static final JButton hero = new JButton("Heroi");
	static final JButton dragon = new JButton("Dragao");
	static final JButton sword = new JButton("Espada");
	static final JButton shield = new JButton("Escudo");
	static final JButton darts = new JButton("Dardos");
	static final JButton door = new JButton("Porta");
	private JPanel buttons;
	private MazePanel maze;
	private char mazeArray[][];
	private int dragonBehavior;
	private int spitsFire;
	
	public MazeCostomBuilder(int dragonBehavior, int spitsFire, int size){
		setPreferredSize(new Dimension(500,500));
		setLayout(new BorderLayout());
		this.spitsFire = spitsFire;
		this.dragonBehavior = dragonBehavior;
		mazeArray = new char[size][size];
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				if(i == 0 || i == size - 1){
					mazeArray[i][j] = 'X';
				}
				else if(j == 0 || j == size - 1){
					mazeArray[i][j] = 'X';
				}
				else
					mazeArray[i][j] = ' ';
			}
		}
		maze = new MazePanel(new Maze(this.dragonBehavior, this.spitsFire, mazeArray));
		buttons = new JPanel();
		buttons.setLayout(new GridLayout(8,1));
		buttons.setBackground(Color.BLACK);
		buttons.setSize(500, getHeight());
		buttons.add(wall);
		buttons.add(grass);
		buttons.add(hero);
		buttons.add(dragon);
		buttons.add(sword);
		buttons.add(shield);
		buttons.add(darts);
		buttons.add(door);
		
		add(maze, BorderLayout.CENTER);
		add(buttons, BorderLayout.EAST);
	}

	public void reloadMaze(){
		remove(maze);
		maze = new MazePanel(new Maze(dragonBehavior, spitsFire, mazeArray));
		add(maze);
		repaint();
	}
	
	
}
