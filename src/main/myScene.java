package main;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;

public abstract class myScene extends Scene{
	private static Font narutoFont = Font.loadFont(ClassLoader.getSystemResource("fonts/njnaruto.ttf").toExternalForm(), 50);
	private static Font narutoFont_small = Font.loadFont(ClassLoader.getSystemResource("fonts/njnaruto.ttf").toExternalForm(), 25);
	private static AudioClip choose = new AudioClip("file:soundfx/accept.wav");
	private static AudioClip click = new AudioClip("file:soundfx/lighter.wav");
	private static AudioClip ready = new AudioClip("file:soundfx/SUDA.wav");
	public myScene(Pane root) {
		super(root);
	}
	public abstract void update();
	public abstract void setDefault();

	public static Font getNarutoFont() {
		return narutoFont;
	}
	public static Font getNarutoFont_small() {
		return narutoFont_small;
	}
	public static void playChoose() {
		choose.play();
	}
	public static void playClick() {
		click.play();
	}
	public static void playReady() {
		ready.play();
	}
}
