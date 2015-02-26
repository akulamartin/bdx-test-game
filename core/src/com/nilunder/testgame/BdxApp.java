package com.nilunder.testgame;

import java.util.HashMap;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.ModelBatch;

import com.nilunder.bdx.Bdx;
import com.nilunder.bdx.GameObject;
import com.nilunder.bdx.Scene;
import com.nilunder.bdx.Instantiator;
import com.nilunder.bdx.utils.*;
import com.nilunder.bdx.inputs.*;

public class BdxApp implements ApplicationListener {

	public PerspectiveCamera cam;
	public ModelBatch modelBatch;

	@Override
	public void create() {
		modelBatch = new ModelBatch();
		
		Bdx.init();
		Gdx.input.setInputProcessor(new GdxProcessor(Bdx.keyboard, Bdx.mouse, Bdx.allocatedFingers));

		Scene.instantiators = new HashMap<String, Instantiator>();
		Scene.instantiators.put("Clouds", new com.nilunder.testgame.inst.iClouds());
		Scene.instantiators.put("HUD", new com.nilunder.testgame.inst.iHUD());
		Scene.instantiators.put("Main", new com.nilunder.testgame.inst.iMain());

		Bdx.scenes.add(new Scene("Main"));
	}

	@Override
	public void dispose() {
		modelBatch.dispose();
	}

	@Override
	public void render() {
		Bdx.profiler.start();
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Bdx.profiler.stop("graphics");

		Bdx.updateInput();
		Bdx.profiler.stop("input");

		for (Scene s : (ArrayListNamed<Scene>)Bdx.scenes.clone()){
			s.update();
			Bdx.profiler.start();
			renderScene(s);
			Bdx.profiler.stop("render");
		}
		Bdx.profiler.update();
	}
	
	
	public void renderScene(Scene scene){
		Gdx.gl.glClear(GL20.GL_DEPTH_BUFFER_BIT);
		modelBatch.begin(scene.cam);
		for (GameObject g : scene.objects){
			if (g.visible()){
				modelBatch.render(g.modelInstance);
			}
		}
		modelBatch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
