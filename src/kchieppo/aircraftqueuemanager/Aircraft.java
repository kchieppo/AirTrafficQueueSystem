package kchieppo.aircraftqueuemanager;

/**
 * The main data type used in the aircraft queue.
 * 
 * @author Kevin Chieppo
 *
 */
public final class Aircraft {

	public static enum ACType
	{
		PASSENGER, CARGO
	}
	public static enum ACSize
	{
		LARGE, SMALL
	}
	
	private ACType acType;
	private ACSize acSize;
	
	/**
	 * Sole constructor. Meant for use by clients.
	 * 
	 * @param acType the type of aircraft (passenger/cargo)
	 * @param acSize the size of aircraft (large/small)
	 */
	public Aircraft(ACType acType, ACSize acSize)
	{
		this.acType = acType;
		this.acSize = acSize;
	}
	
	public void setACType(ACType acType)
	{
		this.acType = acType;
	}
	public ACType getACType()
	{
		return acType;
	}
	
	public void setACSize(ACSize acSize)
	{
		this.acSize = acSize;
	}
	public ACSize getACSize()
	{
		return acSize;
	}
	
	@Override public String toString()
	{
		String out = "ID: " + super.toString()
				+ " Type: " + this.acType
				+ " Size: " + this.acSize;
		return out;
	}
}
