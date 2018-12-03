package main;

import java.util.Optional;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class MainMenuScreen extends myScene {
	static Pane root = new Pane();
	private Image background = new Image(ClassLoader.getSystemResource("background/final_valley_bg.jpg").toString(),1280,740,false,false);
	private ImageView imageView = new ImageView(new Image(ClassLoader.getSystemResource("icon/logo_new.png").toString(), 400, 250, true, true));
	private VBox MenuBox = new VBox(5);
	private int Oldchoice = 0 ;
	private int NewChoice = 0 ;
	public MainMenuScreen() {
		super(root);
		root.setPrefSize(1280, 720);
		root.setBackground(new Background(new BackgroundImage(background, null, null, null, null)));

		MenuBox.setTranslateX(350);
		MenuBox.setTranslateY(250);
		MenuBox.getChildren().addAll(new ListMenu("Singleplayer")
				,new ListMenu("Multiplayer")
				,new ListMenu("Option")
				,new ListMenu("Exit"));
		((ListMenu) MenuBox.getChildren().get(Oldchoice)).setActive(true);
		MenuBox.setAlignment(Pos.CENTER);
		MenuBox.setSpacing(30);

		imageView.setTranslateX(460);
		imageView.setTranslateY(60);
		imageView.prefWidth(1000);
		
		root.getChildren().addAll(MenuBox,imageView);
		
	
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
			name.setFont(getNarutoFont());
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

	@Override
	public void update() {
		moveUp();
		moveDown();
		select();
		back();
	}

	private void back() {
		if(Controller.getKeySkill_P1(1) || Controller.getKeySkill_P2(1)) {
			Main.ChangeScene((Scene)Main.getLoadscreen());
			Timeline load = new Timeline(new KeyFrame(Duration.millis(3000), ae ->{Main.ChangeScene(Main.getIntro());})
					,new KeyFrame(Duration.millis(100), ae->{choose.play();}));
			load.play();
			
		}
	}

	private void select() {
		if(Controller.getKeySkill_P1(0) || Controller.getKeySkill_P2(0)) {
			chooseMenu();
		}
	}

	private void moveDown() {
		if(Controller.getKeyMove_P1(3) || Controller.getKeyMove_P2(3) || Controller.getKeyMove_P1(1) || Controller.getKeyMove_P2(1)) {
			if (Oldchoice == MenuBox.getChildren().size()-1) {NewChoice = 0;} 
			else {NewChoice = Oldchoice + 1;}
			click.play();
			((ListMenu) MenuBox.getChildren().get(Oldchoice)).setActive(false);
			((ListMenu) MenuBox.getChildren().get(NewChoice)).setActive(true);
			Oldchoice = NewChoice;
		}
	}
	private void moveUp() {
		if(Controller.getKeyMove_P1(0) || Controller.getKeyMove_P2(0) || Controller.getKeyMove_P1(2) || Controller.getKeyMove_P2(2)) {
			if (Oldchoice == 0) {NewChoice = 3;} 
			else {NewChoice = Oldchoice - 1;}
			click.play();
			((ListMenu) MenuBox.getChildren().get(Oldchoice)).setActive(false);
			((ListMenu) MenuBox.getChildren().get(NewChoice)).setActive(true);
			Oldchoice = NewChoice;

		}
	}


	private void chooseMenu() {
		if(Oldchoice==0) { 
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Coming Soon.");
			alert.showAndWait();
		}
		else if(Oldchoice==1) {
			Main.ChangeScene((Scene)Main.getLoadscreen());
			Timeline load = new Timeline(new KeyFrame(Duration.millis(3000), ae ->{Main.ChangeScene(Main.getMultiplayer());})
					,new KeyFrame(Duration.millis(100), ae->{choose.play();}));
			load.play();
			MultiPlayerScreen.player.setAutoPlay(true);
			Main.getPlayer().setScene(Main.getMultiplayer());
			Main.getPlayer().run();
		}
		else if(Oldchoice==2) {
			Main.ChangeScene((Scene)Main.getLoadscreen());
			Timeline load = new Timeline(new KeyFrame(Duration.millis(3000), ae ->{Main.ChangeScene(Main.getOptionscreen());})
					,new KeyFrame(Duration.millis(100), ae->{choose.play();}));
			load.play();
			Main.getPlayer().setScene(Main.getOptionscreen());
			Main.getPlayer().run();
		}
		else if(Oldchoice==MenuBox.getChildren().size()-1) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Do you want to exit?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
			    System.exit(1);
			}
		}
		playChoose();
	}
}