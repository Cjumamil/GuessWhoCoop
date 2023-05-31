//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;


import javax.swing.JPanel;

//import javax.swing.JButton;
//import javax.swing.JPanel;

public class GuessWhoGame extends JPanel implements MouseListener, KeyListener, ActionListener {
	
	//Use a 2D array to create a 5x5 game board
	
	public int boardSize = 5; // size of board
	private Character[][] gameBoard;
	private Character selectedChar; // the character to guess
	
	int guesses = 5; //5 guesses 
	ArrayList<String> names;
	
    public GuessWhoGame() {
        gameBoard = new Character[boardSize][boardSize];
        names = new ArrayList<String>(); // Initialize the names ArrayList
        populateNames(); // Populate the names ArrayList
        populateGameBoard();
    }
    
	public void startGame() {
		  //  populateGameBoard();
			selectedChar = selectRandomChar();
		    
		    for(int r = 0; r < 5; r++) {
		    	for(int c = 0; c < 5; c++) {
		    		Character person = gameBoard[r][c];
		    		System.out.println(person.getName() + " " + person.getHair() + " " +  person.getSkinColor() + " hasGlasses: " +  person.hasGlasses() + " hasSmile: " +  person.hasSmile());
		    	}
		    }
		  
		}
    
	
	public void populateGameBoard() {
		//populates the board w/ characters
		
		Random random = new Random();
		
		// traverse the 2D array to populate w/ Chars
		// should create 25 unique characters
		for(int r = 0; r < boardSize; r++) {
			for(int c = 0; c < boardSize; c++) {
				String name = randomName();
				String skinColor = randomSkinColor();
				String hair = randomHair();
				boolean smile = random.nextBoolean();
				boolean hasGlasses = random.nextBoolean();
				
				gameBoard[r][c]= new Character(name, skinColor, hair, smile, hasGlasses);
			}
		}
		
	}
    public void populateNames() {
        String[] nameArray = {"Cooper", "Lance", "Jaden", "Connor", "Christian",
                              "Arhaan", "Adam", "Von", "Coopaloop", "Zip",
                              "Skiddles", "Pluey", "Wuffez", "Bhqrone", "Mr. David",
                              "Guiness", "Neo", "Jumbo", "Milo", "Latte",
                              "Mocha", "Zaia", "Kona", "Finn", "Simba", "Cash",
                              "Lance is fat", "Jaden likes Lady Boys", "COOP is sexy"};
        names.addAll(Arrays.asList(nameArray));
    }
	
    private String randomName() {
        // Generate and return a random name
    	// pull from an ArrayList so that each Char on the 5x5 has a unique name

        
        Random random = new Random();
        int index = random.nextInt(names.size());
        String name = names.remove(index); // removes the name so there are no repeats
        return name;
			  
    	}    

    private String randomSkinColor() {
        //return a random skin color
    	
    	String[] skinColors = {"White", "Black", "Brown", "Yellow"};
    	Random random = new Random();
    	// random.nextInt(n) will generate a random int between 0 and n inclusive
    	int index = random.nextInt(skinColors.length);
    	
    	return skinColors[index];    	
    }

    private String randomHair() {
        // Generate and return a random hair type
    	
    	String[] hairTypes = {"Black", "Blonde", "Brown"};
    	Random random = new Random();
    	int index = random.nextInt(hairTypes.length);
    	
    	return hairTypes[index];   
    }	
	
    	// initialize a boolean array to keep track of used categories.
    	private boolean[] usedHints = new boolean[4];
    	
    	
    	public String getHint() {
    	    // Check if all hints have been used
    	    boolean allHintsUsed = true;
    	    for (boolean usedHint : usedHints) {
    	        if (!usedHint) {
    	            allHintsUsed = false;
    	            break;
    	        }
    	    }
    	    
    	    if (allHintsUsed) {
    	        return "NO MORE HINTS YOU RETA*D!";
    	    }
    	    
    	    Random random = new Random();
    	    int hint = random.nextInt(4); // picks # 0-3 since 4 characteristics
    	    while (usedHints[hint]) { // if this hint has been used already
    	        hint = random.nextInt(4); // get another random hint
    	    }
    	    
    	    usedHints[hint] = true; // mark this hint as used
    	    
    	    if (hint == 0) {
    	        if (selectedChar.hasGlasses()) {
    	            return "Hint: The character has glasses";
    	        } else {
    	            return "Hint: The character does not have glasses";
    	        }
    	    } else if (hint == 1) {
    	        return "Hint: The character's skin color is " + selectedChar.getSkinColor();
    	    } else if (hint == 2) {
    	        return "Hint: The character's hair color is " + selectedChar.getHair();
    	    } else if (hint == 3) {
    	        if (selectedChar.hasSmile()) {
    	            return "Hint: The character is smiling";
    	        } else {
    	            return "Hint: The character is not smiling";
    	        }
    	    }
    	    
    	    return "COOP IS SO DAMN SEXY MMMMMMMMMMMMM!!!"; // This line is not reachable, but included for completeness
    	}



	
	public Character selectRandomChar() {
		// selected a random char from the 2D Character Array
		// this character will be the char that must be guessed
		
	    Random random = new Random();
	    int r = random.nextInt(boardSize);
	    int c = random.nextInt(boardSize);
	    selectedChar = gameBoard[r][c];
	    
	    
	    System.out.println("The slected Character is: " + selectedChar.getName());
	    return selectedChar;
	    
	}
	
	public String getSelectedChar() {
		return selectedChar.getName();
	}


	public int getBoardSize() {
		return boardSize;
	}

	public Character getCharacterAt(int r, int c) {
		return gameBoard[r][c];

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
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
	
	
	
	


}





