package main;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MainMenuScreen extends Scene {
	static Pane root = new Pane();
	private Font narutoFont = Font.loadFont(ClassLoader.getSystemResource("fonts/njnaruto.ttf").toExternalForm(), 50);
	private Image background = new Image(ClassLoader.getSystemResource("background/final_valley_bg.jpg").toString(),1280,740,false,false);
	VBox MenuBox = new VBox(5);
	int Oldchoice = 0 ;
	int NewChoice = 0 ;
	public MainMenuScreen(Main main) {
		super(root);
		root.setPrefSize(1280, 720);
		root.setBackground(new Background(new BackgroundImage(background, null, null, null, null)));
		//-----------<Menu Box>---------------------------------------------------------------
		MenuBox.setTranslateX(350);
		MenuBox.setTranslateY(250);
		//----------<Menu List>---------------------------------------------------------------
		ListMenu vsComp = new ListMenu("Singleplayer");
		ListMenu vsHuman = new ListMenu("Multiplayer");
		ListMenu option = new ListMenu("Option");
		ListMenu exit = new ListMenu("Exit");
		//----------<\Menu List>---------------------------------------------------------------
		MenuBox.getChildren().addAll(vsComp,vsHuman,option,exit);
		((ListMenu) MenuBox.getChildren().get(Oldchoice)).setActive(true);
		MenuBox.setAlignment(Pos.CENTER);
		MenuBox.setSpacing(30);
		//-----------<\Menu Box>---------------------------------------------------------------
		
		root.getChildren().addAll(MenuBox);
		
		setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				MediaPlayer click = new MediaPlayer(new Media(ClassLoader.getSystemResource("lighter.wav").toString()));
				click.play();
				KeyCode key = event.getCode();
				System.out.println("MainMenu:Pressed " + key.toString());
				if (key == KeyCode.SPACE || key == KeyCode.ENTER) {
					if(Oldchoice==0) {main.ChangeScene(main.loadscreen);}
					else if(Oldchoice==1) {main.ChangeScene(main.loadscreen);}
					else if(Oldchoice==MenuBox.getChildren().size()-1) {System.exit(1);}
				}
				else if (key == KeyCode.UP || key == KeyCode.LEFT || key == KeyCode.W || key == KeyCode.A || key == KeyCode.KP_UP || key == KeyCode.KP_LEFT) {
						if (Oldchoice == 0) {NewChoice = 3;} 
						else {NewChoice = Oldchoice - 1;}
				} 
				else if (key == KeyCode.DOWN ||key == KeyCode.RIGHT || key == KeyCode.S || key == KeyCode.D || key == KeyCode.KP_DOWN || key == KeyCode.KP_RIGHT) {
						if (Oldchoice == MenuBox.getChildren().size()-1) {NewChoice = 0;} 
						else {NewChoice = Oldchoice + 1;}
				}
				((ListMenu) MenuBox.getChildren().get(Oldchoice)).setActive(false);
				((ListMenu) MenuBox.getChildren().get(NewChoice)).setActive(true);
				Oldchoice = NewChoice;
			}
		});
	
	}

	public class ListMenu extends HBox {
		private Text name;
		private ImageView kunai = new ImageView(new Image(ClassLoader.getSystemResource("icon/kunai.png").toString(),130,40,true,true));
//		private Runnable script;

		ListMenu(String text) {
//			this.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
			this.setSpacing(10);
			this.setAlignment(Pos.CENTER);
			name = new Text(text);
			name.setFont(MainMenuScreen.this.narutoFont);
			name.setStrokeWidth(2);
			getChildren().addAll(kunai, name);
			setActive(false);
		}

		void setActive(boolean check) {
			kunai.setVisible(check);
			name.setStroke(check ? Color.WHITE : Color.GRAY);
		}
		//dont know why use Thread here
//		public void setOnActivate(Runnable r) {
//            script = r;
//        }
//		public void activate() {
//	            if (script != null)
//	                script.run();
//	     }
		
	}
}