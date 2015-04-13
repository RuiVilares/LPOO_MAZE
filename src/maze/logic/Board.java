package maze.logic;

import java.io.Serializable;
import java.util.Random;
import java.util.Stack;

import data_structures.*;

/**
 * 
 * Class to create a default or a random board.
 * 
 * @author Diogo Trindade
 * @author Rui Vilares
 * 
 */

@SuppressWarnings("serial")
public class Board implements Serializable{
	/** Matrix with elements */
	private char[][] board;
	/** Size of the board */
	private int size;

	/**
	 * Constructs and initializes a default board.
	 * 
	 */
	
	public Board() {
		size = 10;
		board = new char[][]{
				{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
				{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
				{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', 'X', ' ', 'X' },
				{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
				{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
				{ 'X', ' ', 'X', 'X', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' }};
	}
	
	/**
	 * Constructs and initializes a specific board.
	 * 
	 * @param array
	 * 			the matrix with a specific board 
	 */
	
	public Board(char [][] array){
		size = array.length;
		board = array;
	}
	
	/**
	 * Constructs and initializes a random board.
	 * 
	 * @param size
	 * 			the size of the board(need be a odd number)
	 */
	
	public Board(int size) {
		this.size = size;
		board = new char[size][size];
		for(int k = 0; k < board.length; k++){
			for(int m = 0; m < board.length; m++){
				board[k][m] = 'X';
			}
		}
		generateBoard();
	}

	/**
	 * Function to construct a random board.
	 */
	private void generateBoard(){
		int x,y;
		Random r = new Random();
		Stack<Pair> stack = new Stack<Pair>();
		char visited[][] = new char[(board.length-1)/2][(board.length-1)/2];
		for(int k = 0; k < board.length; k++){
			for(int m = 0; m < board.length; m++){
				if(k % 2 != 0 && m % 2 != 0)
					board[k][m] = ' ';
			}
		}
		for(int k = 0; k < (board.length-1)/2; k++){
			for(int m = 0; m < (board.length-1)/2; m++){
				visited[k][m] = '.';
			}
		}
		do{
			x = r.nextInt(board.length);
		}while(x%2==0);

		do{
			y = r.nextInt(board.length);
		}while(y%2==0);
		Pair p1 = new Pair((x-1)/2,(y-1)/2);
		stack.push(p1);
		visited[p1.getY()][p1.getX()] = '+';
		while(!stack.empty()){
			int dir = r.nextInt(4);
			switch(dir){
			case 0:
				if(p1.getY() - 1 >= 0 && visited[p1.getY()-1][p1.getX()] == '.'){
					board[p1.getY()*2][p1.getX()*2 +1] = ' ';
					p1.setY(p1.getY()-1);
					visited[p1.getY()][p1.getX()] = '+';
					Pair p2 = new Pair(0,0);
					p2.copy(p1);
					stack.push(p2);
				}
				break;
			case 1:
				if(p1.getY() + 1 < (board.length-1)/2 && visited[p1.getY()+1][p1.getX()] == '.'){
					board[p1.getY()*2 + 2][p1.getX()*2 + 1] = ' ';
					p1.setY(p1.getY()+1);
					visited[p1.getY()][p1.getX()] = '+';
					Pair p2 = new Pair(0,0);
					p2.copy(p1);
					stack.push(p2);
				}
				break;
			case 2:
				if(p1.getX() - 1 >= 0 && visited[p1.getY()][p1.getX()-1] == '.'){
					board[p1.getY()*2+1][p1.getX()*2] = ' ';
					p1.setX(p1.getX()-1);
					visited[p1.getY()][p1.getX()] = '+';
					Pair p2 = new Pair(0,0);
					p2.copy(p1);
					stack.push(p2);
				}
				break;
			case 3:
				if(p1.getX() + 1 < (board.length-1)/2 && visited[p1.getY()][p1.getX()+1] == '.'){
					board[p1.getY()*2 + 1][p1.getX()*2 + 2] = ' ';
					p1.setX(p1.getX()+1);
					visited[p1.getY()][p1.getX()] = '+';
					Pair p2 = new Pair(0,0);
					p2.copy(p1);
					stack.push(p2);
				}
				break;
			}
			while(!checkOptions(visited,(board.length-1)/2,p1)){
				try{
					stack.pop();
					p1.copy(stack.peek());
				}
				catch(Exception e){
					break;
				}
			}
		}

	}
	
	/**
	 * Auxiliary function to create a random board.
	 * 
	 * @param array
	 * 			matrix with visited and unvisited positions
	 * @param size
	 * 			matrix size
	 * @param p1
	 * 			pair with X and Y positions 
	 * @return true if p1 adjacent position equals to ".", false otherwise.
	 */
	private boolean checkOptions(char[][] array, int size, Pair p1){
		if(p1.getX()+1 < size && array[p1.getY()][p1.getX()+1] == '.'){
			return true;
		}
		if(p1.getX()-1 >= 0 && array[p1.getY()][p1.getX()-1] == '.'){
			return true;
		}
		if(p1.getY()+1 < size && array[p1.getY()+1][p1.getX()] == '.'){
			return true;
		}
		if(p1.getY()-1 >= 0 && array[p1.getY()-1][p1.getX()] == '.'){
			return true;
		}
		return false;
	}
	
	/**
	 * Get char in a determined position.
	 * 
	 * @param x
	 * 			X position
	 * @param y
	 * 			Y position
	 * @return char contained in the given position.
	 */
	
	public char getCell(int x, int y) {
		return board[y][x];
	}

	/**
	 * Get board size.
	 * 
	 * @return Integer value of the board size.
	 */
	
	public int getSize() {
		return size;
	}
	
	/**
	 * Checks if the walls are well placed to play (there are no "holes" on the borders).
	 * 
	 * @return Returns true if the board is playable and false otherwise.
	 */
	public boolean isViable(){
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board.length; j++){
				if(i == 0 || i == size-1){
					if(board[i][j] != 'X'){
						return false;
					}
				}
				else if(j == 0 || j == size-1){
					if(board[i][j] != 'X'){
						return false;
					}
				}
			}
		}
		return true;
	}
}
