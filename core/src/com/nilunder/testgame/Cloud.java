package com.nilunder.testgame;

import com.nilunder.bdx.*;

public class Cloud extends GameObject{

	public void main(){
		if (position().z > 2.8)
			position(0, 0, -2.8f);
		move(0, 0, 0.01f);
	}

}
