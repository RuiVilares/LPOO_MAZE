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
	private final JButton next;

	private JLabel mode;
	private JLabel spitFire;
	private JLabel dragonBehavior;
	private JLabel numberOfDragons;
	private JLabel numberOfDarts;
	private JLabel mazeSize;
	private JSpinner sizeSpinner;
	private JSpinner dragonsSpinner;
	private JSpinner dartsSpinner;
	private String currentMode;
	private JComboBox<String> modeComboBox;
	private JComboBox<String> behaviorComboBox;
	private JComboBox<String> fireComboBox;
	
	
	public Definitions(Gui frame){
		this.frame = frame;
		
		setLayout(new GridLayout(7,2));
		setBackground(Color.WHITE);
		
		String[] modes = { "Classic Mode", "Random Mode", "Custom Mode"};
		String[] fire = { "Yes", "No"};
		String[] behavior = { "Igle", "Random Behavior", "Slepping Behavior"};
		
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
					else if(result.equals("Custom Mode"))
						updateToCustom();
					else
						updateToClassic();
			}	
		});
		
		behaviorComboBox = new JComboBox<String>(behavior);
		fireComboBox = new JComboBox<String>(fire);
		
		mode = new JLabel("Mode:");
		spitFire = new JLabel("Can the dragon spit fire: ");
		dragonBehavior = new JLabel("Choose dragon mode: ");
		mazeSize = new JLabel("Choose the maze size: ");
		SpinnerModel model1 = new SpinnerNumberModel(0,0,5,1);
		SpinnerModel model2 = new SpinnerNumberModel(1,1,5,1);
		SpinnerModel model3 = new SpinnerNumberModel(11,11,31,2);
		sizeSpinner = new JSpinner(model3);
		numberOfDarts = new JLabel("Choose number of darts: ");
		dartsSpinner = new JSpinner(model1);
		numberOfDragons = new JLabel("Choose number of dragons: ");
		dragonsSpinner = new JSpinner(model2);
		
		
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
			case "Slepping Behavior":
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
			int dartsNumber = (Integer)dartsSpinner.getValue();
			int size = (Integer)sizeSpinner.getValue();
			maze = new Maze(size,behavior,fire, dragonsNumber, dartsNumber);
			frame.maze(maze);
		}
		else if(((String)modeComboBox.getSelectedItem()).equals("Custom Mode")){
			int fire = 0;
			int behavior = 0;
			int size = (Integer)sizeSpinner.getValue();
			switch(((String)behaviorComboBox.getSelectedItem())){
			case "Igle":
				behavior = 1;
				break;
			case "Random Behavior":
				behavior = 2;
				break;
			case "Slepping Behavior":
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
			frame.interactiveBuilder(behavior,fire,size);
		}
		else{
			maze = new Maze();
			frame.maze(maze);
		}
	
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
		remove(next);
		if(currentMode == "Classic Mode"){
			add(mazeSize);
			add(sizeSpinner);
			add(dragonBehavior);
			add(behaviorComboBox);
			add(spitFire);
			add(fireComboBox);
			add(numberOfDragons);
			add(dragonsSpinner);
			add(numberOfDarts);
			add(dartsSpinner);
			
		}
		else{
			add(numberOfDragons);
			add(dragonsSpinner);
			add(numberOfDarts);
			add(dartsSpinner);
		}
		currentMode = "Random Mode";
		add(next);
		frame.pack();
		revalidate();
		repaint();
	}
	private void updateToCustom() {
		remove(next);
		if(currentMode == "Classic Mode"){
			remove(next);
			add(mazeSize);
			add(sizeSpinner);
			add(dragonBehavior);
			add(behaviorComboBox);
			add(spitFire);
			add(fireComboBox);
		}
		else{
			remove(numberOfDragons);
			remove(dragonsSpinner);
			remove(numberOfDarts);
			remove(dartsSpinner);
		}
		currentMode = "Custom Mode";
		add(next);
		frame.pack();
		revalidate();
		repaint();
	}


}
