package kchieppo.aircraftqueuemanager;

import kchieppo.aircraftqueue.AircraftQueue;

/**
 * Used by the client to issue requests to the aircraft queue.
 * 
 * @author Kevin Chieppo
 *
 */
public final class AircraftQueueManager {

	public static enum RequestType
	{
		BOOT, ENQUEUE, DEQUEUE
	}
	
	private final AircraftQueue aircraftQueue;
	
	/**
	 * Sole constructor.
	 */
	public AircraftQueueManager()
	{
		this.aircraftQueue = new AircraftQueue();
	}
	
	/**
	 * Processes client request to aircraft queue. This can
	 * be used instead of the more verbose function of the
	 * same name when not requesting an enqueue.
	 * 
	 * @param requestType the type of request for the aircraft queue
	 */
	public void aqm_request_process(RequestType requestType)
	{
		aqm_request_process(requestType, null);
	}
	
	/**
	 * Processes client request to aircraft queue. Must be used
	 * when requesting an enqueue in order to provide an aircraft.
	 * 
	 * @param requestType the type of request for the aircraft queue
	 * @param aircraft the aircraft to be enqueued
	 */
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
