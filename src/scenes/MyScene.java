package scenes;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;

public abstract class MyScene extends Scene{
	private static Font narutoFont = Font.loadFont(ClassLoader.getSystemResource("fonts/njnaruto.ttf").toExternalForm(), 50);
	private static Font narutoFontsmall = Font.loadFont(ClassLoader.getSystemResource("fonts/njnaruto.ttf").toExternalForm(), 25);
	private static AudioClip choose = new AudioClip("file:soundfx/accept.wav");
	private static AudioClip click = new AudioClip("file:soundfx/lighter.wav");
	private static AudioClip ready = new AudioClip("file:soundfx/SUDA.wav");

	public MyScene(Pane root) {
		super(root);
	}

	public abstract void update();

	public abstract void setDefault();

	public static Font getNarutoFont() {
		return narutoFont;
	}

	public static Font getNarutoFontsmall() {
		return narutoFontsmall;
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
