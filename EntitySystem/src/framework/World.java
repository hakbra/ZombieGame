package framework;


import java.util.HashMap;
import java.util.Stack;


public class World {
	public State state;
	
	private HashMap<State, EntityManager> eManagers;
	private HashMap<State, SystemManager> sManagers;
	private HashMap<State, DataManager> dManagers;
	
	private Stack<State> stateStack;

	public World(State s)
	{
		this.state = s;

		this.dManagers = new HashMap<State, DataManager>();
		this.sManagers = new HashMap<State, SystemManager>();
		this.eManagers = new HashMap<State, EntityManager>();
		
		this.stateStack = new Stack<State>();
	}

	public boolean run()
	{
		return state != State.EXIT;
	}

	public void pushState(State s)
	{
		stateStack.push(this.state);
		this.state = s;
	}
	public void popState()
	{
		clear(this.state);
		this.state = stateStack.pop();
	}

	public EntityManager getEntityManager(State s)
	{
		EntityManager em = eManagers.get(s);
		if (em == null)
		{
			em = new EntityManager(this);
			eManagers.put(s, em);
		}
		return em;
	}

	public EntityManager getEntityManager()
	{
		return getEntityManager(state);
	}

	public SystemManager getSystemManager(State s)
	{
		SystemManager sm = sManagers.get(s);
		if (sm == null)
		{
			sm = new SystemManager(this);
			sManagers.put(s, sm);
		}
		return sm;
	}

	public SystemManager getSystemManager()
	{
		return getSystemManager(state);
	}

	public DataManager getDataManager(State s)
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
		return getDataManager(state);
	}
	
	public void addEntity(Entity e, State s)
	{
		getEntityManager(s).addEntity(e);
	}
	
	public void registerID(Entity e, State s)
	{
		getEntityManager(s).addStringID(e);
	}
	
	public void addSystem(CoreSystem cs, State s)
	{
		getSystemManager(s).addSystem(cs);
	}

	public void runSystems() {
		getSystemManager().runSystems();
		getEntityManager().removeEntities();
	}
	
	private void clear(State s)
	{
		eManagers.remove(s);
		sManagers.remove(s);
		dManagers.remove(s);
	}
}
