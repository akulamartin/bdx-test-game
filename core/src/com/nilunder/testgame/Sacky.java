package com.nilunder.testgame;

import com.nilunder.bdx.*;

public class Sacky extends GameObject{

	public void main(){
		children.get("G_Sacky").orientation(scene.camera.orientation());
	}
    
}
