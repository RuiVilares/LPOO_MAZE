package maze.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class SettingsDialog extends JDialog {
	JLabel keyUp;
	JLabel keyDown;
	JLabel keyLeft;
	JLabel keyRight;
	JLabel keySpecial;
	Keys keys;
	
	public SettingsDialog(Keys keys) {
		setLocationRelativeTo(null);
		setTitle("Definicoes");
		setModal(true);
		setBackground(Color.BLACK);
		setLayout(new GridLayout(5,3));
		
		this.keys = keys;
		
		keyUp = new JLabel (KeyEvent.getKeyText(keys.getKeyUp()));
		keyLeft = new JLabel (KeyEvent.getKeyText(keys.getKeyLeft()));
		keyDown = new JLabel (KeyEvent.getKeyText(keys.getKeyDown()));
		keyRight = new JLabel (KeyEvent.getKeyText(keys.getKeyRight()));
		keySpecial = new JLabel (KeyEvent.getKeyText(keys.getSpecialKey()));
		
		add(new JLabel ("MOVIMENTAR PARA CIMA:\t\t"));
		add(keyUp);
		JButton buttonUp = new JButton("Modificar");
		buttonUp.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				final JDialog dialog = new JDialog();
				dialog.setLocationRelativeTo(null);
				dialog.setModal(true);
				dialog.add(new JLabel("Press the new key!"));
				dialog.addKeyListener(new KeyListener(){

					@Override
					public void keyTyped(KeyEvent e) {
						
					}

					@Override
					public void keyPressed(KeyEvent e) {
						getKeys().setKeyUp(e.getKeyCode());
						keyUp.setText(KeyEvent.getKeyText(getKeys().getKeyUp()));
						dialog.dispose();
					}

					@Override
					public void keyReleased(KeyEvent e) {
						
					}
					
				});
				dialog.pack();
				dialog.setVisible(true);
			}
			
		});
		add(buttonUp);
		
		
		add(new JLabel ("MOVIMENTAR PARA BAIXO:\t\t"));
		add(keyDown);
		JButton buttonDown =new JButton("Modificar");
		buttonDown.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				final JDialog dialog = new JDialog();
				dialog.setLocationRelativeTo(null);
				dialog.setModal(true);
				dialog.add(new JLabel("Press the new key!"));
				dialog.addKeyListener(new KeyListener(){

					@Override
					public void keyTyped(KeyEvent e) {

					}

					@Override
					public void keyPressed(KeyEvent e) {
						getKeys().setKeyDown(e.getKeyCode());
						keyDown.setText(KeyEvent.getKeyText(getKeys().getKeyDown()));
						dialog.dispose();
					}

					@Override
					public void keyReleased(KeyEvent e) {

					}

				});
				dialog.pack();
				dialog.setVisible(true);
			}
			
		});
		add(buttonDown);
		
		add(new JLabel ("MOVIMENTAR PARA A ESQUERDA:\t\t"));
		add(keyLeft);
		JButton buttonLeft =new JButton("Modificar");
		buttonLeft.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				final JDialog dialog = new JDialog();
				dialog.setLocationRelativeTo(null);
				dialog.setModal(true);
				dialog.add(new JLabel("Press the new key!"));
				dialog.addKeyListener(new KeyListener(){

					@Override
					public void keyTyped(KeyEvent e) {

					}

					@Override
					public void keyPressed(KeyEvent e) {
						getKeys().setKeyLeft(e.getKeyCode());
						keyLeft.setText(KeyEvent.getKeyText(getKeys().getKeyLeft()));
						dialog.dispose();
					}

					@Override
					public void keyReleased(KeyEvent e) {

					}

				});
				dialog.pack();
				dialog.setVisible(true);
			}
			
		});
		add(buttonLeft);
		
		add(new JLabel ("MOVIMENTAR PARA A DIREITA:\t\t"));
		add(keyRight);
		JButton buttonRight =new JButton("Modificar");
		buttonRight.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				final JDialog dialog = new JDialog();
				dialog.setLocationRelativeTo(null);
				dialog.setModal(true);
				dialog.add(new JLabel("Press the new key!"));
				dialog.addKeyListener(new KeyListener(){

					@Override
					public void keyTyped(KeyEvent e) {

					}

					@Override
					public void keyPressed(KeyEvent e) {
						getKeys().setKeyRight(e.getKeyCode());
						keyRight.setText(KeyEvent.getKeyText(getKeys().getKeyRight()));
						dialog.dispose();
					}

					@Override
					public void keyReleased(KeyEvent e) {

					}

				});
				dialog.pack();
				dialog.setVisible(true);
			}
			
		});
		add(buttonRight);
		
		add(new JLabel ("COMBINAR PARA LANCAR DARDO (CTRL OU ALT):\t\t"));
		add(keySpecial);
		JButton buttonSpecial = new JButton("Trocar");
		buttonSpecial.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				final JDialog dialog = new JDialog();
				dialog.setLocationRelativeTo(null);
				dialog.setModal(true);
				dialog.add(new JLabel("Press the new key!"));
				dialog.addKeyListener(new KeyListener(){

					@Override
					public void keyTyped(KeyEvent e) {

					}

					@Override
					public void keyPressed(KeyEvent e) {
						getKeys().setSpecialKey(e.getKeyCode());
						keySpecial.setText(KeyEvent.getKeyText(getKeys().getSpecialKey()));
						dialog.dispose();
					}

					@Override
					public void keyReleased(KeyEvent e) {

					}

				});
				dialog.pack();
				dialog.setVisible(true);
				
			}
			
		});
		add(buttonSpecial);
		pack();
		setVisible(true);
	}

	public Keys getKeys() {
		return keys;
	}
	
}
