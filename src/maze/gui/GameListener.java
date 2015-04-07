package maze.gui;

import java.util.EventListener;

interface GameListener extends EventListener {
    public void gameDone(GameEvent e);
}
