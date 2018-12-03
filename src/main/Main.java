package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;


public class Main extends Application {
	private static Stage stage ;
	private static int state = 0 ; //0=start(intro) 1=menu 2=play 3=pause
	private static IntroScreen intro;
	private static MainMenuScreen mainmenu;
	private static MultiPlayerScreen multiplayer;
	private static OptionScreen optionscreen;
	private static LoadingScreen loadscreen;
	private static GameScreen gamescreen;
	private static MapChooseScreen mapscreen;
	private static Controller player;

	@Override
	public void start(Stage primaryStage) throws Exception {
		Main.stage = primaryStage;

		intro = new IntroScreen();
		mainmenu = new MainMenuScreen();
		multiplayer = new MultiPlayerScreen();
		optionscreen = new OptionScreen();
		loadscreen = new LoadingScreen();
		mapscreen = new MapChooseScreen();
		gamescreen = new GameScreen();
		
		player = new Controller(KeyCode.W,KeyCode.S,KeyCode.A,KeyCode.D,KeyCode.J,KeyCode.K,KeyCode.L,KeyCode.I,KeyCode.UP,KeyCode.DOWN,KeyCode.LEFT,KeyCode.RIGHT,KeyCode.NUMPAD1,KeyCode.NUMPAD2,KeyCode.NUMPAD3,KeyCode.NUMPAD5);
		player.setScene(intro);
		player.run();
		
		stage.getIcons().add(new Image(ClassLoader.getSystemResource("icon/icon.png").toString()));
		stage.setTitle("Naruto Ultimate Ninja Storm Java Edition by C&T");
		stage.setScene(multiplayer);
		stage.setResizable(false);
		stage.show();

	}
	public static void ChangeScene(Scene nextScene) {
		stage.setScene(nextScene);
	}

	public static void main(String[] args) {
		launch(args);

	}
	public Stage getStage() {
		return stage;
	}
	public int getState() {
		return state;
	}
	public static void setState(int state) {
		Main.state = state;
	}
	public static IntroScreen getIntro() {
		return intro;
	}
	public static void setIntro(IntroScreen intro) {
		Main.intro = intro;
	}
	public static MainMenuScreen getMainmenu() {
		return mainmenu;
	}
	public static void setMainmenu(MainMenuScreen mainmenu) {
		Main.mainmenu = mainmenu;
	}
	public static OptionScreen getOptionscreen() {
		return optionscreen;
	}
	public static void setOptionscreen(OptionScreen optionscreen) {
		Main.optionscreen = optionscreen;
	}
	public static MultiPlayerScreen getMultiplayer() {
		return multiplayer;
	}
	public void setMultiplayer(MultiPlayerScreen multiplayer) {
		Main.multiplayer = multiplayer;
	}
	public static LoadingScreen getLoadscreen() {
		return loadscreen;
	}

	public static void setLoadscreen(LoadingScreen loadscreen) {
		Main.loadscreen = loadscreen;
	}

	public static MapChooseScreen getMapscreen() {
		return mapscreen;
	}
	public static void setMapscreen(MapChooseScreen mapscreen) {
		Main.mapscreen = mapscreen;
	}
	public static  GameScreen getGamescreen() {
		return gamescreen;
	}
	public static void setGamescreen(GameScreen gamescreen) {
		Main.gamescreen = gamescreen;
	}

	
	
}