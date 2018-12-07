package Scenes;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import javafx.util.Duration;
import main.Controller;
import main.Main;

public class IntroScreen extends myScene {
	private static Pane root = new Pane();
	private AudioClip player = new AudioClip("file:music/menu/Blood_Circulator.mp3");;

	public IntroScreen() {
		super(root);
//		this.main = main ;
		final Image background = new Image(ClassLoader.getSystemResource("background/konoha_sky.jpg").toString(), 1300,
				740, false, false);
		root.setBackground(new Background(new BackgroundImage(background, null, null, null, null)));
		root.setPrefSize(1280, 720);

		final Text pressKey = new Text("Press any key to START");
		pressKey.setFont(getNarutoFont());
		pressKey.setFill(Color.ORANGE);
		pressKey.setStroke(Color.WHITE);
		pressKey.setTranslateX(370);
		pressKey.setTranslateY(550);

		// Intro video, to use changing scene would be a better way.
		final MediaPlayer vsource = new MediaPlayer(
				new Media(ClassLoader.getSystemResource("Untitled.mp4").toString()));

		vsource.setAutoPlay(true);
		final MediaView mediaview = new MediaView(vsource);
		mediaview.setFitWidth(1300);
		mediaview.setFitHeight(740);
		mediaview.setX(0);
		mediaview.setY(0);

		// Logo
		final ImageView imageView = new ImageView(
				new Image(ClassLoader.getSystemResource("icon/logo_new.png").toString(), 800, 500, true, true));
		imageView.setTranslateX(260);
		imageView.setTranslateY(120);
		imageView.prefWidth(1000);

		// To Blink
		final Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.3), evt -> pressKey.setVisible(true)),
				new KeyFrame(Duration.seconds(0.7), evt -> pressKey.setVisible(false)));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
		final Timeline timeline2 = new Timeline(new KeyFrame(Duration.millis(3100), ae -> {
			player.play();
		}));
		timeline2.play();

		final Timeline timeline1 = new Timeline(new KeyFrame(Duration.millis(8100), ae -> {
			FadeTransition transition = new FadeTransition(Duration.millis(100), mediaview);
			transition.setToValue(0.0);
			transition.play();
		}));
		timeline1.play();
		root.getChildren().addAll(imageView, pressKey, mediaview);

	}

	@Override
	public void update() {
		if (Controller.getIsPressedMap1().containsValue(true) || Controller.getIsPressedMap2().containsValue(true)
				|| !Controller.getOtherKeys().isEmpty()) {
			final Timeline load = new Timeline(new KeyFrame(Duration.millis(1000), ae -> {
				Main.ChangeScene(Main.getMainmenu());
			}), new KeyFrame(Duration.millis(100), ae -> {
				playChoose();
			}));
			Main.ChangeScene(Main.getLoadscreen());
			System.out.println("Skipped Intro");
			player.stop();
			load.play();
			Main.getPlayer().setScene(Main.getMainmenu());
			Main.getPlayer().run();

		}
	}

	@Override
	public void setDefault() {
		player.stop();
	}

}
