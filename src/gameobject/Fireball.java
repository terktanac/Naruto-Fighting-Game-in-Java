package gameobject;

import characters.CharacterAnimation;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class Fireball extends GameObject {

	private static AudioClip boom = new AudioClip(ClassLoader.getSystemResource("explosion.wav").toString());

	public Fireball(double posx, double posy, boolean direction) {
		super(posx, posy, direction);
		setHasEffect(true);
		setOffSetX(0);
		setOffSetY(0);
		setWidth(82);
		setHeight(83);
		setCount(4);
		setSpeed(4);
		setDamage(25);
		setImageview(new ImageView(ClassLoader.getSystemResource("sys/katon2.png").toString()));
		if (!direction) {
			getImageview().setRotationAxis(Rotate.Y_AXIS);
			getImageview().setRotate(180);
		}
		getImageview().setFitHeight(350);
		getImageview().setFitWidth(350);
		setAnimation((new CharacterAnimation(getImageview(), Duration.millis(300), getCount(), 0, getOffSetX(),
				getOffSetY(), getObjectWidth(), getObjectHeight())));
		getChildren().addAll(getImageview());
	}

	@Override
	public void doEffect() {
		setOffSetX(0);
		setOffSetY(166);
		getAnimation().stop();
		if (getAnimationCount() == 300) {
			getImageview().setViewport(new Rectangle2D(0, 166, getObjectWidth(), getObjectHeight()));
			setAnimationCount(getAnimationCount() - 0.5);
			boom.play();
		} else if (getAnimationCount() >= 280) {
			getImageview().setViewport(new Rectangle2D(0, 166, getObjectWidth(), getObjectHeight()));
			setAnimationCount(getAnimationCount() - 0.5);
		} else if (getAnimationCount() >= 260) {
			getImageview().setViewport(new Rectangle2D(82, 166, getObjectWidth(), getObjectHeight()));
			setAnimationCount(getAnimationCount() - 0.5);
		} else if (getAnimationCount() >= 230) {
			getImageview().setViewport(new Rectangle2D(164, 166, getObjectWidth(), getObjectHeight()));
			setAnimationCount(getAnimationCount() - 0.5);
		} else if (getAnimationCount() >= 200) {
			getImageview().setViewport(new Rectangle2D(246, 166, getObjectWidth(), getObjectHeight()));
			setAnimationCount(getAnimationCount() - 0.5);
		} else {
			setDoing(false);
			setDone(true);
			setAnimationCount(300);
		}

	}

}
