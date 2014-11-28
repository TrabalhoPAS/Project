package myException;

public class DuplicateRoomNameException extends Exception
{
	private static final long serialVersionUID = 1L;
	
	private String roomName = "";
	private int floor = 0;
	
	public DuplicateRoomNameException(String roomName, int floor)
	{
		super();
		this.roomName = roomName;
		this.floor = floor;
	}
	
	public String toString()
	{
		return "DuplicateRoomNameException: " + this.roomName + " Floor: " + floor; 
	}
	
	public int getFloor()
	{
		return this.floor;
	}
	
	public String getRoomName()
	{
		return this.roomName;
	}
}
