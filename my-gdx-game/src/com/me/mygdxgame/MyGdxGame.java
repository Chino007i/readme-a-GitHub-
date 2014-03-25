package com.me.mygdxgame;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;


class Imagen extends Sprite
{
	Imagen(Texture texture)
	{
		super(texture);
		
	}
	InputListener l;
	void avanzar()
	{
		setX(getX()+0.01f);
		if(getX()>0.5f)
			setX(-0.5f);
	}
	
}


public class MyGdxGame implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture texture;
	//private Texture texture_nave;
	
	private Sprite sprite;
	private Sprite sprite2;
	
	//private Sprite sprite_nave;
	private Imagen imagen;
	private Image i;
	Pelotita p;
	private Texture texture_nave;
	private Texture texture_fondo;
	private Sprite sprite_nave;
	private Sprite sprite_fondo;
	int frame=0;
	Stage s;
	ArrayList<Sprite>sprites=new ArrayList<Sprite>();
	ArrayList<Pelotita>pelotitas=new ArrayList<Pelotita>();
	ArrayList<pelotitamala>pelotitasm=new ArrayList<pelotitamala>();
	int rotacion=0;
	Inicio la;
	
	

	@Override
	public void create()
	{		
		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		
		camera = new OrthographicCamera(1, h/w);
		batch = new SpriteBatch();
		
		texture = new Texture(Gdx.files.internal("data/fondo.png"));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		texture_fondo = new Texture(Gdx.files.internal("data/fondo.png"));
		sprite_fondo=new Sprite(texture_fondo,512,512);
		sprite_fondo.setPosition(-0.5f,-0.5f);
		sprite_fondo.setSize(0.5f, 0.50f);
		
		
		TextureRegion region = new TextureRegion(texture, 0, 0, 512, 512);
		
		
		
		
		
		
		sprite = new Sprite(region);
		sprite2 = new Sprite(region);
		sprite2.setPosition(0,-0.5f);
		sprite2.setSize(0.9f, 0.9f * sprite.getHeight() / sprite.getWidth());
		sprite.setSize(0.9f, 0.9f * sprite.getHeight() / sprite.getWidth());
		sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
		sprite.setPosition(0,0);
		sprite.setRotation(25);
		
		
		
		texture_nave = new Texture(Gdx.files.internal("data/nave.png"));
		sprite_nave=new Sprite(texture_nave,128,64);
		sprite_nave.setPosition(-0.5f,-0.5f);
		sprite_nave.setSize(0.5f, 0.50f);
		
		imagen = new Imagen(texture);
		imagen.setPosition(-0.5f,-0.5f);
		//imagen.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
		imagen.setSize(1.0f, 1.0f);
		
		s=new Stage();
		i=new Image(texture);
		s.addActor(i);
		
		for(int i=0;i<5;i++)
		{
			sprites.add(new Sprite(texture));
			sprites.get(i).setX(i*0.2f-0.5f);
			sprites.get(i).setY(i*-0.2f-0.5f);
			sprites.get(i).setSize(1, 1);
		}
		
		//p=new Pelotita();
		//s.addActor(p);
		//for(int i=0;i<5;i++)
		{
			//Pelotita p =new Pelotita((int)(Math.random()*1000%w),(int)(Math.random()*1000%h));
			//s.addActor(p);
			//pelotitas.add(p);
		}
		for(int i=0;i<5;i++)
		{
			pelotitamala t=new pelotitamala((int)(Math.random()*1000%w),(int)(Math.random()*1000%h));
			s.addActor(t);
			pelotitas.add(p);
			
		}
		
		Gdx.input.setInputProcessor(s);
		la=new Inicio();
		s.addActor(la);
		
	}

	@Override
	public void dispose() {
		batch.dispose();
		texture.dispose();
	}

	@Override
	public void render() {	
		
		sprite.setRotation(rotacion);
		imagen.avanzar();
		//p.setX(p.getX()+10);
		//p.setY(p.getY()+5);
		
		Gdx.gl.glClearColor(1, 0, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	
		batch.setProjectionMatrix(camera.combined);
		s.act();
		s.draw();
		
		
		if(la.isVisible()==false)
		{
			if (frame%100==0)
				s.addActor(new Pelotita(0,0));
				frame++;
		}
		
		//p.setX(p.getX()+1);
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
