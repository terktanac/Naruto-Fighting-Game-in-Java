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
	private static Pane root = new Pane();
	public IntroScreen(Main main){
		super(root);
		Image background = new Image(ClassLoader.getSystemResource("background/konoha_sky.jpg").toString(),1300,740,false,false);
		root.setBackground(new Background(new BackgroundImage(background, null, null, null, null)));
		root.setPrefSize(1280,720);
		
		Text pressKey = new Text("Press any key to START");
		pressKey.setFont(narutoFont);
		pressKey.setFill(Color.ORANGE);
		pressKey.setStroke(Color.WHITE);
		pressKey.setTranslateX(370);
		pressKey.setTranslateY(550);		
		
		//Intro video, to use changing scene would be a better way.
		MediaPlayer vsource = new MediaPlayer(new Media(ClassLoader.getSystemResource("Untitled.mp4").toString()));
		//vsource.setStopTime(Duration.seconds(6));
		vsource.setAutoPlay(true);
		MediaView mediaview = new MediaView(vsource);
		mediaview.setFitWidth(1300);
		mediaview.setFitHeight(740);
		mediaview.setX(0);
		mediaview.setY(0);
		/*FadeTransition transition = new FadeTransition(Duration.seconds(30),mediaview);
		transition.setToValue(0.0);
		transition.play();*/
		
		//Logo
		ImageView imageView = new ImageView(new Image(ClassLoader.getSystemResource("icon/logo_new.png").toString(), 800, 500, true, true));
		imageView.setTranslateX(260);
		imageView.setTranslateY(120);
		imageView.prefWidth(1000);
		
		// To Blink
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.3), evt -> pressKey.setVisible(true)),
				new KeyFrame(Duration.seconds(0.7), evt -> pressKey.setVisible(false)));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
		
		// Music
		MediaPlayer player = new MediaPlayer(new Media(ClassLoader.getSystemResource("menu/Blood Circulator.mp3").toString()));
		//player.setAutoPlay(true);
		Timeline timeline2 = new Timeline(new KeyFrame(
		        Duration.millis(1300),
		        ae -> {player.play();}));
		timeline2.play();
		
		setOnKeyPressed(new EventHandler<KeyEvent>() {
			MediaPlayer choose = new MediaPlayer(new Media(ClassLoader.getSystemResource("accept.wav").toString()));
			@Override
			public void handle(KeyEvent event) {
				Timeline load = new Timeline(new KeyFrame(Duration.millis(3000), ae ->{main.ChangeScene((Scene)main.getMainmenu());})
						,new KeyFrame(Duration.millis(100), ae->{choose.play();}));
				main.ChangeScene((Scene)main.getLoadscreen());
				System.out.println("Skipped Intro");
				load.play();
				player.stop();
				choose.play();
				main.setState(1);
			}
		});
		root.getChildren().addAll(imageView,pressKey,mediaview);
		Timeline timeline1 = new Timeline(new KeyFrame(
		        Duration.millis(6450),
		        ae -> {FadeTransition transition = new FadeTransition(Duration.millis(50),mediaview);
				transition.setToValue(0.0);
				transition.play();}));
		timeline1.play();
		//root.getChildren().addAll(pressKey,imageView);

	}
}
