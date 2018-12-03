package characters;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class CharacterAnimation extends Transition{
	private ImageView image ;
	private int count;
	private int col;
	private int offSetX;
	private int offSetY;
	private int width;
	private int height;
	
	public CharacterAnimation(ImageView image,Duration duration, int count, int col, int offSetX, int offSetY, int width,
			int height) {
		super();
		this.image = image;
		this.count = count;
		this.col = col;
		this.offSetX = offSetX;
		this.offSetY = offSetY;
		this.width = width;
		this.height = height;
		
		setCycleDuration(duration);
		setCycleCount(Animation.INDEFINITE);
		setInterpolator(Interpolator.LINEAR);
		this.image.setViewport(new Rectangle2D(offSetX, offSetY, width, height));
		
	}

	@Override
	protected void interpolate(double frac) {
		int index = (int) ((count*frac) % count);
		int x = (index)*width+offSetX;
		int y = offSetY;
		System.out.println(frac+"<<<<<"+x+">>>>"+y);
		image.setViewport(new Rectangle2D(x, y, width - 15, height - 1.3));
	}

	public void setOffSetX(int offSetX) {
		this.offSetX = offSetX;
	}

	public void setOffSetY(int offSetY) {
		this.offSetY = offSetY;
	}
	
}