package main;



import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class LoadScreen extends Scene{
	private static Pane root = new Pane();
	private ImageView lhschar = new ImageView(new Image(ClassLoader.getSystemResource("characters/naruto_sage/naruto_sage_mugs.png").toString(), 400, 250, true, true));
	private ImageView rhschar = new ImageView(new Image(ClassLoader.getSystemResource("characters/sasuke_aka/sasuke_aka_mugs.png").toString(), 400, 250, true, true));
	public LoadScreen(Main main) {
		super(root);
		ArrayList<String> myCharacters = new ArrayList<String>();
		myCharacters.add("characters/naruto_sage/naruto_sage_mugs.png");
		myCharacters.add("characters/sauke_aka/sauke_aka_mugs.png");
		
		root.setPrefSize(1280, 720);
		root.setBackground(new Background(new BackgroundFill(Color.AQUA, null, null)));
		
		lhschar.setTranslateX(200);
		lhschar.setTranslateY(150);
		
		rhschar.setTranslateX(800);
		rhschar.setTranslateY(150);
		
		root.getChildren().addAll(lhschar,rhschar);
	}

}
