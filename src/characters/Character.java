package characters;

import Interface.Fightable;
import Interface.Moveable;
import Interface.Skillable;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public abstract class Character extends Pane implements Fightable, Moveable, Skillable{
	String name;
	private int element; //Plain:0 Fire:1 Earth:2 Water:3 Wind:4
	private int health; // standard:100
	private int atk; // standard:10
	private int def; // standard:5
	private boolean isDead = false;//true = Dead = EndGame
	private boolean isAir = false; //knock up or jump
	private boolean isJump = false;
	private double isFall = 0; //if > 0.00 user can't do anything and need to wait for stand
	private boolean isAttacked = false; //if true user can't move for 0.1 s(or less)
	private double standTime ;//Time period that a character need for stand avg=1 s
	private static double x_speed = 8;
	private static double y_speed = 4;
	private ImageView imageview ;
	private int count;
	private int col;
	private int offSetX;
	private int offSetY;
	private int width;
	private int height;
	private int state;
	private boolean isRight = true;
	private CharacterAnimation animation ;
	public Character(String name, int element, int health, int atk, int def,double standTme,ImageView imageview) {
		super();
		this.name = name;
		this.element = element;
		this.health = health;
		this.atk = atk;
		this.def = def;
		this.standTime = standTme;
		this.state = 0;
		this.count = 6;
		this.col = 0;
		this.offSetX = 0;
		this.offSetY = 0;
		this.width = 111 ;
		this.height = 111 ;
		this.setImageview(imageview);
		this.getImageview().setViewport(new Rectangle2D(offSetX, offSetY, width - 15, height - 1.3));
		this.getImageview().setFitHeight(350);
		this.getImageview().setFitWidth(350);	
		setAnimation(new CharacterAnimation(this.getImageview(), Duration.millis(300), count, col, offSetX, offSetY, width, height));
		getChildren().addAll(imageview);
	}
	public void moveX(double d) {
		boolean right = d>0 ? true:false;
		for(int i=0;i<Math.abs(d);i++) {
			if(right)this.setTranslateX(this.getTranslateX()+1);
			else this.setTranslateX(this.getTranslateX()-1);
		}
	}
	
	public void moveY(double d) {
		boolean right = d>0 ? true:false;
		for(int i=0;i<Math.abs(d);i++) {
			if(right)this.setTranslateY(this.getTranslateY()+1);
			else this.setTranslateY(this.getTranslateY()-1);
		}
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
	@Override
	public int walk_right() {
		if(!isAttacked && !isDead) {
			if(this.isRight() != true) {
				this.getImageview().setRotationAxis(Rotate.Y_AXIS);
				this.getImageview().setRotate(0);
				this.setRight(true);
			}
			this.getAnimation().setOffSetX(0);
			this.getAnimation().setOffSetY(222);
			this.moveX(characters.Character.getX_speed());
			this.getAnimation().play();
			return 1;
		}
		else
			return 0;
	}
	@Override
	public int walk_left() {
		if(!isAttacked && !isDead) {
			if(this.isRight() == true) {
				this.getImageview().setRotationAxis(Rotate.Y_AXIS);
				this.getImageview().setRotate(180);
				this.setRight(false);
			}
			this.getAnimation().setOffSetX(0);
			this.getAnimation().setOffSetY(222);
			this.moveX(-characters.Character.getX_speed());
			this.getAnimation().play();
			return 1;
		}
		else
			return 0;
	}
	@Override
	public int crouch() {
		if(!isAir && !isAttacked && !isJump && !isDead) {
			this.getImageview().setViewport(new Rectangle2D(333, 333, width - 15, height - 1.3));
			return 1;
		}
		else
			return 0;
	}
	@Override
	public int jump() {
		if(!isAir && !isAttacked && !isJump && !isDead) {
			
			this.isJump = true;
			this.isAir = true;
			this.getAnimation().stop();
			return 1;
		}
		else
			return 0;
	}
	@Override
	public int melee() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int range() {
		// TODO Auto-generated method stub
		return 0;
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
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getElement() {
		return element;
	}
	public void setElement(int element) {
		this.element = element;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public int getAtk() {
		return atk;
	}
	public void setAtk(int atk) {
		this.atk = atk;
	}
	public int getDef() {
		return def;
	}
	public void setDef(int def) {
		this.def = def;
	}
	public boolean isDead() {
		return isDead;
	}
	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}
	public static double getX_speed() {
		return x_speed;
	}
	public static double getY_speed() {
		return y_speed;
	}
	public boolean isJump() {
		return isJump;
	}
	public boolean isRight() {
		return isRight;
	}
	public void setRight(boolean isRight) {
		this.isRight = isRight;
	}
	public ImageView getImageview() {
		return imageview;
	}
	public void setImageview(ImageView imageview) {
		this.imageview = imageview;
	}
	public CharacterAnimation getAnimation() {
		return animation;
	}
	public void setAnimation(CharacterAnimation animation) {
		this.animation = animation;
	}
	public boolean isAir() {
		return isAir;
	}
	public void setAir(boolean isAir) {
		this.isAir = isAir;
	}
	public double getIsFall() {
		return isFall;
	}
	public void setIsFall(double isFall) {
		this.isFall = isFall;
	}
	public boolean isAttacked() {
		return isAttacked;
	}
	public void setAttacked(boolean isAttacked) {
		this.isAttacked = isAttacked;
	}
	public double getStandTime() {
		return standTime;
	}
	public void setStandTime(double standTime) {
		this.standTime = standTime;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
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
	public void setJump(boolean isJump) {
		this.isJump = isJump;
	}
	public static void setX_speed(double x_speed) {
		Character.x_speed = x_speed;
	}
	public static void setY_speed(double y_speed) {
		Character.y_speed = y_speed;
	}
	

}
