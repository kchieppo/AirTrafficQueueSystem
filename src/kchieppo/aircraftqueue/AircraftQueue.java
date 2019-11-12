package kchieppo.aircraftqueue;

import kchieppo.aircraftqueuemanager.Aircraft;

import java.util.LinkedList;
import java.util.Queue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Kevin Chieppo
 *
 */
public final class AircraftQueue {

	private final Logger logger = LoggerFactory.getLogger(AircraftQueue.class);
	
	private final Queue<Aircraft>[] acMultiQueue;
	private boolean systemBooted;
	
	/**
	 * 
	 */
	public AircraftQueue()
	{
		acMultiQueue = new Queue[4];
		for(int i = 0; i < acMultiQueue.length; i++)
			acMultiQueue[i] = new LinkedList<Aircraft>();
		
		systemBooted = false;
	}
	
	/**
	 * "Boots" the aircraft queue. This must be called before
	 * the aircraft queue can carry out other commands.
	 */
	public void boot()
	{
		systemBooted = true;
		logger.info("System booted.");
	}
	
	/**
	 * Enqueues the aircraft to a queue in the acMultiQueue.
	 * The chosen queue depends on the aircraft's type and
	 * size. If the system is not booted, a warning is logged
	 * and the function's returned.
	 * 
	 * @param aircraft The aircraft to enqueue
	 */
	public void enqueue(Aircraft aircraft)
	{
		if (aircraft == null) return;
		if (!systemBooted)
		{
			logger.warn("Aircraft {} could not be enqueued because "
					+ "system not booted.", aircraft);
			return;
		}
		
		Aircraft.ACType acType = aircraft.getACType();
		Aircraft.ACSize acSize = aircraft.getACSize();
		
		int acQueueIndex = 0;
		if (acType == Aircraft.ACType.CARGO)
			acQueueIndex += 2;
		if (acSize == Aircraft.ACSize.SMALL)
			acQueueIndex += 1;
		
		acMultiQueue[acQueueIndex].add(aircraft);
		
		logger.info("Aircraft {} has been enqueued.", aircraft);
	}
	
	/**
	 * Dequeues an aircraft from the acMultiQueue. The aircraft to
	 * be dequeued is determined by a priority scheme. The queue
	 * of large passenger aircrafts is checked first, then that of
	 * small passenger aircrafts, then that of large cargo aircrafts,
	 * then finally the queue of small cargo aircrafts.
	 * 
	 * For example, if the first two queues are empty, an aircraft
	 * from the large cargo aircraft queue is dequeued.
	 * 
	 * @return	the dequeued aircraft
	 */
	public Aircraft dequeue()
	{
		if (!systemBooted)
		{
			logger.warn("Aircraft could not be dequeued because "
					+ "system not booted.");
			return null;
		}
		
		for (Queue<Aircraft> acQueue : acMultiQueue)
		{
			if (!acQueue.isEmpty())
			{
				Aircraft aircraft = acQueue.remove();
				logger.info("Aircraft {} has been dequeued.", aircraft);
				return aircraft;
			}
		}
		return null;
	}
	
}
