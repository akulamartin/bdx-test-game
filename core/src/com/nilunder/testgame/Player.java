package com.nilunder.testgame;

import com.nilunder.bdx.*;

import javax.vecmath.*;

public class Player extends GameObject{

	public boolean canThrowBottles;

	public void init(){
		Bdx.scenes.add(0, new Scene("Clouds"));
		Bdx.scenes.add(new Scene("HUD"));
		canThrowBottles = false;
	}

	public void main(){
		if (canThrowBottles && Bdx.mouse.btnHit("left") && noBottlesInScene()){
			GameObject bottle = scene.add("Bottle");
			bottle.position(scene.camera.position());
			Vector3f v = Bdx.mouse.rayDirection();
			v.z += 0.2;
			v.length(15);
			bottle.velocity(v);
			Bdx.sounds.get("woosh").play();
		}
	}

	public boolean noBottlesInScene(){
		return scene.objects.get("Bottle") == null;
	}

}
