package main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MainmenuScreen extends Scene {
	static Pane root = new Pane();
	private int state; // 0=menu 1=play 2=pause 3=load

	public MainmenuScreen() {
		super(root);
		root.setPrefSize(1280, 720);
		root.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		VBox MenuBox = new VBox(10, new Menu("VS Comp."), new Menu("VS Human"), new Menu("Option"));
		MenuBox.setTranslateX(-640);
		root.getChildren().addAll(MenuBox);

	}

	public class Menu extends HBox {
		private Text name;
		private ImageView kunai = new ImageView(new Image(ClassLoader.getSystemResource("icon/kunai.png").toString()));
		private Font font = new Font(18);

		Menu(String text) {
			this.setAlignment(Pos.CENTER);
			this.setPadding(new Insets(10));
			name = new Text(text);
			name.setFont(font);
			getChildren().addAll(kunai, name);
			setActive(false);
		}

		private void setActive(boolean check) {
			kunai.setVisible(check);
			name.setFill(check ? Color.BLACK : Color.GRAY);
		}

	}
}
