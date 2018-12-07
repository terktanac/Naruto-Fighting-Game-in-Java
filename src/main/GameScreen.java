package main;


import java.util.ArrayList;
import java.util.Map;

import allInterface.Collidable;
import characters.Character;
import characters.FireCharacter_1;
import characters.WindCharacter_1;
import gameObject.GameObject;
import gameObject.Shuriken;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class GameScreen extends myScene{
	private static Pane root = new Pane();
//	private static Character[] play1 = {new WindCharacter_1(Character.getMaxHealth()),new FireCharacter_1(Character.getMaxHealth())};
//	private static Character[] play2 = {new WindCharacter_1(Character.getMaxHealth()),new FireCharacter_1(Character.getMaxHealth())};
//	private static Character player1 = play1[0];
//	private static Character player2 = play2[0];
	private static Character player1 ;
	private static Character player2 ;
	private HealthBar healthbarP1;
	private HealthBar healthbarP2;
	private static ArrayList<GameObject> gameObjects1 = new ArrayList<GameObject>();
	private static ArrayList<GameObject> gameObjects2 = new ArrayList<GameObject>();
	private boolean isEnd = false ;
	private PauseMenuScreen pause ;
	private boolean isPause = false ;
	private int currentTime = 300 ;
	private AnimationTimer timer ;
	private long lastTime = -1 ; 
	private Text time;


	public GameScreen() {
		super(root);
		root.setPrefSize(1280, 720);
		root.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		pause = new PauseMenuScreen();
		pause.setVisible(false);
		
		healthbarP1 = new HealthBar(800,312.5, new ImageView(), -25, -50);
		healthbarP2 = new HealthBar(800, 312.5, new ImageView(), 5, -50);
		healthbarP2.setTranslateX(535);
		healthbarP2.setRotationAxis(Rotate.Y_AXIS);
		healthbarP2.setRotate(180);
		
		time = new Text(""+currentTime);
		time.setTranslateX(640);
		time.setTranslateY(100);
		timer = new AnimationTimer() {
			
			@Override
			public void handle(long now) {
				if(now-lastTime > 1000000000) {
					currentTime-- ;
					lastTime = now ;
					time.setText(""+currentTime);
					if(currentTime == 0) {isEnd = true ;}
				}
			}
		};
		timer.start();
		
		healthbarP1 = new HealthBar(800,312.5, new ImageView(), -25, -50);
		healthbarP2 = new HealthBar(800, 312.5, new ImageView(), 5, -50);
		healthbarP2.setTranslateX(535);
		healthbarP2.setRotationAxis(Rotate.Y_AXIS);
		healthbarP2.setRotate(180);
		
//		player1.setTranslateX(300);player1.setTranslateY(300);
//		player2.setTranslateX(500);player2.setTranslateY(300);
//		
//		player2.getImageview().setRotationAxis(Rotate.Y_AXIS);
//		player2.getImageview().setRotate(180);
//		player2.setRight(false);
		
//		root.getChildren().addAll(player1,player2,healthbarP1,healthbarP2,time,pause);
		root.getChildren().addAll(healthbarP1,healthbarP2,time,pause);
		root.getChildren().addAll(gameObjects1);
		
//		player1.getAnimation().play();
//		player2.getAnimation().play();
//		
//		player1.stand();
//		player2.stand();
	}

	public static void setBackground(Image background) {
		root.setBackground(new Background(new BackgroundImage(background, null, null, null, null)));
	}
	
	public void updateArrays() {
		updateskill(1);
		updateskill(2);
		updatemove(1);
		updatemove(2);
	}
	
	private void updatemove(int player) {
		ArrayList<KeyCode> pressed = (player == 1 ? Controller.getPressedListMoveP1(): Controller.getPressedListMoveP2());
		ArrayList<KeyCode> key = (player==1 ? Controller.getKeyP1() : Controller.getKeyP2()) ;
		if(pressed.size() >= 3) {
			if(pressed.get(0) == key.get(0) && pressed.get(1) == key.get(1) && pressed.get(2) == key.get(0)) {
				System.out.println("Change char to earth");
				Controller.removePressed(player,"MOVE",3);
			}
		}
		if(pressed.size()>=1) {Controller.removePressed(player,"MOVE",1);}
		
	}

	private void updateskill(int player) {
		ArrayList<KeyCode> pressed = (player== 1 ? Controller.getPressedListSkillP1() : Controller.getPressedListSkillP2());
		ArrayList<KeyCode> key = (player == 1 ? Controller.getKeyP1() : Controller.getKeyP2());
		if(pressed.size() >= 4) {
			if(pressed.get(0) == key.get(5) && pressed.get(1) == key.get(7) && pressed.get(2) == key.get(6) && pressed.get(3) == key.get(4)) {
				System.out.println("Tier 3 skill");
				if(player == 1) {player1.setSkill3(true);}
				else {player2.setSkill3(true);}
				Controller.removePressed(player, "SKILL", 4);
				return;
			}
		}
		if(pressed.size() >= 3) {
			if(pressed.get(0) == key.get(6) && pressed.get(1) == key.get(4) && pressed.get(2) == key.get(5)) {
				System.out.println("Tier 2 skill");
				if(player == 1) {player1.setSkill2(true);}
				else {player2.setSkill2(true);}
				Controller.removePressed(player, "SKILL", 3);
				return;
			}
		}
		if(pressed.size() >= 2) {
			if(pressed.get(0)==key.get(5) && pressed.get(1)==key.get(4)) {
				System.out.println("Tier 1 skill");
				if(player==1) {player1.setSkill1(true);}
				else {player2.setSkill1(true);}
				Controller.removePressed(player, "SKILL", 2);
				return;
			}
		}
		if(pressed.size() >= 1) {
			if(pressed.get(0) == key.get(4)) {
				if(!isPause) {
					if(player == 1) {meleePressed_1();}
					else if(player == 2) {meleePressed_2();}
				}
				else {choosen();}
			}
			else if(pressed.get(0) == key.get(5)) {
				if(!isPause) {
					if(player == 1) {rangePressed_1();}
					else if(player == 2) {rangePressed_2();}
				}
				else {pause.setVisible(false);isPause = false;}
			}
			else if(pressed.get(0) == key.get(7) && !isPause) {
				if(player == 1) {dodgePressed_1();}
				else if(player == 2) {dodgePressed_2();}
			}
			if(player == 1) {Controller.getPressedListSkillP1().clear();}
			else if(player == 2) {Controller.getPressedListSkillP2().clear();}
		}
	}

	@Override
	public void update() {
		update_1();
		update_2();
		otherKeyPressed();
		if(player1.isDead() || player2.isDead()) {isEnd = true;}
		EndGame();
	}
	
	public void update_1() {
		upPressed_1();
		downPressed_1();
		leftPressed_1();
		rightPressed_1();
		blockPressed_1();
		nonePressed_1();
		doAnimation_1();
		healthbarP1.setHealthBar(player1.getCurrenthealth()/Character.getMaxHealth());
	}
	
	public void update_2() {
		upPressed_2();
		downPressed_2();
		leftPressed_2();
		rightPressed_2();
		blockPressed_2();
		nonePressed_2();
		doAnimation_2();
		healthbarP2.setHealthBar(player2.getCurrenthealth()/Character.getMaxHealth());
	}
	
	public void upPressed_1() {
		if(!Controller.getPressedListMoveP1().isEmpty() && Controller.getIsPressedMap1().get(Controller.getKeyP1().get(0)) && !isPause) {
			player1.jump();
			System.out.println("UPPressed");
		}
		else if(!Controller.getPressedListMoveP1().isEmpty() && Controller.getIsPressedMap1().get(Controller.getKeyP1().get(0)) && isPause) {moveUp();}
		player1.doJump();
	}

	public void downPressed_1() {
		if(Controller.getIsPressedMap1().get(Controller.getKeyP1().get(1)) && !isPause) {
			player1.crouch();
			System.out.println("DOWNPressed");
		}
		else if(player1.isCrouch() && !isPause) {
			player1.setCrouch(false);
		}
		else if(Controller.getIsPressedMap1().get(Controller.getKeyP1().get(1)) && isPause) {moveDown();}
	}
	
	public void leftPressed_1() {
		if(Controller.getIsPressedMap1().get((Controller.getKeyP1().get(2))) && !isPause) {
			player1.walk_left();
			System.out.println("LeftPressed");
		}
		else if(player1.isMove() && !isPause) {
			player1.setMove(false);
		}
		else if(Controller.getIsPressedMap1().get((Controller.getKeyP1().get(2))) && isPause) {moveUp();}
			
		}

	public void rightPressed_1() {
		if(Controller.getIsPressedMap1().get((Controller.getKeyP1().get(3))) && !isPause) {
			player1.walk_right();
			System.out.println("RightPressed");
		}
		else if(player1.isMove() && !isPause) {
			player1.setMove(false);
		}
		else if(Controller.getIsPressedMap1().get((Controller.getKeyP1().get(3))) && isPause) {moveDown();}
	}
	public void meleePressed_1() {
		player1.melee();
		if(checkCollide(player1, player2)) {
			player2.takeDamage(player1.getAtk());
		}
	}
	
	public void rangePressed_1() {
		gameObjects1.add(new Shuriken(player1.getTranslateX(), player1.getTranslateY()+150,player1.isRight()));
		root.getChildren().add(gameObjects1.get(gameObjects1.size()-1));
		gameObjects1.get(gameObjects1.size()-1).getAnimation().play();
		player1.range();

	}
	
	public void blockPressed_1() {
		if(Controller.getIsPressedMap1().get(Controller.getKeyP1().get(6))) {
			player1.block();
		}
		else if(player1.isBlock()) {
			player1.setBlock(false);
		}
	}
	
	public void dodgePressed_1() {
		player1.dodge();

	}
	
	public void nonePressed_1() {
		Map<KeyCode, Boolean> pressed = Controller.getIsPressedMap1();
		if(!pressed.containsValue(true)) {
			player1.stand();
		}
	}
	
	public void doAnimation_1() {
		player1.dead();
		player1.doRange();
		player1.doMelee();
		player1.doDodge();
		player1.basic_skill(player2);
		player1.mid_skill(player2);
		player1.High_skill(player2);
		player1.dotakeDamage();
		if(player1.getTranslateX() > 950) {player1.setTranslateX(950);}
		else if(player1.getTranslateX() < -30) {player1.setTranslateX(-30);}
		if(!gameObjects1.isEmpty()) {
			for(int i = 0; i < gameObjects1.size(); i++) {
				GameObject shu = gameObjects1.get(i);
				if(shu.getTranslateX() <= 1280 && shu.getTranslateX() >= -50) {
					shu.moveX();
					if(shu.isHasEffect()) {
						if(shu.isDone()) {
							root.getChildren().remove(root.getChildren().indexOf(shu));
							gameObjects1.remove(i);
						}
						else if(checkCollide(shu, player2) && !shu.isDoing()) {
							player2.setStackFly(1);
							player2.takeDamage(shu.getDamage());
							shu.setSpeed(0);
							shu.setDoing(true);
							shu.setTranslateX(player2.getTranslateX());
							shu.setTranslateY(player2.getTranslateY());
						}
						else if(shu.isDoing()) {
							shu.doEffect();
						}
					}
					else {
						if(checkCollide(shu, player2)) {
							player2.takeDamage(shu.getDamage());
							root.getChildren().remove(root.getChildren().indexOf(shu));
							gameObjects1.remove(i);
						}
					}
				}
				else {
					root.getChildren().remove(root.getChildren().indexOf(shu));
					gameObjects1.remove(i);
				}
			}
		}
	}
	
	public void upPressed_2() {
		if(!Controller.getPressedListMoveP2().isEmpty() && Controller.getIsPressedMap2().get(Controller.getKeyP2().get(0)) && !isPause) {
			player2.jump();
			System.out.println("UPPressed");
		}
		else if(!Controller.getPressedListMoveP2().isEmpty() && Controller.getIsPressedMap2().get(Controller.getKeyP2().get(0)) && isPause) {
			moveUp();
		}
		player2.doJump();
	}
	
	public void downPressed_2() {
		if(Controller.getIsPressedMap2().get(Controller.getKeyP2().get(1)) && !isPause) {
			//player2.crouch();
			player2.setSkill2(true);
			System.out.println("DOWNPressed");
		}
		else if(player2.isCrouch() && !isPause) {
			player2.setCrouch(false);
		}
		else if(Controller.getIsPressedMap2().get(Controller.getKeyP2().get(1)) && isPause) {
			moveDown();
		}
	}
	
	public void leftPressed_2() {
		if(Controller.getIsPressedMap2().get((Controller.getKeyP2().get(2))) && !isPause) {
			player2.walk_left();
			System.out.println("LeftPressed");
		}
		else if(player2.isMove() && !isPause) {
			player2.setMove(false);
		}
		else if(Controller.getIsPressedMap2().get((Controller.getKeyP2().get(2))) && isPause) {
			moveUp();
		}
	}

	public void rightPressed_2() {
		if(Controller.getIsPressedMap2().get((Controller.getKeyP2().get(3))) && !isPause) {
			player2.walk_right();
			System.out.println("RightPressed");
		}
		else if(player2.isMove() && isPause) {
			player2.setMove(false);
		}
		else if(Controller.getIsPressedMap2().get((Controller.getKeyP2().get(3))) && isPause) {
			moveDown();
		}
	}
	
	public void meleePressed_2() {
		player2.melee();
		if(checkCollide(player2, player1)) {
			player1.takeDamage(player2.getAtk());
		}
	}

	public void rangePressed_2() {
		gameObjects2.add(new Shuriken(player2.getTranslateX(), player2.getTranslateY()+150,player2.isRight()));
		root.getChildren().add(gameObjects2.get(gameObjects2.size()-1));
		gameObjects2.get(gameObjects2.size()-1).getAnimation().play();
		player2.range();

	}
	
	public void blockPressed_2() {
		if(Controller.getIsPressedMap2().get(Controller.getKeyP2().get(6))) {
			player2.block();
		}
		else if(player2.isBlock()) {
			player2.setBlock(false);
		}
	}
	
	public void dodgePressed_2() {
		player2.dodge();

	}
	
	public void nonePressed_2() {
		Map<KeyCode, Boolean> pressed = Controller.getIsPressedMap2();
		if(!pressed.containsValue(true)) {
			player2.stand();
		}
	}
	public void otherKeyPressed() {
		ArrayList<KeyCode> others = Controller.getOtherKeys();
		if(others.size()>0) {
			KeyCode key = others.get(0);
			if((key == KeyCode.ESCAPE || key == KeyCode.BACK_SPACE) && !isEnd) {
				if(!isPause) {
					isPause = true ;
					pause.setVisible(true);
				}
				else {
					isPause = false;
					pause.setVisible(false);
					MultiPlayerScreen.player.stop();
					Main.ChangeScene(Main.getMainmenu());
					Main.getPlayer().setScene(Main.getMainmenu());
					Main.getPlayer().run();
				}
			}
			else if(isPause && (key == KeyCode.ENTER || key == KeyCode.SPACE)) {
				choosen();
			}
			else if(isEnd && (key == KeyCode.ENTER || key == KeyCode.SPACE)) {
				System.exit(1);

			if(!Controller.getOtherKeys().isEmpty())Controller.removePressed(0, "OTHER", 1);
			}
		}
	}
	public boolean checkCollide(Collidable obj1,Collidable obj2) {
		return obj1.getBoundary().intersects(obj2.getBoundary());
	}
	public void doAnimation_2() {
		player2.dead();
		player2.doRange();
		player2.doMelee();
		player2.doDodge();
		player2.basic_skill(player1);
		player2.mid_skill(player1);
		player2.High_skill(player1);
		player2.dotakeDamage();
		if(player2.getTranslateX() > 950) {player2.setTranslateX(950);}
		else if(player2.getTranslateX() < -30) {player2.setTranslateX(-30);}
		if(!gameObjects2.isEmpty()) {
			for(int i = 0; i < gameObjects2.size(); i++) {
				GameObject shu = gameObjects2.get(i);
				if(shu.getTranslateX() <= 1280 && shu.getTranslateX() >= -50) {
					shu.moveX();
					if(shu.isHasEffect()) {
						if(shu.isDone()) {
							root.getChildren().remove(root.getChildren().indexOf(shu));
							gameObjects2.remove(i);
						}
						else if(checkCollide(shu, player1) && !shu.isDoing()) {
							player1.takeDamage(shu.getDamage());
							shu.setSpeed(0);
							shu.setDoing(true);
							shu.setTranslateX(player1.getTranslateX());
							shu.setTranslateY(player1.getTranslateY());
						}
						else if(shu.isDoing()) {
							shu.doEffect();
						}
					}
					else {
						if(checkCollide(shu, player1)) {
							player1.setStackFly(1);
							player1.takeDamage(shu.getDamage());
							shu.setSpeed(0);
							root.getChildren().remove(root.getChildren().indexOf(shu));
							gameObjects2.remove(i);
						}
					}
				}
				else {
					root.getChildren().remove(root.getChildren().indexOf(shu));
					gameObjects2.remove(i);
				}
			}
		}
	}
	public void moveDown() {
		if(pause.getCurChoice() == pause.getMenu().getChildren().size()-1) {pause.setNewChoice(0);}
		else {pause.setNewChoice(pause.getCurChoice()+1);}
		((PauseMenuScreen.ListMenu) pause.getMenu().getChildren().get(pause.getCurChoice())).setActive(false);
		((PauseMenuScreen.ListMenu) pause.getMenu().getChildren().get(pause.getNewChoice())).setActive(true);
		pause.setCurChoice(pause.getNewChoice());
	}
	
	public void moveUp() {
		if(pause.getCurChoice() == 0) {pause.setNewChoice(pause.getMenu().getChildren().size()-1);}
		else {pause.setNewChoice(pause.getCurChoice()-1);}
		((PauseMenuScreen.ListMenu) pause.getMenu().getChildren().get(pause.getCurChoice())).setActive(false);
		((PauseMenuScreen.ListMenu) pause.getMenu().getChildren().get(pause.getNewChoice())).setActive(true);
		pause.setCurChoice(pause.getNewChoice());
	}
	public void choosen() {
		if(pause.getCurChoice() == 0) {
			pause.setVisible(false);
			isPause = false;
		}
		else if(pause.getCurChoice() == 1) {
			//to option
			isPause = false;
			pause.setVisible(false);
			Main.ChangeScene(Main.getOptionscreen());
			Main.getPlayer().setScene(Main.getOptionscreen());
			Main.getPlayer().run();
		}
		else if(pause.getCurChoice() == 2) {
			//to howto
			isPause = false;
			pause.setVisible(false);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Coming Soon.");
			alert.show();
			Main.getPlayer().setScene(Main.getGamescreen());
		}
		else if(pause.getCurChoice() == 3) {
			//to mainmenu
			isPause = false;
			pause.setVisible(false);
			Main.ChangeScene(Main.getMainmenu());
			Main.getPlayer().setScene(Main.getMainmenu());
			Main.getPlayer().run();
		}
		else if(pause.getCurChoice() == 4) {
			System.exit(1);
		}
	}
	public void EndGame() {
		if(isEnd) {
			Text Endtext = new Text("KO!");
			Text Continue = new Text("Press Enter to quit");
			Endtext.setFont(getNarutoFont());
			Endtext.setTranslateX(600);
			Endtext.setTranslateY(300);
			Continue.setTranslateX(600);
			Continue.setTranslateY(300);
			if(player1.getCurrenthealth() == Character.getMaxHealth() || player2.getCurrenthealth() == Character.getMaxHealth()) {
				Endtext.setText("Perfect!");
			}
			else if(player1.getCurrenthealth() == player2.getCurrenthealth()) {
				Endtext.setText("Tie!");
			}
			else if(player1.getCurrenthealth() < player2.getCurrenthealth()) {
				Endtext.setText("Player 2 Win!");
			}
			else if(player1.getCurrenthealth() > player2.getCurrenthealth()) {
				Endtext.setText("Player 1 Win!");
			}
			Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.3), evt -> Continue.setVisible(true)),
					new KeyFrame(Duration.seconds(0.7), evt -> Continue.setVisible(false)));
			timeline.setCycleCount(Animation.INDEFINITE);
			timeline.play();
			
			root.getChildren().addAll(Continue);
		}
	}

	public class HealthBar extends StackPane {
		private double width ;
		private double height ;
		private ImageView healthbarPlain ;
		private ImageView healthbarBorder ;
		private double widthD ;
		private double xD ;
		private double heightD ;
		private double yD ;
		private double[] xPoints ;
		private double[] yPoints ;
		private double[] xPointstemp ;
		Canvas healthbar ;
		GraphicsContext gc ;
		int red = 0, green = 255;
		public HealthBar(double width,double height ,ImageView characters,int xpos,int ypos) {
			this.width = width ;
			this.height = height ;
			widthD = width-215 ;
			xD = xpos+58 ;
			heightD = height-265 ;
			yD = ypos+140;
			healthbarPlain = new ImageView(new Image("icon/healthbar.png", width, height, true, true));
			healthbarBorder = new ImageView(new Image("icon/healthbarborder.png", width, height, true, true));
			xPoints = new double[] {xD,xD+widthD,xD+(widthD*0.99),xD+(widthD*0.579),xD+(widthD*0.562),xD};
			yPoints = new double[] {yD,yD,yD+(heightD*0.42),yD+(heightD*0.42),yD+heightD,yD+heightD};
			xPointstemp = new double[] {xD,xD+widthD,xD+(widthD*0.99),xD+(widthD*0.579),xD+(widthD*0.562),xD};
			setPrefSize(width, height);
			setTranslateX(xpos);
			setTranslateY(ypos);
			healthbarPlain.setTranslateX(xpos);
			healthbarPlain.setTranslateY(ypos);
			healthbarBorder.setTranslateX(xpos);
			healthbarBorder.setTranslateY(ypos);
			healthbar = new Canvas(width, height);
			gc = healthbar.getGraphicsContext2D();
			setHealthBar(1);
			characters.setTranslateX(0);
			characters.setTranslateY(0);
			
			getChildren().addAll(healthbarPlain,healthbar,healthbarBorder,characters);
		}
		public double setHealthBar(double curDIVmax) {
			if(curDIVmax < 0) {curDIVmax = 0;}
			if(curDIVmax>=0.5) {red = (int) (2*255*(1-curDIVmax));}
			else {green = (int) (255*(curDIVmax*2));}
			Stop[] stops = new Stop[] { new Stop(0, Color.rgb(red, green, 0)), new Stop(1, Color.rgb(red, green, 100))};
			gc.setFill(new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops));
			
			if(curDIVmax < 0.579) {
				xPoints[3] = xPointstemp[1]*curDIVmax ;
				xPoints[4] = xPointstemp[2]*curDIVmax ;
			}
			xPoints[1] = xPointstemp[1]*curDIVmax ;
			xPoints[2] = xPointstemp[2]*curDIVmax ;
			gc.clearRect(0, 0, width, height);
			gc.fillPolygon(xPoints, yPoints,6);
			return curDIVmax *100;
		}

	}
	public void setCharacter(int choose1,int choose2) {
//		if(player == 1) {
//			player1 = play1[choose];
//		}
//		else {
//			player2 = play2[choose];
//		}
//		if(player == 1) {player1 = new FireCharacter_1(Character.getMaxHealth());}
//		else {player2 = new WindCharacter_1(Character.getMaxHealth());}
//		root.getChildren().removeAll(player1,player2);
//		System.out.println(root.getChildren());
		if(choose1 == 0) {player1 = new WindCharacter_1(Character.getMaxHealth());}
		else {player1 = new FireCharacter_1(Character.getMaxHealth());}
		if(choose2 == 0) {player2 = new WindCharacter_1(Character.getMaxHealth());}
		else {player2 = new FireCharacter_1(Character.getMaxHealth());}
		root.getChildren().add(0, player1);
		root.getChildren().add(0,player2);
		player1.setTranslateX(300);player1.setTranslateY(300);
		player2.setTranslateX(500);player2.setTranslateY(300);
		
		player2.getImageview().setRotationAxis(Rotate.Y_AXIS);
		player2.getImageview().setRotate(180);
		player2.setRight(false);
		
		player1.getAnimation().play();
		player2.getAnimation().play();
		
		player1.stand();
		player2.stand();
	}

	public void setEnd(boolean isEnd) {
		this.isEnd = isEnd;
	}

	public static Character getPlayer1() {
		return player1;
	}

	public static Character getPlayer2() {
		return player2;
	}

	public static Pane get_Root() {
		return root;
	}

	public static ArrayList<GameObject> getgameObjects1() {
		return gameObjects1;
	}

	public static ArrayList<GameObject> getgameObjects2() {
		return gameObjects2;
	}

	public void setPause(boolean isPause) {
		this.isPause = isPause;
	}

	@Override
	public void setDefault() {
		player1.setCurrenthealth(Character.getMaxHealth());
		player2.setCurrenthealth(Character.getMaxHealth());
		healthbarP1.setHealthBar(1);
		healthbarP2.setHealthBar(1);
		gameObjects1.clear();
		gameObjects2.clear();
		isEnd = false;
		isPause = false;
		pause.setCurChoice(0);
		pause.setNewChoice(0);
		currentTime = 300 ;
	}
	
	
	

}