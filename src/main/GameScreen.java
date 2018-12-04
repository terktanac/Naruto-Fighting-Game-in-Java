package main;


import java.util.ArrayList;
import java.util.Arrays;

import characters.FireCharacter_1;
import characters.WindCharacter_1;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

public class GameScreen extends myScene{
	private static Pane root = new Pane();
	private WindCharacter_1 player1 = new WindCharacter_1();
	private FireCharacter_1 player2 = new FireCharacter_1();
	private HealthBar healthbarP1 = new HealthBar(300, 50, new ImageView());
	private HealthBar healthbarP2 = new HealthBar(300, 50, new ImageView());
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
		
		root.getChildren().addAll(player1,player2,healthbarP1,healthbarP2);
		
		player1.getAnimation().play();
		player2.getAnimation().play();
	}

	public static void setBackground(Image background) {
		root.setBackground(new Background(new BackgroundImage(background, null, null, null, null)));
	}
	
	public void updateArrays() {
		//�礤��� �����ǹ�ء�0.2-0.5�� 
		//��÷ӧҹ��͵Ѵ�͡���е�� ����ͤ��⺡����Ӥ��⺷����� �������������ѹ�á(੾�С������) --> �з����ء�����������鹪�������˹��
		//����Ѻ�������¹��� *������ѧ* ʹ�����������¶�����������ѹ�á�͡��ǹ�����ӵ�����ͧ��������
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
	}
	
	public void update_2() {
		upPressed_2();
		downPressed_2();
		leftPressed_2();
		rightPressed_2();
		nonePressed_2();
	}
	
	public void upPressed_1() {
		if(!Controller.getPressedListMoveP1().isEmpty() && Controller.getIsPressedMap().get(Controller.getKeyP1().get(0))) {
			player1.jump();
			System.out.println("UPPressed");
		}
		if(player1.isJump()) {
			if(player1.getTranslateY() > 100) {
				player1.setTranslateY(player1.getTranslateY()-3);
				player1.getImageview().setViewport(new Rectangle2D(444, 333, player1.get_Width() - 15, player1.get_Height() - 1.3));
			}
			else
				player1.setJump(false);
		}
		else if(player1.isAir()) {
			if(player1.getTranslateY() < 300) {
				player1.setTranslateY(player1.getTranslateY()+3);
				player1.getImageview().setViewport(new Rectangle2D(555, 333, player1.get_Width() - 15, player1.get_Height() - 1.3));
			}
			else {
				player1.setAir(false);
				//player1.stand();
			}
		}
	}

	
	public void downPressed_1() {
		if(Controller.getIsPressedMap().get(Controller.getKeyP1().get(1))) {
			player1.crouch();
			System.out.println("DOWNPressed");
		}
		else {
			player1.setCrouch(false);
			//player1.stand();
		}
	}
	
	public void leftPressed_1() {
		if(Controller.getIsPressedMap().get((Controller.getKeyP1().get(2)))) {
			player1.walk_left();
			System.out.println("LeftPressed");
		}
	}

	public void rightPressed_1() {
		if(Controller.getIsPressedMap().get((Controller.getKeyP1().get(3)))) {
			player1.walk_right();
			System.out.println("RightPressed");
		}
		else {
			//player1.stand();
		}
	}

	public void nonePressed_1() {
		if(Controller.getPressedListMoveP1().isEmpty() && Controller.getPressedListSkillP1().isEmpty()) {
			player1.stand();
		}
		
	}
	
	public void upPressed_2() {
		if(!Controller.getPressedListMoveP2().isEmpty() && Controller.getIsPressedMap().get(Controller.getKeyP2().get(0))) {
			player2.jump();
			System.out.println("UPPressed");
		}
		if(player2.isJump()) {
			if(player2.getTranslateY() > 100) {
				player2.setTranslateY(player2.getTranslateY()-3);
				player2.getImageview().setViewport(new Rectangle2D(444, 333, player2.get_Width() - 15, player2.get_Height() - 1.3));
			}
			else
				player2.setJump(false);
		}
		else if(player2.isAir()) {
			if(player2.getTranslateY() < 300) {
				player2.setTranslateY(player2.getTranslateY()+3);
				player2.getImageview().setViewport(new Rectangle2D(555, 333, player2.get_Width() - 15, player2.get_Height() - 1.3));
			}
			else {
				player2.setAir(false);
				//player2.stand();
			}
		}
	}
	
	public void downPressed_2() {
		if(Controller.getIsPressedMap().get(Controller.getKeyP2().get(1))) {
			player2.crouch();
			System.out.println("DOWNPressed");
		}
		else {
			player2.setCrouch(false);
			//player2.stand();
		}
	}
	
	public void leftPressed_2() {
		if(Controller.getIsPressedMap().get((Controller.getKeyP2().get(2)))) {
			player2.walk_left();
			System.out.println("LeftPressed");
		}
	}

	public void rightPressed_2() {
		if(Controller.getIsPressedMap().get((Controller.getKeyP2().get(3)))) {
			player2.walk_right();
			System.out.println("RightPressed");
		}
		else {
			//player2.stand();
		}
	}

	public void nonePressed_2() {
		if(Controller.getPressedListMoveP2().isEmpty() && Controller.getPressedListSkillP2().isEmpty()) {
			//player2.stand();
		}
		
	}

	public class HealthBar extends StackPane{
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

}
