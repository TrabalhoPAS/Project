package model;

import java.util.HashMap;
import java.util.Iterator;

import controller.HouseFactory;
import model.interfaces.*;

public class House implements IF_House
{
	HashMap<String, IF_Room> roomMap = new HashMap<String, IF_Room>();
	
	public IF_Room getRoom(String name, int floor) throws myException.RoomNotExistentException
	{
		if (this.roomMap.containsKey(name+floor) == false) throw new myException.RoomNotExistentException(name, floor);
		return this.roomMap.get(name+floor);
	}
	
	public void addRoom(String name, int floor) throws myException.DuplicateRoomNameException
	{
		if (this.roomMap.containsKey(name + floor)) throw new myException.DuplicateRoomNameException(name, floor);
		this.roomMap.put(name + floor, HouseFactory.getInstance().createRoom(this, name));
		this.roomMap.get(name + floor).setFloor(floor);
	}
	
	public String toString()
	{
		String rt = "";
		
		Iterator<IF_Room> it = roomMap.values().iterator();
		
		while (it.hasNext())
		{
			IF_Room next = it.next();
			rt += next;
			if (it.hasNext()) rt += "\n";
		}
		
		return rt;
	}

	@Override
	public HashMap<String, IF_Room> getRoomList()
	{
		return this.roomMap;
	}

}
