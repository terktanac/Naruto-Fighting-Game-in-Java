package characters;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class FireCharacter_1 extends Character{

	private static Image image = new Image(ClassLoader.getSystemResource("characters/sasuke_aka/sasuke_aka.png").toString(),1110, 2220, false, false);
	public FireCharacter_1() {
		super("Sasuke", 1, 100, 10, 4,1.2, new ImageView(image));
		setCount(4);
		setOffSetY(111);
		setAnimation(new CharacterAnimation(this.getImageview(), Duration.millis(300), getCount(), getCol(), getOffSetX(), getOffSetY(), get_Width(), get_Height()));
	}

	@Override
	public int walk_right() {
		this.setMove(true);
		if(!isAttacked() && !isDead() && !isCrouch()) {	
			if(this.isRight() != true) {
				this.getImageview().setRotationAxis(Rotate.Y_AXIS);
				this.getImageview().setRotate(0);
				this.setRight(true);
			}
			this.getAnimation().setOffSetX(0);
			this.getAnimation().setOffSetY(222);
			this.setCount(6);
			this.getAnimation().play();
			this.moveX(characters.Character.getX_speed());
			
			return 1;
		}
		else {
			this.setMove(false);
			return 0;
		}
	}
	@Override
	public int walk_left() {
		this.setMove(true);
		if(!isAttacked() && !isDead() && !isCrouch()) {	
			if(this.isRight() == true) {
				this.getImageview().setRotationAxis(Rotate.Y_AXIS);
				this.getImageview().setRotate(180);
				this.setRight(false);
			}
			this.getAnimation().setOffSetX(0);
			this.getAnimation().setOffSetY(222);
			this.setCount(6);
			this.getAnimation().play();
			this.moveX(-characters.Character.getX_speed());
			return 1;
		}
		else {
			this.setMove(false);
			return 0;
		}
	}
	@Override
	public int crouch() {
		if(!isAir() && !isAttacked() && !isJump() && !isDead() && !isMove()) {
			setCrouch(true);
			this.getImageview().setViewport(new Rectangle2D(333, 333, get_Width() - 15, get_Height() - 1.3));
			return 1;
		}
		else 
			return 0;
	}
	@Override
	public int jump() {
		if(!isAir() && !isAttacked() && !isJump() && !isDead() && !isCrouch()) {
			
			setJump(true);
			setAir(true);
			this.getAnimation().stop();
			return 1;
		}
		else
			return 0;
	}
	
@Override
	public int doJump() {
		if(isJump()) {
			if(getTranslateY() > 100) {
				setTranslateY(getTranslateY()-3);
				getImageview().setViewport(new Rectangle2D(444, 333, get_Width() - 15, get_Height() - 1.3));
			}
			else
				setJump(false);
		}
		else if(isAir()) {
			if(getTranslateY() < 300) {
				setTranslateY(getTranslateY()+3);
				getImageview().setViewport(new Rectangle2D(555, 333, get_Width() - 15, get_Height() - 1.3));
			}
			else {
				setAir(false);
				setMove(false);
				stand();
			}
		}
		return 1;
	}
	
	@Override
	public int melee() {
		if(!isAttacked() && !isDead() && !isCrouch() && !isRange()) {
			setMelee(true);
			setMove(true);
			this.getAnimation().stop();
			return 1;
		}
		return 0;
	}
	
	@Override
	public int doMelee() {
		if(isMelee()) {
			if(getDelay() >= 80) {
				getImageview().setViewport(new Rectangle2D(555, 555, get_Width() - 15, get_Height() - 1.3));
				setDelay(getDelay()-1);
			}
			else if(getDelay() >= 60) {
				getImageview().setViewport(new Rectangle2D(666, 555, get_Width() - 15, get_Height() - 1.3));
				setDelay(getDelay()-1);
			}
			else if(getDelay() >= 40) {
				getImageview().setViewport(new Rectangle2D(777, 555, get_Width() - 15, get_Height() - 1.3));
				setDelay(getDelay()-1);
			}
			else if(getDelay() >= 20) {
				getImageview().setViewport(new Rectangle2D(888, 555, get_Width() - 15, get_Height() - 1.3));
				setDelay(getDelay()-1);
			}
			else if(getDelay() >= 0) {
				getImageview().setViewport(new Rectangle2D(999, 555, get_Width() - 15, get_Height() - 1.3));
				setDelay(getDelay()-1);
			}
			else {
				setMelee(false);
				setDelay(100);
				stand();
			}
		}
		return 1;
	}

	@Override
	public int range() {
		if(!isAttacked() && !isDead() && !isCrouch() && !isMelee()) {
			setRange(true);
			setMove(true);
			this.getAnimation().stop();
			return 1;
		}
		return 1;
	}

	@Override
	public int doRange() {
		if(isRange()) {
			if(getDelay() >= 80) {
				getImageview().setViewport(new Rectangle2D(777, 999, get_Width() - 15, get_Height() - 1.3));
				setDelay(getDelay()-1);
			}
			else if(getDelay() >= 60) {
				getImageview().setViewport(new Rectangle2D(888, 999, get_Width() - 15, get_Height() - 1.3));
				setDelay(getDelay()-1);
			}
			else if(getDelay() >= 40) {
				getImageview().setViewport(new Rectangle2D(999, 999, get_Width() - 15, get_Height() - 1.3));
				setDelay(getDelay()-1);
			}
			else if(getDelay() >= 20) {
				getImageview().setViewport(new Rectangle2D(0, 1110, get_Width() - 15, get_Height() - 1.3));
				setDelay(getDelay()-1);
			}
			else {
				setRange(false);
				setDelay(100);
				stand();
			}
		}
		return 1;
	}
	
	@Override
	public int dodge() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int block() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int stand() {
		this.setCount(4);
		this.setOffSetY(111);
		this.getAnimation().setOffSetY(111);
		this.getAnimation().play();
		return 1;
	}

	@Override
	public int basic_skill() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int mid_skill() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int High_skill() {
		// TODO Auto-generated method stub
		return 0;
	}

}
