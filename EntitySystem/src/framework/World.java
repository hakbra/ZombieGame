package framework;


import helpers.Point;

import java.util.HashMap;
import java.util.Stack;

import framework.enums.EventEnum;
import framework.enums.StateEnum;
import framework.managers.DataManager;
import framework.managers.EntityManager;
import framework.managers.EventManager;
import framework.managers.SystemManager;


public class World {
	public StateEnum currentState;
	private StateEnum state;
	
	private HashMap<StateEnum, EntityManager> eManagers;
	private HashMap<StateEnum, SystemManager> sManagers;
	private HashMap<StateEnum, DataManager> dManagers;
	private HashMap<StateEnum, EventManager> evManagers;
	
	private Stack<StateEnum> stateStack;
	public int frame;

	public World(StateEnum s)
	{
		this.frame = 0;
		this.currentState = s;
		this.state = s;

		this.dManagers = new HashMap<StateEnum, DataManager>();
		this.sManagers = new HashMap<StateEnum, SystemManager>();
		this.eManagers = new HashMap<StateEnum, EntityManager>();
		this.evManagers = new HashMap<StateEnum, EventManager>();
		
		this.stateStack = new Stack<StateEnum>();
	}

	public boolean run()
	{
		return currentState != StateEnum.EXIT;
	}

	public void pushState(StateEnum s)
	{
		stateStack.push(this.state);
		this.state = s;
	}
	public void popState()
	{
		this.state = stateStack.pop();
	}

	public EntityManager getEntityManager(StateEnum s)
	{
		EntityManager em = eManagers.get(s);
		if (em == null)
		{
			em = new EntityManager(this, s);
			eManagers.put(s, em);
		}
		return em;
	}

	public EntityManager getEntityManager()
	{
		return getEntityManager(currentState);
	}

	public SystemManager getSystemManager(StateEnum s)
	{
		SystemManager sm = sManagers.get(s);
		if (sm == null)
		{
			sm = new SystemManager(this, s);
			sManagers.put(s, sm);
		}
		return sm;
	}

	public SystemManager getSystemManager()
	{
		return getSystemManager(currentState);
	}

	public DataManager getDataManager(StateEnum s)
	{
		DataManager dm = dManagers.get(s);
		if (dm == null)
		{
			dm = new DataManager(this);
			dManagers.put(s, dm);
		}
		return dm;
	}

	public DataManager getDataManager()
	{
		return getDataManager(currentState);
	}

	public EventManager getEventManager(StateEnum s)
	{
		EventManager em = evManagers.get(s);
		if (em == null)
		{
			em = new EventManager(this);
			evManagers.put(s, em);
		}
		return em;
	}

	public EventManager getEventManager()
	{
		return getEventManager(currentState);
	}
	
	public void addEntity(CoreEntity e, StateEnum s)
	{
		getEntityManager(s).addEntity(e);
		e.world = this;
	}
	
	public void registerID(CoreEntity e, StateEnum s)
	{
		getEntityManager(s).addStringID(e);
	}
	
	public CoreSystem addSystem(CoreSystem cs, StateEnum s)
	{
		cs.world = this;
		cs.state = s;
		getSystemManager(s).addSystem(cs);
		return cs;
	}

	public void runSystems() {
		currentState = state;
		getSystemManager().runSystems();
		getEntityManager().removeEntities();
		this.frame++;
	}
	
	public void clearState(StateEnum s)
	{
		eManagers.remove(s);
		sManagers.remove(s);
		dManagers.remove(s);
	}
}
