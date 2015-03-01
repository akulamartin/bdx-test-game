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

public static class Shadow extends Component{

	private GameObject shadow;
	private float gobjHalfHeight;
	private float baseScale;

	public Shadow(GameObject g){
		super(g);
		shadow = g.scene.add("Shadow");
		shadow.parent(g);
		Vector3f gd = g.dimensions();
		gobjHalfHeight = gd.z / 2;
		Vector3f sd = shadow.dimensions();
		baseScale = gd.x / sd.x;
		state = core;
	}

	public State core = new State(){
		public void main(){
			Vector3f pos = g.position();
			Vector3f v = new Vector3f(0, 0, -20);
			RayHit rh = g.scene.ray(pos, v);

			if (rh != null){
				rh.position.z += 0.01;
				shadow.position(rh.position);
				float delta = rh.position.minus(pos).length() - gobjHalfHeight;
				float scale = baseScale - delta / 7;
				shadow.scale(scale > 0 ? scale : 0.01f);
			}else{
				shadow.position(pos.plus(v));
			}
		}
	};
}

private static class Sound extends Component{

	private float pz;

	public Sound(Sacky sacky){
		super(sacky);
		state = core;
		pz = g.position().z;
	}

	public State core = new State(){
		public void main(){
			if (g.hit("Bottle"))
				Bdx.sounds.get("gulp").play();

			float z = g.position().z;
			float dz = Math.abs(pz - z);
			pz = z;

			if (g.hit("Platform") && dz > 0.01f){
				Bdx.sounds.get("thud").play();
			}
		}
	};
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

			sa.speed = 0.1f + g.velocity().length() / 3;
		}
	};
}



	private Boozing boozing;

	public void init(){
		boozing = new Boozing(this);
		components.add(new Animation(this, boozing));
		components.add(boozing);
		components.add(new Halo(children.get("G_Sacky")));
		components.add(new Sound(this));
		components.add(new Shadow(this));
	}

	public void jump(Vector3f direction){
		Vector3f force = direction;
		force.z = 0;
		force.length(100);
		force.z = 300;
		velocity(0, 0, 0);
		applyForce(force);
	}

	public boolean isNormal(){
		return boozing.state == boozing.normal;
	}

}
