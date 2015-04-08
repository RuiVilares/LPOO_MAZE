package maze.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class EndPane extends JPanel {
	private boolean winner;
	private BufferedImage win;
	private BufferedImage lose;
	
	public EndPane(boolean winner, final Gui frame){
		this.winner = winner;
		setLayout(new BorderLayout());
		try{
			win = ImageIO.read(new File("res/win.gif"));
			lose = ImageIO.read(new File("res/loose.png"));
		}
		catch(IOException e){
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		this.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.mainMenu();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				
			}
			
		});
		this.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent arg0) {
				frame.mainMenu();
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				
			}
			
		});
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		if(winner)
			g.drawImage(win, 0, 0, getWidth(), getHeight(), 0, 0, win.getWidth(), win.getHeight(), null);
		else
			g.drawImage(lose, 0, 0, getWidth(), getHeight(), 0, 0, lose.getWidth(), lose.getHeight(), null);
	}
}
