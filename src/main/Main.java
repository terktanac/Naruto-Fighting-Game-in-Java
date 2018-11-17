package main;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
	private Font narutoFont = Font.loadFont(ClassLoader.getSystemResource("fonts/njnaruto.ttf").toExternalForm(), 50);
	private Stage primaryStage;
	private ImageView imageView;
	private Image background = new Image(ClassLoader.getSystemResource("background/konoha_sky.jpg").toString(),1300,740,false,false);
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		IntroScreen introscreen = new IntroScreen();
		Scene intro = new Scene(introscreen);
		Scene mainmenu = new Scene(new MainmenuScreen());
		
		// Change Scene not good need to change
		intro.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				primaryStage.setScene(mainmenu);
				System.out.println("PRESSED");
				introscreen.player.stop();
			}
		});
		primaryStage.setTitle("Naruto Ultimate Ninja by C&T");
		primaryStage.setScene(intro);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	public void changeScene(String fxml) throws IOException {
		Parent pane = FXMLLoader.load(getClass().getResource(fxml));

		primaryStage.getScene().setRoot(pane);
	}

	public static void main(String[] args) {
		launch(args);

	}

}
