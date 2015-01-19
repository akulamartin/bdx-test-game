package com.nilunder.testgame;

import com.nilunder.bdx.*;
import com.nilunder.bdx.utils.*;
import com.nilunder.bdx.components.*;

import javax.vecmath.*;

public class Sacky extends GameObject{

	private SpriteAnim sa;

	private int platformHits;

	public void init(){
		sa = new SpriteAnim(children.get("G_Sacky"), 32, 32);
		sa.add("buzzed", 0, new int[]{0, 1, 2, 3});
		sa.add("normal", 0, new int[]{4, 5, 6, 7});
		components.add(sa);
	}

	public void main(){
		children.get("G_Sacky").orientation(scene.camera.orientation());

		if (hit("Bottle")){
			jump(Random.direction());
			touchingObjects.get("Bottle").end();
			sa.play("buzzed");
			platformHits = 0;
		}

		if (hit("Platform"))
			++platformHits;

		if (platformHits == 2)
			sa.play("normal");
	}

	public void jump(Vector3f direction){
		Vector3f force = direction;
		force.z = 0;
		force.length(100);
		force.z = 300;
		velocity(0, 0, 0);
		applyForce(force);
	}
    
}
