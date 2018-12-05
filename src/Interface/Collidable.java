package Interface;

import javafx.geometry.Rectangle2D;

public interface Collidable {
	public Rectangle2D getBoundary();
	public int takeDamage();
}
