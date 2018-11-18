package main;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
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
import javafx.util.Duration;

public class IntroScreen extends Scene{
	private Font narutoFont = Font.loadFont(ClassLoader.getSystemResource("fonts/njnaruto.ttf").toExternalForm(), 50);
	private ImageView imageView = new ImageView(new Image(ClassLoader.getSystemResource("icon/logo.png").toString(), 600, 300, true, true));
	private Image background = new Image(ClassLoader.getSystemResource("background/konoha_sky.jpg").toString(),1300,740,false,false);
	MediaPlayer player = new MediaPlayer(new Media(ClassLoader.getSystemResource("menu/Blood Circulator.mp3").toString()));
	static Pane root = new Pane();
	public IntroScreen(Main main){
		super(root);
		
		root.setBackground(new Background(new BackgroundImage(background, null, null, null, null)));
		root.setPrefSize(1280,720);
		
		Text pressKey = new Text("Press any key to START");
		pressKey.setFont(narutoFont);
		pressKey.setFill(Color.WHITE);
		pressKey.setTranslateX(370);
		pressKey.setTranslateY(550);		
		
		//Intro video, to use changing scene would be a better way.
		MediaPlayer vsource = new MediaPlayer(new Media(ClassLoader.getSystemResource("Untitled.mp4").toString()));
		vsource.setAutoPlay(true);
		MediaView mediaview = new MediaView(vsource);
		mediaview.setFitWidth(1300);
		mediaview.setFitHeight(740);
		mediaview.setX(0);
		mediaview.setY(0);
		FadeTransition transition = new FadeTransition(Duration.seconds(30),mediaview);
		transition.setToValue(0.0);
		transition.play();	

		//Logo
		imageView.setTranslateX(350);
		imageView.setTranslateY(120);
		imageView.prefWidth(1000);
		
		// To Blink
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.2), evt -> pressKey.setFill(Color.ORANGE)),
				new KeyFrame(Duration.seconds(0.1), evt -> pressKey.setFill(Color.PINK)));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
		
		// Music
		player.setAutoPlay(true);
		
		root.getChildren().addAll(mediaview,pressKey,imageView);
		
		setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				main.ChangeScene((Scene)main.mainmenu);
				System.out.println("Skipped Intro");
				player.stop();
				main.mainmenu.state = 0;
			}
		});
	}
}
