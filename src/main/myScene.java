package main;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;

public abstract class myScene extends Scene{
	private static Font narutoFont = Font.loadFont(ClassLoader.getSystemResource("fonts/njnaruto.ttf").toExternalForm(), 50);
	static MediaPlayer choose = new MediaPlayer(new Media(ClassLoader.getSystemResource("accept.wav").toString()));
	public myScene(Pane root) {
		super(root);
	}
	public abstract void upPressed();
	public abstract void downPressed();
	public abstract void leftPressed();
	public abstract void rightPressed();
	public abstract void meleePressed();
	public abstract void rangePressed();
	public abstract void dodgePressed();
	public abstract void blockPressed();
	public abstract void SpacePressed();
	public abstract void EnterPressed();
	public abstract void nonePressed();
	public static Font getNarutoFont() {
		return narutoFont;
	}
	
}
