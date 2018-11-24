package main;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class GameScreen extends Scene{
	private static Pane root = new Pane();
	private Main main ;
	public GameScreen(Main main) {
		super(root);
		this.main = main ;
		root.setPrefSize(1280, 720);
		
	}
	

}
