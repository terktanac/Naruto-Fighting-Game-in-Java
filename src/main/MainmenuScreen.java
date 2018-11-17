package main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
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
		
		VBox MenuBox = new VBox(5);
		MenuBox.setMaxSize(100, 100);
		MenuBox.setTranslateX(450);
		ListMenu vsComp = new ListMenu("VS Comp.");
		ListMenu vsHuman = new ListMenu("VS Human");
		ListMenu option = new ListMenu("Option");
		MenuBox.getChildren().addAll(vsComp,vsHuman,option);
		MenuBox.setAlignment(Pos.CENTER);

		root.getChildren().addAll(MenuBox);
	}

	public class ListMenu extends HBox {
		private Text name;
		private ImageView kunai = new ImageView(new Image(ClassLoader.getSystemResource("icon/kunai.png").toString()));
		private Font font = new Font(18);

		ListMenu(String text) {
			this.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
			this.setPrefSize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
			this.setAlignment(Pos.CENTER);
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
