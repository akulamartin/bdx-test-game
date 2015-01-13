package com.nilunder.testgame;

import com.nilunder.bdx.*;

public class Bottle extends GameObject{

	public void main(){
		if (hit("Platform") || position().z < 0)
			end();

		children.get("G_Bottle").orientation(scene.camera.orientation());
	}

}
