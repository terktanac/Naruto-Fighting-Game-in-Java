package GameObject;

import characters.CharacterAnimation;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Rasenshuriken extends GameObject{

	public Rasenshuriken(double posx, double posy, boolean direction) {
		super(posx, posy, direction);
		setOffSetX(333);
		setOffSetY(1998);
		setWidth(111);
		setHeight(111);
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
		setOffSetX(0);
		setOffSetY(0);
		setWidth(210);
		setHeight(129);
		getAnimation().stop();
		for(int i = 0; i < 300; i++) {
			setImageview(new ImageView("rasenshock.png"));
			if(i < 30) {
				getImageview().setViewport(new Rectangle2D(111, 1554, get_Width(), get_Height()));
			}
			else if(i < 20) {
				getImageview().setViewport(new Rectangle2D(111, 1554, get_Width(), get_Height()));
			}
			else if(i < 40) {
				getImageview().setViewport(new Rectangle2D(111, 1554, get_Width(), get_Height()));
			}
			else if(i < 60) {
				getImageview().setViewport(new Rectangle2D(111, 1554, get_Width(), get_Height()));
			}
			else if(i < 80) {
				getImageview().setViewport(new Rectangle2D(111, 1554, get_Width(), get_Height()));
			}
			else if(i < 100) {
				getImageview().setViewport(new Rectangle2D(111, 1554, get_Width(), get_Height()));
			}
			else if(i < 30) {
				getImageview().setViewport(new Rectangle2D(111, 1554, get_Width(), get_Height()));
			}
			else if(i < 30) {
				getImageview().setViewport(new Rectangle2D(111, 1554, get_Width(), get_Height()));
			}
			else if(i < 30) {
				getImageview().setViewport(new Rectangle2D(111, 1554, get_Width(), get_Height()));
			}
		}
		
	}

}
