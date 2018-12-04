package main;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;

public abstract class myScene extends Scene{
	private static Font narutoFont = Font.loadFont(ClassLoader.getSystemResource("fonts/njnaruto.ttf").toExternalForm(), 50);
	//static MediaPlayer click = new MediaPlayer(new Media(ClassLoader.getSystemResource("lighter.wav").toString()));
	public myScene(Pane root) {
		super(root);
	}
	public abstract void update();

	public static Font getNarutoFont() {
		return narutoFont;
	}
	 public static void playChoose() {
		MediaPlayer choose = new MediaPlayer(new Media(ClassLoader.getSystemResource("accept.wav").toString())); 
		choose.play();
	}
	 public static void playClick() {
		MediaPlayer click = new MediaPlayer(new Media(ClassLoader.getSystemResource("lighter.wav").toString()));
		click.play();
	 }
}
