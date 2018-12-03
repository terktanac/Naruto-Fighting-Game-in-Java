package main;


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
		int count = 6;
		int col = 0;
		int offSetX = 0;
		int offSetY = 222;
		int width = 111 ;
		int height = 111 ;
		boolean isRight = true;
		
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
		
		public void moveX(double d) {
			boolean right = d>0 ? true:false;
			for(int i=0;i<Math.abs(d);i++) {
				if(right)this.setTranslateX(this.getTranslateX()+1);
				else this.setTranslateX(this.getTranslateX()-1);
			}
		}
		
		public void moveY(double d) {
			boolean right = d>0 ? true:false;
			for(int i=0;i<Math.abs(d);i++) {
				if(right)this.setTranslateY(this.getTranslateY()+1);
				else this.setTranslateY(this.getTranslateY()-1);
			}
		}
		
	}

	@Override
	public void upPressed() {
		if(Controller.getIsPressedMap().get(Controller.getPressedListP1().get(0))) {
			player.moveY(-characters.Character.getY_speed());
			System.out.println("UPPressed");
		}
	}

	@Override
	public void downPressed() {
		if(Controller.getIsPressedMap().get(Controller.getPressedListP1().get(1))) {
			player.moveY(characters.Character.getY_speed());
			System.out.println("DOWNPressed");
		}
	}

	@Override
	public void leftPressed() {
		if(Controller.getIsPressedMap().get(Controller.getPressedListP1().get(2))) {
			if(player.isRight == true) {
				player.imageview.setRotationAxis(Rotate.Y_AXIS);
				player.imageview.setRotate(180);
				player.isRight = false;
			}
			player.moveX(-characters.Character.getX_speed());
			System.out.println("LeftPressed");
			player.animation.play();
		}
	}

	@Override
	public void rightPressed() {
		if(Controller.getIsPressedMap().get(Controller.getPressedListP1().get(3))) {
			if(player.isRight != true) {
				player.imageview.setRotationAxis(Rotate.Y_AXIS);
				player.imageview.setRotate(0);
				player.isRight = true;
			}
			player.moveX(characters.Character.getX_speed());
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



