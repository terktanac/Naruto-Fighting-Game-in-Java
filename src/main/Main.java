package main;

import java.io.IOException;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
//	private Font narutoFont = new Font(getClass().getResource("//CharsAndSFX//fonts//njnaruto.ttf").toExternalForm(), 50); fix pls
	private Stage primaryStage;
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		Pane root = new Pane();
		MainmenuScreen NextScene = new MainmenuScreen();
		root.setPrefSize(1280, 720);
		Rectangle background = new Rectangle(1280, 720);
		Text pressKey = new Text("Press any key to START");
		pressKey.setFont(new Font(40));
		pressKey.setFill(Color.WHITE);
		pressKey.setTranslateX(420);
		pressKey.setTranslateY(500);
		
		root.getChildren().addAll(background, pressKey);
		
		Scene scene = new Scene(root);
		
		primaryStage.setTitle("Naruto Ultimate Ninja by C&T");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
		//To Blink
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.2), evt -> pressKey.setFill(Color.YELLOW)),
				new KeyFrame(Duration.seconds(0.1), evt -> pressKey.setFill(Color.WHITE)));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
		//Change Scene
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				primaryStage.getScene().setRoot(NextScene);
				System.out.println("PRESSED");
			}
		});
	}

	public void changeScene(String fxml) throws IOException {
		Parent pane = FXMLLoader.load(getClass().getResource(fxml));

		primaryStage.getScene().setRoot(pane);
	}

	public static void main(String[] args) {
		launch(args);

	}

}
