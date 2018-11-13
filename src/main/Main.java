package main;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
	
	private static final Font FONT = new Font("/CharsAndSFX/njnaruto.ttf", 25); //fix font
	@Override
	public void start(Stage primaryStage) throws Exception {
		Pane root = new Pane();
		root.setPrefSize(1280, 720);

		Rectangle background = new Rectangle(1280,720);
		Text pressKey = new Text("Press any key to START");
		pressKey.setFont(FONT);
		pressKey.setFill(Color.WHITE);
		pressKey.setTranslateX(440);
		pressKey.setTranslateY(360);
		root.getChildren().addAll(background,pressKey);
		primaryStage.setTitle("Naruto Ultimate Ninja by C&T");
		primaryStage.setScene(new Scene(root));
		primaryStage.setResizable(false);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);

	}

}
