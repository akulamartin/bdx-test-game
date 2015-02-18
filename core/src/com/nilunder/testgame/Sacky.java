package com.nilunder.testgame;

import com.nilunder.bdx.*;
import com.nilunder.bdx.utils.*;
import com.nilunder.bdx.components.*;

import javax.vecmath.*;

public class Sacky extends GameObject{

private static class Boozing extends Component{
	private int alcohol;
	private Sacky sacky;

	public Boozing(Sacky sacky){
		super(sacky);
		this.sacky = sacky;
		state = normal;
	}

	public State normal = new State(){
		public void main(){
			if (g.hit("Bottle")){
				drinkBottle();
				state = buzzed;
			}

		}
	};

	public State buzzed = new State(){
		public void main(){
			if (g.hit("Bottle"))
				drinkBottle();

			if (g.hit("Platform")){
				if (--alcohol == 0)
					state = normal;
			}
		}
	};

	private void drinkBottle(){
		sacky.jump(Random.direction());
		g.touchingObjects.get("Bottle").end();
		alcohol = 2;
	}
}

private static class Animation extends Component{
	private SpriteAnim sa;
	private Sacky sacky;
	private Boozing boozing;

	public Animation(Sacky sacky, Boozing boozing){
		super(sacky);
		this.sacky = sacky;
		this.boozing = boozing;
		sa = new SpriteAnim(g.children.get("G_Sacky"), 32, 32);
		sa.add("buzzed", 0, new int[]{0, 1, 2, 3});
		sa.add("normal", 0, new int[]{4, 5, 6, 7});
		g.components.add(sa);
		state = core;
	}

	private State core = new State(){
		public void main(){
			if (boozing.state == boozing.buzzed)
				sa.play("buzzed");
			else
				sa.play("normal");
		}
	};
}



	public void init(){
		Boozing boozing = new Boozing(this);
		components.add(new Animation(this, boozing));
		components.add(boozing);
		components.add(new Halo(children.get("G_Sacky")));
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
