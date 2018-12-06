package GameObject;

import characters.CharacterAnimation;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Shuriken extends GameObject{
	
	public Shuriken(double posx, double posy, boolean direction) {
		super(posx, posy, direction);
		setOffSetX(70);
		setOffSetY(50);
		setWidth(70);
		setHeight(50);
		setCount(2);
		setSpeed(4);
		setImageview(new ImageView("sys/weapons.png"));
		getImageview().setFitHeight(70);
		getImageview().setFitWidth(50);
		setAnimation((new CharacterAnimation(getImageview(), Duration.millis(300), getCount(), 0, getOffSetX(), getOffSetY(), get_Width(), get_Height())));
		getChildren().addAll(getImageview());
	}

	@Override
	public void doEffect() {
		
		
	}

	
	
	
}