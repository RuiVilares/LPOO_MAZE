package maze.logic;

import java.util.Random;
import java.util.Stack;

import data_structures.*;

public class Board {
	private char[][] board;
	
	public Board(int size){
		board = new char[size][size];
		for(int k = 0; k < board.length; k++){
			for(int m = 0; m < board.length; m++){
				board[k][m] = 'X';
			}
		}
		generateLabirinto();
	}

private void generateLabirinto(){
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

	public char getCell(int x, int y){
		return board[y][x];
	}
	
	public int getSize(){
		return board.length;
	}
}