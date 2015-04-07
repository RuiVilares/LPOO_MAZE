package maze.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import maze.logic.Maze;

@SuppressWarnings("serial")
public class Definitions extends JPanel{
	
	private Gui frame;
	private JButton next;
	private String currentMode;
	private JLabel mode;
	private JLabel spitFire;
	private JLabel dragonBehavior;
	private JLabel numberOfDragons;
	private JSpinner dragonsSpinner;
	private JComboBox<String> modeComboBox;
	private JComboBox<String> behaviorComboBox;
	private JComboBox<String> fireComboBox;
	
	
	public Definitions(Gui frame){
		this.frame = frame;
		
		setLayout(new GridLayout(5,2));
		setBackground(Color.GRAY);
		
		String[] modes = { "Classic Mode", "Random Mode"};
		String[] fire = { "Yes", "No"};
		String[] behavior = { "Igle", "Random Behavior", "Sleppin Behavior"};
		
		currentMode = "Classic Mode";
		modeComboBox = new JComboBox<String>(modes);
		modeComboBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unchecked")
				JComboBox<String> cb = (JComboBox<String>)e.getSource();
				String result = ((String)cb.getSelectedItem());
				if(!result.equals(currentMode))
					if(result.equals("Random Mode"))
						updateToRandom();
					else
						updateToClassic();
			}	
		});
		
		behaviorComboBox = new JComboBox<String>(behavior);
		fireComboBox = new JComboBox<String>(fire);
		
		mode = new JLabel("Mode:");
		spitFire = new JLabel("Can the dragon spit fire: ");
		dragonBehavior = new JLabel("Choose dragon mode: ");
		numberOfDragons = new JLabel("Choose number of dragons: ");
		SpinnerModel model = new SpinnerNumberModel(1,1,5,1);
		dragonsSpinner = new JSpinner(model);
		
		
		next = new JButton("Next");
		next.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
		    {
				next();
		    }

		});
		next.setPreferredSize(new Dimension(100,50));
		add(mode);
		add(modeComboBox);
		add(next);
	}


	private void next() {
		Maze maze;
		if(((String)modeComboBox.getSelectedItem()).equals("Random Mode")){
			int fire = 0;
			int behavior = 0;
			switch(((String)behaviorComboBox.getSelectedItem())){
			case "Igle":
				behavior = 1;
				break;
			case "Random Behavior":
				behavior = 2;
				break;
			case "Sleppin Behavior":
				behavior = 3;
				break;
			}
			switch(((String)fireComboBox.getSelectedItem())){
			case "Yes":
				fire = 1;
				break;
			case "No":
				fire = 2;
				break;
			}
			int dragonsNumber = (Integer)dragonsSpinner.getValue();
			maze = new Maze(behavior,fire, dragonsNumber);
		}
		else
			maze = new Maze();
		
		frame.maze(maze);
	}

	private void updateToClassic() {
		currentMode = "Classic Mode";
		removeAll();
		add(mode);
		add(modeComboBox);
		add(next);
		frame.pack();
		revalidate();
		repaint();
	}


	private void updateToRandom() {
		currentMode = "Random Mode";
		remove(next);
		add(dragonBehavior);
		add(behaviorComboBox);
		add(spitFire);
		add(fireComboBox);
		add(numberOfDragons);
		add(dragonsSpinner);
		add(next);
		frame.pack();
		revalidate();
		repaint();
	}


}
