
public class MancalaTester2
{


	public static void main(String[] args)
	{
		MancalaData mD = new MancalaData();

		SelectGUI sg = new SelectGUI();

		mD.attach(sg);						// initialize GUI with Board choice
	}

}
