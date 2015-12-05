
import java.awt.Color;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;

/**
 * Mancala Project
 * CS 151 Fall 2015 - Section 02
 * 
 * 12/05/2015
 * Team NFG - Anthony Vo, Matthew Hancock, Thien Van
 *
 */

/**
 * Interface for each Board View/Controller
 *
 */
public interface BoardTemplate
{

	public void init(MancalaData d);
	public void stateChanged(ChangeEvent e);
	public MouseListener listener(final int x);
	public void initializePlayingArea(JPanel panel, int numStones);
	public void stylePanel(JLabel component, Color c);

}
