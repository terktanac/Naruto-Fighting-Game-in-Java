package gameobject;

import characters.CharacterAnimation;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;

public class Chidori extends GameObject {

	private static AudioClip chidoriObjecthit = new AudioClip("file:soundfx/chidori_hit.wav");

	public Chidori(double posx, double posy, boolean direction) {
		super(posx, posy, direction);
		setHasEffect(true);
		setOffSetX(0);
		setOffSetY(0);
		setWidth(321);
		setHeight(241);
		setCount(5);
		setSpeed(0);
		setDamage(25);
		setImageview(new ImageView("sys/chidori.png"));
		getImageview().setFitHeight(241.0 * 350.0 / 321.0);
		getImageview().setFitWidth(321.0 * 350.0 / 321.0);
		setAnimation((new CharacterAnimation(getImageview(), Duration.millis(100), getCount(), 0, getOffSetX(),
				getOffSetY(), getObjectWidth(), getObjectHeight())));
		getChildren().addAll(getImageview());
	}

	@Override
	public void doEffect() {
		if (getDelay() == 200) {
			getAnimation().play();
			setDelay(getDelay() - 1);
			chidoriObjecthit.play();
		} else if (getDelay() >= 200) {
			getAnimation().play();
			setDelay(getDelay() - 1);
		} else {
			setDoing(false);
			setDone(true);
			setDelay(300);
		}

	}

}
