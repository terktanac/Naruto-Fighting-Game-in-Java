package gameobject;

import allInterface.Collidable;
import characters.CharacterAnimation;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class GameObject extends Pane implements Collidable {
	private boolean hasEffect = false;
	private int offSetX;
	private int offSetY;
	private int width;
	private int height;
	private int count;
	private int speed;
	private int damage;
	private int delay = 300;
	private boolean direction;
	private boolean isDone;
	private boolean isDoing;
	private ImageView imageview;
	private CharacterAnimation animation;

	public GameObject(double posx, double posy, boolean direction) {
		this.direction = direction;
		this.setTranslateX(posx);
		this.setTranslateY(posy);
	}

	public final void moveX() {
		for (int i = 0; i < speed; i++) {
			if (direction) {
				setTranslateX(getTranslateX() + 1);
			} else {
				setTranslateX(getTranslateX() - 1);
			}
		}
	}

	public final int getOffSetX() {
		return offSetX;
	}

	public final void setOffSetX(int offSetX) {
		this.offSetX = offSetX;
	}

	public final int getOffSetY() {
		return offSetY;
	}

	public final void setOffSetY(int offSetY) {
		this.offSetY = offSetY;
	}

	public final int getObjectWidth() {
		return width;
	}

	public final void setWidth(int width) {
		this.width = width;
	}

	public final int getObjectHeight() {
		return height;
	}

	public final void setHeight(int height) {
		this.height = height;
	}

	public final int getCount() {
		return count;
	}

	public final void setCount(int count) {
		this.count = count;
	}

	public final ImageView getImageview() {
		return imageview;
	}

	public final void setImageview(ImageView imageview) {
		this.imageview = imageview;
	}

	public final void setSpeed(int speed) {
		this.speed = speed;
	}

	public final int getDamage() {
		return damage;
	}

	public final void setDamage(int damage) {
		this.damage = damage;
	}

	public final void setDirection(boolean direction) {
		this.direction = direction;
	}

	public final void setAnimation(CharacterAnimation animation) {
		this.animation = animation;
	}

	public final CharacterAnimation getAnimation() {
		return animation;
	}

	public final boolean isDirection() {
		return direction;
	}

	@Override
	public final Rectangle2D getBoundary() {
		return new Rectangle2D(getTranslateX(), getTranslateY(), width, height);

	}

	public final int getSpeed() {
		return speed;
	}

	public abstract void doEffect();

	public final boolean isDone() {
		return isDone;
	}

	public final void setDone(boolean isDone) {
		this.isDone = isDone;
	}

	public final boolean isDoing() {
		return isDoing;
	}

	public final void setDoing(boolean isDoing) {
		this.isDoing = isDoing;
	}

	public final int getDelay() {
		return delay;
	}

	public final void setDelay(int delay) {
		this.delay = delay;
	}

	public final boolean isHasEffect() {
		return hasEffect;
	}

	public final void setHasEffect(boolean hasEffect) {
		this.hasEffect = hasEffect;
	}

}
