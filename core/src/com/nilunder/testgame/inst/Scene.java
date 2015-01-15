package com.nilunder.testgame.inst;

import com.badlogic.gdx.utils.JsonValue;
import com.nilunder.bdx.Instantiator;import com.nilunder.bdx.GameObject;
import com.nilunder.testgame.*;
public class Scene extends Instantiator {

	public GameObject newObject(JsonValue gobj){
		String name = gobj.name;

		if (name.equals("Sacky"))
			return new com.nilunder.testgame.Sacky();
		if (name.equals("Crux"))
			return new com.nilunder.testgame.Crux();
		if (name.equals("Bottle"))
			return new com.nilunder.testgame.Bottle();
		if (name.equals("Player"))
			return new com.nilunder.testgame.Player();

		return super.newObject(gobj);
	}
	
}
