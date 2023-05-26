import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GuessWhoUI {
  
   private JFrame frame;
   private JPanel boardPanel;
   private JButton hintButt;
   private GuessWhoGame game;
   private int boardSize = 5;
   
   // characteristic layers
   
   private JLabel outlineLayer;
   private JLabel skinLayer;
   private JLabel shadesLayer;
   private JLabel mouthLayer;
   private JLabel hairLayer;
   
   
   
    public void startGame() {
       game = new GuessWhoGame();
       game.startGame();
   }
  
   public GuessWhoUI() {
       createUI();
   }
 
   private void createUI() {
       frame = new JFrame("Guess Who");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      // frame.setPreferredSize(new Dimension(600,600));
       frame.setResizable(false);
       //add image layers to 
       
       game = new GuessWhoGame(); // Create an instance of GuessWhoGame
      
       boardPanel = new JPanel(); // Create the panel to hold the game board
       boardPanel.setLayout(new GridLayout(boardSize, boardSize)); // Set layout manager for the board panel
      
       for (int r = 0; r < boardSize; r++) {
           for (int c = 0; c < boardSize; c++) {
               JButton button = new JButton(); // Create a button for each cell on the game board
               button.setBackground(Color.pink); // background color of buttons
               Character character = game.getCharacterAt(r, c); // Get the character at the current position
               button.setText(character.getName()); // Set the button text to the character's name
              
               //ImageIcon
               ImageIcon skinColor = new ImageIcon("skin" + character.getSkinColor() + ".png"); // skin color
               ImageIcon charOutline = new ImageIcon("defaultChar.png"); // skin color
               ImageIcon hairColor = new ImageIcon("hair" + character.getHair() + ".png"); // skin color

               
          	 	ImageIcon shades = new ImageIcon("shades.png");  // no shades
          	 	ImageIcon mouth = new ImageIcon("mouthSmile.png");

               
               
               
               
               skinLayer = new JLabel(skinColor, JLabel.CENTER);
               shadesLayer = new JLabel(shades, JLabel.CENTER);
               outlineLayer = new JLabel(charOutline, JLabel.CENTER);
               hairLayer = new JLabel(hairColor, JLabel.CENTER);
               mouthLayer = new JLabel(mouth, JLabel.CENTER);


              
               button.setPreferredSize(new Dimension(130, 180));

               
               // add all the layers top to bttm
               // ORDER MATTERS DO NOT FUCKING MOVE THESE .. Lance   
               button.add(hairLayer);
               button.add(outlineLayer);
               button.add(shadesLayer); 
               button.add(mouthLayer);
               button.add(skinLayer);
                
               
               
               button.addActionListener(new ActionListener() {
                   public void actionPerformed(ActionEvent e) {
                       // Handle button click event
                       // You can perform actions related to the clicked character here
                   }
               });
               boardPanel.add(button); // Add the button to the panel
               
           }
       }
      
       hintButt = new JButton("Get Hint"); // Create the hint button
       hintButt.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               String hint = game.getHint();
               System.out.println(hint);
           }
       });
      
       frame.add(boardPanel, BorderLayout.NORTH); // Add the board panel to the frame
      
     //  JPanel hint = new JPanel();
      // hint.setPreferredSize(new Dimension(600,600));
    //   hint.add(hintButt);
    //   frame.add(hint); // Add the hint button to the frame
       
       
       //Create a panel for the South portion the GUI
       JPanel picture = new JPanel();
       picture.add(hintButt, BorderLayout.NORTH);
    
      
     
       frame.add(picture);
       frame.pack(); // Pack the components to fit the frame size
       frame.setVisible(true);
       
       
   }
  
   public static void main(String[] args) {
       GuessWhoUI guessWhoUI = new GuessWhoUI();
       guessWhoUI.startGame();
   }
}

/*
 * import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class GuessWhoUI extends JPanel implements ActionListener, MouseListener, KeyListener {
	
	public ImageIcon imageIcon = new ImageIcon("skinYellow.png");
	
	public void paint(Graphics g) {
		
		super.paintComponent(g);
		
		// Anything you want to paint onto the screen, put here
		g.setColor(Color.black);
		g.fillOval(100, 100, 100, 100);
		
	}
	
	public GuessWhoUI() {
		// Create a JFrame to hold the image
        JFrame frame = new JFrame("Guess Who Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        
        frame.add(this);
        // Load the image from a file
        //ImageIcon imageIcon = new ImageIcon("skinYellow.png");

        // Create a JLabel and set the image icon
        // JLabel label = new JLabel(imageIcon);

        // Add the label to the frame
        // frame.getContentPane().add(label);
        
        // Set the frame to be visible
        frame.setVisible(true);
	}
	
    public static void main(String[] args) {
        GuessWhoUI g = new GuessWhoUI();
    }

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	repaint();
		
	}
}



*/ 
 

