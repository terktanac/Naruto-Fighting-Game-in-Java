package characters;

import java.util.ArrayList;

import gameobject.GameObject;
import gameobject.Rasenshuriken;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import scenes.GameScreen;

public class Naruto extends Character {

	private static Image image = new Image(
			ClassLoader.getSystemResource("characters/naruto_sage/naruto_sage_Clear.png").toString(), 1110, 2220, false,
			false);
	private static AudioClip hit1 = new AudioClip("file:image/characters/naruto_sage/sfx_hit.wav");
	private static AudioClip rasengan = new AudioClip("file:image/characters/naruto_sage/sfx_rasengan.wav");
	private static AudioClip rasenganReady = new AudioClip("file:soundfx/rasengan_ready.wav");
	private static AudioClip rasenganHit = new AudioClip("file:soundfx/rasengan_hit.wav");
	private static AudioClip rasenrengan = new AudioClip("file:image/characters/naruto_sage/sfx_rasenrengan.wav");
	private static AudioClip rasenshuriken = new AudioClip("file:image/characters/naruto_sage/sfx_rasenshuri.wav");
	private static AudioClip injured1 = new AudioClip("file:image/characters/naruto_sage/sfx_injured.wav");
	private static AudioClip injured2 = new AudioClip("file:image/characters/naruto_sage/sfx_injured2.wav");

	public Naruto(double currentHealth) {
		super("Naruto", 4, currentHealth, 8, 2, 0.6, new ImageView(image));
		setOffSetY(111);
		setAnimation(new CharacterAnimation(this.getImageview(), Duration.millis(300), getCount(), getCol(),
				getOffSetX(), getOffSetY(), getCharacterWidth(), getCharacterHeight()));
	}

	@Override
	public final int walkRight() {
		if (!isAttacked() && !isDead() && !isCrouch() && !isBlock()) {
			this.setMove(true);
			if (!this.isRight()) {
				this.getImageview().setRotationAxis(Rotate.Y_AXIS);
				this.getImageview().setRotate(0);
				this.setRight(true);
			}
			this.getAnimation().setOffSetX(0);
			this.getAnimation().setOffSetY(222);
			this.getAnimation().play();
			this.moveX(characters.Character.getXspeed());
			if (getCountFoot() == 0 && !isAir()) {
				foot1.play();
			}
			setCountFoot((getCountFoot() + 1) % 40);
			return 1;
		} else {
			this.setMove(false);
			return 0;
		}
	}

	@Override
	public final int walkLeft() {
		this.setMove(true);
		if (!isAttacked() && !isDead() && !isCrouch() && !isBlock()) {
			if (this.isRight()) {
				this.getImageview().setRotationAxis(Rotate.Y_AXIS);
				this.getImageview().setRotate(180);
				this.setRight(false);
			}
			this.getAnimation().setOffSetX(0);
			this.getAnimation().setOffSetY(222);
			this.getAnimation().play();
			this.moveX(-characters.Character.getXspeed());
			System.out.println(getCountFoot());
			if (getCountFoot() == 0 && !isAir()) {
				foot1.play();
			}
			setCountFoot((getCountFoot() + 1) % 40);
			return 1;
		} else {
			this.setMove(false);
			return 0;
		}
	}

	@Override
	public final int crouch() {
		if (!isAir() && !isAttacked() && !isJump() && !isDead() && !isMove()) {
			setCrouch(true);
			this.getImageview()
					.setViewport(new Rectangle2D(333, 333, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public final int doJump() {
		if (isJump()) {
			if (getTranslateY() > 100) {
				setTranslateY(getTranslateY() - 2);
				getImageview()
						.setViewport(new Rectangle2D(444, 333, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
			} else {
				setJump(false);
			}
		} else if (isAir()) {
			if (getTranslateY() < 300) {
				setTranslateY(getTranslateY() + 2);
				getImageview()
						.setViewport(new Rectangle2D(555, 333, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
			} else {
				land.play();
				setAir(false);
				setMove(false);
				stand();
			}
		}
		return 1;
	}

	@Override
	public final int doMelee() {
		if (isMelee()) {
			if (getAnimationCount() > 70) {
				getImageview()
						.setViewport(new Rectangle2D(555, 555, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
				setAnimationCount(getAnimationCount() - 0.5);
			} else if (getAnimationCount() == 70) {
				getImageview()
						.setViewport(new Rectangle2D(555, 555, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
				setAnimationCount(getAnimationCount() - 0.5);
				hit1.play();
			} else if (getAnimationCount() >= 50) {
				getImageview()
						.setViewport(new Rectangle2D(666, 555, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
				setAnimationCount(getAnimationCount() - 0.5);
			} else if (getAnimationCount() >= 30) {
				getImageview()
						.setViewport(new Rectangle2D(777, 555, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
				setAnimationCount(getAnimationCount() - 0.5);
			} else {
				setMelee(false);
				setAnimationCount(100);
				stand();
			}
		}
		return 1;
	}

	@Override
	public final int doRange() {
		if (isRange()) {
			if (getAnimationCount() > 80) {
				getImageview()
						.setViewport(new Rectangle2D(666, 777, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
				setAnimationCount(getAnimationCount() - 0.5);
			} else if (getAnimationCount() == 80) {
				getImageview()
						.setViewport(new Rectangle2D(666, 777, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
				setAnimationCount(getAnimationCount() - 0.5);
				hit1.play();
			} else if (getAnimationCount() >= 60) {
				getImageview()
						.setViewport(new Rectangle2D(777, 777, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
				setAnimationCount(getAnimationCount() - 0.5);
			} else if (getAnimationCount() >= 40) {
				getImageview()
						.setViewport(new Rectangle2D(888, 777, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
				setAnimationCount(getAnimationCount() - 0.5);
			} else if (getAnimationCount() >= 20) {
				getImageview()
						.setViewport(new Rectangle2D(999, 777, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
				setAnimationCount(getAnimationCount() - 0.5);
			} else if (getAnimationCount() >= 0) {
				getImageview()
						.setViewport(new Rectangle2D(0, 888, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
				setAnimationCount(getAnimationCount() - 0.5);
			} else {
				setRange(false);
				setAnimationCount(100);
				stand();
			}
			return 1;
		}
		return 0;
	}

	@Override
	public final int block() {
		if (!isAir() && !isAttacked() && !isJump() && !isDead() && !isMove()) {
			setBlock(true);
			this.getImageview()
					.setViewport(new Rectangle2D(999, 333, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public final int doDodge() {
		if (isDodge()) {
			System.out.println("dodge");
			if (getAnimationCount() >= 80) {
				getImageview().setViewport(new Rectangle2D(440, 0, getCharacterWidth(), getCharacterHeight()));
				setAnimationCount(getAnimationCount() - 0.5);
			} else if (getAnimationCount() >= 60) {
				getImageview().setViewport(new Rectangle2D(880, 0, getCharacterWidth(), getCharacterHeight()));
				setAnimationCount(getAnimationCount() - 0.5);
			} else if (getAnimationCount() >= 40) {
				getImageview().setViewport(new Rectangle2D(1320, 0, getCharacterWidth(), getCharacterHeight()));
				setAnimationCount(getAnimationCount() - 0.5);
			} else if (getAnimationCount() >= 20) {
				getImageview().setViewport(new Rectangle2D(880, 0, getCharacterWidth(), getCharacterHeight()));
				setAnimationCount(getAnimationCount() - 0.5);
			} else if (getAnimationCount() >= 0) {
				getImageview().setViewport(new Rectangle2D(440, 0, getCharacterWidth(), getCharacterHeight()));
				setAnimationCount(getAnimationCount() - 0.5);
			} else {
				setOffSetX(0);
				setOffSetY(0);
				setWidth(111);
				setHeight(111);
				getImageview().setImage(image);
				getImageview().setViewport(new Rectangle2D(getOffSetX(), getOffSetY(), getCharacterWidth() - 15,
						getCharacterHeight() - 1.3));
				getImageview().setFitHeight(350);
				getImageview().setFitWidth(350);
				setDodge(false);
				setAnimationCount(100);
				setTranslateX(getTranslateX() + 250);
				setLimitDodge(getLimitDodge() - 1);
				stand();
			}
			return 1;
		}
		return 0;

	}

	@Override
	public final int stand() {
		this.setOffSetY(111);
		this.getAnimation().setOffSetY(111);
		this.getAnimation().play();
		return 1;
	}

	@Override
	public final int basicSkill(Character target, ArrayList<GameObject> playerObject) {
		if (isSkill1()) {
			if (getSkillAnimationCount() >= 200) {
				getImageview()
						.setViewport(new Rectangle2D(0, 1221, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
			} else if (getSkillAnimationCount() >= 180) {
				getImageview()
						.setViewport(new Rectangle2D(111, 1221, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
			} else if (getSkillAnimationCount() >= 160) {
				getImageview()
						.setViewport(new Rectangle2D(222, 1221, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
			} else if (getSkillAnimationCount() > 140) {
				getImageview()
						.setViewport(new Rectangle2D(333, 1221, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
			} else if (getSkillAnimationCount() == 140) {
				getImageview()
						.setViewport(new Rectangle2D(333, 1221, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
				rasenganReady.play();
			} else if (getSkillAnimationCount() >= 120) {
				getImageview()
						.setViewport(new Rectangle2D(111, 1221, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
			} else if (getSkillAnimationCount() >= 100) {
				getImageview()
						.setViewport(new Rectangle2D(222, 1221, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
			} else if (getSkillAnimationCount() >= 80) {
				getImageview()
						.setViewport(new Rectangle2D(333, 1221, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
			} else if (getSkillAnimationCount() >= 60) {
				getImageview()
						.setViewport(new Rectangle2D(111, 1221, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
			} else if (getSkillAnimationCount() >= 40) {
				getImageview()
						.setViewport(new Rectangle2D(222, 1221, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
			} else if (getSkillAnimationCount() > 20) {
				getImageview()
						.setViewport(new Rectangle2D(333, 1221, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
			} else if (getSkillAnimationCount() == 20) {
				getImageview()
						.setViewport(new Rectangle2D(333, 1221, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
				rasengan.play();
			} else if (getSkillAnimationCount() >= 0) {
				if (isRight()) {
					setTranslateX(target.getTranslateX() - 50);
				} else {
					setTranslateX(target.getTranslateX());
				}
				getImageview()
						.setViewport(new Rectangle2D(444, 1441, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
			} else if (getSkillAnimationCount() >= -20) {
				getImageview()
						.setViewport(new Rectangle2D(555, 1441, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
				setSkillAnimationCount(getSkillAnimationCount() - 0.25);
			} else if (getSkillAnimationCount() >= -40) {
				getImageview()
						.setViewport(new Rectangle2D(666, 1441, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
			} else if (getSkillAnimationCount() >= -60) {
				getImageview()
						.setViewport(new Rectangle2D(777, 1441, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
			} else if (getSkillAnimationCount() > -80) {
				getImageview()
						.setViewport(new Rectangle2D(888, 1441, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
			} else if (getSkillAnimationCount() == -80) {
				getImageview()
						.setViewport(new Rectangle2D(888, 1441, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
				rasenganHit.play();
			} else if (getSkillAnimationCount() >= -100) {
				getImageview()
						.setViewport(new Rectangle2D(999, 1441, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
			} else {
				target.setStackFly(1);
				target.takeDamage(25);
				setSkillAnimationCount(250);
				setSkill1(false);
				stand();
			}
			return 1;
		}
		return 0;
	}

	@Override
	public final int midSkill(Character target, ArrayList<GameObject> playerObject) {
		if (isSkill2()) {
			if (getSkillAnimationCount() >= 200) {
				getImageview()
						.setViewport(new Rectangle2D(0, 1665, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
			} else if (getSkillAnimationCount() >= 180) {
				getImageview()
						.setViewport(new Rectangle2D(111, 1665, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
			} else if (getSkillAnimationCount() >= 160) {
				getImageview()
						.setViewport(new Rectangle2D(222, 1665, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
			} else if (getSkillAnimationCount() >= 140) {
				getImageview()
						.setViewport(new Rectangle2D(333, 1665, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
			} else if (getSkillAnimationCount() >= 120) {
				getImageview()
						.setViewport(new Rectangle2D(111, 1665, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
			} else if (getSkillAnimationCount() >= 100) {
				getImageview()
						.setViewport(new Rectangle2D(222, 1665, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
			} else if (getSkillAnimationCount() > 80) {
				getImageview()
						.setViewport(new Rectangle2D(333, 1665, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
			} else if (getSkillAnimationCount() == 80) {
				getImageview()
						.setViewport(new Rectangle2D(333, 1665, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
				rasenganReady.play();
			} else if (getSkillAnimationCount() >= 60) {
				getImageview()
						.setViewport(new Rectangle2D(111, 1665, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
			} else if (getSkillAnimationCount() >= 40) {
				getImageview()
						.setViewport(new Rectangle2D(222, 1665, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
			} else if (getSkillAnimationCount() > 20) {
				getImageview()
						.setViewport(new Rectangle2D(333, 1665, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
			} else if (getSkillAnimationCount() == 20) {
				getImageview()
						.setViewport(new Rectangle2D(333, 1665, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
				rasenrengan.play();
			} else if (getSkillAnimationCount() >= 0) {
				if (isRight()) {
					setTranslateX(target.getTranslateX() - 50);
				} else {
					setTranslateX(target.getTranslateX());
				}
				getImageview()
						.setViewport(new Rectangle2D(444, 1665, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
			} else if (getSkillAnimationCount() >= -20) {
				getImageview()
						.setViewport(new Rectangle2D(555, 1665, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
			} else if (getSkillAnimationCount() >= -40) {
				getImageview()
						.setViewport(new Rectangle2D(666, 1665, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
			} else if (getSkillAnimationCount() >= -60) {
				getImageview()
						.setViewport(new Rectangle2D(777, 1665, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
			} else if (getSkillAnimationCount() > -80) {
				getImageview()
						.setViewport(new Rectangle2D(888, 1665, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
			} else if (getSkillAnimationCount() == -80) {
				getImageview()
						.setViewport(new Rectangle2D(888, 1665, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
				rasenganHit.play();
			} else if (getSkillAnimationCount() >= -100) {
				getImageview()
						.setViewport(new Rectangle2D(999, 1665, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
			} else {
				target.setStackFly(1);
				target.takeDamage(50);
				setSkillAnimationCount(250);
				setSkill2(false);
				stand();
			}
			return 1;
		}
		return 0;
	}

	@Override
	public int highSkill(Character target, ArrayList<GameObject> playerObject) {
		if (isSkill3()) {
			if (getSkillAnimationCount() == 250) {
				getImageview()
						.setViewport(new Rectangle2D(0, 1887, getCharacterWidth(), getCharacterHeight()));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
				rasenshuriken.play();
			} else if (getSkillAnimationCount() >= 200) {
				getImageview()
						.setViewport(new Rectangle2D(0, 1887, getCharacterWidth(), getCharacterHeight()));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
			} else if (getSkillAnimationCount() >= 180) {
				getImageview()
						.setViewport(new Rectangle2D(111, 1887, getCharacterWidth(), getCharacterHeight()));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
			} else if (getSkillAnimationCount() >= 160) {
				getImageview()
						.setViewport(new Rectangle2D(222, 1887, getCharacterWidth(), getCharacterHeight()));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
			} else if (getSkillAnimationCount() >= 140) {
				getImageview()
						.setViewport(new Rectangle2D(333, 1887, getCharacterWidth(), getCharacterHeight()));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
			} else if (getSkillAnimationCount() >= 120) {
				getImageview()
						.setViewport(new Rectangle2D(111, 1887, getCharacterWidth(), getCharacterHeight()));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
			} else if (getSkillAnimationCount() >= 100) {
				getImageview()
						.setViewport(new Rectangle2D(222, 1887, getCharacterWidth(), getCharacterHeight()));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
			} else if (getSkillAnimationCount() >= 80) {
				getImageview()
						.setViewport(new Rectangle2D(333, 1887, getCharacterWidth(), getCharacterHeight()));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
			} else if (getSkillAnimationCount() >= 60) {
				getImageview()
						.setViewport(new Rectangle2D(111, 1887, getCharacterWidth(), getCharacterHeight()));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
			} else if (getSkillAnimationCount() >= 40) {
				getImageview()
						.setViewport(new Rectangle2D(222, 1887, getCharacterWidth(), getCharacterHeight()));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
			} else if (getSkillAnimationCount() >= 20) {
				getImageview()
						.setViewport(new Rectangle2D(333, 1887, getCharacterWidth(), getCharacterHeight()));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
			} else if (getSkillAnimationCount() >= 0) {
				getImageview()
						.setViewport(new Rectangle2D(444, 1887, getCharacterWidth(), getCharacterHeight()));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
			} else if (getSkillAnimationCount() >= -20) {
				getImageview()
						.setViewport(new Rectangle2D(555, 1887, getCharacterWidth(), getCharacterHeight()));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
			} else if (getSkillAnimationCount() >= -40) {
				getImageview()
						.setViewport(new Rectangle2D(666, 1887, getCharacterWidth(), getCharacterHeight()));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
			} else if (getSkillAnimationCount() >= -60) {
				getImageview()
						.setViewport(new Rectangle2D(777, 1887, getCharacterWidth(), getCharacterHeight()));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
			} else if (getSkillAnimationCount() >= -80) {
				getImageview()
						.setViewport(new Rectangle2D(888, 1887, getCharacterWidth(), getCharacterHeight()));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
			} else if (getSkillAnimationCount() >= -100) {
				getImageview()
						.setViewport(new Rectangle2D(555, 1887, getCharacterWidth(), getCharacterHeight()));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
			} else if (getSkillAnimationCount() >= -120) {
				getImageview()
						.setViewport(new Rectangle2D(666, 1887, getCharacterWidth(), getCharacterHeight()));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
			} else if (getSkillAnimationCount() >= -140) {
				getImageview()
						.setViewport(new Rectangle2D(777, 1887, getCharacterWidth(), getCharacterHeight()));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
			} else if (getSkillAnimationCount() >= -160) {
				getImageview()
						.setViewport(new Rectangle2D(888, 1887, getCharacterWidth(), getCharacterHeight()));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
			} else if (getSkillAnimationCount() >= -180) {
				getImageview()
						.setViewport(new Rectangle2D(999, 1887, getCharacterWidth(), getCharacterHeight()));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
			} else if (getSkillAnimationCount() >= -200) {
				getImageview()
						.setViewport(new Rectangle2D(0, 1998, getCharacterWidth(), getCharacterHeight()));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
			} else if (getSkillAnimationCount() >= -220) {
				getImageview()
						.setViewport(new Rectangle2D(111, 1998, getCharacterWidth(), getCharacterHeight()));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
			} else if (getSkillAnimationCount() > -240) {
				getImageview()
						.setViewport(new Rectangle2D(222, 1998, getCharacterWidth(), getCharacterHeight()));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
			} else if (getSkillAnimationCount() == -240) {
				getImageview()
						.setViewport(new Rectangle2D(222, 1998, getCharacterWidth(), getCharacterHeight()));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
				target.setStackFly(1);
				playerObject.add(new Rasenshuriken(getTranslateX(), getTranslateY() - 100, isRight(), target));
				GameScreen.getPaneRoot().getChildren().add(playerObject.get(playerObject.size() - 1));
				playerObject.get(playerObject.size() - 1).getAnimation().play();
			} else if (getSkillAnimationCount() >= -260) {
				getImageview()
						.setViewport(new Rectangle2D(0, 1554, getCharacterWidth(), getCharacterHeight()));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
			} else if (getSkillAnimationCount() >= -280) {
				getImageview()
						.setViewport(new Rectangle2D(111, 1554, getCharacterWidth(), getCharacterHeight()));
				setSkillAnimationCount(getSkillAnimationCount() - 0.5);
			} else {
				target.getAnimation().stop();
				setSkillAnimationCount(250);
				setSkill3(false);
				return 1;
			}
		}
		return 0;
	}

	@Override
	public int dotakeDamage() {
		if (isAttacked() && !isDead()) {
			if (getStackFly() == 3) {
				if (getAnimationCount() == 100) {
					this.getImageview()
							.setViewport(new Rectangle2D(0, 444, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
					setAnimationCount(getAnimationCount() - 0.5);
					injured1.play();
				} else if (getAnimationCount() >= 0) {
					this.getImageview()
							.setViewport(new Rectangle2D(0, 444, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
					setAnimationCount(getAnimationCount() - 0.5);
				} else {
					setStackFly(2);
					setAnimationCount(100);
					setAttacked(false);
					stand();
					getAnimation().play();

				}
			} else if (getStackFly() == 2) {
				if (getAnimationCount() == 100) {
					this.getImageview().setViewport(
							new Rectangle2D(111, 444, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
					setAnimationCount(getAnimationCount() - 0.5);
					injured1.play();
				} else if (getAnimationCount() >= 0) {
					this.getImageview().setViewport(
							new Rectangle2D(111, 444, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
					setAnimationCount(getAnimationCount() - 0.5);
				} else {
					setStackFly(1);
					setAttacked(false);
					setAnimationCount(100);
					stand();
					getAnimation().play();

				}
			} else {
				if (getLongAnimationCount() > 150) {
					getImageview().setViewport(
							new Rectangle2D(111, 444, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
					setLongAnimationCount(getLongAnimationCount() - 0.5);
					if (isRight()) {
						setTranslateX(getTranslateX() - 3);
					} else {
						setTranslateX(getTranslateX() + 3);
					}
				} else if (getLongAnimationCount() == 150) {
					getImageview().setViewport(
							new Rectangle2D(111, 444, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
					setLongAnimationCount(getLongAnimationCount() - 0.5);
					if (isRight()) {
						setTranslateX(getTranslateX() - 3);
					} else {
						setTranslateX(getTranslateX() + 3);
					}
					injured2.play();
				} else if (getLongAnimationCount() >= 130) {
					getImageview().setViewport(
							new Rectangle2D(222, 444, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
					setLongAnimationCount(getLongAnimationCount() - 0.5);
					if (isRight()) {
						setTranslateX(getTranslateX() - 3);
					} else {
						setTranslateX(getTranslateX() + 3);
					}
				} else if (getLongAnimationCount() >= 100) {
					getImageview().setViewport(
							new Rectangle2D(333, 444, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
					setLongAnimationCount(getLongAnimationCount() - 0.5);
					if (isRight()) {
						setTranslateX(getTranslateX() - 3);
					} else {
						setTranslateX(getTranslateX() + 3);
					}
				} else if (getLongAnimationCount() >= 50) {
					getImageview().setViewport(
							new Rectangle2D(777, 444, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
					setLongAnimationCount(getLongAnimationCount() - 0.5);
				} else if (getLongAnimationCount() >= 25) {
					getImageview().setViewport(
							new Rectangle2D(888, 444, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
					setLongAnimationCount(getLongAnimationCount() - 0.5);
				} else if (getLongAnimationCount() >= 0) {
					getImageview().setViewport(
							new Rectangle2D(999, 444, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
					setLongAnimationCount(getLongAnimationCount() - 0.5);
				} else {
					setStackFly(3);
					setAttacked(false);
					setLongAnimationCount(170);
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
		if (!isDead()) {
			return 0;
		}
		if (getLongAnimationCount() >= 150) {
			getImageview().setViewport(new Rectangle2D(111, 444, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
			setLongAnimationCount(getLongAnimationCount() - 0.5);
			if (isRight()) {
				setTranslateX(getTranslateX() - 3);
			} else {
				setTranslateX(getTranslateX() + 3);
			}
		} else if (getLongAnimationCount() >= 130) {
			getImageview().setViewport(new Rectangle2D(222, 444, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
			setLongAnimationCount(getLongAnimationCount() - 0.5);
			if (isRight()) {
				setTranslateX(getTranslateX() - 3);
			} else {
				setTranslateX(getTranslateX() + 3);
			}
		} else if (getLongAnimationCount() >= 100) {
			getImageview().setViewport(new Rectangle2D(333, 444, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
			setLongAnimationCount(getLongAnimationCount() - 0.5);
			if (isRight()) {
				setTranslateX(getTranslateX() - 3);
			} else {
				setTranslateX(getTranslateX() + 3);
			}
		} else if (getLongAnimationCount() >= 50) {
			getImageview().setViewport(new Rectangle2D(777, 444, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
			setLongAnimationCount(getLongAnimationCount() - 0.5);
		} else if (getLongAnimationCount() >= 25) {
			getImageview().setViewport(new Rectangle2D(888, 444, getCharacterWidth() - 15, getCharacterHeight() - 1.3));
			setLongAnimationCount(getLongAnimationCount() - 0.5);
		} else {
			getAnimation().stop();
		}
		return 1;
	}

}
