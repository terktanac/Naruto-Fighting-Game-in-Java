package main;


import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class OptionScreen extends Scene{
	
	private static Pane root = new Pane();
	public OptionScreen(Main main) {
		super(root);
		// TODO Auto-generated constructor stub
		root.setPrefSize(1280, 720);
		root.setBackground(new Background(new BackgroundFill(Color.BISQUE, null, null)));
	}

}
