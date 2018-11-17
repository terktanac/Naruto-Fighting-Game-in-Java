package main;

import java.io.IOException;

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
import javafx.scene.media.MediaPlayer.Status;
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
<<<<<<< HEAD
		IntroScreen introscreen = new IntroScreen();
		Scene intro = new Scene(introscreen);
		Scene mainmenu = new Scene(new MainmenuScreen());
=======
		Pane root = new Pane();
		root.setBackground(new Background(new BackgroundImage(background, null, null, null, null)));
		MainmenuScreen NextScene = new MainmenuScreen();
		root.setPrefSize(1280, 720);
		Text pressKey = new Text("Press any key to START");
		pressKey.setFont(narutoFont);
		pressKey.setFill(Color.WHITE);
		pressKey.setTranslateX(370);
		pressKey.setTranslateY(550);		
		
		//Intro video, to use changing scene would be a better way.
		MediaPlayer vsource = new MediaPlayer(new Media(ClassLoader.getSystemResource("Untitled.mp4").toString()));
		vsource.setAutoPlay(true);
		MediaView mediaview = new MediaView(vsource);
		mediaview.setX(5);
		mediaview.setScaleX(1.2);
		root.getChildren().add(mediaview);
		
		imageView = new ImageView(new Image(ClassLoader.getSystemResource("icon/logo.png").toString(), 600, 300, true, true));
		imageView.setTranslateX(350);
		imageView.setTranslateY(120);
		imageView.prefWidth(1000);
		root.getChildren().addAll(imageView, pressKey);
		Scene scene = new Scene(root);

		primaryStage.setTitle("Naruto Ultimate Ninja by C&T");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

		// To Blink
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.2), evt -> pressKey.setFill(Color.ORANGE)),
				new KeyFrame(Duration.seconds(0.1), evt -> pressKey.setFill(Color.PINK)));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
		
		// Music
		MediaPlayer player = new MediaPlayer(new Media(ClassLoader.getSystemResource("menu/Blood Circulator.mp3").toString()));
		player.setAutoPlay(true);
>>>>>>> 0c5f794c637b6f45a8db05f7a1652d89268e8576
		
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
