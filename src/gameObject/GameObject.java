package gameObject;

import allInterface.Collidable;
import characters.CharacterAnimation;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;

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
	private boolean isDone = false;
	private boolean isDoing = false;
	private ImageView imageview;
	private CharacterAnimation animation;

	public GameObject(double posx, double posy, boolean direction) {
		this.direction = direction;
		this.setTranslateX(posx);
		this.setTranslateY(posy);
	}

	public void moveX() {
		for (int i = 0; i < speed; i++) {
			if (direction) {
				setTranslateX(getTranslateX() + 1);
			} else {
				setTranslateX(getTranslateX() - 1);
			}
		}
	}

	public int getOffSetX() {
		return offSetX;
	}

	public void setOffSetX(int offSetX) {
		this.offSetX = offSetX;
	}

	public int getOffSetY() {
		return offSetY;
	}

	public void setOffSetY(int offSetY) {
		this.offSetY = offSetY;
	}

	public int get_Width() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int get_Height() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public ImageView getImageview() {
		return imageview;
	}

	public void setImageview(ImageView imageview) {
		this.imageview = imageview;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public void setDirection(boolean direction) {
		this.direction = direction;
	}

	public void setAnimation(CharacterAnimation animation) {
		this.animation = animation;
	}

	public CharacterAnimation getAnimation() {
		return animation;
	}

	public boolean isDirection() {
		return direction;
	}

	@Override
	public Rectangle2D getBoundary() {
		return new Rectangle2D(getTranslateX(), getTranslateY(), width, height);

	}

	public int getSpeed() {
		return speed;
	}

	public abstract void doEffect();

	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}

	public boolean isDoing() {
		return isDoing;
	}

	public void setDoing(boolean isDoing) {
		this.isDoing = isDoing;
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public boolean isHasEffect() {
		return hasEffect;
	}

	public void setHasEffect(boolean hasEffect) {
		this.hasEffect = hasEffect;
	}

}
