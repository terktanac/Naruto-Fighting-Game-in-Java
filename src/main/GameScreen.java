package main;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import org.omg.CosNaming.IstringHelper;

import characters.CharacterAnimation;
import characters.FireCharacter_1;
import characters.WindCharacter_1;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
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
	public GameScreen() {
		super(root);
		root.setPrefSize(1280, 720);
		root.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		
		healthbarP1.setTranslateX(-50); healthbarP1.setTranslateY(-80);
		
		healthbarP2.setTranslateX(530); healthbarP2.setTranslateY(-80);
		healthbarP2.setRotationAxis(Rotate.Y_AXIS);
		healthbarP2.setRotate(180);
		
		player1.setTranslateX(300);player1.setTranslateY(300);
		player2.setTranslateX(500);player2.setTranslateY(300);
		
		player2.getImageview().setRotationAxis(Rotate.Y_AXIS);
		player2.getImageview().setRotate(180);
		player2.setRight(false);
		
		root.getChildren().addAll(player1,player2,healthbarP1,healthbarP2);
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
		//เช็คคอมโบ กะให้วนทุกๆ0.2-0.5วิ 
		//การทำงานคือตัดออกทีละตัว ถ้าเจอคอมโบก็ให้ทำคอมโบที่ว่า ถ้าไม่เจอให้ทำอันแรก(เฉพาะการโจมตี) --> จะทำให้ทุกการโจมตีมีเว้นชั่วเวลาหนึ่ง
		updateskill(1);
		updateskill(2);
		//สำรหับการเปลี่ยนตัว *ไว้ทีหลัง* สนใจแค่ว่ามีมั้ยถ้าไม่มีเอาอันแรกออกส่วนปุ่มทำต่อเนื่องอยู่แล้ว
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
			System.out.println();
			Controller.removePressed(player, "SKILL", 1);}
	}


	@Override
	public void update() {
		update_1();
		update_2();
	}
	
	public void update_1() {
		upPressed_1();
		downPressed_1();
		leftPressed_1();
		rightPressed_1();
		nonePressed_1();
		meleePressed_1();
		rangePressed_1();
	}
	
	public void update_2() {
		upPressed_2();
		downPressed_2();
		leftPressed_2();
		rightPressed_2();
		nonePressed_2();
		meleePressed_2();
		rangePressed_2();
	}
	
	public void upPressed_1() {
		if(!Controller.getPressedListMoveP1().isEmpty() && Controller.getIsPressedMap1().get(Controller.getKeyP1().get(0))) {
			player1.jump();
			System.out.println("UPPressed");
		}
		player1.doJump();
	}

	
	public void downPressed_1() {
		if(Controller.getIsPressedMap1().get(Controller.getKeyP1().get(1))) {
			player1.crouch();
			System.out.println("DOWNPressed");
		}
		else if(player1.isCrouch()) {
			player1.setCrouch(false);
		}
	}
	
	public void leftPressed_1() {
		if(Controller.getIsPressedMap1().get((Controller.getKeyP1().get(2)))) {
			player1.walk_left();
			System.out.println("LeftPressed");
		}
		else if(player1.isMove()) {
			player1.setMove(false);
		}
	}

	public void rightPressed_1() {
		if(Controller.getIsPressedMap1().get((Controller.getKeyP1().get(3)))) {
			player1.walk_right();
			System.out.println("RightPressed");
		}
		else if(player1.isMove()) {
			player1.setMove(false);
		}
	}

	public void meleePressed_1() {
		if(Controller.getIsPressedMap1().get((Controller.getKeyP1().get(4)))) {
			player1.melee();
		}
		player1.doMelee();
	}
	
	public void rangePressed_1() {
		if(Controller.getIsPressedMap1().get((Controller.getKeyP1().get(5)))) {
			shurikens1.add(new Shuriken(player1.getOffSetX(), player1.getOffSetY()));
			root.getChildren().add(shurikens1.get(shurikens1.size()-1));
			player1.range();
		}
		player1.doRange();
		if(!shurikens1.isEmpty()) {
			for(int i = 0; i < shurikens1.size(); i++) {
				shurikens1.get(i).animation.play();
				if(player1.isRight() && shurikens1.get(i).getTranslateX() <= 1280)
					shurikens1.get(i).moveX(player1.getX_speed());
				else if(player1.isRight() && shurikens1.get(i).getTranslateX() >= 0)
					shurikens1.get(i).moveX(-player1.getX_speed());
			}
		}
	}
	
	public void nonePressed_1() {
		ArrayList<KeyCode> key = Controller.getKeyP1();
		Map<KeyCode, Boolean> pressed = Controller.getIsPressedMap1();
		if(!pressed.containsValue(true)) {
			player1.stand();
		}
	}
	
	public void upPressed_2() {
		if(!Controller.getPressedListMoveP2().isEmpty() && Controller.getIsPressedMap2().get(Controller.getKeyP2().get(0))) {
			player2.jump();
			System.out.println("UPPressed");
		}
		player2.doJump();
	}
	
	public void downPressed_2() {
		if(Controller.getIsPressedMap2().get(Controller.getKeyP2().get(1))) {
			player2.crouch();
			System.out.println("DOWNPressed");
		}
		else if(player2.isCrouch()) {
			player2.setCrouch(false);
		}
	}
	
	public void leftPressed_2() {
		if(Controller.getIsPressedMap2().get((Controller.getKeyP2().get(2)))) {
			player2.walk_left();
			System.out.println("LeftPressed");
		}
		else if(player2.isMove()) {
			player2.setMove(false);
		}
	}

	public void rightPressed_2() {
		if(Controller.getIsPressedMap2().get((Controller.getKeyP2().get(3)))) {
			player2.walk_right();
			System.out.println("RightPressed");
		}
		else if(player2.isMove()) {
			player2.setMove(false);
		}
	}
	
	public void meleePressed_2() {
		if(Controller.getIsPressedMap2().get((Controller.getKeyP2().get(4)))) {
			player2.melee();
		}
		player2.doMelee();
	}

	public void rangePressed_2() {
		if(Controller.getIsPressedMap2().get((Controller.getKeyP2().get(5)))) {
			player2.range();
		}
		player2.doRange();
	}
	
	public void nonePressed_2() {
		ArrayList<KeyCode> key = Controller.getKeyP2();
		Map<KeyCode, Boolean> pressed = Controller.getIsPressedMap2();
		if(!pressed.containsValue(true)) {
			player2.stand();
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
	
	public class Shuriken extends ImageView {
		private int posX;
		private int posY;
		private int offSetX = 70;
		private int offSetY = 50;
		private int width = 70;
		private int height = 50;
		private int count = 2;
		private ImageView imageview ;
		private CharacterAnimation animation ;
		
		public Shuriken(int posX, int posY) {
			super();
			imageview = new ImageView("sys/weapons.png");
			imageview.setTranslateX(posX);
			imageview.setTranslateY(posY);
			animation = (new CharacterAnimation(imageview, Duration.millis(300), count, 0, offSetX, offSetY, width, height));
		}

		public void moveX(double d) {
			boolean right = d>0 ? true:false;
			for(int i=0;i<Math.abs(d);i++) {
				if(right)setTranslateX(getTranslateX()+3);
				else setTranslateX(getTranslateX()-3);
			}
		}
	}

}
