package main;



import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import main.MainMenuScreen.ListMenu;

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
		
		setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				MediaPlayer click = new MediaPlayer(new Media(ClassLoader.getSystemResource("lighter.wav").toString()));
				click.play();
				KeyCode key = event.getCode();
				System.out.println("Multiplayer:Pressed " + key.toString());
				if (key == KeyCode.SPACE || key == KeyCode.ENTER) {

				}
				else if (key == KeyCode.UP || key == KeyCode.LEFT || key == KeyCode.W || key == KeyCode.A || key == KeyCode.KP_UP || key == KeyCode.KP_LEFT) {
						
				} 
				else if (key == KeyCode.UP || key == KeyCode.LEFT || key == KeyCode.W || key == KeyCode.A || key == KeyCode.KP_UP || key == KeyCode.KP_LEFT) {
					
				}
				else if (key == KeyCode.DOWN ||key == KeyCode.RIGHT || key == KeyCode.S || key == KeyCode.D || key == KeyCode.KP_DOWN || key == KeyCode.KP_RIGHT) {
						
				}
			}
		});
	}
	

}
