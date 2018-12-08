package main;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import scenes.GameScreen;
import scenes.IntroScreen;
import scenes.LoadingScreen;
import scenes.MainMenuScreen;
import scenes.MapChooseScreen;
import scenes.MultiPlayerScreen;
import scenes.MyScene;
import scenes.OptionScreen;

public class Main extends Application {
	private static Stage stage;
	private static IntroScreen intro;
	private static MainMenuScreen mainmenu;
	private static MultiPlayerScreen multiplayer;
	private static OptionScreen optionscreen;
	private static LoadingScreen loadscreen;
	private static GameScreen gamescreen;
	private static MapChooseScreen mapscreen;
	private static Controller player;

	@Override
	public final void start(Stage primaryStage) throws Exception {
		Main.stage = primaryStage;

		player = new Controller(KeyCode.W, KeyCode.S, KeyCode.A, KeyCode.D, KeyCode.J, KeyCode.K, KeyCode.L, KeyCode.I,
				KeyCode.UP, KeyCode.DOWN, KeyCode.LEFT, KeyCode.RIGHT, KeyCode.NUMPAD1, KeyCode.NUMPAD2,
				KeyCode.NUMPAD3, KeyCode.NUMPAD5);

		intro = new IntroScreen();
		mainmenu = new MainMenuScreen();
		multiplayer = new MultiPlayerScreen();
		optionscreen = new OptionScreen();
		loadscreen = new LoadingScreen();
		mapscreen = new MapChooseScreen();
		gamescreen = new GameScreen();

		ChangeScene(intro);
		
		stage.getIcons().add(new Image(ClassLoader.getSystemResource("icon/icon.png").toString()));
		stage.setTitle("Naruto Ultimate Ninja Storm Java Edition by C&T");
		stage.setScene(intro);
		stage.setResizable(false);
		stage.show();

	}

	public static void ChangeScene(MyScene nextScene) {
		stage.setScene(nextScene);
		player.setScene(nextScene);
		player.run();
	}

	public static void setDefault() {
		intro.setDefault();
		mainmenu.setDefault();
		multiplayer.setDefault();
		optionscreen.setDefault();
		loadscreen.setDefault();
		mapscreen.setDefault();
		gamescreen.setDefault();
	}

	public static void main(String[] args) {
		launch(args);

	}

	public static Stage getStage() {
		return stage;
	}

	public static IntroScreen getIntro() {
		return intro;
	}

	public static MainMenuScreen getMainmenu() {
		return mainmenu;
	}

	public static MultiPlayerScreen getMultiplayer() {
		return multiplayer;
	}

	public static OptionScreen getOptionscreen() {
		return optionscreen;
	}

	public static LoadingScreen getLoadscreen() {
		return loadscreen;
	}

	public static GameScreen getGamescreen() {
		return gamescreen;
	}

	public static MapChooseScreen getMapscreen() {
		return mapscreen;
	}

	public static Controller getPlayer() {
		return player;
	}

	

}