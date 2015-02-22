package com.nilunder.testgame.inst;

import com.badlogic.gdx.utils.JsonValue;
import com.nilunder.bdx.Instantiator;import com.nilunder.bdx.GameObject;
import com.nilunder.testgame.*;
public class iHUD extends Instantiator {

	public GameObject newObject(JsonValue gobj){
		String name = gobj.name;

		if (name.equals("Score"))
			return new com.nilunder.testgame.Score();

		return super.newObject(gobj);
	}
	
}
