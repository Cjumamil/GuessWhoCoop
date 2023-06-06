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
import java.awt.*;
import javax.swing.*;
import java.util.Random;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;


public class GuessWhoUI {

	private JFrame frame;
	private String previousCharacter;
	private JPanel boardPanel;
	private JButton hintButt;
	private JButton restartButton;
	private GuessWhoGame game;
	private int boardSize = 5;
	private JLabel titleLabel;
	private Timer timer;
	private JLabel outlineLayer;
	private JLabel skinLayer;
	private JLabel shadesLayer;
	private JLabel mouthLayer;
	private JLabel hairLayer;
	ImageIcon backgroundIcon = new ImageIcon("BG.PNG");
	JLabel backgroundLabel = new JLabel(backgroundIcon);
	private int incorrectGuessCount = 0; 
	

	public void playMusic(String filePath) {
		try {
			URL url = this.getClass().getClassLoader().getResource(filePath);
			AudioInputStream audioInput = AudioSystem.getAudioInputStream(url);
			Clip clip = AudioSystem.getClip();
			clip.open(audioInput);
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY); // For continuous loop
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	public void recursiveBlackout(Component component) {
	    if (component instanceof Container) {
	        for (Component child : ((Container)component).getComponents()) {
	            recursiveBlackout(child);
	        }
	    }
	    component.setBackground(Color.BLACK);
	    if (component instanceof JButton) {
	        ((JButton) component).setContentAreaFilled(false);
	        ((JButton) component).setOpaque(true);
	    }
	}

	private String[] phrases = {
			"Don't forget to blink!", "Made by Cooper, Lance, and Jaden!", "Happy guessing!",
			"Don't let the characters fool you!", "May the odds be in your favor!", "Good Luck!"
	};

	private Random rand = new Random();
	
	private void loadPreviousCharacter() {
	    try (BufferedReader reader = new BufferedReader(new FileReader("previousCharacter.txt"))) {
	        previousCharacter = reader.readLine();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	public GuessWhoUI() {
	    loadPreviousCharacter();
	    createStartScreen();
	}

	private void createStartScreen() {
		playMusic("Wallpaper-_1_.wav");
		frame = new JFrame("Guess Who");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setPreferredSize(new Dimension(1800, 900));
		frame.setResizable(false);
		JPanel startScreen = new JPanel();
		startScreen.setLayout(new BorderLayout());
		titleLabel = new JLabel("Welcome to Guess Who!", JLabel.CENTER);
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 100));
		startScreen.add(titleLabel, BorderLayout.CENTER);
		JButton startButton = new JButton("Start Game");
		startButton.setFont(new Font("SansSerif", Font.BOLD, 50));
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				createUI();
				startGame();
			}
		});
		startScreen.add(startButton, BorderLayout.SOUTH);
		// Colorful background and random phrases
		startScreen.setBackground(new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
		JLabel phraseLabel = new JLabel(phrases[rand.nextInt(phrases.length)], JLabel.CENTER);
		phraseLabel.setFont(new Font("SansSerif", Font.PLAIN, 75));
		startScreen.add(phraseLabel, BorderLayout.NORTH);
		// Simple animation
		timer = new Timer(1000, new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				titleLabel.setForeground(new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));

			}
		});
		timer.start();
		frame.add(startScreen);
		frame.pack();
		frame.setVisible(true);
	}

	public void startGame() {
		game.startGame();
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
            	   JOptionPane.showMessageDialog(null, "YOU'RE A WINNER!");
	       	        if (button.isEnabled()) {
	    	            button.setBackground(Color.green); // Change the background color
	
	    	            // Perform other actions related to the clicked cell here36
	    	            System.out.println(character.getName() + " was guessed");
	    	            
	    	            gameOver();
	    	        }	   
            	    	 } else {
 				            incorrectGuessCount++;
 				            if (incorrectGuessCount == 10) {
 				                recursiveBlackout(frame);
 				                JOptionPane.showMessageDialog(null, "Game Over! You are a loser!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
 				                gameOver();
 				            } else {
 				                // If it's a wrong guess and it's not the third one, color the button gray.
 				                button.setBackground(Color.GRAY);
 				            }
 				        }
 				        button.setEnabled(false);
 				    }
 				});
               
               boardPanel.add(button); // Add the button to the panel
           }
       }
      
       hintButt = new JButton("Get Hint"); // Create the hint button
       hintButt.addActionListener(new ActionListener() {

           public void actionPerformed(ActionEvent e) {

               String hint = game.getHint();
               System.out.println(hint); // print whatever the hint button displays

               if(hint.equals("NO MORE HINTS!")) {
                   hintButt.setEnabled(false);
               }
               ((JButton) e.getSource()).setText(hint);
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
               game = new GuessWhoGame(); // create a new game instance
               incorrectGuessCount = 0;  // reset incorrectGuessCount to 0
               createUI(); //create a new UI after the game has been reset
               System.out.println("New Game Started");              
           }
       });
       JButton previousCharacterButton = new JButton("Previous Character");
       previousCharacterButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               JOptionPane.showMessageDialog(null, previousCharacter);
           }
       });
       frame.add(boardPanel, BorderLayout.NORTH); // Add the board panel to the frame

       //Create a panel for the South portion the GUI
       JPanel picture = new JPanel();

       picture.add(hintButt, BorderLayout.NORTH);
       picture.add(restartButton, BorderLayout.NORTH);
       picture.add(previousCharacterButton, BorderLayout.NORTH); // add previousCharacterButton here

       frame.add(picture);
       frame.pack(); 
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
	    savePreviousCharacter(game.getSelectedChar().getName());
	}
   private void savePreviousCharacter(String characterName) {
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter("previousCharacter.txt"))) {
	        writer.write(characterName);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
  
   public static void main(String[] args) {
       GuessWhoUI guessWhoUI = new GuessWhoUI();
       guessWhoUI.startGame();
   }
}