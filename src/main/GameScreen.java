package main;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.Interpolator;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
	private AnimationTimer timer ;
	private long lastTimeTriggered = -1;
	public GameScreen() {
		super(root);
		root.setPrefSize(1280, 720);
		root.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		timer = new AnimationTimer() {
			
			@Override
			public void handle(long now) {
				upPressed();
				leftPressed();
				downPressed();
				rightPressed();
			}
		};
		timer.start();
//		setOnKeyPressed(new EventHandler<KeyEvent>() {
//
//			@Override
//			public void handle(KeyEvent event) {
//				KeyCode key = event.getCode();
//				if(key == KeyCode.UP) {
//					player.animation.play();
//					player.animation.setOffSetY(96);
//					player.moveY(-2);
//				}
//				else if(key == KeyCode.DOWN) {
//					player.animation.play();
//					player.animation.setOffSetY(0);
//					player.moveY(2);
//				}
//			}
//		});
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
		
		CharacterAnimation animation ;

		public Characters(ImageView imageview) {
			super();
			this.imageview = imageview;
			this.imageview.setViewport(new Rectangle2D(offSetX, offSetY, width, height));
			animation = new CharacterAnimation(imageview, Duration.millis(200), count, col, offSetX, offSetY, width, height);
			getChildren().addAll(imageview);
		}
		
		public void moveX(int x) {
			boolean right = x>0 ? true:false;
			for(int i=0;i<Math.abs(x);i++) {
				if(right)this.setTranslateX(this.getTranslateX()+1);
				else this.setTranslateX(this.getTranslateX()-1);
			}
		}
		
		public void moveY(int y) {
			boolean right = y>0 ? true:false;
			for(int i=0;i<Math.abs(y);i++) {
				if(right)this.setTranslateY(this.getTranslateY()+1);
				else this.setTranslateY(this.getTranslateY()-1);
			}
		}
		
	}

	@Override
	public void upPressed() {
		if(Controller.getIsPressed().get(Main.getPlayer1().getUpKey())) {
			player.moveY(-2);
			System.out.println("UPPressed");
		}
	}

	@Override
	public void downPressed() {
		if(Controller.getIsPressed().get(Main.getPlayer1().getDownKey())) {
			player.moveY(2);
			System.out.println("DOWNPressed");
		}
	}

	@Override
	public void leftPressed() {
		if(Controller.getIsPressed().get(Main.getPlayer1().getLeftKey())) {
			player.moveX(-2);
			System.out.println("LeftPressed");
		}
	}

	@Override
	public void rightPressed() {
		if(Controller.getIsPressed().get(Main.getPlayer1().getRightKey())) {
			player.moveX(2);
			System.out.println("RightPressed");
		}
	}

	public static void setBackground(Image background) {
		root.setBackground(new Background(new BackgroundImage(background, null, null, null, null)));
	}

}



