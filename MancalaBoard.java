package mancala;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.event.*;

import java.util.*;

import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

class MancalaBoard extends JFrame implements ChangeListener
{
	private static final int WIDTH = 1200;
	private static final int HEIGHT = 400;

	// private int activePlayer = 1; //T: set default first player to 1
	private Point mousePoint; // T: supports mouseevents
	private Border lineBorder = LineBorder.createBlackLineBorder();
	private MancalaData data;
	private JLabel[] bins = new JLabel[14];
	private int numStones;	//T: initially numStones = 0
	private JLabel user1Score ;
	private JLabel user2Score ;
	private JLabel status ;
	private JLabel special;
	private int[] undoArray = new int[14];
	public MancalaBoard(MancalaData d)
	{
		init(d);
	}

	public void init(MancalaData d)
	{	
		this.data = d;
		
		setLocation(100, 200);
		Dimension binSize = new Dimension(125, 350);
		setLayout(new BorderLayout());
		JPanel user1Bin = new JPanel();
		JPanel user2Bin = new JPanel();
		JPanel playingArea = new JPanel();
		
		// Set up status and special, sometimes gets cut off, needs to be in a bigger space
		status = new JLabel("Player A's turn");
		special = new JLabel("");
		
		// Set up player 1 bin
		user1Bin.setPreferredSize(binSize);
		user1Bin.setBorder(lineBorder);
		user1Bin.setBackground(Color.lightGray);
		user1Score = new JLabel(Integer.toString(data.getMancala(1)));
		stylePanel(user1Score, Color.lightGray);
		user1Bin.add(user1Score);

		// Set up player 2 bin
		user2Bin.setPreferredSize(binSize);
		user2Bin.setBorder(lineBorder);
		user2Bin.setBackground(Color.lightGray);
		user2Score = new JLabel(Integer.toString(data.getMancala(2)));
		stylePanel(user2Score, Color.lightGray);
		user2Bin.add(user2Score);

		// Header for game
		JLabel header = new JLabel("Mancala");
		
		header.setFont(header.getFont().deriveFont(30f));
		header.setHorizontalAlignment(JLabel.CENTER);
		header.setVerticalAlignment(JLabel.CENTER);
		
		// Player initial input for game
		JPanel input = new JPanel();
		JLabel query = new JLabel("Select # of stones to begin game");
		input.add(query);
			
		JButton undo = new JButton("Undo");
		undo.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				for(int i = 0; i < data.board.length;i++){
					data.board[i] = undoArray[i];
				}
				
				
				status.setText("Move undone. "+data.status);
				special.setText("");
				for (int i = 0; i <bins.length; i++)
				{ // Player 1's Mancala at position 6, and Player 2's at 13.
					if ((i == (bins.length - 1) / 2) || (i == bins.length - 1))
					{
						user2Score.setText(Integer.toString(data.getMancala(2)));
						user1Score.setText(Integer.toString(data.getMancala(1)));
					} else
					{	
						bins[i].setText(Integer.toString(data.board[i]));
					}
				}		
				
			}

		});
		input.add(undo);
		
		//data.initMancala(0);	//T: try testing with 0
		
		// Needs some work to get initialize 3 stones or 4 stones buttons to work
		JButton threeStones = new JButton("3");
		threeStones.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				numStones = 3;
				
				data.initMancala(3);
				
				data.update(-1);
				updateBoardLayout();
			}

		});
		input.add(threeStones);
		JButton fourStones = new JButton("4");
		fourStones.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				numStones = 4;
				
				data.initMancala(4);
				
				data.update(-1);
				updateBoardLayout();
			}

		});
		input.add(fourStones);
		// added status and special to input, not sure where else to put it.
		input.add(status);
		input.add(special);
		// Always init 4 stonesPerPit for now since 3 and 4 buttons do not work
		initializePlayingArea(playingArea, numStones);
		
		
		add(header, BorderLayout.NORTH);
		add(user1Bin, BorderLayout.EAST);
		add(user2Bin, BorderLayout.WEST);
		add(playingArea, BorderLayout.CENTER);
		add(input, BorderLayout.SOUTH);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		setVisible(true);
	}

	public void stateChanged(ChangeEvent e)
	{	
		repaint();
	}

	public MouseListener listener(final int x)
	{
		return new MouseAdapter()
		{
			public void mousePressed(MouseEvent event)
			{
				
				System.out.println("Clicked bin " + x);
				int stones = Integer.parseInt(bins[x ].getText());
				System.out.println("Number of stones in bin = " + stones);
				
				//snapshot of array before choosing pit
				for(int i = 0; i < data.board.length;i++){
					undoArray[i] = data.board[i];
				}
				
				data.update(x);
				updateBoardLayout();
										// testing.
			}
		};
	}

	// helper method for initialization and after picking number of stones to use
	private void updateBoardLayout()
	{
		status.setText(data.status);
		special.setText(data.special);
		for (int i = 0; i <bins.length; i++)
		{ // Player 1's Mancala at position 6, and Player 2's at 13.
			if ((i == (bins.length - 1) / 2) || (i == bins.length - 1))
			{
				user2Score.setText(Integer.toString(data.getMancala(2)));
				user1Score.setText(Integer.toString(data.getMancala(1)));
			} else
			{	
				bins[i].setText(Integer.toString(data.board[i]));
			}
		}	
	}
	
	public void initializePlayingArea(JPanel panel, int numStones)
	{
		panel.setLayout(new GridLayout(0, 6));

		JLabel a1 = new JLabel("A1");
		JLabel a2 = new JLabel("A2");
		JLabel a3 = new JLabel("A3");
		JLabel a4 = new JLabel("A4");
		JLabel a5 = new JLabel("A5");
		JLabel a6 = new JLabel("A6");

		JLabel b1 = new JLabel("B1");
		JLabel b2 = new JLabel("B2");
		JLabel b3 = new JLabel("B3");
		JLabel b4 = new JLabel("B4");
		JLabel b5 = new JLabel("B5");
		JLabel b6 = new JLabel("B6");

		stylePanel(a1, new Color(120, 240, 40));
		stylePanel(a2, new Color(120, 240, 40));
		stylePanel(a3, new Color(120, 240, 40));
		stylePanel(a4, new Color(120, 240, 40));
		stylePanel(a5, new Color(120, 240, 40));
		stylePanel(a6, new Color(120, 240, 40));

		stylePanel(b1, new Color(120, 240, 40));
		stylePanel(b2, new Color(120, 240, 40));
		stylePanel(b3, new Color(120, 240, 40));
		stylePanel(b4, new Color(120, 240, 40));
		stylePanel(b5, new Color(120, 240, 40));
		stylePanel(b6, new Color(120, 240, 40));

		panel.add(b6);
		panel.add(b5);
		panel.add(b4);
		panel.add(b3);
		panel.add(b2);
		panel.add(b1);

		for (int x = bins.length -2; (x >(bins.length - 1) / 2 ); x--)
		{	
			
			System.out.println("Index = " + x);
			bins[x] = new JLabel(String.valueOf(data.board[x]));
			stylePanel(bins[x], null);
			bins[x].addMouseListener(listener(x ));
			panel.add(bins[x ]);
		}
		for (int x = 0; x <((bins.length - 1) / 2); x++)
		{
			
			System.out.println("Index = " + x);
			bins[x] = new JLabel(String.valueOf(data.board[x]));
			stylePanel(bins[x], null);
			bins[x].addMouseListener(listener(x ));
			panel.add(bins[x]);
		}
		panel.add(a1);
		panel.add(a2);
		panel.add(a3);
		panel.add(a4);
		panel.add(a5);
		panel.add(a6);

	}

	public void stylePanel(JLabel component, Color c)
	{
		component.setBorder(lineBorder);
		component.setFont(component.getFont().deriveFont(30f));
		component.setHorizontalAlignment(JLabel.CENTER);
		component.setVerticalAlignment(JLabel.CENTER);
		if (c != null)
		{
			System.out.println("In color background");
			component.setBackground(c);
			component.setOpaque(true);
		}
	}

}
