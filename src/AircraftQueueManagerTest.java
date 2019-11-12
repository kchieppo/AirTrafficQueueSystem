import java.util.Random;

public class AircraftQueueManagerTest {

	public static void main(String[] args)
	{
		final AircraftQueueManager acQueueManager = new AircraftQueueManager();
		
		acQueueManager.aqm_request_process(AircraftQueueManager.RequestType.BOOT);
		
		Random rand = new Random();
		for (int i = 0; i < 10; i++)
		{
			Aircraft.ACType acType = Aircraft.ACType.values()[rand.nextInt(2)];
			Aircraft.ACSize acSize = Aircraft.ACSize.values()[rand.nextInt(2)];
			
			acQueueManager.aqm_request_process(
					AircraftQueueManager.RequestType.ENQUEUE,
					new Aircraft(acType, acSize));
		}
		
		for (int i = 0; i < 10; i++)
			acQueueManager.aqm_request_process(AircraftQueueManager.RequestType.DEQUEUE);
	}
	
}
