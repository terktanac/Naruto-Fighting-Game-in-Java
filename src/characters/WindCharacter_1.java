package characters;

import main.GameScreen;
import gameObject.Rasenshuriken;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class WindCharacter_1 extends Character{

	private static Image image = new Image(ClassLoader.getSystemResource("characters/naruto_sage/naruto_sage.png").toString(),1110, 2220, false, false);
	private static AudioClip hit1 = new AudioClip("file:image/characters/naruto_sage/sfx_hit.wav");
	private static AudioClip rasengan = new AudioClip("file:image/characters/naruto_sage/sfx_rasengan.wav");
	private static AudioClip rasengan_ready = new AudioClip("file:soundfx/rasengan_ready.wav");
	private static AudioClip rasengan_hit = new AudioClip("file:soundfx/rasengan_hit.wav");
	private static AudioClip rasenrengan = new AudioClip("file:image/characters/naruto_sage/sfx_rasenrengan.wav");
	private static AudioClip rasenshuriken = new AudioClip("file:image/characters/naruto_sage/sfx_rasenshuri.wav");
	private static AudioClip injured1 = new AudioClip("file:image/characters/naruto_sage/sfx_injured.wav");
	private static AudioClip injured2 = new AudioClip("file:image/characters/naruto_sage/sfx_injured2.wav");
	
	public WindCharacter_1(double currentHealth) {
		super("Naruto", 4,currentHealth, 8, 2,0.6, new ImageView(image));
		setOffSetY(111);
		setAnimation(new CharacterAnimation(this.getImageview(), Duration.millis(300), getCount(), getCol(), getOffSetX(), getOffSetY(), get_Width(), get_Height()));
	}
	@Override
	public int walk_right() {
		if(!isAttacked() && !isDead() && !isCrouch() && !isBlock()) {
			this.setMove(true);
			if(this.isRight() != true) {
				this.getImageview().setRotationAxis(Rotate.Y_AXIS);
				this.getImageview().setRotate(0);
				this.setRight(true);
			}
			this.getAnimation().setOffSetX(0);
			this.getAnimation().setOffSetY(222);
			this.getAnimation().play();
			this.moveX(characters.Character.getX_speed());
			if(getCountFoot() == 0 && !isAir()) {
				foot1.play();
			}
			setCountFoot((getCountFoot()+1)%40);
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
		if(!isAttacked() && !isDead() && !isCrouch() && !isBlock()) {
			if(this.isRight() == true) {
				this.getImageview().setRotationAxis(Rotate.Y_AXIS);
				this.getImageview().setRotate(180);
				this.setRight(false);
			}
			this.getAnimation().setOffSetX(0);
			this.getAnimation().setOffSetY(222);
			this.getAnimation().play();
			this.moveX(-characters.Character.getX_speed());
			System.out.println(getCountFoot());
			if(getCountFoot() == 0 && !isAir()) {
				foot1.play();
			}
			setCountFoot((getCountFoot()+1)%40);
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
				land.play();
				setAir(false);
				setMove(false);
				stand();
			}
		}
		return 1;
	}

	@Override
	public int doMelee() {
		if(isMelee()) {
			if(getDelay() > 70) {
				getImageview().setViewport(new Rectangle2D(555, 555, get_Width() - 15, get_Height() - 1.3));
				setDelay(getDelay()-1);
			}
			else if(getDelay() == 70) {
				getImageview().setViewport(new Rectangle2D(555, 555, get_Width() - 15, get_Height() - 1.3));
				setDelay(getDelay()-1);
				hit1.play();
			}
			else if(getDelay() >= 50) {
				getImageview().setViewport(new Rectangle2D(666, 555, get_Width() - 15, get_Height() - 1.3));
				setDelay(getDelay()-1);
			}
			else if(getDelay() >= 30) {
				getImageview().setViewport(new Rectangle2D(777, 555, get_Width() - 15, get_Height() - 1.3));
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
	public int doRange() {
		if(isRange()) {
			if(getDelay() > 80) {
				getImageview().setViewport(new Rectangle2D(666, 777, get_Width() - 15, get_Height() - 1.3));
				setDelay(getDelay()-1);
			}
			else if(getDelay() == 80) {
				getImageview().setViewport(new Rectangle2D(666, 777, get_Width() - 15, get_Height() - 1.3));
				setDelay(getDelay()-1);
				hit1.play();
			}
			else if(getDelay() >= 60) {
				getImageview().setViewport(new Rectangle2D(777, 777, get_Width() - 15, get_Height() - 1.3));
				setDelay(getDelay()-1);
			}
			else if(getDelay() >= 40) {
				getImageview().setViewport(new Rectangle2D(888, 777, get_Width() - 15, get_Height() - 1.3));
				setDelay(getDelay()-1);
			}
			else if(getDelay() >= 20) {
				getImageview().setViewport(new Rectangle2D(999, 777, get_Width() - 15, get_Height() - 1.3));
				setDelay(getDelay()-1);
			}
			else if(getDelay() >= 0) {
				getImageview().setViewport(new Rectangle2D(0, 888, get_Width() - 15, get_Height() - 1.3));
				setDelay(getDelay()-1);
			}
			else {
				setRange(false);
				setDelay(100);
				stand();
			}
			return 1;
		}
		return 0;
	}
	
	@Override
	public int block() {
		if(!isAir() && !isAttacked() && !isJump() && !isDead() && !isMove()) {
			setBlock(true);
			this.getImageview().setViewport(new Rectangle2D(999, 333, get_Width() - 15, get_Height() - 1.3));
			return 1;
		}
		else 
			return 0;
	}
	
	@Override
	public int doDodge() {
		if(isDodge()) {
			System.out.println("dodge");
			if(getDelay() >= 80) {
				getImageview().setViewport(new Rectangle2D(440, 0, get_Width(), get_Height()));
				setDelay(getDelay()-1);
			}
			else if(getDelay() >= 60) {
				getImageview().setViewport(new Rectangle2D(880, 0, get_Width(), get_Height()));
				setDelay(getDelay()-1);
			}
			else if(getDelay() >= 40) {
				getImageview().setViewport(new Rectangle2D(1320, 0, get_Width(), get_Height()));
				setDelay(getDelay()-1);
			}
			else if(getDelay() >= 20) {
				getImageview().setViewport(new Rectangle2D(880, 0, get_Width(), get_Height()));
				setDelay(getDelay()-1);
			}
			else if(getDelay() >= 0) {
				getImageview().setViewport(new Rectangle2D(440, 0, get_Width(), get_Height()));
				setDelay(getDelay()-1);
			}
			else {
				setOffSetX(0);
				setOffSetY(0);
				setWidth(111);
				setHeight(111);
				getImageview().setImage(image);
				getImageview().setViewport(new Rectangle2D(getOffSetX(), getOffSetY(), get_Width() - 15, get_Height() - 1.3));
				getImageview().setFitHeight(350);
				getImageview().setFitWidth(350);	
				setDodge(false);
				setDelay(100);
				setTranslateX(getTranslateX()+250);
				setLimitDodge(getLimitDodge()-1);
				stand();
			}
			return 1;
		}
		return 0;
		
	}
	
	@Override
	public int stand() {
		this.setOffSetY(111);
		this.getAnimation().setOffSetY(111);
		this.getAnimation().play();
		return 1;
	}
	@Override
	public int basic_skill(Character target) {
		if(isSkill1()) {
			if(getSkillDelay() >= 200) {
				getImageview().setViewport(new Rectangle2D(0, 1221, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() >= 180) {
				getImageview().setViewport(new Rectangle2D(111, 1221, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() >= 160) {
				getImageview().setViewport(new Rectangle2D(222, 1221, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() > 140) {
				getImageview().setViewport(new Rectangle2D(333, 1221, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() == 140) {
				getImageview().setViewport(new Rectangle2D(333, 1221, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
				rasengan_ready.play();
			}
			else if(getSkillDelay() >= 120) {
				getImageview().setViewport(new Rectangle2D(111, 1221, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() >= 100) {
				getImageview().setViewport(new Rectangle2D(222, 1221, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() >= 80) {
				getImageview().setViewport(new Rectangle2D(333, 1221, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() >= 60) {
				getImageview().setViewport(new Rectangle2D(111, 1221, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() >= 40) {
				getImageview().setViewport(new Rectangle2D(222, 1221, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() > 20) {
				getImageview().setViewport(new Rectangle2D(333, 1221, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() == 20) {
				getImageview().setViewport(new Rectangle2D(333, 1221, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
				rasengan.play();
			}
			else if(getSkillDelay() >= 0) {
				if(isRight())
					setTranslateX(target.getTranslateX()-50);
				else
					setTranslateX(target.getTranslateX()+200);
				getImageview().setViewport(new Rectangle2D(444, 1441, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() >= -20) {
				getImageview().setViewport(new Rectangle2D(555, 1441, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() >= -40) {
				getImageview().setViewport(new Rectangle2D(666, 1441, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() >= -60) {
				getImageview().setViewport(new Rectangle2D(777, 1441, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() > -80) {
				getImageview().setViewport(new Rectangle2D(888, 1441, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() == -80) {
				getImageview().setViewport(new Rectangle2D(888, 1441, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
				rasengan_hit.play();
			}
			else if(getSkillDelay() >= -100) {
				getImageview().setViewport(new Rectangle2D(999, 1441, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else {
				target.setStackFly(1);
				target.takeDamage(25);	
				setSkillDelay(250);
				setSkill1(false);
				stand();
			}
			return 1;
		}
		return 0;
	}
	@Override
	public int mid_skill(Character target) {
		if(isSkill2()) {
			if(getSkillDelay() >= 200) {
				getImageview().setViewport(new Rectangle2D(0, 1665, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() >= 180) {
				getImageview().setViewport(new Rectangle2D(111, 1665, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() >= 160) {
				getImageview().setViewport(new Rectangle2D(222, 1665, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() >= 140) {
				getImageview().setViewport(new Rectangle2D(333, 1665, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() >= 120) {
				getImageview().setViewport(new Rectangle2D(111, 1665, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() >= 100) {
				getImageview().setViewport(new Rectangle2D(222, 1665, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() > 80) {
				getImageview().setViewport(new Rectangle2D(333, 1665, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() == 80) {
				getImageview().setViewport(new Rectangle2D(333, 1665, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
				rasengan_ready.play();
			}
			else if(getSkillDelay() >= 60) {
				getImageview().setViewport(new Rectangle2D(111, 1665, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() >= 40) {
				getImageview().setViewport(new Rectangle2D(222, 1665, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() > 20) {
				getImageview().setViewport(new Rectangle2D(333, 1665, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() == 20) {
				getImageview().setViewport(new Rectangle2D(333, 1665, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
				rasenrengan.play();
			}
			else if(getSkillDelay() >= 0) {
				if(isRight())
					setTranslateX(target.getTranslateX()-50);
				else
					setTranslateX(target.getTranslateX()+200);
				getImageview().setViewport(new Rectangle2D(444, 1665, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() >= -20) {
				getImageview().setViewport(new Rectangle2D(555, 1665, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() >= -40) {
				getImageview().setViewport(new Rectangle2D(666, 1665, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() >= -60) {
				getImageview().setViewport(new Rectangle2D(777, 1665, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() > -80) {
				getImageview().setViewport(new Rectangle2D(888, 1665, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() == -80) {
				getImageview().setViewport(new Rectangle2D(888, 1665, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
				rasengan_hit.play();
			}
			else if(getSkillDelay() >= -100) {
				getImageview().setViewport(new Rectangle2D(999, 1665, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else {
				target.setStackFly(1);
				target.takeDamage(50);			
				setSkillDelay(250);
				setSkill2(false);
				stand();
			}
			return 1;
		}
		return 0;
	}
	@Override
	public int High_skill(Character target) {
		if(isSkill3()) {
			if(getSkillDelay() >= 200) {
				getImageview().setViewport(new Rectangle2D(0, 1887, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() >= 180) {
				getImageview().setViewport(new Rectangle2D(111, 1887, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() >= 160) {
				getImageview().setViewport(new Rectangle2D(222, 1887, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() >= 140) {
				getImageview().setViewport(new Rectangle2D(333, 1887, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() >= 120) {
				getImageview().setViewport(new Rectangle2D(111, 1887, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() >= 100) {
				getImageview().setViewport(new Rectangle2D(222, 1887, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() >= 80) {
				getImageview().setViewport(new Rectangle2D(333, 1887, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() >= 60) {
				getImageview().setViewport(new Rectangle2D(111, 1887, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() >= 40) {
				getImageview().setViewport(new Rectangle2D(222, 1887, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() >= 20) {
				getImageview().setViewport(new Rectangle2D(333, 1887, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() >= 0) {
				getImageview().setViewport(new Rectangle2D(444, 1887, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() >= -20) {
				getImageview().setViewport(new Rectangle2D(555, 1887, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() >= -40) {
				getImageview().setViewport(new Rectangle2D(666, 1887, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() >= -60) {
				getImageview().setViewport(new Rectangle2D(777, 1887, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() >= -80) {
				getImageview().setViewport(new Rectangle2D(888, 1887, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() >= -100) {
				getImageview().setViewport(new Rectangle2D(555, 1887, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() >= -120) {
				getImageview().setViewport(new Rectangle2D(666, 1887, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() >= -140) {
				getImageview().setViewport(new Rectangle2D(777, 1887, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() >= -160) {
				getImageview().setViewport(new Rectangle2D(888, 1887, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() >= -180) {
				getImageview().setViewport(new Rectangle2D(999, 1887, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() >= -200) {
				getImageview().setViewport(new Rectangle2D(0, 1998, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() >= -220) {
				getImageview().setViewport(new Rectangle2D(111, 1998, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() > -240) {
				getImageview().setViewport(new Rectangle2D(222, 1998, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() == -240) {
				getImageview().setViewport(new Rectangle2D(222, 1998, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
				rasenshuriken.play();
				target.setStackFly(1);
				GameScreen.getgameObjects1().add(new Rasenshuriken(getTranslateX(), getTranslateY()-100,isRight()));
				GameScreen.get_Root().getChildren().add(GameScreen.getgameObjects1().get(GameScreen.getgameObjects1().size()-1));
				GameScreen.getgameObjects1().get(GameScreen.getgameObjects1().size()-1).getAnimation().play();
			}
			else if(getSkillDelay() >= -260) {
				getImageview().setViewport(new Rectangle2D(0, 1554, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() >= -280) {
				getImageview().setViewport(new Rectangle2D(111, 1554, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else {
				target.getAnimation().stop();
				setSkillDelay(250);
				setSkill3(false);
				return 1;
			}
		}
		return 0;
	}
	@Override
	public int dotakeDamage() {
		if(isAttacked() && !isDead()) {
			if(getStackFly() == 3) {
				if(getDelay() == 100) {
					this.getImageview().setViewport(new Rectangle2D(0, 444, get_Width() - 15, get_Height() - 1.3));
					setDelay(getDelay()-1);
					injured1.play();
				}
				else if(getDelay() >= 0) {
					this.getImageview().setViewport(new Rectangle2D(0, 444, get_Width() - 15, get_Height() - 1.3));
					setDelay(getDelay()-1);
				}
				else {
					setStackFly(2);
					setDelay(100);
					setAttacked(false);
					stand();
					getAnimation().play();
					
				}
			}
			else if(getStackFly() == 2) {
				if(getDelay() == 100) {
					this.getImageview().setViewport(new Rectangle2D(111, 444, get_Width() - 15, get_Height() - 1.3));
					setDelay(getDelay()-1);
					injured1.play();
				}
				else if(getDelay() >= 0) {
					this.getImageview().setViewport(new Rectangle2D(111, 444, get_Width() - 15, get_Height() - 1.3));
					setDelay(getDelay()-1);
				}
				else {
					setStackFly(1);
					setAttacked(false);
					setDelay(100);
					stand();
					getAnimation().play();
					
				}
			}
			else {
				if(getLongDelay() > 150) {
					getImageview().setViewport(new Rectangle2D(111, 444, get_Width() - 15, get_Height() - 1.3));
					setLongDelay(getLongDelay()-1);
					if(isRight())
						setTranslateX(getTranslateX()-3);
					else
						setTranslateX(getTranslateX()+3);
				}
				else if(getLongDelay() == 150) {
					getImageview().setViewport(new Rectangle2D(111, 444, get_Width() - 15, get_Height() - 1.3));
					setLongDelay(getLongDelay()-1);
					if(isRight())
						setTranslateX(getTranslateX()-3);
					else
						setTranslateX(getTranslateX()+3);
					injured2.play();
				}
				else if(getLongDelay() >= 130) {
					getImageview().setViewport(new Rectangle2D(222, 444, get_Width() - 15, get_Height() - 1.3));
					setLongDelay(getLongDelay()-1);
					if(isRight())
						setTranslateX(getTranslateX()-3);
					else
						setTranslateX(getTranslateX()+3);
				}
				else if(getLongDelay() >= 100) {
					getImageview().setViewport(new Rectangle2D(333, 444, get_Width() - 15, get_Height() - 1.3));
					setLongDelay(getLongDelay()-1);
					if(isRight())
						setTranslateX(getTranslateX()-3);
					else
						setTranslateX(getTranslateX()+3);
				}
				else if(getLongDelay() >= 50) {
					getImageview().setViewport(new Rectangle2D(777, 444, get_Width() - 15, get_Height() - 1.3));
					setLongDelay(getLongDelay()-1);
				}
				else if(getLongDelay() >= 25) {
					getImageview().setViewport(new Rectangle2D(888, 444, get_Width() - 15, get_Height() - 1.3));
					setLongDelay(getLongDelay()-1);
				}
				else if(getLongDelay() >= 0) {
					getImageview().setViewport(new Rectangle2D(999, 444, get_Width() - 15, get_Height() - 1.3));
					setLongDelay(getLongDelay()-1);
				}
				else {
					setStackFly(3);
					setAttacked(false);
					setLongDelay(170);
					stand();
					getAnimation().play();
				}
			}
			return 1;
		}
		return 0;
	}
	@Override
	public int dead() {
		if(!isDead())
			return 0;
		if(getLongDelay() >= 150) {
			getImageview().setViewport(new Rectangle2D(111, 444, get_Width() - 15, get_Height() - 1.3));
			setLongDelay(getLongDelay()-1);
			if(isRight())
				setTranslateX(getTranslateX()-3);
			else
				setTranslateX(getTranslateX()+3);
		}
		else if(getLongDelay() >= 130) {
			getImageview().setViewport(new Rectangle2D(222, 444, get_Width() - 15, get_Height() - 1.3));
			setLongDelay(getLongDelay()-1);
			if(isRight())
				setTranslateX(getTranslateX()-3);
			else
				setTranslateX(getTranslateX()+3);
		}
		else if(getLongDelay() >= 100) {
			getImageview().setViewport(new Rectangle2D(333, 444, get_Width() - 15, get_Height() - 1.3));
			setLongDelay(getLongDelay()-1);
			if(isRight())
				setTranslateX(getTranslateX()-3);
			else
				setTranslateX(getTranslateX()+3);
		}
		else if(getLongDelay() >= 50) {
			getImageview().setViewport(new Rectangle2D(777, 444, get_Width() - 15, get_Height() - 1.3));
			setLongDelay(getLongDelay()-1);
		}
		else if(getLongDelay() >= 25) {
			getImageview().setViewport(new Rectangle2D(888, 444, get_Width() - 15, get_Height() - 1.3));
			setLongDelay(getLongDelay()-1);
		}
		else {
			getAnimation().stop();
		}
		return 1;
	}
	
	
}
