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
		mainmenu.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				KeyCode key = event.getCode();
				System.out.println("Pressed "+key.toString());
				if(key==KeyCode.UP || key==KeyCode.W || key==KeyCode.KP_UP) {
					if(mainmenu.Oldchoice == 0) {mainmenu.NewChoice = 3;}
					else {mainmenu.NewChoice=mainmenu.Oldchoice-1;}
					}
				else if(key == KeyCode.DOWN || key == KeyCode.S || key == KeyCode.KP_DOWN) {
					if(mainmenu.Oldchoice==3) {mainmenu.NewChoice = 0;}
					else {mainmenu.NewChoice = mainmenu.Oldchoice+1;}
				}
				((ListMenu)mainmenu.MenuBox.getChildren().get(mainmenu.Oldchoice)).setActive(false);
				((ListMenu)mainmenu.MenuBox.getChildren().get(mainmenu.NewChoice)).setActive(true);
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
