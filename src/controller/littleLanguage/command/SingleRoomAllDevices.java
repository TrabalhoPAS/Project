package controller.littleLanguage.command;

import java.util.Iterator;

import model.interfaces.IF_Device;

public class SingleRoomAllDevices extends Command
{
	private String roomName = "";
	private int floor = 0;
	
	public void execute() throws Exception
	{
		Iterator<IF_Device> it = house.getRoom(roomName, floor).getDeviceList().values().iterator();
		while (it.hasNext())
		{
			IF_Device device = it.next();
			doForADevice(device);
		}
	}


	public String getRoomName()
	{
		return roomName;
	}


	public void setRoomName(String roomName)
	{
		this.roomName = roomName;
	}


	public int getFloor()
	{
		return floor;
	}


	public void setFloor(int floor)
	{
		this.floor = floor;
	}

}
