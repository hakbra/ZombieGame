package framework.systems;

import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import helpers.Color;
import helpers.Draw;
import helpers.Point;
import engine.GLEngine;
import framework.CoreSystem;
import framework.Entity;
import framework.EntityManager;
import framework.World;
import framework.components.Angle;
import framework.components.Circle;
import framework.components.ColorComp;
import framework.components.Item;
import framework.components.Polygon;
import framework.components.Position;
import framework.components.TextureComp;
import framework.components.Velocity;

public class SubLightRenderSystem extends CoreSystem{

	public SubLightRenderSystem(World w) {
		super(w);
	}
	
	public void run(EntityManager em)
	{
		Entity worldComp = em.getByStringID("camera");
		Point camTrans = em.getComponent(worldComp, Position.class).position.neg();
		
		Entity ground = em.getByStringID("ground");
		if (ground != null)
		{
			Polygon poly = em.getComponent(ground, Polygon.class);
			ColorComp color = em.getComponent(ground, ColorComp.class);
			
			Draw.setColor(color.color);
			Draw.polygon(poly.localPoints);
		}
		
		glPushMatrix();
		Draw.translate(camTrans);

		for (Entity e : em.getEntityAll(Item.class))
		{
			glPushMatrix();

			if (em.hasComponent(e, Position.class))
				Draw.translate(em.getComponent(e, Position.class).position);

			if (em.hasComponent(e, Angle.class))
				Draw.rotate(em.getComponent(e, Angle.class).angle);


			if (em.hasComponent(e, TextureComp.class))
			{
				TextureComp t = em.getComponent(e, TextureComp.class);
				t.render(world, e);
			}

			glPopMatrix();
		}
		
		for (Entity e : em.getEntityAll(Circle.class))
		{
			glPushMatrix();

			if (em.hasComponent(e, Position.class))
				Draw.translate(em.getComponent(e, Position.class).position);

			if (em.hasComponent(e, Angle.class))
				Draw.rotate(em.getComponent(e, Angle.class).angle);

			if (em.hasComponent(e, ColorComp.class))
				Draw.setColor(em.getComponent(e, ColorComp.class).color);

			if (em.hasComponent(e, TextureComp.class))
			{
				TextureComp t = em.getComponent(e, TextureComp.class);
				t.render(world, e);
			}
			else
			{
				Circle circ = em.getComponent(e, Circle.class);
				Draw.circle(circ.radius);
			}

			glPopMatrix();
		}
		glPopMatrix();
	}
}
