package gameobject;

import characters.Character;
import characters.CharacterAnimation;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;

public class Amaterasu extends GameObject {

	private Character target;
	private static AudioClip sharingan = new AudioClip("file:soundfx/sharingan.wav");

	public Amaterasu(double posx, double posy, boolean direction, Character target) {
		super(posx, posy, direction);
		this.target = target;
		setHasEffect(true);
		setOffSetX(0);
		setOffSetY(171);
		setWidth(111);
		setHeight(171);
		setCount(5);
		setSpeed(0);
		target.setStackFly(1);
		setDamage(100);
		setImageview(new ImageView("sys/amaterasu.png"));
		getImageview().setFitHeight(171.0 * 350.0 / 171.0);
		getImageview().setFitWidth(111.0 * 350.0 / 171.0);
		setAnimation(new CharacterAnimation(getImageview(), Duration.millis(300), getCount(), 0, getOffSetX(),
				getOffSetY(), getObjectWidth(), getObjectHeight()));
		getChildren().addAll(getImageview());
	}

	@Override
	public final void doEffect() {
		setTranslateX(target.getTranslateX());
		setTranslateY(target.getTranslateY());
		if (getAnimationCount() == 300) {
			getImageview().setViewport(new Rectangle2D(0, 0, getObjectWidth(), getObjectHeight()));
			setAnimationCount(getAnimationCount() - 0.5);
			sharingan.play();
		} else if (getAnimationCount() >= 280) {
			getImageview().setViewport(new Rectangle2D(0, 0, getObjectWidth(), getObjectHeight()));
			setAnimationCount(getAnimationCount() - 0.5);
		} else if (getAnimationCount() >= 260) {
			getImageview().setViewport(new Rectangle2D(111, 0, getObjectWidth(), getObjectHeight()));
			setAnimationCount(getAnimationCount() - 0.5);
		} else if (getAnimationCount() >= 230) {
			getImageview().setViewport(new Rectangle2D(222, 0, getObjectWidth(), getObjectHeight()));
			setAnimationCount(getAnimationCount() - 0.5);
		} else if (getAnimationCount() >= 200) {
			getImageview().setViewport(new Rectangle2D(333, 0, getObjectWidth(), getObjectHeight()));
			setAnimationCount(getAnimationCount() - 0.5);
		} else if (getAnimationCount() >= 170) {
			getImageview().setViewport(new Rectangle2D(444, 0, getObjectWidth(), getObjectHeight()));
			setAnimationCount(getAnimationCount() - 0.5);
		} else if (getAnimationCount() >= 100) {
			getAnimation().play();
			setAnimationCount(getAnimationCount() - 0.5);
		} else if (getAnimationCount() >= 80) {
			getAnimation().stop();
			getImageview().setViewport(new Rectangle2D(0, 342, getObjectWidth(), getObjectHeight()));
			setAnimationCount(getAnimationCount() - 0.5);
		} else if (getAnimationCount() >= 60) {
			getImageview().setViewport(new Rectangle2D(111, 342, getObjectWidth(), getObjectHeight()));
			setAnimationCount(getAnimationCount() - 0.5);
		} else if (getAnimationCount() >= 40) {
			getImageview().setViewport(new Rectangle2D(222, 342, getObjectWidth(), getObjectHeight()));
			setAnimationCount(getAnimationCount() - 0.5);
		} else if (getAnimationCount() >= 20) {
			getImageview().setViewport(new Rectangle2D(333, 342, getObjectWidth(), getObjectHeight()));
			setAnimationCount(getAnimationCount() - 0.5);
		} else if (getAnimationCount() >= 0) {
			getImageview().setViewport(new Rectangle2D(444, 342, getObjectWidth(), getObjectHeight()));
			setAnimationCount(getAnimationCount() - 0.5);
		} else {
			setDoing(false);
			setDone(true);
			setAnimationCount(300);
		}
	}

}
