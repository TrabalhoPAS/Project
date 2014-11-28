package controller.littleLanguage.command;

import java.util.Iterator;

import model.interfaces.IF_Room;

public class SingleFloorSingleDevice extends Command
{
	private int floorNumber = 0;
	private String deviceName = "";
	
	@Override
	public void execute() throws Exception
	{
		Iterator<IF_Room> it = house.getRoomList().values().iterator();
		while (it.hasNext())
		{
			IF_Room room = it.next();
			if (room.getFloor() == floorNumber)
				doForADevice(room.getDevice(deviceName));
		}
	}

	public int getFloorNumber()
	{
		return floorNumber;
	}

	public void setFloorNumber(int floorNumber)
	{
		this.floorNumber = floorNumber;
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
