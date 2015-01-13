package com.nilunder.testgame;

import com.nilunder.bdx.*;

public class Player extends GameObject{

	public void main(){
		if (Bdx.mouse.btnHit("left")){
			GameObject icosphere = scene.add("Bottle");
			icosphere.position(scene.camera.position());
			icosphere.velocity(Bdx.mouse.rayDirection().mul(10));
		}
	}

}
