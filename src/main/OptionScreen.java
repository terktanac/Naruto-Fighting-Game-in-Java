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
	private KeyCode up_1 = KeyCode.W ;
	private KeyCode up_2 = KeyCode.UP;
	private KeyCode down_1 = KeyCode.S;
	private KeyCode down_2 = KeyCode.DOWN;
	private KeyCode right_1 = KeyCode.D;
	private KeyCode right_2 = KeyCode.RIGHT;
	private KeyCode left_1 = KeyCode.A;
	private KeyCode left_2 = KeyCode.LEFT;
	private KeyCode melee_1 = KeyCode.F;
	private KeyCode melee_2 = KeyCode.QUOTE;
	private KeyCode range_1 = KeyCode.G;
	private KeyCode range_2 = KeyCode.SEMICOLON;
	private KeyCode defense_1 = KeyCode.H;
	private KeyCode defense_2 = KeyCode.L;
	private KeyCode dodge_1 = KeyCode.T;
	private KeyCode dodge_2 = KeyCode.P;
	private Font narutoFont = Font.loadFont(ClassLoader.getSystemResource("fonts/njnaruto.ttf").toExternalForm(), 50);
	private int OldChoice_1 = 0 ;
	private int NewChoice_1 = 0 ;
	private int OldChoice_2 = 0 ;
	private int NewChoice_2 = 0 ;

	public OptionScreen(Main main) {
		super(root);
		root.setPrefSize(1280, 720);
		root.setBackground(new Background(new BackgroundFill(Color.BISQUE, null, null)));
		
		VBox optionmenu_1 = new VBox(10);
		optionmenu_1.setTranslateX(150);
		optionmenu_1.setTranslateY(100);
		optionmenu_1.getChildren().addAll(
		new ListOption("Jump: "+getUp_1(), 1)
		,new ListOption("Crouch: "+getDown_1(), 1)
		,new ListOption("Left: "+getLeft_1(), 1)
		,new ListOption("Right: "+getRight_1(), 1)
		,new ListOption("Melee: "+getMelee_1(), 1)
		,new ListOption("Range: "+getRange_1(), 1)
		,new ListOption("Defense: "+getDefense_1(), 1)
		,new ListOption("Dodge: "+getDodge_1(), 1)
		);
		((ListOption)optionmenu_1.getChildren().get(OldChoice_1)).setActive(true);
		
		VBox optionmenu_2 = new VBox(10);
		optionmenu_2.setTranslateX(750);
		optionmenu_2.setTranslateY(100);
		optionmenu_2.getChildren().addAll(
		new ListOption("Jump: "+getUp_1(), 2)
		,new ListOption("Crouch: "+getDown_1(), 2)
		,new ListOption("Left: "+getLeft_1(), 2)
		,new ListOption("Right: "+getRight_1(), 2)
		,new ListOption("Melee: "+getMelee_1(), 2)
		,new ListOption("Range: "+getRange_1(), 2)
		,new ListOption("Defense: "+getDefense_1(), 2)
		,new ListOption("Dodge: "+getDodge_1(), 2)
		);
		((ListOption)optionmenu_2.getChildren().get(OldChoice_2)).setActive(true);
		
		root.getChildren().addAll(optionmenu_1,optionmenu_2);
		
		setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				MediaPlayer click = new MediaPlayer(new Media(ClassLoader.getSystemResource("lighter.wav").toString()));
				MediaPlayer choose = new MediaPlayer(new Media(ClassLoader.getSystemResource("accept5.wav").toString()));
				KeyCode key = event.getCode();
				System.out.println("OptionMenu:Pressed " + key.toString());
				if(key == getMelee_1()) {}
				else if(key == getUp_1() || key == getLeft_1()) {NewChoice_1 = (OldChoice_1-1)%8;click.play();}
				else if(key == getDown_1() || key == getRight_1()) {NewChoice_1 = (OldChoice_1+1)%8;click.play();}
				if(key == getMelee_2()) {}
				else if(key == getUp_2() || key == getLeft_2()) {NewChoice_2 = (OldChoice_2-1)%8;click.play();}
				else if(key == getDown_2() || key == getRight_2()) {NewChoice_2 = (OldChoice_2+1)%8;click.play();}
				((ListOption) optionmenu_1.getChildren().get(OldChoice_1)).setActive(false);
				((ListOption) optionmenu_1.getChildren().get(NewChoice_1)).setActive(true);
				OldChoice_1 = NewChoice_1;
				((ListOption) optionmenu_2.getChildren().get(OldChoice_2)).setActive(false);
				((ListOption) optionmenu_2.getChildren().get(NewChoice_2)).setActive(true);
				OldChoice_1 = NewChoice_2;

			}
		});
	}
	public class ListOption extends HBox {
		private Text text;
		private int player;
		private ImageView shuriken1 = new ImageView(new Image(ClassLoader.getSystemResource("icon/shuriken.gif").toString(),130,40,true,true));
		private ImageView shuriken2 = new ImageView(new Image(ClassLoader.getSystemResource("icon/shuriken.gif").toString(),130,40,true,true));
		ListOption(String text,int player) {
			this.setBackground(new Background(new BackgroundImage(new Image(ClassLoader.getSystemResource("icon/frame.png").toString(),400,110,true,false), null, null, null, null)));
			this.player = player;
			this.setSpacing(10);
			this.setAlignment(Pos.CENTER);
			this.setPrefSize(400, 100);
			this.text = new Text(text);
			this.text.setFont(narutoFont);
			this.text.setStrokeWidth(2);
			getChildren().addAll(shuriken1, this.text,shuriken2);
			setActive(false);
		}

		void setActive(boolean check) {
			shuriken1.setVisible(check);
			shuriken2.setVisible(check);
			if(player == 1) {text.setStroke(check ? Color.ORANGE : Color.YELLOW);}
			else if(player == 2) {text.setStroke(check ? Color.RED : Color.YELLOW);}
		}
	}

	public KeyCode getUp_1() {
		return up_1;
	}
	public void setUp_1(KeyCode up_1) {
		this.up_1 = up_1;
	}
	public KeyCode getUp_2() {
		return up_2;
	}
	public void setUp_2(KeyCode up_2) {
		this.up_2 = up_2;
	}
	public KeyCode getDown_1() {
		return down_1;
	}
	public void setDown_1(KeyCode down_1) {
		this.down_1 = down_1;
	}
	public KeyCode getDown_2() {
		return down_2;
	}
	public void setDown_2(KeyCode down_2) {
		this.down_2 = down_2;
	}
	public KeyCode getRight_1() {
		return right_1;
	}
	public void setRight_1(KeyCode right_1) {
		this.right_1 = right_1;
	}
	public KeyCode getRight_2() {
		return right_2;
	}
	public void setRight_2(KeyCode right_2) {
		this.right_2 = right_2;
	}
	public KeyCode getLeft_1() {
		return left_1;
	}
	public void setLeft_1(KeyCode left_1) {
		this.left_1 = left_1;
	}
	public KeyCode getLeft_2() {
		return left_2;
	}
	public void setLeft_2(KeyCode left_2) {
		this.left_2 = left_2;
	}
	public KeyCode getMelee_1() {
		return melee_1;
	}
	public void setMelee_1(KeyCode melee_1) {
		this.melee_1 = melee_1;
	}
	public KeyCode getMelee_2() {
		return melee_2;
	}
	public void setMelee_2(KeyCode melee_2) {
		this.melee_2 = melee_2;
	}
	public KeyCode getRange_1() {
		return range_1;
	}
	public void setRange_1(KeyCode range_1) {
		this.range_1 = range_1;
	}
	public KeyCode getRange_2() {
		return range_2;
	}
	public void setRange_2(KeyCode range_2) {
		this.range_2 = range_2;
	}
	public KeyCode getDefense_1() {
		return defense_1;
	}
	public void setDefense_1(KeyCode defense_1) {
		this.defense_1 = defense_1;
	}
	public KeyCode getDefense_2() {
		return defense_2;
	}
	public void setDefense_2(KeyCode defense_2) {
		this.defense_2 = defense_2;
	}
	public KeyCode getDodge_1() {
		return dodge_1;
	}
	public void setDodge_1(KeyCode dodge_1) {
		this.dodge_1 = dodge_1;
	}
	public KeyCode getDodge_2() {
		return dodge_2;
	}
	public void setDodge_2(KeyCode dodge_2) {
		this.dodge_2 = dodge_2;
	}

}
