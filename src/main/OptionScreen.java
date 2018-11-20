package main;

import java.util.Optional;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import main.MainMenuScreen.ListMenu;

public class OptionScreen extends Scene{
	
	private static Pane root = new Pane();
	private KeyCode key_1[] = {KeyCode.W,KeyCode.S,KeyCode.A,KeyCode.D,KeyCode.F,KeyCode.G,KeyCode.H,KeyCode.T};
	private KeyCode key_2[] = {KeyCode.UP,KeyCode.DOWN,KeyCode.LEFT,KeyCode.RIGHT,KeyCode.QUOTE,KeyCode.SEMICOLON,KeyCode.L,KeyCode.P};
	private Font narutoFont = Font.loadFont(ClassLoader.getSystemResource("fonts/njnaruto.ttf").toExternalForm(), 50);
	private int OldChoice_1 = 0 ;
	private int NewChoice_1 = 0 ;
	private int OldChoice_2 = 0 ;
	private int NewChoice_2 = 0 ;
	VBox optionmenu_1 = new VBox(10);
	VBox optionmenu_2 = new VBox(10);
	
	public OptionScreen(Main main) {
		super(root);
		root.setPrefSize(1280, 720);
		root.setBackground(new Background(new BackgroundFill(Color.BISQUE, null, null)));
		
		optionmenu_1.setTranslateX(150);
		optionmenu_1.setTranslateY(100);
		optionmenu_1.getChildren().addAll(
		new ListOption("Jump: "+getKey_1()[0], 1)
		,new ListOption("Crouch: "+getKey_1()[1], 1)
		,new ListOption("Left: "+getKey_1()[2], 1)
		,new ListOption("Right: "+getKey_1()[3], 1)
		,new ListOption("Melee: "+getKey_1()[4], 1)
		,new ListOption("Range: "+getKey_1()[5], 1)
		,new ListOption("Defense: "+getKey_1()[6], 1)
		,new ListOption("Dodge: "+getKey_1()[7], 1)
		);
		((ListOption)optionmenu_1.getChildren().get(OldChoice_1)).setActive(true);
		

		optionmenu_2.setTranslateX(750);
		optionmenu_2.setTranslateY(100);
		optionmenu_2.getChildren().addAll(
		new ListOption("Jump: "+getKey_2()[0], 2)
		,new ListOption("Crouch: "+getKey_2()[1], 2)
		,new ListOption("Left: "+getKey_2()[2], 2)
		,new ListOption("Right: "+getKey_2()[3], 2)
		,new ListOption("Melee: "+getKey_2()[4], 2)
		,new ListOption("Range: "+getKey_2()[5], 2)
		,new ListOption("Defense: "+getKey_2()[6], 2)
		,new ListOption("Dodge: "+getKey_2()[7], 2)
		);
		((ListOption)optionmenu_2.getChildren().get(OldChoice_2)).setActive(true);
		
		root.getChildren().addAll(optionmenu_1,optionmenu_2);
		
		setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				MediaPlayer click = new MediaPlayer(new Media(ClassLoader.getSystemResource("lighter.wav").toString()));
				MediaPlayer choose = new MediaPlayer(new Media(ClassLoader.getSystemResource("accept5.wav").toString()));
				KeyCode tmp_1[] = getKey_1();
				KeyCode tmp_2[] = getKey_2();
				KeyCode key = event.getCode();
				boolean wait1=false,wait2=false;
				System.out.println("OptionMenu:Pressed " + key.toString());
				if(wait1) {tmp_1[OldChoice_1]=key;choose.play();}
				else if(key == getKey_1()[4]) {wait1 = true;choose.play();}
				else if(key == getKey_1()[0] || key == getKey_1()[2]) {NewChoice_1 = (OldChoice_1-1+8)%8;click.play();changeActive(1);}
				else if(key == getKey_1()[1] || key == getKey_1()[3]) {NewChoice_1 = (OldChoice_1+1)%8;click.play();changeActive(1);}
				else if(wait2) {tmp_2[OldChoice_1]=key;choose.play();}
				else if(key == getKey_2()[4]) {wait2 = true;choose.play();}
				else if(key == getKey_2()[0] || key == getKey_2()[2]) {NewChoice_2 = (OldChoice_2-1+8)%8;click.play();changeActive(2);}
				else if(key == getKey_2()[1] || key == getKey_2()[3]) {NewChoice_2 = (OldChoice_2+1)%8;click.play();changeActive(2);}
				else if(key == KeyCode.ENTER) {setKey_1(tmp_1);setKey_2(tmp_2);choose.play();main.ChangeScene(main.getMainmenu());}

			}
		});
	}
	public class ListOption extends HBox {
		private Text text;
		private int player;
		//private ImageView shuriken1 = new ImageView(new Image(ClassLoader.getSystemResource("icon/shuriken.gif").toString(),130,40,true,true));
		//private ImageView shuriken2 = new ImageView(new Image(ClassLoader.getSystemResource("icon/shuriken.gif").toString(),130,40,true,true));
		ListOption(String text,int player) {
			this.setBackground(new Background(new BackgroundImage(new Image(ClassLoader.getSystemResource("icon/frame.png").toString(),400,110,true,false), null, null, null, null)));
			this.player = player;
			this.setSpacing(10);
			this.setAlignment(Pos.CENTER);
			this.setPrefSize(400, 100);
			this.text = new Text(text);
			this.text.setFont(narutoFont);
			this.text.setStrokeWidth(2);
			//getChildren().addAll(shuriken1, this.text,shuriken2);
			getChildren().addAll(this.text);
			setActive(false);
		}

		void setActive(boolean check) {
			//shuriken1.setVisible(check);
			//shuriken2.setVisible(check);
			if(player == 1) {text.setStroke(check ? Color.ORANGE : Color.YELLOW);}
			else if(player == 2) {text.setStroke(check ? Color.RED : Color.YELLOW);}
		}
	}
	public void changeActive(int player) {
		if(player == 1) {
			((ListOption) optionmenu_1.getChildren().get(OldChoice_1)).setActive(false);
			((ListOption) optionmenu_1.getChildren().get(NewChoice_1)).setActive(true);
			OldChoice_1 = NewChoice_1;
		}
		else if(player == 2) {
			((ListOption) optionmenu_2.getChildren().get(OldChoice_2)).setActive(false);
			((ListOption) optionmenu_2.getChildren().get(NewChoice_2)).setActive(true);
			OldChoice_2 = NewChoice_2;
		}
	}
	public KeyCode[] getKey_1() {
		return key_1;
	}
	public void setKey_1(KeyCode[] key_1) {
		this.key_1 = key_1;
	}
	public KeyCode[] getKey_2() {
		return key_2;
	}
	public void setKey_2(KeyCode[] key_2) {
		this.key_2 = key_2;
	}
	
}
