import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Font;

public class GuessWhoUI {
  
   private JFrame frame;
   private JPanel boardPanel;
   private JButton hintButt;
   private JButton restartButton;
   private GuessWhoGame game;
   private int boardSize = 5;
   
   // characteristic layers
   
   private JLabel outlineLayer;
   private JLabel skinLayer;
   private JLabel shadesLayer;
   private JLabel mouthLayer;
   private JLabel hairLayer;
   
   
   
    public void startGame() {
    	game.startGame();
   }
  
   public GuessWhoUI() {
       createUI();
   }
 
   private void createUI() {
       frame = new JFrame("Guess Who");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setPreferredSize(new Dimension(1800, 900));
       frame.setResizable(false);
       
       //default layers 

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
               ImageIcon charOutline = new ImageIcon("defaultChar.png");
               ImageIcon hairColor = new ImageIcon("hair" + character.getHair() + ".png"); 
               ImageIcon shades = new ImageIcon("shades.png");
               ImageIcon noShades = new ImageIcon("defaultChar.png"); //just a filler file
               ImageIcon mouth = new ImageIcon("mouthSmile.png");
               ImageIcon mouth2 = new ImageIcon("mouthMehNew.png");



               
               skinLayer = new JLabel(skinColor, JLabel.CENTER);
              
               if(character.hasGlasses()) {
            	   shadesLayer = new JLabel(shades, JLabel.CENTER);
               }else {
            	   shadesLayer = new JLabel(noShades, JLabel.CENTER); // just a filler
               }
                             
               
               outlineLayer = new JLabel(charOutline, JLabel.CENTER);
               hairLayer = new JLabel(hairColor, JLabel.CENTER);
               if (character.hasSmile()) {
                   mouthLayer = new JLabel(mouth, JLabel.CENTER);
               }else {
            	   mouthLayer = new JLabel(mouth2, JLabel.CENTER);
               }


              
               button.setPreferredSize(new Dimension(120, 160));

               
               // add all the layers top to bottom
               // ORDER MATTERS DO NOT FUCKING MOVE THESE .. Lance   
               // Lance you're actually brain dead for moving these >:(
               button.add(hairLayer);
               button.add(outlineLayer);
               button.add(shadesLayer); 
               button.add(mouthLayer);
               button.add(skinLayer);
               
                
               
               button.addActionListener(new ActionListener() {
            	    public void actionPerformed(ActionEvent e) {
            	    	
            	    	if(character.equals(game.getSelectedChar())){
            	   JOptionPane.showMessageDialog(null, "YOU'RE A WEINER!");
       	        if (button.isEnabled()) {
    	            button.setBackground(Color.green); // Change the background color

    	            // Perform other actions related to the clicked cell here36
    	            System.out.println(character.getName() + " was guessed");
    	            
    	            gameOver();
    	        }	   
               }else {
            	   JOptionPane.showMessageDialog(null, "Try Again!");
       	        if (button.isEnabled()) {
    	            button.setBackground(Color.gray); // Change the background color
    	            // Perform other actions related to the clicked cell here
    	            System.out.println(character.getName() + " was guessed");
    	        }
               }
                	    button.setEnabled(false); // Make the button unclickable
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
               JOptionPane.showMessageDialog(null, hint);
           }
       });
       
       restartButton = new JButton("Restart Game");
       restartButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
              try {
            	  JOptionPane.showMessageDialog(null, "New Game Starting . .");
            	    Thread.sleep(2000); // = 2 seconds
            	} catch (InterruptedException e1) {
            	    e1.printStackTrace();
            	}
              frame.dispose(); // removes old window
              game.startGame();
              createUI();
              
              System.out.println("New Game Started");

              
              
           }
       });
       
      
       frame.add(boardPanel, BorderLayout.NORTH); // Add the board panel to the frame
      
       
       
       //Create a panel for the South portion the GUI
       JPanel picture = new JPanel();

       picture.add(hintButt, BorderLayout.NORTH);
       picture.add(restartButton, BorderLayout.NORTH);
    
      
     
       frame.add(picture);
       frame.pack(); // Pack the components to fit the frame size
       frame.setVisible(true);
       
       
   
   
  
   }
   
   public void gameOver() {
	    // Disable all buttons in the boardPanel
	    for (Component component : boardPanel.getComponents()) {
	        if (component instanceof JButton) {
	            JButton button = (JButton) component;
	            button.setEnabled(false);
	        }
	    }
	}
  
   public static void main(String[] args) {
       GuessWhoUI guessWhoUI = new GuessWhoUI();
       guessWhoUI.startGame();
   }
}
 
