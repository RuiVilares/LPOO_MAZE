package maze.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class WinPane extends JPanel {
	private BufferedImage win;
	
	public WinPane(){
		setLayout(new BorderLayout());
		try{
			win = ImageIO.read(new File("res/win.gif"));
		}
		catch(IOException e){
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.GRAY);
		g.drawImage(win, 0, 0, getWidth(), getHeight(), 0, 0, win.getWidth(), win.getHeight(), null);
	}
}
