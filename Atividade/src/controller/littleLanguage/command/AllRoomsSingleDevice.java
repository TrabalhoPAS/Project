package controller.littleLanguage.command;

import java.util.Iterator;

import model.interfaces.*;

public class AllRoomsSingleDevice extends Command
{
	private String deviceName = "";
	
	@Override
	public void execute() throws Exception
	{
		Iterator<IF_Room> it = house.getRoomList().values().iterator();
		boolean didIt = false;
		Exception exception = null;
		while (it.hasNext())
		{
			IF_Room room = it.next();
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

}
