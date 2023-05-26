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
       //game = new GuessWhoGame();
       game.startGame();
   }
  
   public GuessWhoUI() {
       createUI();
   }
 
   private void createUI() {
       frame = new JFrame("Guess Who");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setResizable(true);
       
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
               ImageIcon charOutline = new ImageIcon("defaultChar.png"); // skin color
               ImageIcon hairColor = new ImageIcon("hair" + character.getHair() + ".png"); // skin color
               ImageIcon shades = new ImageIcon("shades.png");
               ImageIcon mouth = new ImageIcon("mouthSmile.png");
               ImageIcon mouth2 = new ImageIcon("mouthMeh.png");



               
               skinLayer = new JLabel(skinColor, JLabel.CENTER);
               if (character.hasGlasses()) {
                   shadesLayer = new JLabel(shades, JLabel.CENTER);
               }               
               
               outlineLayer = new JLabel(charOutline, JLabel.CENTER);
               hairLayer = new JLabel(hairColor, JLabel.CENTER);
               if (character.hasSmile()) {
                   mouthLayer = new JLabel(mouth, JLabel.CENTER);
               }else {
            	   mouthLayer = new JLabel(mouth2, JLabel.CENTER);
               }


              
               button.setPreferredSize(new Dimension(130, 180));

               
               // add all the layers top to bttm
               // ORDER MATTERS DO NOT FUCKING MOVE THESE .. Lance   
               button.add(hairLayer);
               button.add(outlineLayer);
               button.add(shadesLayer); 
               button.add(mouthLayer);
               button.add(skinLayer);
                
               
               
               // action when cell clicked
               button.addActionListener(new ActionListener() {
                   public void actionPerformed(ActionEvent e) {
                       // Handle button click event
                       // You can perform actions related to the clicked character here
                	   System.out.println(character.getName() + " was guessed");
                	   //game.checkGuess(character.getName());
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
 

