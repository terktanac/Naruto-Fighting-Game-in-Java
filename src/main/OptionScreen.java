package main;

import java.util.ArrayList;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
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

public class OptionScreen extends myScene{
	
	private static Pane root = new Pane();
//	private KeyCode up_1 = KeyCode.W ;
//	private KeyCode up_2 = KeyCode.UP;
//	private KeyCode down_1 = KeyCode.S;
//	private KeyCode down_2 = KeyCode.DOWN;
//	private KeyCode right_1 = KeyCode.D;
//	private KeyCode right_2 = KeyCode.RIGHT;
//	private KeyCode left_1 = KeyCode.A;
//	private KeyCode left_2 = KeyCode.LEFT;
//	private KeyCode melee_1 = KeyCode.J;
//	private KeyCode melee_2 = KeyCode.NUMPAD1;
//	private KeyCode range_1 = KeyCode.K;
//	private KeyCode range_2 = KeyCode.NUMPAD2;
//	private KeyCode defense_1 = KeyCode.L;
//	private KeyCode defense_2 = KeyCode.NUMPAD3;
//	private KeyCode dodge_1 = KeyCode.I;
//	private KeyCode dodge_2 = KeyCode.NUMPAD5;
	private int OldChoice_1 = 0 ;
	private int NewChoice_1 = 0 ;
	private int OldChoice_2 = 0 ;
	private int NewChoice_2 = 0 ;
	private static ArrayList<KeyCode> keySetting = new ArrayList<KeyCode>();
	private ArrayList<ListOption> listoption1 = new ArrayList<ListOption>();
	private ArrayList<ListOption> listoption2 = new ArrayList<ListOption>();
	private boolean setState1 = false;
	private boolean setState2 = false;

	public OptionScreen() {
		super(root);
		root.setPrefSize(1280, 720);
		Image image = new Image(ClassLoader.getSystemResource("background/paper.jpg").toString(),1300,720,false,true);
		root.setBackground(new Background(new BackgroundImage(image, null, null, null, null)));
		
		setKeySetting();
		
		listoption1.add(new ListOption("Jump: "+getUp_1(), 1));
		listoption1.add(new ListOption("Crouch: "+getDown_1(), 1));
		listoption1.add(new ListOption("Left: "+getLeft_1(), 1));
		listoption1.add(new ListOption("Right: "+getRight_1(), 1));
		listoption1.add(new ListOption("Melee: "+getMelee_1(), 1));
		listoption1.add(new ListOption("Range: "+getRange_1(), 1));
		listoption1.add(new ListOption("Defense: "+getDefense_1(), 1));
		listoption1.add(new ListOption("Dodge: "+getDodge_1(), 1));
		
		VBox optionmenu_1 = new VBox(10);
		optionmenu_1.setTranslateX(250);
		optionmenu_1.setTranslateY(150);
		optionmenu_1.setSpacing(10);
		optionmenu_1.getChildren().addAll(listoption1);
		((ListOption)optionmenu_1.getChildren().get(OldChoice_1)).setActive(true);
		
		listoption2.add(new ListOption("Jump: "+getUp_2(), 2));
		listoption2.add(new ListOption("Crouch: "+getDown_2(), 2));
		listoption2.add(new ListOption("Left: "+getLeft_2(), 2));
		listoption2.add(new ListOption("Right: "+getRight_2(), 2));
		listoption2.add(new ListOption("Melee: "+getMelee_2(), 2));
		listoption2.add(new ListOption("Range: "+getRange_2(), 2));
		listoption2.add(new ListOption("Defense: "+getDefense_2(), 2));
		listoption2.add(new ListOption("Dodge: "+getDodge_2(), 2));
		
		VBox optionmenu_2 = new VBox(10);
		optionmenu_2.setTranslateX(800);
		optionmenu_2.setTranslateY(150);
		optionmenu_2.setSpacing(10);
		
		optionmenu_2.getChildren().addAll(listoption2);
		((ListOption)optionmenu_2.getChildren().get(OldChoice_2)).setActive(true);
		
		root.getChildren().addAll(optionmenu_1,optionmenu_2);
		
		Text pressKey1 = new Text("Press "+getMelee_1().toString()+" key for player 1 or "+getMelee_2().toString()+" key for player 2 to change the key.");
		pressKey1.setFont(getNarutoFont());
		pressKey1.setFill(Color.WHITE);
		pressKey1.setStroke(Color.BLACK);
		pressKey1.setTranslateX(230);
		pressKey1.setTranslateY(100);
		pressKey1.setVisible(false);
		
		Text pressKey2 = new Text("Press the new key.");
		pressKey2.setFont(getNarutoFont());
		pressKey2.setFill(Color.WHITE);
		pressKey2.setStroke(Color.BLACK);
		pressKey2.setTranslateX(530);
		pressKey2.setTranslateY(100);
		pressKey2.setVisible(false);
		
		Text pressKey3 = new Text("Press Enter or Space to confirm your change.");
		pressKey3.setFont(getNarutoFont());
		pressKey3.setFill(Color.WHITE);
		pressKey3.setStroke(Color.BLACK);
		pressKey3.setTranslateX(400);
		pressKey3.setTranslateY(100);
		pressKey3.setVisible(false);
		
		root.getChildren().addAll(pressKey1,pressKey2,pressKey3);
		
		Timeline timeline1 = new Timeline(new KeyFrame(Duration.seconds(0.3), evt -> pressKey1.setVisible(true)),
				new KeyFrame(Duration.seconds(0.7), evt -> pressKey1.setVisible(false)));
		timeline1.setCycleCount(Animation.INDEFINITE);
		
		Timeline timeline2 = new Timeline(new KeyFrame(Duration.seconds(0.3), evt -> pressKey2.setVisible(true)),
				new KeyFrame(Duration.seconds(0.7), evt -> pressKey2.setVisible(false)));
		timeline2.setCycleCount(Animation.INDEFINITE);
		
		Timeline timeline3 = new Timeline(new KeyFrame(Duration.seconds(0.3), evt -> pressKey3.setVisible(true)),
				new KeyFrame(Duration.seconds(0.7), evt -> pressKey3.setVisible(false)));
		timeline3.setCycleCount(Animation.INDEFINITE);
		
		timeline1.play();
		setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				MediaPlayer click = new MediaPlayer(new Media(ClassLoader.getSystemResource("lighter.wav").toString()));
				MediaPlayer choose = new MediaPlayer(new Media(ClassLoader.getSystemResource("accept5.wav").toString()));
				KeyCode key = event.getCode();
				System.out.println("OptionMenu:Pressed " + key.toString());
				if(setState1 == true) {
					System.out.println(OldChoice_1+key.toString());
					keySetting.set(OldChoice_1, key);
					setState1 = false;
					timeline2.stop();
					pressKey2.setVisible(false);
					timeline3.play();
				}
				else if(setState2 == true) {
					keySetting.set(OldChoice_2+8, key);
					setState2 = false;
					timeline2.stop();
					pressKey2.setVisible(false);
					timeline3.play();
				}
				else {
					if(key == KeyCode.BACK_SPACE) {
						choose.play();
						Main.ChangeScene(Main.getMainmenu());
					}
					if(key == KeyCode.ENTER || key == KeyCode.SPACE) {
						timeline3.stop();
						pressKey3.setVisible(false);
						timeline1.play();
						choose.play();
						setRealKey();
						setListOption1();
						setListOption2();
						for(int i = 0; i < keySetting.size(); i++) {
							System.out.println(keySetting.get(i));
						}
					}
					else if(key == getMelee_1()) {
						setState1 = true;
						choose.play();
						timeline1.stop();
						pressKey1.setVisible(false);
						timeline2.play();
					}
					else if(key == getUp_1() || key == getLeft_1()) {NewChoice_1 = (OldChoice_1-1+8)%8;click.play();}
					else if(key == getDown_1() || key == getRight_1()) {NewChoice_1 = (OldChoice_1+1+8)%8;click.play();}
					else if(key == getMelee_2()) {
						setState2 = true;
						choose.play();
						timeline1.stop();
						pressKey1.setVisible(false);
						timeline2.play();
					}
					else if(key == getUp_2() || key == getLeft_2()) {NewChoice_2 = (OldChoice_2-1+8)%8;click.play();}
					else if(key == getDown_2() || key == getRight_2()) {NewChoice_2 = (OldChoice_2+1+8)%8;click.play();}
					
					((ListOption) optionmenu_1.getChildren().get(OldChoice_1)).setActive(false);
					((ListOption) optionmenu_1.getChildren().get(NewChoice_1)).setActive(true);
					OldChoice_1 = NewChoice_1;
					
					((ListOption) optionmenu_2.getChildren().get(OldChoice_2)).setActive(false);
					((ListOption) optionmenu_2.getChildren().get(NewChoice_2)).setActive(true);
					OldChoice_2 = NewChoice_2;
				}

			}
		});
	}
	public class ListOption extends HBox {
		private Text text;
		private int player;
		private ImageView shuriken1 = new ImageView(new Image(ClassLoader.getSystemResource("icon/shuriken.png").toString(),130,40,true,true));
		private ImageView shuriken2 = new ImageView(new Image(ClassLoader.getSystemResource("icon/shuriken.png").toString(),130,40,true,true));
		ListOption(String text,int player) {
			this.setBackground(new Background(new BackgroundImage(new Image(ClassLoader.getSystemResource("icon/frame.png").toString(),230,60,false,true), null, null, null, null)));
			this.player = player;
			this.setSpacing(10);
			this.setAlignment(Pos.CENTER);
			this.setPrefSize(180, 60);
			this.text = new Text(text);
			this.text.setFont(getNarutoFont());
			this.text.setStrokeWidth(2);
			getChildren().addAll(shuriken1, this.text,shuriken2);
			//getChildren().addAll(this.text);
			setActive(false);
		}

		void setActive(boolean check) {
			shuriken1.setVisible(check);
			shuriken2.setVisible(check);
			if(player == 1) {
				text.setStroke(check ? Color.ORANGE : Color.YELLOW);
				text.setFill(check ?  Color.WHITE : Color.BLACK);
			}
			else if(player == 2) {
				text.setStroke(check ? Color.RED : Color.YELLOW);
				text.setFill(check ?  Color.WHITE : Color.BLACK);
			}
		}
	}


	public static ArrayList<KeyCode> getKeySetting() {
		return keySetting;
	}
	public void setKeySetting(ArrayList<KeyCode> keySetting) {
		OptionScreen.keySetting = keySetting;
	}
	public void setKeySetting() {
		OptionScreen.keySetting.add(up_1);
		OptionScreen.keySetting.add(down_1);
		OptionScreen.keySetting.add(right_1);
		OptionScreen.keySetting.add(left_1);
		OptionScreen.keySetting.add(melee_1);
		OptionScreen.keySetting.add(range_1);
		OptionScreen.keySetting.add(defense_1);
		OptionScreen.keySetting.add(dodge_1);
		OptionScreen.keySetting.add(up_2);
		OptionScreen.keySetting.add(down_2);
		OptionScreen.keySetting.add(right_2);
		OptionScreen.keySetting.add(left_2);
		OptionScreen.keySetting.add(melee_2);
		OptionScreen.keySetting.add(range_2);
		OptionScreen.keySetting.add(defense_2);
		OptionScreen.keySetting.add(dodge_2);
	}
	public void setRealKey() {
		up_1 = keySetting.get(0);
		up_2 = keySetting.get(8);
		down_1 = keySetting.get(1);
		down_2 = keySetting.get(9);
		right_1 = keySetting.get(2);
		right_2 = keySetting.get(10);
		left_1 = keySetting.get(3);
		left_2 = keySetting.get(11);
		melee_1 = keySetting.get(4);
		melee_2 = keySetting.get(12);
		range_1 = keySetting.get(5);
		range_2 = keySetting.get(13);
		defense_1 = keySetting.get(6);
		defense_2 = keySetting.get(14);
		dodge_1 = keySetting.get(7);
		dodge_2 = keySetting.get(15);
	}
	public void setListOption1() {
		listoption1.get(0).text.setText("Jump: "+getUp_1());
		listoption1.get(1).text.setText("Crouch: "+getDown_1());
		listoption1.get(2).text.setText("Left: "+getLeft_1());
		listoption1.get(3).text.setText("Right: "+getRight_1());
		listoption1.get(4).text.setText("Melee: "+getMelee_1());
		listoption1.get(5).text.setText("Range: "+getRange_1());
		listoption1.get(6).text.setText("Defense: "+getDefense_1());
		listoption1.get(7).text.setText("Dodge: "+getDodge_1());
	}
	public void setListOption2() {
		listoption2.get(0).text.setText("Jump: "+getUp_2());
		listoption2.get(1).text.setText("Crouch: "+getDown_2());
		listoption2.get(2).text.setText("Left: "+getLeft_2());
		listoption2.get(3).text.setText("Right: "+getRight_2());
		listoption2.get(4).text.setText("Melee: "+getMelee_2());
		listoption2.get(5).text.setText("Range: "+getRange_2());
		listoption2.get(6).text.setText("Defense: "+getDefense_2());
		listoption2.get(7).text.setText("Dodge: "+getDodge_2());
	}
	@Override
	public void update() {
		update_P1();
		update_P2();
	}
	private void update_P1() {
		moveUp_1();
		moveDown_1();
		choose_1();
		back_1();
	}

	private void update_P2() {
		moveUp_2();
		moveDown_2();
		choose_2();
		back_2();
	}
	public void changeKeyCode(int player,int choice) {
		setOnKeyPressed((KeyEvent event)->{
			KeyCode key = event.getCode();
			if(player == 1) {Controller.getKeyP1().set(choice, key);}
			else {Controller.getKeyP2().set(choice, key);}
		});
	}
	private void moveUp_1() {
		if(Controller.getKeyMove_P1(0) || Controller.getKeyMove_P1(2)) {
			NewChoice_1 = (OldChoice_1-1+8)%8;
			click.play();
		}
	}
	private void moveDown_1() {
		if(Controller.getKeyMove_P1(1) || Controller.getKeyMove_P1(3)) {
			NewChoice_1 = (OldChoice_1+1+8)%8;
			click.play();
		}
	}
	private void choose_1() {
		if(Controller.getKeySkill_P1(0)) {
			setState1 = true;
			choose.play();
			changeKeyCode(1, OldChoice_1);
		}
	}
	private void back_1() {
		// TODO Auto-generated method stub
		
	}
	private void moveUp_2() {
		if(Controller.getKeyMove_P2(0) || Controller.getKeyMove_P2(2)) {
			NewChoice_2 = (OldChoice_2-1+8)%8;
			click.play();
		}
	}
	private void moveDown_2() {
		if(Controller.getKeyMove_P2(1) || Controller.getKeyMove_P2(3)) {
			NewChoice_2 = (OldChoice_2+1+8)%8;
			click.play();
		}
	}
	private void choose_2() {
		if(Controller.getKeySkill_P2(0)) {
			setState2 = true;
			choose.play();
			changeKeyCode(2, OldChoice_2);

		}
	}
	private void back_2() {
		// TODO Auto-generated method stub
		
	}

}