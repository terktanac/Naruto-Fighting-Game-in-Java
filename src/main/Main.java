package main;

import exception.ImageNotFoundException;
import exception.SoundNotFoundException;
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
		try {
			checkImage();
		} catch (ImageNotFoundException e) {
			System.out.println("Please contact developers.");
		}
		try {
			checkSound();
		} catch (SoundNotFoundException e) {
			System.out.println("Please contact developers.");
		}
		try {
			intro = new IntroScreen();
			mainmenu = new MainMenuScreen();
			multiplayer = new MultiPlayerScreen();
			optionscreen = new OptionScreen();
			loadscreen = new LoadingScreen();
			mapscreen = new MapChooseScreen();
			gamescreen = new GameScreen();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Please contact developers.");
		}

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

	public static void checkImage() throws ImageNotFoundException {
		if (ClassLoader.getSystemResource("sys/big_smoke_log.png") == null)
			throw new ImageNotFoundException("sys/big_smoke_log.png");

		if (ClassLoader.getSystemResource("characters/naruto_sage/naruto_sage_Clear.png") == null)
			throw new ImageNotFoundException("characters/naruto_sage/naruto_sage_Clear.png");

		if (ClassLoader.getSystemResource("characters/sasuke_aka/sasuke_aka_Clear.png") == null)
			throw new ImageNotFoundException("characters/sasuke_aka/sasuke_aka_Clear.png");

		if (ClassLoader.getSystemResource("sys/amaterasu.png") == null)
			throw new ImageNotFoundException("sys/amaterasu.png");

		if (ClassLoader.getSystemResource("sys/chidori.png") == null)
			throw new ImageNotFoundException("sys/chidori.png");

		if (ClassLoader.getSystemResource("sys/katon2.png") == null)
			throw new ImageNotFoundException("sys/katon2.png");

		if (ClassLoader.getSystemResource("sys/weapons.png") == null)
			throw new ImageNotFoundException("sys/weapons.png");

		if (ClassLoader.getSystemResource("characters/naruto_sage/naruto_sage_s.png") == null)
			throw new ImageNotFoundException("characters/naruto_sage/naruto_sage_s.png");
		if (ClassLoader.getSystemResource("characters/sasuke_aka/sasuke_aka_s.png") == null)
			throw new ImageNotFoundException("characters/sasuke_aka/sasuke_aka_s.png");
		if (ClassLoader.getSystemResource("icon/healthbar.png") == null)
			throw new ImageNotFoundException("icon/healthbar.png");
		if (ClassLoader.getSystemResource("icon/healthbarborder.png") == null)
			throw new ImageNotFoundException("icon/healthbarborder.png");

		if (ClassLoader.getSystemResource("background/konoha_sky.jpg") == null)
			throw new ImageNotFoundException("background/konoha_sky.jpg");
		if (ClassLoader.getSystemResource("Untitled.mp4") == null)
			throw new ImageNotFoundException("Untitled.mp4");
		if (ClassLoader.getSystemResource("icon/logo_new.png") == null)
			throw new ImageNotFoundException("icon/logo_new.png");

		if (ClassLoader.getSystemResource("background/four_seal.gif") == null)
			throw new ImageNotFoundException("background/four_seal.gif");

		if (ClassLoader.getSystemResource("background/final_valley_bg.jpg") == null)
			throw new ImageNotFoundException("background/final_valley_bg.jpg");

		if (ClassLoader.getSystemResource("background/arena.png") == null)
			throw new ImageNotFoundException("background/arena.png");
		if (ClassLoader.getSystemResource("background/arena_active.jpg") == null)
			throw new ImageNotFoundException("background/arena_active.jpg");
		if (ClassLoader.getSystemResource("background/final_valley1.jpg") == null)
			throw new ImageNotFoundException("background/final_valley1.jpg");
		if (ClassLoader.getSystemResource("background/final_valley_active.jpg") == null)
			throw new ImageNotFoundException("background/final_valley_active.jpg");
		if (ClassLoader.getSystemResource("background/forest.png") == null)
			throw new ImageNotFoundException("background/forest.png");
		if (ClassLoader.getSystemResource("background/forest_active.jpg") == null)
			throw new ImageNotFoundException("background/forest_active.jpg");
		if (ClassLoader.getSystemResource("background/hospital.png") == null)
			throw new ImageNotFoundException("background/hospital.png");
		if (ClassLoader.getSystemResource("background/hospital_active.jpg") == null)
			throw new ImageNotFoundException("background/hospital_active.jpg");
		if (ClassLoader.getSystemResource("background/konoha_road.jpg") == null)
			throw new ImageNotFoundException("background/konoha_road.jpg");
		if (ClassLoader.getSystemResource("background/konoha_road_active.jpg") == null)
			throw new ImageNotFoundException("background/konoha_road_active.jpg");
		if (ClassLoader.getSystemResource("background/konoha_village.png") == null)
			throw new ImageNotFoundException("background/konoha_village.png");
		if (ClassLoader.getSystemResource("background/konoha_village_active.jpg") == null)
			throw new ImageNotFoundException("background/konoha_village_active.jpg");
		if (ClassLoader.getSystemResource("background/shinobi2.jpg") == null)
			throw new ImageNotFoundException("");

		if (ClassLoader.getSystemResource("characters/naruto_sage/naruto_sage_mugs_2.png") == null)
			throw new ImageNotFoundException("characters/naruto_sage/naruto_sage_mugs_2.png");
		if (ClassLoader.getSystemResource("characters/sasuke_aka/sasuke_aka_mugs_2.png") == null)
			throw new ImageNotFoundException("characters/sasuke_aka/sasuke_aka_mugs_2.png");
		if (ClassLoader.getSystemResource("characters/naruto_sage/naruto_sage_mugs_3.png") == null)
			throw new ImageNotFoundException("characters/naruto_sage/naruto_sage_mugs_3.png");
		if (ClassLoader.getSystemResource("characters/sasuke_aka/sasuke_aka_mugs_3.png") == null)
			throw new ImageNotFoundException("characters/sasuke_aka/sasuke_aka_mugs_3.png");
		if (ClassLoader.getSystemResource("characters/naruto_sage/face.jpg") == null)
			throw new ImageNotFoundException("characters/naruto_sage/face.jpg");
		if (ClassLoader.getSystemResource("characters/naruto_sage/face_active.jpg") == null)
			throw new ImageNotFoundException("characters/naruto_sage/face_active.jpg");
		if (ClassLoader.getSystemResource("characters/sasuke_aka/face.jpg") == null)
			throw new ImageNotFoundException("characters/sasuke_aka/face.jpg");
		if (ClassLoader.getSystemResource("characters/sasuke_aka/face_active.jpg") == null)
			throw new ImageNotFoundException("characters/sasuke_aka/face_active.jpg");

		if (ClassLoader.getSystemResource("fonts/njnaruto.ttf") == null)
			throw new ImageNotFoundException("fonts/njnaruto.ttf");

		if (ClassLoader.getSystemResource("background/paper.jpg") == null)
			throw new ImageNotFoundException("background/paper.jpg");
		if (ClassLoader.getSystemResource("icon/shuriken.png") == null)
			throw new ImageNotFoundException("icon/shuriken.png");
		if (ClassLoader.getSystemResource("icon/frame.png") == null)
			throw new ImageNotFoundException("icon/frame.png");
	}

	public static void checkSound() throws SoundNotFoundException {
		if (ClassLoader.getSystemResource("characters/naruto_sage/sfx_hit.wav") == null)
			throw new SoundNotFoundException("characters/naruto_sage/sfx_hit.wav");
		if (ClassLoader.getSystemResource("characters/naruto_sage/sfx_rasengan.wav") == null)
			throw new SoundNotFoundException("characters/naruto_sage/sfx_rasengan.wav");
		if (ClassLoader.getSystemResource("rasengan_ready.wav") == null)
			throw new SoundNotFoundException("rasengan_ready.wav");
		if (ClassLoader.getSystemResource("rasengan_hit.wav") == null)
			throw new SoundNotFoundException("rasengan_hit.wav");
		if (ClassLoader.getSystemResource("characters/naruto_sage/sfx_rasenrengan.wav") == null)
			throw new SoundNotFoundException("characters/naruto_sage/sfx_rasenrengan.wav");
		if (ClassLoader.getSystemResource("characters/naruto_sage/sfx_rasenshuri.wav") == null)
			throw new SoundNotFoundException("characters/naruto_sage/sfx_rasenshuri.wav");
		if (ClassLoader.getSystemResource("characters/naruto_sage/sfx_injured.wav") == null)
			throw new SoundNotFoundException("characters/naruto_sage/sfx_injured.wav");
		if (ClassLoader.getSystemResource("characters/naruto_sage/sfx_injured2.wav") == null)
			throw new SoundNotFoundException("characters/naruto_sage/sfx_injured2.wav");

		if (ClassLoader.getSystemResource("characters/sasuke_aka/sfx_chidori.wav") == null)
			throw new SoundNotFoundException("characters/sasuke_aka/sfx_chidori.wav");
		if (ClassLoader.getSystemResource("chidori_loop.wav") == null)
			throw new SoundNotFoundException("chidori_loop.wav");
		if (ClassLoader.getSystemResource("characters/sasuke_aka/sfx_katon.wav") == null)
			throw new SoundNotFoundException("characters/sasuke_aka/sfx_katon.wav");
		if (ClassLoader.getSystemResource("characters/sasuke_aka/sfx_ult.wav") == null)
			throw new SoundNotFoundException("characters/sasuke_aka/sfx_ult.wav");
		if (ClassLoader.getSystemResource("characters/sasuke_aka/sfx_injured.wav") == null)
			throw new SoundNotFoundException("characters/sasuke_aka/sfx_injured.wav");
		if (ClassLoader.getSystemResource("characters/sasuke_aka/sfx_injured2.wav") == null)
			throw new SoundNotFoundException("characters/sasuke_aka/sfx_injured2.wav");

		if (ClassLoader.getSystemResource("sharingan.wav") == null)
			throw new SoundNotFoundException("sharingan.wav");
		if (ClassLoader.getSystemResource("chidori_hit.wav") == null)
			throw new SoundNotFoundException("chidori_hit.wav");

		if (ClassLoader.getSystemResource("explosion.wav") == null)
			throw new SoundNotFoundException("explosion.wav");

		if (ClassLoader.getSystemResource("rasenshuriken.wav") == null)
			throw new SoundNotFoundException("rasenshuriken.wav");
		if (ClassLoader.getSystemResource("rasenshuriken4.wav") == null)
			throw new SoundNotFoundException("rasenshuriken4.wav");

		if (ClassLoader.getSystemResource("game/Nankou_Furaku.mp3") == null)
			throw new SoundNotFoundException("game/Nankou_Furaku.mp3");

		if (ClassLoader.getSystemResource("menu/Blood_Circulator.mp3") == null)
			throw new SoundNotFoundException("");

		if (ClassLoader.getSystemResource("menu/Gekiha.mp3") == null)
			throw new SoundNotFoundException("menu/Gekiha.mp3");

		if (ClassLoader.getSystemResource("accept.wav") == null)
			throw new SoundNotFoundException("accept.wav");
		if (ClassLoader.getSystemResource("lighter.wav") == null)
			throw new SoundNotFoundException("lighter.wav");
		if (ClassLoader.getSystemResource("SUDA.wav") == null)
			throw new SoundNotFoundException("SUDA.wav");

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