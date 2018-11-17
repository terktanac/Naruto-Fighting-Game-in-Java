package characters;

import Interface.Fightable;
import Interface.Moveable;
import Interface.Skillable;

public abstract class Character implements Fightable, Moveable, Skillable{
	String name;
	private int element; //Plain:0 Fire:1 Earth:2 Water:3 Wind:4
	private int health; // standard:100
	private int atk; // standard:10
	private int def; // standard:5
	private boolean isDead;//true = Dead = EndGame
	private boolean isAir ; //knock up or jump
	private double isFall; //if > 0.00 user can't do anything and need to wait for stand
	private boolean isAttacked; //if true user can't move for 0.1 s(or less)
	private double standTime ;//Time period that a character need for stand avg=1 s
	public Character(String name, int element, int health, int atk, int def,double standTme) {
		super();
		this.name = name;
		this.element = element;
		this.health = health;
		this.atk = atk;
		this.def = def;
		this.standTime = standTme;
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
	

}
