package controller.littleLanguage.command;

import java.util.Iterator;

import model.interfaces.*;

public class AllRoomsAllDevices extends Command
{
	public void execute() throws Exception
	{
		Iterator<IF_Room> it = house.getRoomList().values().iterator();
		while (it.hasNext())
		{
			doForARoom(it.next());
		}
	}
}
