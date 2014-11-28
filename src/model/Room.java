package model;

import java.util.HashMap;
import java.util.Iterator;

import model.interfaces.*;
import controller.HouseFactory;

public class Room implements IF_Room
{
	private IF_House house = null;
	private String name = new String();
	private HashMap<String, IF_Device> deviceMap = new HashMap<String, IF_Device>();
	private int floor;
	
	public Room(IF_House house, String newName)
	{
		this.house = house;
		this.setName(newName);
	}
	
	public String toString()
	{
		String rt = "Room: " + this.getName() + " (Floor: " + this.getFloor() + ")\n";
		
		Iterator<IF_Device> it = this.deviceMap.values().iterator();
		
		while (it.hasNext())
		{
			IF_Device next = it.next();
			rt += "\t" + next;
			if (it.hasNext()) rt += "\n";
		}
		
		return rt;
	}
	
	public IF_House getHouse()
	{
		return this.house;
	}
	
	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}	
	
	public IF_Device getDevice(String name) throws myException.DeviceNotExistentException
	{
		if (this.deviceMap.containsKey(name) == false) throw new myException.DeviceNotExistentException(name);
		return this.deviceMap.get(name);
	}
	
	public void addDevice(IF_Device.Type type, String dName) throws myException.DuplicateDeviceNameException
	{
		if (this.deviceMap.containsKey(dName)) throw new myException.DuplicateDeviceNameException(dName);
		this.deviceMap.put(dName, HouseFactory.getInstance().createDevice(type, this, dName));
	}

	public void setFloor(int floor)
	{
		this.floor = floor;
	}

	public int getFloor()
	{
		return this.floor;
	}

	@Override
	public HashMap<String, IF_Device> getDeviceList()
	{
		return this.deviceMap;
	}

}
