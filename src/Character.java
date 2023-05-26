import javax.swing.JButton;

public class Character extends JButton {
	
	// characteristics of a Character Obj
    private String name;
    private String skinColor;
    private String hair;
    private boolean smile;
    private boolean hasGlasses;

    public Character(String name, String skinColor, String hair, boolean smile, boolean hasGlasses) {
        this.name = name;
        this.skinColor = skinColor;
        this.hair = hair;
        this.smile = smile;
        this.hasGlasses = hasGlasses;        
    }

    public String getName() {
        return name;
    }

    public String getSkinColor() {
        return skinColor;
    }

    public String getHair() {
        return hair;
    }

    public boolean hasSmile() {
        return smile;
    }
 
    public boolean hasGlasses() {
        return hasGlasses;
    }
}

