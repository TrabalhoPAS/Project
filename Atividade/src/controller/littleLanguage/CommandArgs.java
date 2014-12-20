package controller.littleLanguage;

import model.interfaces.IF_House;

public class CommandArgs
{
	String roomName = "";
	String deviceName = "";
	int floorNumber = 0;
	int grade = 0;
	IF_House house = null;
	public String getRoomName()
	{
		return roomName;
	}
	public void setRoomName(String roomName)
	{
		this.roomName = roomName;
	}
	public String getDeviceName()
	{
		return deviceName;
	}
	public void setDeviceName(String deviceName)
	{
		this.deviceName = deviceName;
	}
	public int getFloorNumber()
	{
		return floorNumber;
	}
	public void setFloorNumber(int floorNumber)
	{
		this.floorNumber = floorNumber;
	}
	public int getGrade()
	{
		return grade;
	}
	public void setGrade(int grade)
	{
		this.grade = grade;
	}
	public IF_House getHouse()
	{
		return house;
	}
	public void setHouse(IF_House house)
	{
		this.house = house;
	}
}
