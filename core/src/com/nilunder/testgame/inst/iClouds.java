package com.nilunder.testgame.inst;

import com.badlogic.gdx.utils.JsonValue;
import com.nilunder.bdx.Instantiator;import com.nilunder.bdx.GameObject;
import com.nilunder.testgame.*;
public class iClouds extends Instantiator {

	public GameObject newObject(JsonValue gobj){
		String name = gobj.name;

		if (name.equals("Cloud"))
			return new com.nilunder.testgame.Cloud();

		return super.newObject(gobj);
	}
	
}
