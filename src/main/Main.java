package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;


public class Main extends Application {
	private Stage stage ;
	private int state = 0 ; //0=start(intro) 1=menu 2=play 3=pause
	private IntroScreen intro;
	private MainMenuScreen mainmenu;
	private MultiPlayerScreen multiplayer;
	private OptionScreen optionscreen;
	private LoadingScreen loadscreen;
	private GameScreen gamescreen;
	private MapChooseScreen mapscreen;
	private Controller player1;
	private Controller player2;
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.stage = primaryStage;

		intro = new IntroScreen(this);
		mainmenu = new MainMenuScreen(this);
		multiplayer = new MultiPlayerScreen(this);
		optionscreen = new OptionScreen(this);
		loadscreen = new LoadingScreen(this);
		mapscreen = new MapChooseScreen(this);
		gamescreen = new GameScreen(this);
//		player1 = new Controller(KeyCode.W,KeyCode.S,KeyCode.A,KeyCode.D,KeyCode.J,KeyCode.K,KeyCode.L,KeyCode.I);
//		player1.setScene(optionscreen);
//		player1.run();
//		player2 = new Controller(KeyCode.W,KeyCode.S,KeyCode.A,KeyCode.D,KeyCode.J,KeyCode.K,KeyCode.L,KeyCode.I);
//		player2.setScene(optionscreen);
//		player2.run();
		
		stage.getIcons().add(new Image(ClassLoader.getSystemResource("icon/icon.png").toString()));
		stage.setTitle("Naruto Ultimate Ninja Storm Java Edition by C&T");
		stage.setScene(intro);
		stage.setResizable(false);
		stage.show();

	}
	public void ChangeScene(Scene nextScene) {
		stage.setScene(nextScene);
	}

	public static void main(String[] args) {
		launch(args);

	}
	public Stage getStage() {
		return stage;
	}
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public IntroScreen getIntro() {
		return intro;
	}
	public void setIntro(IntroScreen intro) {
		this.intro = intro;
	}
	public MainMenuScreen getMainmenu() {
		return mainmenu;
	}
	public void setMainmenu(MainMenuScreen mainmenu) {
		this.mainmenu = mainmenu;
	}
	public OptionScreen getOptionscreen() {
		return optionscreen;
	}
	public void setOptionscreen(OptionScreen optionscreen) {
		this.optionscreen = optionscreen;
	}
	public MultiPlayerScreen getMultiplayer() {
		return multiplayer;
	}
	public void setMultiplayer(MultiPlayerScreen multiplayer) {
		this.multiplayer = multiplayer;
	}
	public LoadingScreen getLoadscreen() {
		return loadscreen;
	}

	public void setLoadscreen(LoadingScreen loadscreen) {
		this.loadscreen = loadscreen;
	}

	public MapChooseScreen getMapscreen() {
		return mapscreen;
	}
	public void setMapscreen(MapChooseScreen mapscreen) {
		this.mapscreen = mapscreen;
	}
	public GameScreen getGamescreen() {
		return gamescreen;
	}
	public void setGamescreen(GameScreen gamescreen) {
		this.gamescreen = gamescreen;
	}

	
}