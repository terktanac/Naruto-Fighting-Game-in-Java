package gameobject;

import characters.CharacterAnimation;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Shuriken extends GameObject {

	public Shuriken(double posx, double posy, boolean direction) {
		super(posx, posy, direction);
		setOffSetX(70);
		setOffSetY(50);
		setWidth(70);
		setHeight(50);
		setCount(2);
		setSpeed(4);
		setDamage(5);
		setImageview(new ImageView(ClassLoader.getSystemResource("sys/weapons.png").toString()));
		getImageview().setFitHeight(70);
		getImageview().setFitWidth(50);
		setAnimation((new CharacterAnimation(getImageview(), Duration.millis(300), getCount(), 0, getOffSetX(),
				getOffSetY(), getObjectWidth(), getObjectHeight())));
		getChildren().addAll(getImageview());
	}

	@Override
	public void doEffect() {

	}

}