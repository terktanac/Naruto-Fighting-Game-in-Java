package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application {
	private Stage stage ;
	int state = 0 ; //0=start(intro) 1=menu 2=play 3=pause
	IntroScreen intro;
	MainMenuScreen mainmenu;
	LoadScreen loadscreen;
	OptionScreen optionscreen;
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.stage = primaryStage;

		intro = new IntroScreen(this);
		mainmenu = new MainMenuScreen(this);
		loadscreen = new LoadScreen(this);
		optionscreen = new OptionScreen(this);
		
		stage.getIcons().add(new Image(ClassLoader.getSystemResource("icon/icon.png").toString()));
		stage.setTitle("Naruto Ultimate Ninja Storm by C&T");
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

}