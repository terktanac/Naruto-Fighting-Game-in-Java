package characters;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class WindCharacter_1 extends Character{

	private static Image image = new Image(ClassLoader.getSystemResource("characters/naruto_sage/naruto_sage.png").toString(),1110, 2220, false, false);
	public WindCharacter_1() {
		super("Naruto", 4, 100, 8, 2,0.6, new ImageView(image));
		// TODO Auto-generated constructor stub
	}

	
	
}
