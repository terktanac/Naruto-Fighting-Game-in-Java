package Scenes;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;

public class LoadingScreen extends myScene {
	private static Pane root = new Pane();
	private Image background = new Image(ClassLoader.getSystemResource("background/four_seal.gif").toString(), 1280,
			740, false, false);

	public LoadingScreen() {
		super(root);
		root.setPrefSize(1280, 720);
		root.setBackground(new Background(new BackgroundImage(background, null, null, null, null)));

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDefault() {
		// TODO Auto-generated method stub

	}

}
