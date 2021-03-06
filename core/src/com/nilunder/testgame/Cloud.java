package com.nilunder.testgame;

import com.nilunder.bdx.*;
import com.nilunder.bdx.utils.*;

import javax.vecmath.*;

public class Cloud extends GameObject{

	private Vector3f bl;
	private Vector3f tr;

	public void init(){
		bl = parent().parent().position();
		tr = parent().position();
	}

	public void main(){
		if (position().z > tr.z)
			position(bl.x + Random.random() * (tr.x - bl.x), bl.y, bl.z);
		move(0, 0, 0.01f);
	}

}
