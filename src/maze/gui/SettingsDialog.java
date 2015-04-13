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
import java.awt.Font;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class SettingsDialog extends JDialog {
	JLabel keyUp;
	JLabel keyDown;
	JLabel keyLeft;
	JLabel keyRight;
	JLabel keySpecial;
	Keys keys;
	
	public SettingsDialog(Keys keys) {
		setLocationRelativeTo(getParent());
		setTitle("Definicoes");
		setModal(true);
		setBackground(Color.BLACK);
		getContentPane().setLayout(new GridLayout(5,3));
		
		this.keys = keys;
		
		keyUp = new JLabel (KeyEvent.getKeyText(keys.getKeyUp()));
		keyUp.setHorizontalAlignment(SwingConstants.CENTER);
		keyUp.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		keyLeft = new JLabel (KeyEvent.getKeyText(keys.getKeyLeft()));
		keyLeft.setHorizontalAlignment(SwingConstants.CENTER);
		keyLeft.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		keyDown = new JLabel (KeyEvent.getKeyText(keys.getKeyDown()));
		keyDown.setHorizontalAlignment(SwingConstants.CENTER);
		keyDown.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		keyRight = new JLabel (KeyEvent.getKeyText(keys.getKeyRight()));
		keyRight.setHorizontalAlignment(SwingConstants.CENTER);
		keyRight.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		keySpecial = new JLabel (KeyEvent.getKeyText(keys.getSpecialKey()));
		keySpecial.setHorizontalAlignment(SwingConstants.CENTER);
		keySpecial.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		
		JLabel label = new JLabel ("MOVIMENTAR PARA CIMA:\t\t");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Dialog", Font.PLAIN, 10));
		getContentPane().add(label);
		getContentPane().add(keyUp);
		JButton buttonUp = new JButton("Modificar");
		buttonUp.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				final JDialog dialog = new JDialog();
				dialog.setLocationRelativeTo(getParent());
				dialog.setModal(true);
				JLabel pressKey = new JLabel("Press the new key!");
				pressKey.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
				dialog.getContentPane().add(pressKey);
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
		getContentPane().add(buttonUp);
		
		
		JLabel label_1 = new JLabel ("MOVIMENTAR PARA BAIXO:\t\t");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Dialog", Font.PLAIN, 10));
		getContentPane().add(label_1);
		getContentPane().add(keyDown);
		JButton buttonDown =new JButton("Modificar");
		buttonDown.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				final JDialog dialog = new JDialog();
				dialog.setLocationRelativeTo(getParent());
				dialog.setModal(true);
				JLabel pressKey = new JLabel("Press the new key!");
				pressKey.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
				dialog.getContentPane().add(pressKey);
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
		getContentPane().add(buttonDown);
		
		JLabel label_2 = new JLabel ("MOVIMENTAR PARA A ESQUERDA:\t\t");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Dialog", Font.PLAIN, 10));
		getContentPane().add(label_2);
		getContentPane().add(keyLeft);
		JButton buttonLeft =new JButton("Modificar");
		buttonLeft.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				final JDialog dialog = new JDialog();
				dialog.setLocationRelativeTo(getParent());
				dialog.setModal(true);
				JLabel pressKey = new JLabel("Press the new key!");
				pressKey.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
				dialog.getContentPane().add(pressKey);
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
		getContentPane().add(buttonLeft);
		
		JLabel label_3 = new JLabel ("MOVIMENTAR PARA A DIREITA:\t\t");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("Dialog", Font.PLAIN, 10));
		getContentPane().add(label_3);
		getContentPane().add(keyRight);
		JButton buttonRight =new JButton("Modificar");
		buttonRight.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				final JDialog dialog = new JDialog();
				dialog.setLocationRelativeTo(getParent());
				dialog.setModal(true);
				JLabel pressKey = new JLabel("Press the new key!");
				pressKey.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
				dialog.getContentPane().add(pressKey);
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
		getContentPane().add(buttonRight);
		
		JLabel label_4 = new JLabel ("COMBINAR PARA LANCAR DARDO (CTRL OU ALT):\t\t");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("Dialog", Font.PLAIN, 10));
		getContentPane().add(label_4);
		getContentPane().add(keySpecial);
		JButton buttonSpecial = new JButton("Modificar");
		buttonSpecial.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				final JDialog dialog = new JDialog();
				dialog.setLocationRelativeTo(getParent());
				dialog.setModal(true);
				JLabel pressKey = new JLabel("Press the new key!");
				pressKey.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
				dialog.getContentPane().add(pressKey);
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
		getContentPane().add(buttonSpecial);
		pack();
		setVisible(true);
	}

	public Keys getKeys() {
		return keys;
	}
	
}
