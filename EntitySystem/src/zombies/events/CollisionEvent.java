package zombies.events;

import framework.CoreEntity;
import framework.DynEnum;
import framework.events.Event;
import framework.utils.Point;

public class CollisionEvent extends Event{
	public CoreEntity collider;
	public CoreEntity obstacle;
	public Point pointOfImpact;
	public boolean inside;
	
	public CollisionEvent(CoreEntity a, CoreEntity b, Point poi, boolean i)
	{
		this.collider = a;
		this.obstacle = b;
		this.pointOfImpact = poi;
		this.inside = i;
		this.type = DynEnum.at("event").get("collision");
	}
}