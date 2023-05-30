import java.awt.BorderLayout;

import java.awt.Color;
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
       frame.setPreferredSize(new Dimension(1800, 1500));
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
               ImageIcon charOutline = new ImageIcon("defaultChar.png"); // skin color
               ImageIcon hairColor = new ImageIcon("hair" + character.getHair() + ".png"); // skin color
               ImageIcon shades = new ImageIcon("shades.png");
               ImageIcon mouth = new ImageIcon("mouthSmile.png");
               ImageIcon mouth2 = new ImageIcon("mouthMehNew.png");



               
               skinLayer = new JLabel(skinColor, JLabel.CENTER);
              
               shadesLayer = new JLabel(shades, JLabel.CENTER);
                             
               
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
                	   //call to guessCheck here
                	   
                	   
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
               JOptionPane.showMessageDialog(null, hint);
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
 
/*import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
public class GuessWhoUI {
private JFrame frame;
private JPanel boardPanel;
private JButton hintButton;
private GuessWhoGame game;
private int boardSize = 5;
private JTextArea hintTextArea;
private JTextField guessTextField;
private List<String> hints;
private int hintCount;
public void startGame() {
game = new GuessWhoGame();
game.startGame();
hints = new ArrayList<>();
hintCount = 0;
}
public GuessWhoUI() {
createUI();
}
private void createUI() {
frame = new JFrame("Guess Who");
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setResizable(false);
game = new GuessWhoGame();
boardPanel = new JPanel();
boardPanel.setLayout(new GridLayout(boardSize, boardSize));
for (int r = 0; r < boardSize; r++) {
for (int c = 0; c < boardSize; c++) {
JButton button = new JButton();
Character character = game.getCharacterAt(r, c);
ImageIcon skinIcon = new ImageIcon("skinYellow.png");
ImageIcon glassesIcon = new ImageIcon("hairBlack.png");
JLabel skinLayer = new JLabel(skinIcon, JLabel.CENTER);
JLabel glassesLayer = new JLabel(glassesIcon, JLabel.CENTER);
button.setText(character.getName());
button.setPreferredSize(new Dimension(120, 150));
button.add(skinLayer);
button.add(glassesLayer);
button.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
// Handle button click event
// You can perform actions related to the clicked character here
}
});
boardPanel.add(button);
}
}
hintButton = new JButton("Get Hint");
hintButton.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
if (hintCount < 4) {
String hint = game.getHint();
hints.add(hint);
hintCount++;
updateHintTextArea();
} else {
showGuessInput();
}
}
});
frame.add(boardPanel, BorderLayout.NORTH);
hintTextArea = new JTextArea();
hintTextArea.setEditable(false);
JScrollPane scrollPane = new JScrollPane(hintTextArea);
frame.add(scrollPane, BorderLayout.CENTER);
JPanel controlPanel = new JPanel();
controlPanel.add(hintButton);
frame.add(controlPanel, BorderLayout.SOUTH);
frame.pack();
frame.setVisible(true);
}
private void updateHintTextArea() {
StringBuilder hintBuilder = new StringBuilder();
for (String hint : hints) {
hintBuilder.append(hint).append("\n");
}
hintTextArea.setText(hintBuilder.toString());
}
private void showGuessInput() {
    JPanel guessPanel = new JPanel();
    guessTextField = new JTextField(20);
    JButton guessButton = new JButton("Make a Guess");
    guessButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            String guess = guessTextField.getText();
            // Process the guess here
        }
    });
    guessPanel.add(guessTextField);
    guessPanel.add(guessButton);


    JPanel controlPanel = new JPanel();
    controlPanel.add(hintButton);


    frame.getContentPane().add(guessPanel, BorderLayout.SOUTH);
    frame.getContentPane().add(controlPanel, BorderLayout.CENTER);
    frame.revalidate();
    frame.repaint();
}


public static void main(String[] args) {
GuessWhoUI guessWhoUI = new GuessWhoUI();
guessWhoUI.startGame();
}


}
*/
