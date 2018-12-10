package gameobject;

import characters.Character;
import characters.CharacterAnimation;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;

public class Rasenshuriken extends GameObject {

	private Image shock = new Image("sys/rasenshock.png");
	private static AudioClip rasenshurikenObjecthit1 = new AudioClip(
			ClassLoader.getSystemResource("rasenshuriken.wav").toString());
	private static AudioClip rasenshurikenObjecthit2 = new AudioClip(
			ClassLoader.getSystemResource("rasenshuriken4.wav").toString());
	private Character target;
	private boolean check;

	public Rasenshuriken(double posx, double posy, boolean direction, Character target) {
		super(posx, posy, direction);
		this.target = target;
		setHasEffect(true);
		setOffSetX(333);
		setOffSetY(1998);
		setWidth(111);
		setHeight(111);
		setDamage(100);
		setCount(3);
		setSpeed(2);
		setImageview(new ImageView(
				ClassLoader.getSystemResource("characters/naruto_sage/naruto_sage_Clear.png").toString()));
		getImageview().setFitHeight(350);
		getImageview().setFitWidth(350);
		setAnimation((new CharacterAnimation(getImageview(), Duration.millis(600), getCount(), 0, getOffSetX(),
				getOffSetY(), getObjectWidth(), getObjectHeight())));
		getChildren().addAll(getImageview());
	}

	@Override
	public void doEffect() {
		getImageview().setImage(shock);
		setOffSetX(0);
		setOffSetY(0);
		setWidth(630);
		setHeight(387);
		getImageview().setFitWidth(630);
		getImageview().setFitHeight(387);
		if (target.isRight() && !check) {
			check = true;
			setTranslateX(target.getTranslateX() - 300);
		}
		setTranslateY(target.getTranslateY());
		getAnimation().stop();
		if (getAnimationCount() >= 280) {
			getImageview().setViewport(new Rectangle2D(0, 0, getObjectWidth(), getObjectHeight()));
			setAnimationCount(getAnimationCount() - 0.5);
		} else if (getAnimationCount() > 260) {
			getImageview().setViewport(new Rectangle2D(630, 0, getObjectWidth(), getObjectHeight()));
			setAnimationCount(getAnimationCount() - 0.5);
		} else if (getAnimationCount() == 260) {
			getImageview().setViewport(new Rectangle2D(630, 0, getObjectWidth(), getObjectHeight()));
			setAnimationCount(getAnimationCount() - 0.5);
			rasenshurikenObjecthit1.play();
			rasenshurikenObjecthit2.play();
		} else if (getAnimationCount() >= 230) {
			getImageview().setViewport(new Rectangle2D(1260, 0, getObjectWidth(), getObjectHeight()));
			setAnimationCount(getAnimationCount() - 0.5);
		} else if (getAnimationCount() >= 200) {
			getImageview().setViewport(new Rectangle2D(0, 387, getObjectWidth(), getObjectHeight()));
			setAnimationCount(getAnimationCount() - 0.5);
		} else if (getAnimationCount() >= 170) {
			getImageview().setViewport(new Rectangle2D(630, 387, getObjectWidth(), getObjectHeight()));
			setAnimationCount(getAnimationCount() - 0.5);
		} else if (getAnimationCount() >= 140) {
			getImageview().setViewport(new Rectangle2D(0, 387, getObjectWidth(), getObjectHeight()));
			setAnimationCount(getAnimationCount() - 0.5);
		} else if (getAnimationCount() >= 110) {
			getImageview().setViewport(new Rectangle2D(1260, 0, getObjectWidth(), getObjectHeight()));
			setAnimationCount(getAnimationCount() - 0.5);
		} else if (getAnimationCount() >= 90) {
			getImageview().setViewport(new Rectangle2D(0, 387, getObjectWidth(), getObjectHeight()));
			setAnimationCount(getAnimationCount() - 0.5);
		} else if (getAnimationCount() >= 70) {
			getImageview().setViewport(new Rectangle2D(1260, 387, getObjectWidth(), getObjectHeight()));
			setAnimationCount(getAnimationCount() - 0.5);
		} else if (getAnimationCount() >= 50) {
			getImageview().setViewport(new Rectangle2D(0, 774, getObjectWidth(), getObjectHeight()));
			setAnimationCount(getAnimationCount() - 0.5);
		} else if (getAnimationCount() >= 25) {
			getImageview().setViewport(new Rectangle2D(630, 774, getObjectWidth(), getObjectHeight()));
			setAnimationCount(getAnimationCount() - 0.5);
		} else if (getAnimationCount() >= 0) {
			getImageview().setViewport(new Rectangle2D(1260, 774, getObjectWidth(), getObjectHeight()));
			setAnimationCount(getAnimationCount() - 0.5);
		} else {
			setDoing(false);
			setDone(true);
			setAnimationCount(300);
		}
	}

}
