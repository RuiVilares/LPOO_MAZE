package maze.gui;
import java.awt.AWTEvent;


@SuppressWarnings("serial")
public class GameEvent extends AWTEvent{

	public GameEvent(Object source, int id) {
		super(source, id);
	}

}
