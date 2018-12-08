package characters;

import allInterface.Collidable;
import allInterface.Fightable;
import allInterface.Moveable;
import allInterface.Skillable;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import scenes.GameScreen;

public abstract class Character extends Pane implements Fightable, Moveable, Skillable, Collidable {
	private String name;
	private int element; // Plain:0 Fire:1 Earth:2 Water:3 Wind:4
	private static final double MAXHEALTH = 500;
	private double currenthealth; // standard:1000
	private int atk; // standard:10
	private int def; // standard:5
	private double animationCount = 100;
	private double longAnimationCount = 170;
	private double skillAnimationCount = 250;
	private int meleeRound = 0;
	private int limitDodge = 5;
	private int stackFly = 3;
	private int countFoot = 0;
	private boolean isSkill1 = false;
	private boolean isSkill2 = false;
	private boolean isSkill3 = false;
	private boolean isDead = false;// true = Dead = EndGame
	private boolean isAir = false; // knock up or jump
	private boolean isJump = false;
	private boolean isCrouch = false;
	private boolean isBlock = false;
	private boolean isDodge = false;
	private boolean isMove = false;
	private double isFall = 0; // if > 0.00 user can't do anything and need to wait for stand
	private boolean isAttacked = false; // if true user can't move for 0.1 s(or less)
	private boolean isMelee = false;
	private boolean isRange = false;
	private double standTime;// Time period that a character need for stand avg=1 s
	private static double xSpeed = 2;
	private static double ySpeed = 2;
	private ImageView imageview;
	private ImageView smoke;
	private int count;
	private int col;
	private int offSetX;
	private int offSetY;
	private int width;
	private int height;
	private int state;
	private boolean isRight = true;
	private CharacterAnimation animation;
	protected static AudioClip foot1 = new AudioClip("file:soundfx/footstep1.wav");
	protected static AudioClip jump = new AudioClip("file:soundfx/jump.wav");
	protected static AudioClip land = new AudioClip("file:soundfx/jump_land.wav");
	protected static AudioClip hit = new AudioClip("file:soundfx/hit1.wav");
	protected static AudioClip hitSuccess = new AudioClip("file:soundfx/hit1_sucess.wav");
	protected static AudioClip shuriken = new AudioClip("file:soundfx/sword_miss1.wav");
	protected static AudioClip dodge = new AudioClip("file:soundfx/smokebomb_setoff.wav");
	protected static AudioClip block = new AudioClip("file:soundfx/Block.wav");

	public Character(String name, int element, double currentHealth, int atk, int def, double standTme,
			ImageView imageview) {
		super();
		this.name = name;
		this.element = element;
		this.currenthealth = currentHealth;
		this.atk = atk;
		this.def = def;
		this.standTime = standTme;
		this.state = 0;
		this.count = 6;
		this.col = 0;
		this.offSetX = 0;
		this.offSetY = 0;
		this.width = 111;
		this.height = 111;
		this.smoke = new ImageView("sys/big_smoke_log.png");
		this.setImageview(imageview);
		this.getImageview().setViewport(new Rectangle2D(offSetX, offSetY, width - 15, height - 1.3));
		this.getImageview().setFitHeight(350);
		this.getImageview().setFitWidth(350);
		getChildren().addAll(imageview);
	}

	public final void moveX(double d) {
		boolean right = d > 0 ? true : false;
		for (int i = 0; i < Math.abs(d); i++) {
			if (right) {
				this.setTranslateX(this.getTranslateX() + 1);
			} else {
				this.setTranslateX(this.getTranslateX() - 1);
			}
		}
	}

	public final void moveY(double d) {
		final boolean right;
		if (d > 0) {
			right = true;
		} else {
			right = false;
		}
		for (int i = 0; i < Math.abs(d); i++) {
			if (right) {
				this.setTranslateY(this.getTranslateY() + 1);
			} else {
				this.setTranslateY(this.getTranslateY() - 1);
			}
		}
	}

	@Override
	public final Rectangle2D getBoundary() {
		return new Rectangle2D(getTranslateX()+40, getTranslateY()+10, width-50, height);
	}

	@Override
	public final int jump() {
		if (!isAir() && !isAttacked() && !isJump() && !isDead() && !isCrouch() && !isBlock()) {
			jump.play();
			setMove(true);
			setJump(true);
			setAir(true);
			this.getAnimation().stop();
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public final int melee(Character target) {
		if (!isAttacked() && !isDead() && !isCrouch() && !isRange() && !isBlock()) {
			setMelee(true);
			setMove(true);
			this.getAnimation().stop();
			if (GameScreen.checkCollide(this, target)) {
				hitSuccess.play();
				target.takeDamage(getAtk());
			} else {
				hit.play();
			}
			return 1;
		}
		return 0;
	}

	@Override
	public final int range() {
		if (!isAttacked() && !isDead() && !isCrouch() && !isMelee() && !isBlock()) {
			setRange(true);
			setMove(true);
			this.getAnimation().stop();
			shuriken.play();
			return 1;
		}
		return 0;
	}

	@Override
	public final int dodge() {
		if (!isAttacked() && !isDead() && !isCrouch() && !isMelee() && !isRange() && !isBlock() && !isJump()
				&& !isDodge() && getLimitDodge() > 0) {
			setDodge(true);
			setMove(true);
			this.getAnimation().stop();
			getImageview().setImage(getSmoke().getImage());
			setWidth(440);
			setHeight(199);
			getImageview().setViewport(new Rectangle2D(440, 0, getCharacterWidth(), getCharacterHeight()));
			getImageview().setFitWidth(440.0 * (350.0 / 199.0));
			getImageview().setFitHeight(350);
			setTranslateX(getTranslateX() - 250);
			dodge.play();
			return 1;
		}
		return 0;
	}

	@Override
	public final double takeDamage(double dmg) {
		if (!isDodge()) {
			setMelee(false);
			setRange(false);
			setSkill1(false);
			setSkill2(false);
			setSkill3(false);
			if (isBlock()) {
				setCurrenthealth(getCurrenthealth() - (dmg - getDef()));
				block.play();
			} else {
				setCurrenthealth(getCurrenthealth() - dmg);
				setAttacked(true);
				this.animation.stop();
			}
			if (getCurrenthealth() <= 0) {
				setDead(true);
				setLongAnimationCount(170);
			}
		}
		System.out.println("Current Health: " + getCurrenthealth());
		return getCurrenthealth();
	}

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final int getElement() {
		return element;
	}

	public final void setElement(int element) {
		this.element = element;
	}

	public final double getCurrenthealth() {
		return currenthealth;
	}

	public final void setCurrenthealth(double d) {
		this.currenthealth = d;
	}

	public static double getMaxHealth() {
		return MAXHEALTH;
	}

	public final int getAtk() {
		return atk;
	}

	public final void setAtk(int atk) {
		this.atk = atk;
	}

	public final int getDef() {
		return def;
	}

	public final void setDef(int def) {
		this.def = def;
	}

	public final double getAnimationCount() {
		return animationCount;
	}

	public final void setAnimationCount(double animationCount) {
		this.animationCount = animationCount;
	}

	public final double getLongAnimationCount() {
		return longAnimationCount;
	}

	public final void setLongAnimationCount(double longAnimationCount) {
		this.longAnimationCount = longAnimationCount;
	}

	public final int getMeleeRound() {
		return meleeRound;
	}

	public final void setMeleeRound(int meleeRound) {
		this.meleeRound = meleeRound;
	}

	public final boolean isDead() {
		return isDead;
	}

	public final void setDead(boolean isDead) {
		this.isDead = isDead;
	}

	public final boolean isAir() {
		return isAir;
	}

	public final void setAir(boolean isAir) {
		this.isAir = isAir;
	}

	public final boolean isJump() {
		return isJump;
	}

	public final void setJump(boolean isJump) {
		this.isJump = isJump;
	}

	public final boolean isCrouch() {
		return isCrouch;
	}

	public final void setCrouch(boolean isCrouch) {
		this.isCrouch = isCrouch;
	}

	public final double getIsFall() {
		return isFall;
	}

	public final void setIsFall(double isFall) {
		this.isFall = isFall;
	}

	public final boolean isAttacked() {
		return isAttacked;
	}

	public final void setAttacked(boolean isAttacked) {
		this.isAttacked = isAttacked;
	}

	public final boolean isMelee() {
		return isMelee;
	}

	public final void setMelee(boolean isMelee) {
		this.isMelee = isMelee;
	}

	public final boolean isRange() {
		return isRange;
	}

	public final void setRange(boolean isRange) {
		this.isRange = isRange;
	}

	public final boolean isMove() {
		return isMove;
	}

	public final void setMove(boolean isMove) {
		this.isMove = isMove;
	}

	public final double getStandTime() {
		return standTime;
	}

	public final void setStandTime(double standTime) {
		this.standTime = standTime;
	}

	public static final double getXspeed() {
		return xSpeed;
	}

	public static final void setXspeed(double xSpeed) {
		Character.xSpeed = xSpeed;
	}

	public static final double getYspeed() {
		return ySpeed;
	}

	public static final void setYspeed(double ySpeed) {
		Character.ySpeed = ySpeed;
	}

	public final ImageView getImageview() {
		return imageview;
	}

	public final void setImageview(ImageView imageview) {
		this.imageview = imageview;
	}

	public final int getCount() {
		return count;
	}

	public final void setCount(int count) {
		this.count = count;
	}

	public final int getCol() {
		return col;
	}

	public final void setCol(int col) {
		this.col = col;
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

	public final int getCharacterWidth() {
		return width;
	}

	public final void setWidth(int width) {
		this.width = width;
	}

	public final int getCharacterHeight() {
		return height;
	}

	public final void setHeight(int height) {
		this.height = height;
	}

	public final int getState() {
		return state;
	}

	public final void setState(int state) {
		this.state = state;
	}

	public final boolean isRight() {
		return isRight;
	}

	public final void setRight(boolean isRight) {
		this.isRight = isRight;
	}

	public final CharacterAnimation getAnimation() {
		return animation;
	}

	public final void setAnimation(CharacterAnimation animation) {
		this.animation = animation;
	}

	public final boolean isBlock() {
		return isBlock;
	}

	public final void setBlock(boolean isBlock) {
		this.isBlock = isBlock;
	}

	public final boolean isDodge() {
		return isDodge;
	}

	public final void setDodge(boolean isDodge) {
		this.isDodge = isDodge;
	}

	public final boolean isSkill1() {
		return isSkill1;
	}

	public final void setSkill1(boolean isSkill1) {
		this.isSkill1 = isSkill1;
	}

	public final boolean isSkill2() {
		return isSkill2;
	}

	public final void setSkill2(boolean isSkill2) {
		this.isSkill2 = isSkill2;
	}

	public final boolean isSkill3() {
		return isSkill3;
	}

	public final void setSkill3(boolean isSkill3) {
		this.isSkill3 = isSkill3;
	}

	public final int getLimitDodge() {
		return limitDodge;
	}

	public final void setLimitDodge(int limitDodge) {
		this.limitDodge = limitDodge;
	}

	public final ImageView getSmoke() {
		return smoke;
	}

	public final void setSmoke(ImageView smoke) {
		this.smoke = smoke;
	}

	public final int getStackFly() {
		return stackFly;
	}

	public final void setStackFly(int stackFly) {
		this.stackFly = stackFly;
	}

	public final double getSkillAnimationCount() {
		return skillAnimationCount;
	}

	public final void setSkillAnimationCount(double skillAnimationCount) {
		this.skillAnimationCount = skillAnimationCount;
	}

	public final boolean getSkill() {
		return isSkill1 && isSkill2 && isSkill3;
	}

	public final int getCountFoot() {
		return countFoot;
	}

	public final void setCountFoot(int countFoot) {
		this.countFoot = countFoot;
	}

}
