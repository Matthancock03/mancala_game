
/**
 * Mancala Project
 * CS 151 Fall 2015 - Section 02
 * 
 * 12/05/2015
 * Team NFG - Anthony Vo, Matthew Hancock, Thien Van
 *
 */
public class MancalaTester2
{


	public static void main(String[] args)
	{
		MancalaData mD = new MancalaData();

		SelectGUI sg = new SelectGUI();

		mD.attach(sg);						// initialize GUI with Board choice
	}

}
