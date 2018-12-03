package main;

import java.util.Optional;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
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
		
		setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				MediaPlayer click = new MediaPlayer(new Media(ClassLoader.getSystemResource("lighter.wav").toString()));
				MediaPlayer choose = new MediaPlayer(new Media(ClassLoader.getSystemResource("accept5.wav").toString()));
				KeyCode key = event.getCode();
				System.out.println("MainMenu:Pressed " + key.toString());
				if (key == KeyCode.SPACE || key == KeyCode.ENTER || key == Main.getOptionscreen().getMelee_1() || key == Main.getOptionscreen().getMelee_2()) {
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
					}
					else if(Oldchoice==2) {
						Main.ChangeScene((Scene)Main.getLoadscreen());
						Timeline load = new Timeline(new KeyFrame(Duration.millis(3000), ae ->{Main.ChangeScene(Main.getOptionscreen());})
								,new KeyFrame(Duration.millis(100), ae->{choose.play();}));
						load.play();
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
					choose.play();
				}
				else if (key == Main.getOptionscreen().getUp_1() || key == Main.getOptionscreen().getUp_2() 
						|| key == Main.getOptionscreen().getLeft_1() || key == Main.getOptionscreen().getLeft_2()) {
						if (Oldchoice == 0) {NewChoice = 3;} 
						else {NewChoice = Oldchoice - 1;}
						click.play();
				} 
				else if (key == Main.getOptionscreen().getDown_1() || key == Main.getOptionscreen().getDown_2() 
						|| key == Main.getOptionscreen().getRight_1() || key == Main.getOptionscreen().getRight_2()) {
						if (Oldchoice == MenuBox.getChildren().size()-1) {NewChoice = 0;} 
						else {NewChoice = Oldchoice + 1;}
						click.play();
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
	public void upPressed() {
		// TODO Auto-generated method stub
		System.out.println("mainmenu up");
	}

	@Override
	public void downPressed() {
		// TODO Auto-generated method stub
		System.out.println("mainmenu down");
	}

	@Override
	public void leftPressed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rightPressed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void meleePressed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rangePressed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dodgePressed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void blockPressed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void SpacePressed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void EnterPressed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nonePressed() {
		// TODO Auto-generated method stub
		
	}
}