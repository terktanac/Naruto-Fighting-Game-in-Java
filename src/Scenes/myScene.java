package Scenes;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;

public abstract class myScene extends Scene {
	private static Font narutoFont = Font.loadFont(ClassLoader.getSystemResource("fonts/njnaruto.ttf").toExternalForm(),
			50);
	private static AudioClip choose = new AudioClip("file:soundfx/accept.wav");
	private static AudioClip click = new AudioClip("file:soundfx/lighter.wav");

	public myScene(Pane root) {
		super(root);
	}

	public abstract void update();

	public abstract void setDefault();

	public static Font getNarutoFont() {
		return narutoFont;
	}

	public static void playChoose() {
		choose.play();
	}

	public static void playClick() {
		click.play();
	}
}
