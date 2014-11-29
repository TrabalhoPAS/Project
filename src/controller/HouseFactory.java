package controller;

import model.interfaces.IF_Device;
import model.interfaces.IF_House;
import model.interfaces.IF_Room;
import controller.interfaces.*;

public class HouseFactory implements IF_HouseFactory
{
	private static IF_HouseFactory instance = new HouseFactory();
	private HouseFactory(){}
	public static IF_HouseFactory getInstance()
	{
		return instance;
	}
	
	@Override
	public IF_House createHouse()
	{
		IF_House house = new model.House();
		try
		{
			house.addRoom("Sala", 0);
			IF_Room room = house.getRoom("Sala", 0); 
			room.addDevice(IF_Device.Type.SWITCH, "Luz");
			room.addDevice(IF_Device.Type.DOOR, "Porta");
			room.addDevice(IF_Device.Type.SWITCH, "Camera");
			
			house.addRoom("Quarto", 0);
			house.getRoom("Quarto", 0).addDevice(IF_Device.Type.SWITCH, "Luz");
			house.getRoom("Quarto", 0).addDevice(IF_Device.Type.DOOR, "Porta");
			
			house.addRoom("QuartoSolteiro", 1);
			house.getRoom("QuartoSolteiro", 1).addDevice(IF_Device.Type.SWITCH, "Luz");
			house.getRoom("QuartoSolteiro", 1).addDevice(IF_Device.Type.DOOR, "Porta");
			house.getRoom("QuartoSolteiro", 1).addDevice(IF_Device.Type.GRADABLE, "Ar");
			
			house.addRoom("QuartoCasal", 1);
			room = house.getRoom("QuartoCasal", 1);
			room.addDevice(IF_Device.Type.SWITCH, "Luz");
			room.addDevice(IF_Device.Type.DOOR, "Porta");
			room.addDevice(IF_Device.Type.GRADABLE, "Ar");
		}
		catch (Exception e){}
		
		return house;
	}
	@Override
	public IF_Room createRoom(IF_House house, String name)
	{
		return new model.Room(house, name);
	}
	
	public IF_Device createDevice(IF_Device.Type type, IF_Room room, String name)
	{
		switch (type)
		{
		case SWITCH:
			return new model.Switch(room, name);
		case DOOR:
			return new model.Door(room, name);
		case GRADABLE:
			return new model.Gradable(room, name);
		default:
			return null;
		}
		
	}
	
}
