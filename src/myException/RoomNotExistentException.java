package myException;

public class RoomNotExistentException extends Exception
{
	private static final long serialVersionUID = 1L;
	private String roomName = "";
	private int floor = 0;
	
	public RoomNotExistentException(String roomName, int floor)
	{
		super();
		this.roomName = roomName;
		this.floor = floor;
	}
	
	public String toString()
	{
		return "RoomNotExistentException: " + this.roomName + " Floor: " + getFloor();
	}
	
	public int getFloor()
	{
		return this.floor;
	}
	
	public String getRoomName()
	{
		return this.roomName;
	}
	
	public String getMessage()
	{
		return toString();
	}
}
