package kchieppo.aircraftqueuemanager;

import kchieppo.aircraftqueue.AircraftQueue;

/**
 * @author Kevin Chieppo
 *
 */
public final class AircraftQueueManager {

	public static enum RequestType
	{
		BOOT, ENQUEUE, DEQUEUE
	}
	
	private final AircraftQueue aircraftQueue;
	
	public AircraftQueueManager()
	{
		this.aircraftQueue = new AircraftQueue();
	}
	
	public void aqm_request_process(RequestType requestType)
	{
		aqm_request_process(requestType, null);
	}
	
	public void aqm_request_process(RequestType requestType, Aircraft aircraft)
	{
		switch (requestType)
		{
			case BOOT:
				aircraftQueue.boot();
				break;
				
			case DEQUEUE:
				aircraftQueue.dequeue();
				break;
				
			case ENQUEUE:
				aircraftQueue.enqueue(aircraft);
				break;
				
			default:
				break;
		}
	}
	
}
