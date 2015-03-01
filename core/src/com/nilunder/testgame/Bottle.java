package com.nilunder.testgame;

import com.nilunder.bdx.*;

public class Bottle extends GameObject{

	public void init(){
		components.add(new Sacky.Shadow(this));
	}

	public void main(){
		if (hit("Platform") || position().z < 0)
			end();
		else
			children.get("G_Bottle").orientation(scene.camera.orientation());
	}

}
