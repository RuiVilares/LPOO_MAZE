package maze.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import data_structures.Pair;
import maze.logic.Maze;

@SuppressWarnings("serial")
public class MazeCustomPanel extends JPanel {
	
	private static final JButton wallButton = new JButton("Parede");
	private static final JButton grassButton = new JButton("Relva");
	private static final JButton heroButton = new JButton("Heroi");
	private static final JButton dragonButton = new JButton("Dragao");
	private static final JButton swordButton = new JButton("Espada");
	private static final JButton shieldButton = new JButton("Escudo");
	private static final JButton dartsButton = new JButton("Dardos");
	private static final JButton doorButton = new JButton("Porta");
	private static final JButton playButton = new JButton("Jogar");
	private static final JButton backButton = new JButton("Voltar");
	private char selectedPiece;
	private JPanel buttonsPanel;
	private MazePanel mazePanel;
	private char mazeArray[][];
	private int dragonBehavior;
	private int spitsFire;
	
	public MazeCustomPanel(int dragonBehavior, int spitsFire, final int size, final Gui frame){
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
		mazePanel = new MazePanel(new Maze(this.dragonBehavior, this.spitsFire, mazeArray));
		
		
		buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(9,1));
		buttonsPanel.setBackground(Color.BLACK);
		selectedPiece = '\0';
		
		wallButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				selectedPiece = 'X';
			}
			
		});
		buttonsPanel.add(wallButton);
		
		grassButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				selectedPiece = ' ';
			}
			
		});
		buttonsPanel.add(grassButton);
		
		heroButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				selectedPiece = 'H';
			}
			
		});
		buttonsPanel.add(heroButton);
		
		dragonButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				selectedPiece = 'D';
			}
			
		});
		buttonsPanel.add(dragonButton);
		
		swordButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				selectedPiece = 'E';
			}
			
		});
		buttonsPanel.add(swordButton);
		
		shieldButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				selectedPiece = 'O';
			}
			
		});
		buttonsPanel.add(shieldButton);
		
		dartsButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				selectedPiece = '/';
			}
			
		});
		buttonsPanel.add(dartsButton);
		
		doorButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				selectedPiece = 'S';
			}
			
		});
		buttonsPanel.add(doorButton);
		
		backButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				frame.definitions();
			}
			
		});
		buttonsPanel.add(backButton);
		
		playButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				frame.maze(mazePanel.getMaze());
			}
			
		});
		buttonsPanel.add(playButton);
		
		this.addMouseListener(new MouseListener(){
			private Pair hero = null;
			private Pair sword = null;
			private Pair exit = null;
			private Pair shield = null;
			@Override
			public void mouseClicked(MouseEvent e) {
				if(selectedPiece != '\0'){
					int y = e.getY() * 11 / mazePanel.getHeight();
					int x = e.getX() * 11 / mazePanel.getWidth();
					mazeArray[y][x] = selectedPiece;
					Pair pair = new Pair(x,y);
					if(pair.equals(hero)){
						hero = null;
					}
					else if(pair.equals(sword)){
						sword = null;
					}
					else if(pair.equals(exit)){
						exit = null;
					}
					else if(pair.equals(shield)){
						shield = null;
					}
					switch (selectedPiece){
					case 'H':
						if(hero != null){
							mazeArray[hero.getY()][hero.getX()] = ' ';
							hero.setX(x);
							hero.setY(y);
						}
						else{
							hero = new Pair(x,y);
						}
						break;
					case 'E':
						if(sword != null){
							mazeArray[sword.getY()][sword.getX()] = ' ';
							sword.setX(x);
							sword.setY(y);
						}
						else{
							sword = new Pair(x,y);
						}
						break;
					case 'S':
						if(exit != null){
							mazeArray[exit.getY()][exit.getX()] = ' ';
							exit.setX(x);
							exit.setY(y);
						}
						else{
							exit = new Pair(x,y);
						}
						break;
					case 'O':
						if(shield != null){
							mazeArray[shield.getY()][shield.getX()] = ' ';
							shield.setX(x);
							shield.setY(y);
						}
						else{
							shield = new Pair(x,y);
						}
						break;
					}
					
					reloadMaze();
				}

			}

			@Override
			public void mousePressed(MouseEvent e) {
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				
			}
			
		});
		add(mazePanel, BorderLayout.CENTER);
		add(buttonsPanel, BorderLayout.EAST);
		
	}

	public void reloadMaze(){
		mazePanel.setMaze(new Maze(dragonBehavior, spitsFire, mazeArray));
	}
	
	
}
