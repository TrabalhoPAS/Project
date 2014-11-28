package model.interfaces;

public interface IF_Device
{
	public enum Type
	{
		SWITCH,
		DOOR,
		GRADABLE
	}
	public enum Status
	{
		ON,
		OFF,
		OPEN,
		CLOSED,
		LOCKED,
		UNLOCKED
	}
	
	public IF_Room getRoom();
	
	public void setName(String name);
	public String getName();
	
	public void setStatus(Status newStatus);
	public Status getStatus();
	
	public void setGrade(int grade);
	public int getGrade();
}
