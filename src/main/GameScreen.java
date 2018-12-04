package main;


import characters.WindCharacter_1;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class GameScreen extends myScene{
	private static Pane root = new Pane();
	private WindCharacter_1 player = new WindCharacter_1();
	public GameScreen() {
		super(root);
		root.setPrefSize(1280, 720);
		root.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

		player.setTranslateX(300);player.setTranslateY(300);
		root.getChildren().addAll(player);
		player.getAnimation().play();
	}

	public static void setBackground(Image background) {
		root.setBackground(new Background(new BackgroundImage(background, null, null, null, null)));
	}

	public void upPressed() {
		if(!Controller.getPressedListMoveP1().isEmpty() && Controller.getIsPressedMap().get(Controller.getKeyP1().get(0))) {
			player.jump();
			System.out.println("UPPressed");
		}
		if(player.isJump()) {
			if(player.getTranslateY() > 100) {
				player.setTranslateY(player.getTranslateY()-10);
				player.getImageview().setViewport(new Rectangle2D(444, 333, player.get_Width() - 15, player.get_Height() - 1.3));
			}
			else
				player.setJump(false);
		}
		else if(player.isAir()) {
			if(player.getTranslateY() < 300) {
				player.setTranslateY(player.getTranslateY()+10);
				player.getImageview().setViewport(new Rectangle2D(555, 333, player.get_Width() - 15, player.get_Height() - 1.3));
			}
			else {
				player.setAir(false);
				player.getAnimation().play();
			}
		}
	}
	public void updateArrays() {
		//เช็คคอมโบ กะให้วนทุกๆ0.2-0.5วิ 
		//การทำงานคือตัดออกทีละตัว ถ้าเจอคอมโบก็ให้ทำคอมโบที่ว่า ถ้าไม่เจอให้ทำอันแรก(เฉพาะการโจมตี) --> จะทำให้ทุกการโจมตีมีเว้นชั่วเวลาหนึ่ง
		//สำรหับการเปลี่ยนตัว *ไว้ทีหลัง* สนใจแค่ว่ามีมั้ยถ้าไม่มีเอาอันแรกออกส่วนปุ่มทำต่อเนื่องอยู่แล้ว
	}
	@Override
	public void update() {
		upPressed();
		downPressed();
		leftPressed();
		rightPressed();
		nonePressed();
	}
	public void downPressed() {
		if(Controller.getIsPressedMap().get(Controller.getKeyP1().get(1))) {
			player.crouch();
			System.out.println("DOWNPressed");
		}
	}

	public void leftPressed() {
		if(Controller.getIsPressedMap().get((Controller.getKeyP1().get(2)))) {
			player.walk_left();
			System.out.println("LeftPressed");
		}
	}

	public void rightPressed() {
		if(Controller.getIsPressedMap().get((Controller.getKeyP1().get(3)))) {
			player.walk_right();
			System.out.println("RightPressed");
		}
	}



	public void nonePressed() {
		if(Controller.getPressedListMoveP1().isEmpty() && Controller.getPressedListSkillP1().isEmpty()) {
			player.setOffSetY(111);
			player.getAnimation().setOffSetY(111);
			player.getAnimation().play();
		}
		
	}



}
