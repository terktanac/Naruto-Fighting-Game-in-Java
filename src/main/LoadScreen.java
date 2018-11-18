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

public class LoadScreen extends Scene{
	private int player1 = 0,player2 = 0; //default character =0 : naruto
	private static Pane root = new Pane();
	private ImageView lhschar ;
	private ImageView rhschar ;
	public LoadScreen(Main main) {
		super(root);
		ArrayList<Image> Characters = new ArrayList<Image>();
		Characters.add(new Image(ClassLoader.getSystemResource("characters/naruto_sage/naruto_sage_mugs.png").toString(),400,250,false,false));
		Characters.add(new Image(ClassLoader.getSystemResource("characters/sasuke_aka/sasuke_aka_mugs.png").toString(),400,250,false,false));
//		ArrayList<String> myCharacters = new ArrayList<String>();
//		myCharacters.add("characters/naruto_sage/naruto_sage_mugs.png");
//		myCharacters.add("characters/sasuke_aka/sasuke_aka_mugs.png");
		
		root.setPrefSize(1280, 720);
		root.setBackground(new Background(new BackgroundFill(Color.AQUA, null, null)));
		lhschar = new ImageView(Characters.get(player1));
		lhschar.setTranslateX(200);
		lhschar.setTranslateY(150);
		rhschar = new ImageView(Characters.get(player2));
		rhschar.setTranslateX(800);
		rhschar.setTranslateY(150);
		
		root.getChildren().addAll(lhschar,rhschar);
		
		setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				MediaPlayer click = new MediaPlayer(new Media(ClassLoader.getSystemResource("lighter.wav").toString()));
				MediaPlayer choose = new MediaPlayer(new Media(ClassLoader.getSystemResource("accept5.wav").toString()));
				boolean chosen = false;
				KeyCode key = event.getCode();
				System.out.println("Multiplayer:Pressed " + key.toString());
				
				
				
				if (key == KeyCode.SPACE || key == KeyCode.ENTER) {
					choose.play();
					chosen = true;
				} 
				else if ((key == KeyCode.W || key == KeyCode.A) && !chosen) {
					click.play();
					player1 = (player1+1)%Characters.size();
					lhschar.setImage(Characters.get(player1));
				}
				else if ((key == KeyCode.UP || key == KeyCode.LEFT) && !chosen) {
					click.play();
					player2 = (player2+1+Characters.size())%Characters.size();
					rhschar.setImage(Characters.get(player2));
				}
				else if ((key == KeyCode.S || key == KeyCode.D) && !chosen) {
					click.play();
					player1 = (player1-1+Characters.size())%Characters.size();
					lhschar.setImage(Characters.get(player1));
				}
				else if ((key == KeyCode.DOWN ||key == KeyCode.RIGHT) && !chosen) {
					click.play();
					player2 = (player2-1+Characters.size())%Characters.size();
					rhschar.setImage(Characters.get(player2));
				}
			}
		});
	}
	

}
