package maze.gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import maze.logic.Maze;

@SuppressWarnings("serial")
public class MazeCostomBuilder extends JPanel {
	private JPanel images;
	private MazePanel maze;
	private char mazeArray[][];
	private int dragonBehavior;
	private int spitsFire;
	
	public MazeCostomBuilder(int dragonBehavior, int spitsFire, int size){
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
		images = maze.getImagePane();
		add(images, BorderLayout.EAST);
		//add(maze, BorderLayout.CENTER);
	}

	public void reloadMaze(){
		remove(maze);
		maze = new MazePanel(new Maze(dragonBehavior, spitsFire, mazeArray));
		add(maze, BorderLayout.CENTER);
		repaint();
	}
	
	
}
