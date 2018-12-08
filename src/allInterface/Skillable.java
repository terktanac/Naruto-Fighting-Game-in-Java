package allInterface;

import java.util.ArrayList;

import characters.Character;
import gameobject.GameObject;

public interface Skillable {
	public int basic_skill(Character target, ArrayList<GameObject> playerObject);
	public int mid_skill(Character target, ArrayList<GameObject> playerObject);
	public int High_skill(Character target, ArrayList<GameObject> playerObject);
}
