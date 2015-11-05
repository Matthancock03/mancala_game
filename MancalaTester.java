

public class MancalaTester
{


	public static void main(String[] args)
	{
		//testing if game works properly
		MancalaData mD = new MancalaData(4);
		MancalaBoard board = new MancalaBoard(mD);

		System.out.println("Player 1's turn");
		mD.pickPit(0,1);
		mD.pickPit(7,2);
		mD.pickPit(1,1);
		mD.pickPit(2,1);
		mD.pickPit(9,2);
		mD.pickPit(0,1);
		mD.pickPit(10,2);
		mD.pickPit(3,1);
		mD.pickPit(11,2);
		mD.pickPit(0,1);
		mD.pickPit(10,2);
		mD.pickPit(0,1);
		mD.pickPit(11,2);
		mD.pickPit(12,2);
		mD.pickPit(8,2);
		mD.pickPit(6,1);

		//test if ending the game works with player 1 having 8 consecutive turns
		System.out.println("\nTesting if ending the game works, player 1 given 8 consecutive turns");
		mD.pickPit(4,1);
		mD.pickPit(5,1);
		mD.pickPit(0,1);
		mD.pickPit(1,1);
		mD.pickPit(2,1);
		mD.pickPit(3,1);
		mD.pickPit(4,1);
		mD.pickPit(5,1);



		System.out.println();
		System.out.println("Player 1's Mancala: "+ mD.getMancala(1));
		System.out.println("Player 2's Mancala: "+ mD.getMancala(2));
		System.out.println(mD.checkWinner());
	}

}
