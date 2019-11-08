import java.util.LinkedList;
import java.util.Queue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AircraftQueue {

	final Logger logger = LoggerFactory.getLogger(AircraftQueue.class);
	
	private Queue<Aircraft>[] acMultiQueue;
	private boolean systemBooted;
	
	public AircraftQueue()
	{
		acMultiQueue = new Queue[4];
		for(int i = 0; i < acMultiQueue.length; i++)
			acMultiQueue[i] = new LinkedList<Aircraft>();
		
		systemBooted = false;
	}
	
	public void boot()
	{
		systemBooted = true;
		logger.info("System booted.");
	}
	
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
