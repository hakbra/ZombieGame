package framework.components;

import helpers.Time;
import framework.Component;



public class Timer extends Component{

	public long start;
	public long time;
	
	public Timer(long t)
	{
		name = "Time";
		this.time = t;
		this.start = Time.getTime();
	}
	
}
