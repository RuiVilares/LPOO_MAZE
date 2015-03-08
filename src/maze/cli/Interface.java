package maze.cli;

import java.util.Scanner;

public class Interface {
	private Scanner reader;
	
	public Interface(){
		reader = new Scanner(System.in);
	}
	
	public char readChar(){
		char read;
		System.out.print("> ");
		read = reader.next().charAt(0);
		return read;
	}
	public void finalize(){
		reader.close();
	}
	public void printString(String str){
		System.out.print(str);
	}
	public void printWinningMessage(){
		System.out.println("\t\t\t\t YOU WIN !!!!!! \n \n \n \n \n \n \n \n \n");
	}
	public void printLoosingMessage(){
		System.out.println("\t\t\t\t YOU WIN !!!!!! \n \n \n \n \n \n \n \n \n");
	}
}
