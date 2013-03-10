package framework.components;


import java.util.Random;

import org.lwjgl.opengl.GL11;

import framework.CoreComponent;
import framework.CoreEntity;
import framework.helpers.Color;
import framework.helpers.Draw;
import framework.managers.EntityManager;



public class Health extends CoreComponent{
	
	public float current;
	public float max;
	
	public Health()
	{
		this.current = 100;
		this.max = 100;
		this.name = "Health";
	}
	public Health(float m)
	{
		this.current = m;
		this.max = m;
		this.name = "Health";
	}
}
