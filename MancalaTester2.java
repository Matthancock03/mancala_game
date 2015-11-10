package mancala_game;


public class MancalaTester2
{


	public static void main(String[] args)
	{
		//testing if game works properly
		MancalaData mD = new MancalaData();
		MancalaBoard board = new MancalaBoard(mD);

		mD.attach(board);
	}

}
