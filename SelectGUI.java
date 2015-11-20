
import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SelectGUI extends JFrame implements ChangeListener
{
   private BoardTemplate bt;
   private MancalaData mD = new MancalaData();

   /**
      Constructs a BarFrame object
      @param dataModel the data that is displayed in the barchart
   */
   public SelectGUI()
   {

	  JPanel input = new JPanel();

      setLocation(0,200);
      setLayout(new BorderLayout());

      JLabel header = new JLabel("Pick a Mancala Board Style");
      add(header, BorderLayout.NORTH);
      add(input, BorderLayout.SOUTH);


      JButton aStyle = new JButton("Style A");
      aStyle.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				bt = new MancalaBoardA(mD);
			}

		});

		input.add(aStyle);

		JButton bStyle = new JButton("Style B");
		bStyle.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				bt = new MancalaBoardB(mD);
			}

		});
		input.add(bStyle);

      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      pack();
      setVisible(true);
   }

   /**
      Called when the data in the model is changed.
      @param e the event representing the change
   */
   public void stateChanged(ChangeEvent e)
   {
		mD.attach((ChangeListener) bt);
   }
}
