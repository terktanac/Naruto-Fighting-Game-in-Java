package main;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

<<<<<<< HEAD:src/main/MainMenuScreen.java
public class MainMenuScreen extends Pane {
	private int state; // 0=menu 1=play 2=pause 3=load
	public MainMenuScreen() {
		this.setPrefSize(1280, 720);
		setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		VBox MenuBox = new VBox(10,new Menu("VS Comp."),new Menu("VS Human"),new Menu("Option"));
		MenuBox.setTranslateX(-640);
		getChildren().addAll(MenuBox);
||||||| merged common ancestors
public class MainmenuScreen extends Pane {
	private int state; // 0=menu 1=play 2=pause 3=load
	public MainmenuScreen() {
		this.setPrefSize(1280, 720);
		setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		VBox MenuBox = new VBox(10,new Menu("VS Comp."),new Menu("VS Human"),new Menu("Option"));
		MenuBox.setTranslateX(-640);
		getChildren().addAll(MenuBox);
=======
public class MainmenuScreen extends Scene {
	static Pane root = new Pane();
	int state; // 0=menu 1=play 2=pause 3=load
	private Font narutoFont = Font.loadFont(ClassLoader.getSystemResource("fonts/njnaruto.ttf").toExternalForm(), 50);
	VBox MenuBox = new VBox(5);
	int Oldchoice = 0 ;
	int NewChoice = 0 ;
	public MainmenuScreen() {
		super(root);
		root.setPrefSize(1280, 720);
		root.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
>>>>>>> 608014e3febda4c910a379f172ff4027281212f9:src/main/MainmenuScreen.java
		
		//-----------<Menu Box>---------------------------------------------------------------
		MenuBox.setTranslateX(450);
		MenuBox.setTranslateY(250);
		//----------<Menu List>---------------------------------------------------------------
		ListMenu vsComp = new ListMenu("VS Comp."); 
		ListMenu vsHuman = new ListMenu("VS Human");
		ListMenu option = new ListMenu("Option");
		ListMenu exit = new ListMenu("Exit");
		//----------<\Menu List>---------------------------------------------------------------
		MenuBox.getChildren().addAll(vsComp,vsHuman,option,exit);
		((ListMenu) MenuBox.getChildren().get(Oldchoice)).setActive(true);
		MenuBox.setAlignment(Pos.CENTER);
		//-----------<\Menu Box>---------------------------------------------------------------
		
		root.getChildren().addAll(MenuBox);
		
	
	}
	public void ChooseMenu() {
		root.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				KeyCode key = event.getCode();
				System.out.println("Pressed "+key.toString());
				if(key==KeyCode.UP || key==KeyCode.W) {
					if(Oldchoice == 0) {NewChoice = 3;}
					else {NewChoice=Oldchoice-1;}
					((ListMenu)MenuBox.getChildren().get(Oldchoice)).setActive(false);
					((ListMenu)MenuBox.getChildren().get(NewChoice)).setActive(true);
					Oldchoice = NewChoice;
					}
				}
			});
	}

	public class ListMenu extends HBox {
		private Text name;
		private ImageView kunai = new ImageView(new Image(ClassLoader.getSystemResource("icon/kunai.png").toString(),150,40,true,true));
//		private Runnable script;

		ListMenu(String text) {
//			this.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
			this.setPrefSize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
			this.setAlignment(Pos.CENTER);
			name = new Text(text);
			name.setFont(MainmenuScreen.this.narutoFont);
			getChildren().addAll(kunai, name);
			setActive(false);
		}

		void setActive(boolean check) {
			kunai.setVisible(check);
			name.setFill(check ? Color.BLACK : Color.GRAY);
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
