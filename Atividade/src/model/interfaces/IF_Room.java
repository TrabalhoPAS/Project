package model.interfaces;

import java.util.HashMap;

public interface IF_Room
{
	public IF_House getHouse();
	
	public String getName();
	public void setName(String newName);
	
	public HashMap<String, IF_Device> getDeviceList();
	public IF_Device getDevice(String name) throws myException.DeviceNotExistentException;
	public void addDevice(IF_Device.Type type, String name) throws myException.DuplicateDeviceNameException;
	
	public void setFloor(int floor);
	public int getFloor();
}
