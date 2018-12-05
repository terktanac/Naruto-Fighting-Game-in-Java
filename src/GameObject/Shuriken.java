package GameObject;

import Interface.Collidable;
import characters.CharacterAnimation;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Shuriken extends Pane implements Collidable{
	
	private int offSetX = 70;
	private int offSetY = 50;
	private int width = 70;
	private int height = 50;
	private int count = 2;
	private boolean direction;
	private ImageView imageview ;
	private CharacterAnimation animation ;
	
	public Shuriken(double posx, double posy, boolean direction) {
		super();
		this.direction = direction;
		imageview = new ImageView("sys/weapons.png");
		this.setTranslateX(posx);
		this.setTranslateY(posy);
		animation = (new CharacterAnimation(imageview, Duration.millis(300), count, 0, offSetX, offSetY, width, height));
		imageview.setFitHeight(70);
		imageview.setFitWidth(50);
		getChildren().addAll(imageview);
	}

	public void moveX(double d) {
		for(int i=0;i<Math.abs(d);i++) {
			if(direction)setTranslateX(getTranslateX()+2);
			else setTranslateX(getTranslateX()-2);
		}
	}

	public CharacterAnimation getAnimation() {
		return animation;
	}

	public boolean isDirection() {
		return direction;
	}

	@Override
	public Rectangle2D getBoundary() {
		return null;
		// TODO Auto-generated method stub
		
	}

	
}