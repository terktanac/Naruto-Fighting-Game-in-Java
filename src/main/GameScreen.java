package main;


import characters.CharacterAnimation;
import characters.WindCharacter_1;
import javafx.animation.Animation;
import javafx.animation.Animation.Status;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

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

	@Override
	public void upPressed() {
		
		if(!Controller.getPressedListP1().isEmpty() && Controller.getIsPressedMap().get(Main.getPlayer().getKeyP1().get(0))) {
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

	@Override
	public void downPressed() {
		if(!Controller.getPressedListP1().isEmpty() && Controller.getIsPressedMap().get(Main.getPlayer().getKeyP1().get(1))) {
			player.crouch();
			System.out.println("DOWNPressed");
		}
	}

	@Override
	public void leftPressed() {
		
		if(!Controller.getPressedListP1().isEmpty() && Controller.getIsPressedMap().get((Main.getPlayer().getKeyP1().get(2)))) {
			player.walk_left();
			System.out.println("LeftPressed");
		}
	}

	@Override
	public void rightPressed() {
		
		if(!Controller.getPressedListP1().isEmpty() && Controller.getIsPressedMap().get((Main.getPlayer().getKeyP1().get(3)))) {
			player.walk_right();
			System.out.println("RightPressed");
		}
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
		Main.getPlayer();
		// TODO Auto-generated method stub
		if(Controller.getPressedListP1().isEmpty()) {
			player.setOffSetY(111);
			player.getAnimation().setOffSetY(111);
			player.getAnimation().play();
		}
		
	}

}



