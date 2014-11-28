package controller.littleLanguage.command;

import model.interfaces.*;

public class SingleRoomSingleDevice extends Command
{
	private String roomName = "";
	private String deviceName = "";
	private int floorNumber = 0;
	
	public void execute() throws Exception
	{
		IF_Device device = house.getRoom(roomName, floorNumber).getDevice(deviceName);
		doForADevice(device);
	}

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

}
