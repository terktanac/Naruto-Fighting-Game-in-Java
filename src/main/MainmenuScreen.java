package main;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class MainmenuScreen extends Pane {
	public MainmenuScreen() {
		setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
		HBox MenuBox = new HBox();
		
	}
	public class Menu extends VBox{
		Image kunai = new Image(getClass().getResourceAsStream("/CharsAndSFX/kunai.png"));
		Menu(String text){
			
		}
	}
}
