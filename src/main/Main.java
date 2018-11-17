package main;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import main.MainmenuScreen.ListMenu;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
<<<<<<< HEAD
		this.primaryStage = primaryStage;
//<<<<<<< HEAD
		IntroScreen introscreen = new IntroScreen();
		Scene intro = new Scene(introscreen);
		Scene mainmenu = new Scene(new MainMenuScreen());
//=======
		Pane root = new Pane();
		root.setBackground(new Background(new BackgroundImage(background, null, null, null, null)));
		MainMenuScreen NextScene = new MainMenuScreen();
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
||||||| merged common ancestors
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
=======

		IntroScreen intro = new IntroScreen();
		MainmenuScreen mainmenu = new MainmenuScreen();
		LoadScreen loadscreen = new LoadScreen();
>>>>>>> 608014e3febda4c910a379f172ff4027281212f9

		primaryStage.setTitle("Naruto Ultimate Ninja by C&T");
		primaryStage.setScene(intro);
		primaryStage.setResizable(false);
		primaryStage.show();

<<<<<<< HEAD
		// To Blink
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.2), evt -> pressKey.setFill(Color.ORANGE)),
				new KeyFrame(Duration.seconds(0.1), evt -> pressKey.setFill(Color.PINK)));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
		
		// Music
		MediaPlayer player = new MediaPlayer(new Media(ClassLoader.getSystemResource("menu/Blood Circulator.mp3").toString()));
		player.setAutoPlay(true);
//>>>>>>> 0c5f794c637b6f45a8db05f7a1652d89268e8576
		
		// Change Scene not good need to change
||||||| merged common ancestors
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
=======
		// Change Scene
>>>>>>> 608014e3febda4c910a379f172ff4027281212f9
		intro.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				primaryStage.setScene(mainmenu);
				System.out.println("PRESSED");
				intro.player.stop();
				mainmenu.state = 0;
			}
		});
		//Don't like this----it's ugly want to change---
		mainmenu.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				KeyCode key = event.getCode();
				System.out.println("Pressed " + key.toString());
				if (key == KeyCode.SPACE || key == KeyCode.ENTER) {
					if(mainmenu.Oldchoice==1) {primaryStage.setScene(loadscreen);}
				}
				else if (key == KeyCode.UP || key == KeyCode.W || key == KeyCode.KP_UP) {
						if (mainmenu.Oldchoice == 0) {mainmenu.NewChoice = 3;} 
						else {mainmenu.NewChoice = mainmenu.Oldchoice - 1;}
				} 
				else if (key == KeyCode.DOWN || key == KeyCode.S || key == KeyCode.KP_DOWN) {
						if (mainmenu.Oldchoice == mainmenu.MenuBox.getChildren().size()-1) {mainmenu.NewChoice = 0;} 
						else {mainmenu.NewChoice = mainmenu.Oldchoice + 1;}
				}
				((ListMenu) mainmenu.MenuBox.getChildren().get(mainmenu.Oldchoice)).setActive(false);
				((ListMenu) mainmenu.MenuBox.getChildren().get(mainmenu.NewChoice)).setActive(true);
				mainmenu.Oldchoice = mainmenu.NewChoice;
				}
		});
		primaryStage.setTitle("Naruto Ultimate Ninja by C&T");
		primaryStage.setScene(intro);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);

	}

}
