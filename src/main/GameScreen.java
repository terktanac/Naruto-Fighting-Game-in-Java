package main;


import java.util.ArrayList;
import java.util.Map;
import GameObject.Shuriken;
import Interface.Collidable;
import characters.Character;
import characters.FireCharacter_1;
import characters.WindCharacter_1;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
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
	private WindCharacter_1 player1 = new WindCharacter_1();
	private FireCharacter_1 player2 = new FireCharacter_1();
	private HealthBar healthbarP1 = new HealthBar(300, 50, new ImageView());
	private HealthBar healthbarP2 = new HealthBar(300, 50, new ImageView());
	private ArrayList<Shuriken> shurikens1 = new ArrayList<Shuriken>();
	private ArrayList<Shuriken> shurikens2 = new ArrayList<Shuriken>();
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
		
		healthbarP1.setTranslateX(-50); healthbarP1.setTranslateY(-80);
		
		healthbarP2.setTranslateX(530); healthbarP2.setTranslateY(-80);
		healthbarP2.setRotationAxis(Rotate.Y_AXIS);
		healthbarP2.setRotate(180);
		
		player1.setTranslateX(300);player1.setTranslateY(300);
		player2.setTranslateX(500);player2.setTranslateY(300);
		
		player2.getImageview().setRotationAxis(Rotate.Y_AXIS);
		player2.getImageview().setRotate(180);
		player2.setRight(false);
		
		root.getChildren().addAll(player1,player2,healthbarP1,healthbarP2,time,pause);
		root.getChildren().addAll(shurikens1);
		
		player1.getAnimation().play();
		player2.getAnimation().play();
		
		player1.stand();
		player2.stand();
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
		if(pressed.size() >= 2) {
			if(pressed.get(0)==key.get(5) && pressed.get(1)==key.get(4)) {
				System.out.println("Tier 1 skill");
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
			else if(pressed.get(0) == key.get(6) && !isPause) {
				if(player == 1) {blockPressed_1();}
				else if(player == 2) {blockPressed_2();}
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
		nonePressed_1();
		doAnimation_1();
	}
	
	public void update_2() {
		upPressed_2();
		downPressed_2();
		leftPressed_2();
		rightPressed_2();
		nonePressed_2();
		doAnimation_2();
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
			//player1.crouch();
			player1.setSkill2(true);
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
		shurikens1.add(new Shuriken(player1.getTranslateX(), player1.getTranslateY()+150,player1.isRight()));
		root.getChildren().add(shurikens1.get(shurikens1.size()-1));
		shurikens1.get(shurikens1.size()-1).getAnimation().play();
		player1.range();

	}
	
	public void blockPressed_1() {
		player1.block();
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
		player1.dotakeDamage();
		if(!shurikens1.isEmpty()) {
			for(int i = 0; i < shurikens1.size(); i++) {
				Shuriken shu = shurikens1.get(i);
				if(shu.getTranslateX() <= 1280 && shu.getTranslateX() >= -50) {
					if(shu.isDirection()) {shurikens1.get(i).moveX(Shuriken.getSpeed());}
					else{shurikens1.get(i).moveX(-Shuriken.getSpeed());}
					if(checkCollide(shu, player2)) {
						player2.takeDamage(Shuriken.getDamage());
						root.getChildren().remove(root.getChildren().indexOf(shu));
						shurikens1.remove(i);
					}
				}
				else {
					root.getChildren().remove(root.getChildren().indexOf(shu));
					shurikens1.remove(i);
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
			player2.crouch();
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
		shurikens2.add(new Shuriken(player2.getTranslateX(), player2.getTranslateY()+150,player2.isRight()));
		root.getChildren().add(shurikens2.get(shurikens2.size()-1));
		shurikens2.get(shurikens2.size()-1).getAnimation().play();
		player2.range();

	}
	
	public void blockPressed_2() {
		player2.block();
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
			if(key == KeyCode.ESCAPE || key == KeyCode.BACK_SPACE) {
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
				Main.setDefault();
				Main.ChangeScene(Main.getIntro());
				Main.getPlayer().setScene(Main.getIntro());
				Main.getPlayer().run();
			}
			if(!Controller.getOtherKeys().isEmpty())Controller.removePressed(0, "OTHER", 1);
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
		player2.dotakeDamage();
		if(!shurikens2.isEmpty()) {
			for(int i = 0; i < shurikens2.size(); i++) {
				Shuriken shu = shurikens2.get(i);
				if(shu.getTranslateX() <= 1280 && shu.getTranslateX() >= -50) {
					if(shu.isDirection()) {shurikens2.get(i).moveX(Shuriken.getSpeed());}
					else{shurikens2.get(i).moveX(-Shuriken.getSpeed());}
					if(checkCollide(shu, player1)) {
						player1.takeDamage(Shuriken.getDamage());
						root.getChildren().remove(root.getChildren().indexOf(shu));
						shurikens2.remove(i);
					}
				}
				else {
					root.getChildren().remove(root.getChildren().indexOf(shu));
					shurikens2.remove(i);
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
			Text Continue = new Text("Press Enter to restart");
			Endtext.setFont(getNarutoFont());
			if(player1.getCurrenthealth() == player1.getMaxHealth() || player2.getCurrenthealth() == player2.getMaxHealth()) {
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
			FadeTransition end = new FadeTransition(Duration.seconds(3), Endtext);
			end.setFromValue(0.3);
			end.setToValue(1.0);
			end.setAutoReverse(true);
			end.setCycleCount(2);
			
			FadeTransition con = new FadeTransition(Duration.seconds(2),Continue);
			con.setFromValue(0.3);
			con.setToValue(1.0);
			con.setAutoReverse(true);
			con.setCycleCount(Animation.INDEFINITE);
			
			SequentialTransition play = new SequentialTransition(end,con);
			play.play();
			
			root.getChildren().addAll(Endtext,Continue);
		}
	}

	public class HealthBar extends StackPane {
		private int width ;
		private int height ;
		private ImageView healthbarPlain = new ImageView("icon/healthbar.png");
		private ImageView healthbarBorder = new ImageView("icon/healthbarborder.png");
		GraphicsContext gc ;
		public HealthBar(int width,int height ,ImageView characters) {
			this.width = width;
			this.height = height ;
			double[] xPoints = {0,height,height,height*0.42,height*0.42,0};
			double[] yPoints = {0,0,width*0.562,width*0.579,width*0.99,width};
			setPrefSize(width, height);
			Canvas healthbar = new Canvas(width, height);
			gc = healthbar.getGraphicsContext2D();
			setHealthBar(1);
			gc.fillPolygon(xPoints, yPoints,6 );
			gc.strokePolygon(xPoints, yPoints, 6);
//			gc.fillRoundRect(0, 0, width, height-10, 20, 20);
			
			characters.setTranslateX(0);
			characters.setTranslateY(0);
			
			getChildren().addAll(healthbarPlain,healthbar,healthbarBorder,characters);
		}
		public double setHealthBar(double curDIVmax) {
			int firstcolor = 0, secondcolor = 255;
			if(curDIVmax>0.5) {firstcolor = (int) (255*(1-curDIVmax));}
			else {secondcolor = (int) (255*(1-curDIVmax));}
			gc.setFill(new LinearGradient(0, 0, (double)height, (double)width*curDIVmax, true, CycleMethod.REFLECT
					,new Stop(0.0, Color.rgb(firstcolor, secondcolor, 0))
					,new Stop(1.0,Color.rgb(firstcolor, secondcolor, 100))));
			return curDIVmax *100;
		}

	}

	public void setEnd(boolean isEnd) {
		this.isEnd = isEnd;
	}

	public Character getPlayer1() {
		return player1;
	}

	public Character getPlayer2() {
		return player2;
	}

	public ArrayList<Shuriken> getShurikens1() {
		return shurikens1;
	}

	public ArrayList<Shuriken> getShurikens2() {
		return shurikens2;
	}

	public void setPause(boolean isPause) {
		this.isPause = isPause;
	}

	@Override
	public void setDefault() {
		player1.setCurrenthealth(player1.getMaxHealth());
		player2.setCurrenthealth(player2.getMaxHealth());
		healthbarP1.setHealthBar(1);
		healthbarP2.setHealthBar(1);
		shurikens1.clear();
		shurikens2.clear();
		isEnd = false;
		isPause = false;
		pause.setCurChoice(0);
		pause.setNewChoice(0);
		currentTime = 300 ;
	}
	
	
	

}