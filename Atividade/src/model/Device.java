package model;

import model.interfaces.IF_Device;
import model.interfaces.IF_Room;

public abstract class Device implements IF_Device
{
	private IF_Room room = null;
	private String name = new String();
	private int grade = 0;
	private Status status;
	
	public Device(IF_Room room, String newName)
	{
		this.room = room;
		this.setName(newName);
		this.setStatus(Status.OFF);
		this.setStatus(Status.CLOSED);
	}
	
	abstract boolean isGradable();
	abstract boolean isValidStatus(Status status);
	
	public String toString()
	{
		String rt = "Device: " + this.getName() + " | Status: " + getStatus();
		if (isGradable())
			rt += "(" + this.getGrade() + ")";
		return rt;
	}
	
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setStatus(Status newStatus)
	{
		if (!isValidStatus(newStatus)) return;
		this.status = newStatus;
	}
	
	public Status getStatus()
	{
		return this.status;
	}

	public int getGrade()
	{
		return grade;
	}

	public void setGrade(int grade)
	{
		this.grade = grade;
	}
	
	public IF_Room getRoom()
	{
		return this.room;
	}
}
