
public class MancalaTester2
{


	public static void main(String[] args)
	{
		//testing if game works properly
		MancalaData mD = new MancalaData(4);
		MancalaBoard board = new MancalaBoard(mD);

		mD.attach(board);
	}

}
