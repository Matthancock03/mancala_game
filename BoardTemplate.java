
import java.awt.Color;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;

public interface BoardTemplate
{

	public void init(MancalaData d);
	public void stateChanged(ChangeEvent e);
	public MouseListener listener(final int x);
	public void initializePlayingArea(JPanel panel, int numStones);
	public void stylePanel(JLabel component, Color c);

}
