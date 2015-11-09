import java.awt.*;
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

class MancalaBoard extends JFrame implements ChangeListener{
  private static final int WIDTH = 900;
  private static final int HEIGHT = 400;

  private Border lineBorder = LineBorder.createBlackLineBorder();
  private MancalaData data;
  private JLabel[] bins = new JLabel[12];

  public MancalaBoard(MancalaData data){
    init(data);
  }

  public void init(MancalaData data){
    this.data = data;
    int numStones = 4;
    setLocation(100,200);
    Dimension binSize = new Dimension(125,350);
    setLayout(new BorderLayout());
    JPanel user1Bin = new JPanel();
    JPanel user2Bin = new JPanel();
    JPanel playingArea = new JPanel();

    //Set up player 1 bin
    user1Bin.setPreferredSize(binSize);
    user1Bin.setBorder(lineBorder);
    user1Bin.setBackground(Color.lightGray);
    JLabel user1Score = new JLabel("0");
    stylePanel(user1Score, Color.lightGray);
    user1Bin.add(user1Score);


    //Set up player 2 bin
    user2Bin.setPreferredSize(binSize);
    user2Bin.setBorder(lineBorder);
    user2Bin.setBackground(Color.lightGray);
    JLabel user2Score = new JLabel("0");
    stylePanel(user2Score, Color.lightGray);
    user2Bin.add(user2Score);

    //Header for game
    JLabel header = new JLabel("Mancala");
    header.setFont(header.getFont().deriveFont(30f));
    header.setHorizontalAlignment(JLabel.CENTER);
    header.setVerticalAlignment(JLabel.CENTER);

    //Player initial input for game
    JPanel input = new JPanel();
    JLabel query = new JLabel("Select # of stones to begin game");
    JButton threeStones = new JButton("3");
    JButton fourStones = new JButton("4");
    input.add(query);
    input.add(threeStones);
    input.add(fourStones);

    initializePlayingArea(playingArea, numStones);

    add(header,BorderLayout.NORTH);
    add(user1Bin,BorderLayout.WEST);
    add(user2Bin,BorderLayout.EAST);
    add(playingArea, BorderLayout.CENTER);
    add(input,BorderLayout.SOUTH);



    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(WIDTH, HEIGHT);
    setVisible(true);
  }

  public void stateChanged(ChangeEvent e)
  {
      repaint();
  }

  public MouseListener listener(int x){
  return new MouseAdapter(){
    public void mousePressed(MouseEvent e)
      {
        System.out.println("Clicked bin " + x);
        int stones = Integer.parseInt(bins[x - 1].getText());
        System.out.println("Number of stones in bin = " + stones);
        bins[x -1].setText(String.valueOf(stones - 1)); //Just testing.


      }
    };
  }

  public void initializePlayingArea(JPanel panel, int numStones){
    panel.setLayout(new GridLayout(0,6));

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


    panel.add(b1);
    panel.add(b2);
    panel.add(b3);
    panel.add(b4);
    panel.add(b5);
    panel.add(b6);


    for(int x = bins.length - 1; x >= 0; x--){
      int index;
      if(x > bins.length / 2 - 1){
        index = x;
      }else{
        index = (bins.length / 2 - 1) - x;
      }
      System.out.println("Index = " + index);
      bins[index] = new JLabel(String.valueOf(numStones));
      stylePanel(bins[index], null);
      bins[index].addMouseListener(listener(index + 1));
      panel.add(bins[index]);
    }

    panel.add(a1);
    panel.add(a2);
    panel.add(a3);
    panel.add(a4);
    panel.add(a5);
    panel.add(a6);

  }

  public void stylePanel(JLabel component, Color c){
    component.setBorder(lineBorder);
    component.setFont(component.getFont().deriveFont(30f));
    component.setHorizontalAlignment(JLabel.CENTER);
    component.setVerticalAlignment(JLabel.CENTER);
    if(c != null){
      System.out.println("In color background");
      component.setBackground(c);
      component.setOpaque(true);
    }
  }

}
