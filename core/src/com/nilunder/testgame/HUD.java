package com.nilunder.testgame;

import com.nilunder.bdx.*;

public class HUD extends GameObject{

public static class GameState extends Component{

	private GameObject btnPlay;
	private Player player;
	private Sacky sacky;

	public GameState(GameObject g){
		super(g);
		btnPlay = g.scene.objects.get("Play");
		btnPlay.components.add(new Button(btnPlay));
		Scene mainScene = Bdx.scenes.get("Main");
		player = (Player)mainScene.objects.get("Player");
		sacky = (Sacky)mainScene.objects.get("Sacky");
		state = menu;
	}

	private State menu = new State(){
		public void main(){
			if (Bdx.mouse.clicked(btnPlay)){
				view("Play");
				player.canThrowBottles = true;
				state = play;
			}
		}
	};

	private State play = new State(){
		public void main(){
			if (!sacky.isNormal()){
				state = playing;
			}
		}
	};

	private State playing = new State(){
		public void main(){
			if (sacky.isNormal()){
				view("Menu");
				player.canThrowBottles = false;
				state = menu;
			}
		}
	};

	private void view(String name){
		g.scene.camera.position(g.scene.objects.get(name + "View").position());
	}
}

public static class Button extends Component{

	public Button(GameObject g){
		super(g);
		state = normal;
	}

	private State normal = new State(){
		public void main(){
			if (mouseOver()){
				g.scale(1.1f);
				state = mouseOver;
			}
		}
	};

	private State mouseOver = new State(){
		public void main(){
			if (!mouseOver()){
				g.scale(1f);
				state = normal;
			}
		}
	};

	private boolean mouseOver(){
		RayHit rh = Bdx.mouse.ray();
		return rh != null && rh.object == g;
	}
}

	public void init(){
		components.add(new GameState(this));
	}

}
