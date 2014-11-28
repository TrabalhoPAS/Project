package controller.interfaces;

import model.interfaces.IF_Device;
import model.interfaces.IF_House;
import model.interfaces.IF_Room;

public interface IF_HouseFactory
{
	public model.interfaces.IF_House createHouse();
	
	public IF_Room createRoom(IF_House house, String name);
	
	public model.interfaces.IF_Device createDevice(IF_Device.Type type, IF_Room room, String name);

}
