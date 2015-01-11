package com.nilunder.testgame;

import com.nilunder.bdx.*;

public class Player extends GameObject{

	private GameObject icosphere;

	public void init(){
		icosphere = scene.objects.get("Icosphere");
	}

	public void main(){
		if (Bdx.mouse.btnHit("left")){
			icosphere.position(scene.camera.position());
			icosphere.velocity(Bdx.mouse.rayDirection().mul(10));
		}
	}

}
