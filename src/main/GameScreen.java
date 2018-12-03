package main;

<<<<<<< HEAD
import java.util.ResourceBundle.Control;

import characters.Character;
=======

>>>>>>> 5757be41977bd9a5927689e5019bfe477cc6c75f
import javafx.animation.Animation;
import javafx.animation.Interpolator;
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
	private Image image = new Image(ClassLoader.getSystemResource("characters/naruto_sage/naruto_sage.png").toString(),1110, 2220, false, false);
	private ImageView imageV = new ImageView(image);
	private Characters player = new Characters(imageV);
	public GameScreen() {
		super(root);
		root.setPrefSize(1280, 720);
		root.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

		player.setTranslateX(500);player.setTranslateY(500);
		root.getChildren().addAll(player);
	}
	public class CharacterAnimation extends Transition{
		private ImageView image ;
		private int count;
		private int col;
		private int offSetX;
		private int offSetY;
		private int width;
		private int height;
		
		public CharacterAnimation(ImageView image,Duration duration, int count, int col, int offSetX, int offSetY, int width,
				int height) {
			super();
			this.image = image;
			this.count = count;
			this.col = col;
			this.offSetX = offSetX;
			this.offSetY = offSetY;
			this.width = width;
			this.height = height;
			
			setCycleDuration(duration);
			setCycleCount(Animation.INDEFINITE);
			setInterpolator(Interpolator.LINEAR);
			this.image.setViewport(new Rectangle2D(offSetX, offSetY, width, height));
			
		}

		@Override
		protected void interpolate(double frac) {
			int index = (int) ((count*frac) % count);
			int x = (index)*width+offSetX;
			int y = offSetY;
			System.out.println(frac+"<<<<<"+x+">>>>"+y);
			image.setViewport(new Rectangle2D(x, y, width - 15, height - 1.3));
		}

		public void setOffSetX(int offSetX) {
			this.offSetX = offSetX;
		}

		public void setOffSetY(int offSetY) {
			this.offSetY = offSetY;
		}
		
	}
	
	public class Characters extends Pane{
		ImageView imageview ;
<<<<<<< HEAD
		int count = 3;
		int col = 5 ;
		int offSetX = 2;
		int offSetY = 5;
		int width = 100 ;
		int height = 100 ;
		boolean isJump = false ;
=======
		int count = 6;
		int col = 0;
		int offSetX = 0;
		int offSetY = 222;
		int width = 111 ;
		int height = 111 ;
		boolean isRight = true;
>>>>>>> 5757be41977bd9a5927689e5019bfe477cc6c75f
		
		CharacterAnimation animation ;

		public Characters(ImageView imageview) {
			super();
			this.imageview = imageview;
			this.imageview.setViewport(new Rectangle2D(offSetX, offSetY, width - 15, height - 1.3));
			this.imageview.setFitHeight(350);
			this.imageview.setFitWidth(350);
			animation = new CharacterAnimation(this.imageview, Duration.millis(300), count, col, offSetX, offSetY, width, height);
			getChildren().addAll(imageview);
		}
		
		public void moveX(boolean move) {
				if(move)this.setTranslateX(this.getTranslateX()+ Character.getX_speed());
				else this.setTranslateX(this.getTranslateX()-Character.getX_speed());
		}
		
		public void moveY(boolean move) {
			for(int i=0 ; i < 20 ;i++ ) {
			if(move)this.setTranslateY(this.getTranslateY()+ Character.getY_speed());
			else this.setTranslateY(this.getTranslateY()-Character.getY_speed());
			}
		}
		}
		
	

	@Override
	public void upPressed() {
		if(Controller.getIsPressedMap().get(Main.getPlayer1().getUpKey()) && !player.isJump) {
			player.moveY(false);
			System.out.println("UpPressed");
			player.isJump = true ;
			player.moveY(true);
		}
	}

	@Override
	public void downPressed() {
		
	}

	@Override
	public void leftPressed() {
<<<<<<< HEAD
		if(Controller.getIsPressedMap().get(Main.getPlayer1().getLeftKey())) {
			player.moveX(false);
=======
		if(Controller.getIsPressed().get(Main.getPlayer1().getLeftKey())) {
			if(player.isRight == true) {
				player.imageview.setRotationAxis(Rotate.Y_AXIS);
				player.imageview.setRotate(180);
				player.isRight = false;
			}
			player.moveX(-Controller.getX_speed());
>>>>>>> 5757be41977bd9a5927689e5019bfe477cc6c75f
			System.out.println("LeftPressed");
			player.animation.play();
		}
	}

	@Override
	public void rightPressed() {
<<<<<<< HEAD
		if(Controller.getIsPressedMap().get(Main.getPlayer1().getRightKey())) {
			player.moveX(true);
=======
		if(Controller.getIsPressed().get(Main.getPlayer1().getRightKey())) {
			if(player.isRight != true) {
				player.imageview.setRotationAxis(Rotate.Y_AXIS);
				player.imageview.setRotate(0);
				player.isRight = true;
			}
			player.moveX(Controller.getX_speed());
>>>>>>> 5757be41977bd9a5927689e5019bfe477cc6c75f
			System.out.println("RightPressed");
			player.animation.play();
		}
	}

	public static void setBackground(Image background) {
		root.setBackground(new Background(new BackgroundImage(background, null, null, null, null)));
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
		// TODO Auto-generated method stub
		
	}

}



