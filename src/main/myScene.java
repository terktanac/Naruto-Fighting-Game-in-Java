package main;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public abstract class myScene extends Scene{

	public myScene(Pane root) {
		super(root);
	}
	public abstract void upPressed();
	public abstract void downPressed();
	public abstract void leftPressed();
	public abstract void rightPressed();
}
