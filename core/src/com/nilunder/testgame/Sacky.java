package com.nilunder.testgame;

import com.nilunder.bdx.*;
import com.nilunder.bdx.utils.*;

import javax.vecmath.*;

public class Sacky extends GameObject{

	public void main(){
		children.get("G_Sacky").orientation(scene.camera.orientation());

		if (hit("Bottle")){
			jump(Random.direction());
			touchingObjects.get("Bottle").end();
		}
	}

	public void jump(Vector3f direction){
		Vector3f force = direction;
		force.z = 0;
		force.length(100);
		force.z = 300;
		applyForce(force);
	}
    
}
