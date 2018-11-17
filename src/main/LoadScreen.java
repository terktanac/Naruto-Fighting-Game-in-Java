package main;

import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class LoadScreen extends Scene{
	private static Pane root = new Pane();
	public LoadScreen() {
		super(root);
		root.setPrefSize(1280, 720);
		root.setBackground(new Background(new BackgroundFill(Color.AQUA, null, null)));
	}

}
