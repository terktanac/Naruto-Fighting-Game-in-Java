package allInterface;

import java.util.ArrayList;

import characters.Character;
import gameobject.GameObject;

public interface Skillable {
	public int basicSkill(Character target, ArrayList<GameObject> playerObject);
	public int midSkill(Character target, ArrayList<GameObject> playerObject);
	public int highSkill(Character target, ArrayList<GameObject> playerObject);
}
