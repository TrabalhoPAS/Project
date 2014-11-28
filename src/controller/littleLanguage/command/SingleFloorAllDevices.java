package controller.littleLanguage.command;

import java.util.Iterator;

import model.interfaces.IF_Device;
import model.interfaces.IF_Room;

public class SingleFloorAllDevices extends Command
{
	private int floorNumber = 0;

	@Override
	public void execute() throws Exception
	{
		Iterator<IF_Room> it = house.getRoomList().values().iterator();
		while (it.hasNext())
		{
			IF_Room room = it.next();
			if (room.getFloor() == floorNumber)
			{
				Iterator<IF_Device> it2 = room.getDeviceList().values().iterator();
				while (it2.hasNext())
				{
					doForADevice(it2.next());
				}
			}
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

}
