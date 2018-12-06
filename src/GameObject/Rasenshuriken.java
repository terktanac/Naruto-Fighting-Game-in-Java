package GameObject;

import characters.CharacterAnimation;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Rasenshuriken extends GameObject{

	private Image shock = new Image("sys/rasenshock.png");
	
	public Rasenshuriken(double posx, double posy, boolean direction) {
		super(posx, posy, direction);
		setHasEffect(true);
		setOffSetX(333);
		setOffSetY(1998);
		setWidth(111);
		setHeight(111);
		setDamage(100);
		setCount(3);
		setSpeed(2);
		setImageview(new ImageView("characters/naruto_sage/naruto_sage.png"));
		getImageview().setFitHeight(350);
		getImageview().setFitWidth(350);
		setAnimation((new CharacterAnimation(getImageview(), Duration.millis(300), getCount(), 0, getOffSetX(), getOffSetY(), get_Width(), get_Height())));
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
		getAnimation().stop();		
		if(getDelay() >= 280) {
			getImageview().setViewport(new Rectangle2D(0, 0, get_Width(), get_Height()));
			setDelay(getDelay()-1);
		}
		else if(getDelay() >= 260) {
			getImageview().setViewport(new Rectangle2D(630, 0, get_Width(), get_Height()));
			setDelay(getDelay()-1);
		}
		else if(getDelay() >= 230) {
			getImageview().setViewport(new Rectangle2D(1260, 0, get_Width(), get_Height()));
			setDelay(getDelay()-1);
		}
		else if(getDelay() >= 200) {
			getImageview().setViewport(new Rectangle2D(0, 387, get_Width(), get_Height()));
			setDelay(getDelay()-1);
		}
		else if(getDelay() >= 170) {
			getImageview().setViewport(new Rectangle2D(630, 387, get_Width(), get_Height()));
			setDelay(getDelay()-1);
		}
		else if(getDelay() >= 140) {
			getImageview().setViewport(new Rectangle2D(0, 387, get_Width(), get_Height()));
			setDelay(getDelay()-1);
		}
		else if(getDelay() >= 110) {
			getImageview().setViewport(new Rectangle2D(1260, 0, get_Width(), get_Height()));
			setDelay(getDelay()-1);
		}
		else if(getDelay() >= 90) {
			getImageview().setViewport(new Rectangle2D(0, 387, get_Width(), get_Height()));
			setDelay(getDelay()-1);
		}
		else if(getDelay() >= 70) {
			getImageview().setViewport(new Rectangle2D(1260, 387, get_Width(), get_Height()));
			setDelay(getDelay()-1);
		}
		else if(getDelay() >= 50) {
			getImageview().setViewport(new Rectangle2D(0, 774, get_Width(), get_Height()));
			setDelay(getDelay()-1);
		}
		else if(getDelay() >= 25) {
			getImageview().setViewport(new Rectangle2D(630, 774, get_Width(), get_Height()));
			setDelay(getDelay()-1);
		}
		else if(getDelay() >= 0) {
			getImageview().setViewport(new Rectangle2D(1260, 774, get_Width(), get_Height()));
			setDelay(getDelay()-1);
		}
		else {
			setDoing(false);
			setDone(true);
			setDelay(300);
		}
	}

}
