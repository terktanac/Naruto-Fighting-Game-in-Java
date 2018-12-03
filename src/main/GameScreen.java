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
			System.out.println("UPPressed");
			if(!player.isJump()) {
				player.getAnimation().setOffSetX(222);
				player.getAnimation().setOffSetY(333);
				player.getAnimation().stop();
				player.setJump(true);
				Timeline load = new Timeline(
						new KeyFrame(Duration.millis(300), ae ->{player.moveY(characters.Character.getY_speed());})
						,new KeyFrame(Duration.millis(100), ae->{player.moveY(-characters.Character.getY_speed());}));
				load.play();
				player.setJump(false);
				player.getAnimation().play();
			}
		}
	}

	@Override
	public void downPressed() {
		if(!Controller.getPressedListP1().isEmpty() && Controller.getIsPressedMap().get(Main.getPlayer().getKeyP1().get(1))) {
			player.moveY(characters.Character.getY_speed());
			System.out.println("DOWNPressed");
		}
	}

	@Override
	public void leftPressed() {
		if(!Controller.getPressedListP1().isEmpty() && Controller.getIsPressedMap().get((Main.getPlayer().getKeyP1().get(2)))) {
			if(player.isRight() == true) {
				player.getImageview().setRotationAxis(Rotate.Y_AXIS);
				player.getImageview().setRotate(180);
				player.setRight(false);
			}
			player.getAnimation().setOffSetX(0);
			player.getAnimation().setOffSetY(222);
			player.moveX(-characters.Character.getX_speed());
			System.out.println("LeftPressed");
			player.getAnimation().play();
		}
	}

	@Override
	public void rightPressed() {
		if(!Controller.getPressedListP1().isEmpty() && Controller.getIsPressedMap().get((Main.getPlayer().getKeyP1().get(3)))) {
			if(player.isRight() != true) {
				player.getImageview().setRotationAxis(Rotate.Y_AXIS);
				player.getImageview().setRotate(0);
				player.setRight(true);
			}
			player.getAnimation().setOffSetX(0);
			player.getAnimation().setOffSetY(222);
			player.moveX(characters.Character.getX_speed());
			System.out.println("RightPressed");
			player.getAnimation().play();
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



