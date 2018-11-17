package main;



import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class LoadScreen extends Scene{
	private static Pane root = new Pane();
	
	public LoadScreen() {
		super(root);
		ArrayList<String> myCharacters = new ArrayList<String>();
		myCharacters.add("characters.naruto_sage/naruto_sage_mugs.png");
		myCharacters.add("characters.sauke_aka/sauke_aka_mugs.png");
		root.setPrefSize(1280, 720);
		root.setBackground(new Background(new BackgroundFill(Color.AQUA, null, null)));
	}

}
