import java.util.ArrayList;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MancalaData
{

	int[] board;
	int stonesPerPit;
	int player1;
	int player2;

	int activePlayer = 1;					//T: set default active player
	ArrayList<ChangeListener> listeners;	//T: add listeners to datamodel

	public MancalaData(int sPP)
	{
		listeners = new ArrayList<ChangeListener>();

		board = new int[14];
		stonesPerPit = sPP;
		player1 = 1;
		player2 = 2;
		for (int i = 0; i < board.length; i++)
		{ // Player 1's Mancala at position 6, and Player 2's at 13.
			if ((i == (board.length - 1) / 2) || (i == board.length - 1))
			{
				board[i] = 0;
			} else
			{
				board[i] = stonesPerPit;
			}
		}
	}

	/**
	 * Simulates picking a pit from the board.
	 *
	 * @param p
	 * @param player
	 * @return boolean to see if valid move.
	 */
	public int pickPit(int p, int player)
	{
		int stonesLeft = board[p];
		if (p == 6)
		{
			System.out.println("Cannot pick from player 1's Mancala, choose another pit player " + player);
			return player;
		} else if (p == 13)
		{
			System.out.println("Cannot pick from player 2's Mancala, choose another pit player " + player);
			return player;
		}
		if (stonesLeft == 0)
		{
			System.out.println("Pit is empty, choose another pit player " + player);
			return player;
		}

		int position = p + 1;
		while (stonesLeft != 0)
		{ // if player 1 reached player 2's Mancala, go back to pit 0.
			if ((player == 1) && ((position) == board.length - 1))
			{
				position = 0;
				continue;
			}
			// if player 2 reaches player 1's Mancala, go to the next pit which
			// is 7.
			else if ((player == 2) && ((position) == (board.length - 1) / 2))
			{
				position++;
				continue;
			}
			stonesLeft--;
			// if last stone is in own player's Mancala, return current player
			// for next player.
			if ((stonesLeft == 0) && (player == 1) && (position == ((board.length - 1) / 2)))
			{
				board[position]++;
				board[p] = 0;
				testContent();
				System.out.println("Last stone in current player's Mancala, player " + player + "'s turn again");
				if (checkGameEnd())
				{

					System.out.println("Game has ended, check for winner.");
					testContent();
					return 0;
				}
				return player;
			}

			else if ((stonesLeft == 0) && (player == 2) && (position == (board.length - 1)))
			{
				board[position]++;
				board[p] = 0;
				testContent();
				System.out.println("Last stone in current player's Mancala, player " + player + "'s turn again");
				if (checkGameEnd())
				{

					System.out.println("Game has ended, check for winner.");
					testContent();
					return 0;
				}
				return player;
			}
			// if last stone placed is in empty pit of current player, take
			// stones of opposing pit.
			else if ((stonesLeft == 0) && (player == 1) && (board[position] == 0) && (position < 6))
			{

				System.out.println("Last stone in current player's empty pit, steal from other player's directly opposing pit");
				board[(board.length - 1) / 2]++;
				board[(board.length - 1) / 2] += board[12 - position];

				board[p] = 0;
				board[12 - position] = 0;
				testContent();
				if (checkGameEnd())
				{

					System.out.println("Game has ended, check for winner.");
					testContent();
					return 0;
				}
				System.out.println("Player " + ((player == 2) ? 1 : 2) + "'s turn");

				if (player == 2)
					activePlayer = (player + 1) % 2;
				return (player == 2) ? 1 : 2;
			} else if ((stonesLeft == 0) && (player == 2) && (board[position] == 0) && (position > 6))
			{

				System.out.println("Last stone in current player's empty pit, steal from other player's directly opposing pit");

				board[board.length - 1]++;
				board[board.length - 1] += board[12 - position];

				board[p] = 0;
				board[12 - position] = 0;
				testContent();
				if (checkGameEnd())
				{

					System.out.println("Game has ended, check for winner.");
					testContent();
					return 0;
				}
				System.out.println("Player " + ((player == 2) ? 1 : 2) + "'s turn");
				if (player == 2)
					activePlayer = (player + 1) % 2;
				return (player == 2) ? 1 : 2;
			}
			board[position]++;

			// if reached end of board, go back to postion 0.
			if (position + 1 == board.length)
			{
				position = 0;
				continue;
			}

			position++;

		}
		board[p] = 0;
		testContent();
		if (checkGameEnd())
		{

			System.out.println("Game has ended, check for winner.");
			testContent();
			return 0;
		}
		System.out.println("Player " + ((player == 2) ? 1 : 2) + "'s turn");
		if (player == 2)
			activePlayer = (player + 1) % 2;
		return (player == 2) ? 1 : 2;
	}

	public int getMancala(int player)
	{
		if (player == 1)
		{
			return board[(board.length - 1) / 2];
		} else if (player == 2)
		{
			return board[board.length - 1];
		} else
		{
			System.out.println("Invalid player, please enter 1 for player 1 or 2 for player 2.");
			return 0;
		}

	}
	/**
	 * Checks for winner.
	 * @return 1 for player 1 won, 2 for player 2 won, 0 for tie, -1 for game has not ended yet.
	 */
	public int checkWinner()
	{
		if (checkGameEnd())
		{
			if (getMancala(1) > getMancala(2))
			{
				System.out.println("Player 1 has won.");
				return 1;
			} else if (getMancala(1) < getMancala(2))
			{
				System.out.println("Player 2 has won.");
				return 2;
			} else if (getMancala(1) == getMancala(2))
			{
				System.out.println("It is a tie.");
				return 0;
			}
		}
		System.out.println("Game has not ended yet.");
		return -1;
	}

	private boolean checkGameEnd()
	{
		int total = 0;
		// check if any stones are left on player 1's side.
		for (int i = 0; i < 6; i++)
		{
			total = total + board[i];
		}
		// if there aren't any, take all of the stones on player 2's side and
		// add them to their Mancala.
		if (total == 0)
		{
			for (int i = 7; i < 13; i++)
			{
				total = total + board[i];
				board[i] = 0;

			}
			board[board.length - 1] += total;
			return true;
		}

		total = 0;
		// check if any stones are left on player 2's side.
		for (int i = 7; i < 13; i++)
		{
			total = total + board[i];
		}
		// if there aren't any, take all of the stones on player 1's side and
		// add them to their Mancala.
		if (total == 0)
		{
			for (int i = 0; i < 6; i++)
			{
				total = total + board[i];
				board[i] = 0;
			}
			board[(board.length - 1) / 2] += total;
			return true;
		}
		return false;
	}

	/**
	 * prints content of board
	 */
	public void testContent()
	{

		for (int i = 0; i < board.length; i++)
		{
			if (i == board.length - 1 || i == (board.length - 1) / 2)
			{
				System.out.print("[");
			}
			if (board[i] < 10)
			{
				System.out.print(" " + board[i] + " ");
			} else
			{
				System.out.print(board[i] + " ");
			}
			if (i == board.length - 1 || i == (board.length - 1) / 2)
			{
				System.out.print("]");
			}

		}
		System.out.println();
	}

	//T: Controller implementation
	   public void attach(ChangeListener c)
	   {
	      listeners.add(c);		// see MancalaTester2
	   }

	   /**
			logic when a move is made
	   */
	   public void update(int pit)
	   {
	      pickPit(pit, activePlayer);
	      for (ChangeListener l : listeners)
	      {
	    	  l.stateChanged(new ChangeEvent(this));	// MancalaBoard detects a change
	      }
	   }
}
