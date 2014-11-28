package controller.littleLanguage.command;

import java.util.Iterator;

import model.interfaces.IF_Room;

public class AllOfRoomSingleDevice extends Command
{
	private String deviceName = "";
	private String roomName = "" ;

	@Override
	public void execute() throws Exception
	{
		Iterator<IF_Room> it = house.getRoomList().values().iterator();
		Exception exception = null;
		boolean didIt = false;
		while (it.hasNext())
		{
			IF_Room room = it.next();
			if (room.getName().equals(roomName))
			{
				try
				{
					doForADevice(room.getDevice(deviceName));
					didIt = true;
				}
				catch (myException.DeviceNotExistentException e)
				{
					exception = e;
				}
			}
		}
		if (!didIt) throw exception;
	}

	public String getDeviceName()
	{
		return deviceName;
	}

	public void setDeviceName(String deviceName)
	{
		this.deviceName = deviceName;
	}

	public String getRoomName()
	{
		return roomName;
	}

	public void setRoomName(String roomName)
	{
		this.roomName = roomName;
	}

}
