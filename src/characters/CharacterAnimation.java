package characters;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class CharacterAnimation extends Transition {
	private ImageView image;
	private int count;
	private int offSetX;
	private int offSetY;
	private int width;
	private int height;

	public CharacterAnimation(ImageView image, Duration duration, int count, int col, int offSetX, int offSetY,
			int width, int height) {
		super();
		this.image = image;
		this.count = count;
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
	protected final void interpolate(double frac) {
		final int x = ((int) ((count * frac) % count)) * width + offSetX;
		final int y = offSetY;
		image.setViewport(new Rectangle2D(x, y, width - 15, height - 1.3));
	}

	public final void setOffSetX(int offSetX) {
		this.offSetX = offSetX;
	}

	public final void setOffSetY(int offSetY) {
		this.offSetY = offSetY;
	}

}