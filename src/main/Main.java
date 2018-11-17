package main;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		IntroScreen intro = new IntroScreen();
		MainmenuScreen mainmenu = new MainmenuScreen();

		primaryStage.setTitle("Naruto Ultimate Ninja by C&T");
		primaryStage.setScene(intro);
		primaryStage.setResizable(false);
		primaryStage.show();

		// Change Scene
		intro.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				primaryStage.setScene(mainmenu);
				System.out.println("PRESSED");
				intro.player.stop();
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
