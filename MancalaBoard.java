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
  private JPanel[] bins = new JPanel[12];

  public MancalaBoard(MancalaData data){
    init(data);
  }

  public void init(MancalaData data){
    this.data = data;
    setLocation(100,200);
    Dimension binSize = new Dimension(150,350);
    setLayout(new BorderLayout());
    JPanel user1Bin = new JPanel();
    JPanel user2Bin = new JPanel();
    JPanel playingArea = new JPanel();

    user1Bin.setPreferredSize(binSize);
    user1Bin.setBorder(lineBorder);
    user1Bin.setBackground(Color.lightGray);

    user2Bin.setPreferredSize(binSize);
    user2Bin.setBorder(lineBorder);
    user2Bin.setBackground(Color.lightGray);

    initializePlayingArea(playingArea);

    add(user1Bin,BorderLayout.WEST);
    add(user2Bin,BorderLayout.EAST);
    add(playingArea, BorderLayout.CENTER);


    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(WIDTH, HEIGHT);
    setVisible(true);
  }

  public void stateChanged(ChangeEvent e)
  {
      repaint();
  }

  public MouseListener listener(){
  return new MouseAdapter(){
    public void mousePressed(MouseEvent e)
      {
        System.out.println("Clicked");

      }
    };
  }

  public void initializePlayingArea(JPanel panel){
    panel.setLayout(new GridLayout(0,6));

    JLabel a1 = new JLabel("<html><body><center>A1</center></body></html>");
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


    a1.setBorder(lineBorder);
    a2.setBorder(lineBorder);
    a3.setBorder(lineBorder);
    a4.setBorder(lineBorder);
    a5.setBorder(lineBorder);
    a6.setBorder(lineBorder);
    //a1.setFont(a1.getFont().deriveFont(50f));
    //a2.setFont(a2.getFont().deriveFont(50f));
    //a3.setFont(a3.getFont().deriveFont(50f));
    //a4.setFont(a4.getFont().deriveFont(50f));
    //a5.setFont(a5.getFont().deriveFont(50f));
    //a6.setFont(a6.getFont().deriveFont(50f));
    a1.setHorizontalTextPosition(JLabel.CENTER);

    b1.setHorizontalTextPosition(SwingConstants.RIGHT);

    b1.setBorder(lineBorder);
    b2.setBorder(lineBorder);
    b3.setBorder(lineBorder);
    b4.setBorder(lineBorder);
    b5.setBorder(lineBorder);
    b6.setBorder(lineBorder);

    panel.add(b1);
    panel.add(b2);
    panel.add(b3);
    panel.add(b4);
    panel.add(b5);
    panel.add(b6);

    for(int x = 0; x < bins.length; x++){
      bins[x] = new JPanel();
      bins[x].setBorder(lineBorder);
      bins[x].addMouseListener(listener());
      panel.add(bins[x]);
    }

    panel.add(a1);
    panel.add(a2);
    panel.add(a3);
    panel.add(a4);
    panel.add(a5);
    panel.add(a6);

  }

}
