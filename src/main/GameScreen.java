package main;

import java.util.ResourceBundle.Control;

import characters.Character;
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
import javafx.util.Duration;

public class GameScreen extends myScene{
	private static Pane root = new Pane();
	private Image image = new Image(ClassLoader.getSystemResource("characters/naruto_sage/naruto_sage.png").toString());
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
			int index = Math.min((int)Math.floor(count*frac),count-1);
			int x = (index%col)*width+offSetX;
			int y = (index/col)*height+offSetY;
			image.setViewport(new Rectangle2D(x, y, width, height));
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
		int count = 3;
		int col = 5 ;
		int offSetX = 2;
		int offSetY = 5;
		int width = 100 ;
		int height = 100 ;
		boolean isJump = false ;
		
		CharacterAnimation animation ;

		public Characters(ImageView imageview) {
			super();
			this.imageview = imageview;
			this.imageview.setViewport(new Rectangle2D(offSetX, offSetY, width, height));
			animation = new CharacterAnimation(imageview, Duration.millis(200), count, col, offSetX, offSetY, width, height);
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
		if(Controller.getIsPressedMap().get(Main.getPlayer1().getLeftKey())) {
			player.moveX(false);
			System.out.println("LeftPressed");
		}
	}

	@Override
	public void rightPressed() {
		if(Controller.getIsPressedMap().get(Main.getPlayer1().getRightKey())) {
			player.moveX(true);
			System.out.println("RightPressed");
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



