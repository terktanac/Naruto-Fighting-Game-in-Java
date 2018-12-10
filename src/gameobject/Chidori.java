package gameobject;

import characters.CharacterAnimation;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;

public class Chidori extends GameObject {

	private static AudioClip chidoriObjecthit = new AudioClip(ClassLoader.getSystemResource("chidori_hit.wav").toString());

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
		setImageview(new ImageView(ClassLoader.getSystemResource("sys/chidori.png").toString()));
		getImageview().setFitHeight(241.0 * 350.0 / 321.0);
		getImageview().setFitWidth(321.0 * 350.0 / 321.0);
		setAnimation((new CharacterAnimation(getImageview(), Duration.millis(300), getCount(), 0, getOffSetX(),
				getOffSetY(), getObjectWidth(), getObjectHeight())));
		getChildren().addAll(getImageview());
	}

	@Override
	public void doEffect() {
		if (getAnimationCount() == 200) {
			getAnimation().play();
			setAnimationCount(getAnimationCount() - 0.5);
			chidoriObjecthit.play();
		} else if (getAnimationCount() >= 200) {
			getAnimation().play();
			setAnimationCount(getAnimationCount() - 0.5);
		} else {
			setDoing(false);
			setDone(true);
			setAnimationCount(300);
		}

	}

}
