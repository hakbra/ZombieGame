package framework.systems;

import helpers.Data;
import helpers.Time;
import zombies.states.MessageState;
import framework.CoreSystem;
import framework.World;
import framework.components.Bullet;
import framework.components.Damage;
import framework.components.Health;
import framework.components.Hero;
import framework.components.Zombie;
import framework.enums.EventEnum;
import framework.enums.StateEnum;
import framework.managers.EntityManager;


public class DamageSystem extends CoreSystem{


	public DamageSystem(World w)
	{
		super(w);
		this.event = EventEnum.DAMAGE;
	}

	@Override
	public void run(EntityManager em)
	{
	}

	@Override
	public void recieveEvent(Data i)
	{
		EntityManager em = world.getEntityManager();

		if (em.hasComponent(i.a, Zombie.class) && em.hasComponent(i.b, Zombie.class))
			return;

		Damage dam = em.getComponent(i.a, Damage.class);
		Health health = em.getComponent(i.b, Health.class);

		if (i.b == dam.parent)
			return;

		if (!dam.canDamage())
			return;
		
		if (health.current <= 0)
			return;

		dam.time = Time.getTime();

		health.current -= dam.amount;
		if (health.current <= 0)
		{
			em.removeEntity(i.b);

			if (em.hasComponent(i.b, Hero.class) && em.getEntity(Hero.class).size() == 1)
			{
				world.popState();
				MessageState.init(world, "GAME OVER");
				world.pushState(StateEnum.MESSAGE);
			}
			else if (em.hasComponent(i.a, Bullet.class))
			{
				Bullet bullet = em.getComponent(i.a, Bullet.class);
				Hero hero = em.getComponent(bullet.parent, Hero.class);
				hero.kills++;
			}
		}

		dam.time = Time.getTime();

	}
}
