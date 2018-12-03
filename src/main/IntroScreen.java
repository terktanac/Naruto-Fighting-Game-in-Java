 package main;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
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
import javafx.scene.text.Text;

import javafx.util.Duration;

public class IntroScreen extends myScene{
	private static Pane root = new Pane();
	private MediaPlayer player;
	public IntroScreen(){
		super(root);
//		this.main = main ;
		Image background = new Image(ClassLoader.getSystemResource("background/konoha_sky.jpg").toString(),1300,740,false,false);
		root.setBackground(new Background(new BackgroundImage(background, null, null, null, null)));
		root.setPrefSize(1280,720);
		
		Text pressKey = new Text("Press any key to START");
		pressKey.setFont(getNarutoFont());
		pressKey.setFill(Color.ORANGE);
		pressKey.setStroke(Color.WHITE);
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
		player = new MediaPlayer(new Media(ClassLoader.getSystemResource("menu/Blood Circulator.mp3").toString()));
		//player.setAutoPlay(true);
		Timeline timeline2 = new Timeline(new KeyFrame(
		        Duration.millis(3100),
		        ae -> {player.play();}));
		timeline2.play();
		
		setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				Timeline load = new Timeline(new KeyFrame(Duration.millis(1000), ae ->{Main.ChangeScene(Main.getMainmenu());})
						,new KeyFrame(Duration.millis(100), ae->{choose.play();}));
				Main.ChangeScene(Main.getLoadscreen());
				System.out.println("Skipped Intro");
				load.play();
		player.stop();
				choose.play();
				Main.setState(1);
			}
		});
		
		Timeline timeline1 = new Timeline(new KeyFrame(
		        Duration.millis(8100),
		        ae -> {FadeTransition transition = new FadeTransition(Duration.millis(100),mediaview);
				transition.setToValue(0.0);
				transition.play();}));
		timeline1.play();
		root.getChildren().addAll(imageView,pressKey,mediaview);

	}
	public void keyHandling() {
		setOnKeyPressed(new EventHandler<KeyEvent>() {
			MediaPlayer choose = new MediaPlayer(new Media(ClassLoader.getSystemResource("accept.wav").toString()));
			@Override
			public void handle(KeyEvent event) {
				Timeline load = new Timeline(new KeyFrame(Duration.millis(1000), ae ->{Main.ChangeScene(Main.getMainmenu());})
						,new KeyFrame(Duration.millis(100), ae->{choose.play();}));
				Main.ChangeScene(Main.getLoadscreen());
				System.out.println("Skipped Intro");
				load.play();
				player.stop();
				choose.play();
				Main.setState(1);
			}
		});
	}
	@Override
	public void upPressed() {
		
	}
	@Override
	public void downPressed() {
	}
	@Override
	public void leftPressed() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void rightPressed() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void meleePressed() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void rangePressed() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void dodgePressed() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void blockPressed() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void SpacePressed() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void EnterPressed() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void nonePressed() {
		// TODO Auto-generated method stub
		
	}
}
