package controller.littleLanguage.command;

import controller.littleLanguage.CommandArgs;
import controller.littleLanguage.Parser;
import controller.littleLanguage.SyntaxErrorException;
import controller.littleLanguage.Parser.Scope;
import controller.littleLanguage.UnsupportedCommandException;

public class CommandFactory
{
	private static CommandFactory instance = new CommandFactory();
	
	public static CommandFactory getInstance()
	{
		return instance;
	}
	
	public IF_Command createSRSD(CommandArgs args)
	{
		SingleRoomSingleDevice srsd = new SingleRoomSingleDevice();
		srsd.setDeviceName(args.getDeviceName());
		srsd.setRoomName(args.getRoomName());
		srsd.setFloorNumber(args.getFloorNumber());
		return srsd;
	}
	
	public IF_Command createSRAD(CommandArgs args)
	{
		SingleRoomAllDevices srad = new SingleRoomAllDevices();
		srad.setRoomName(args.getRoomName());
		srad.setFloor(args.getFloorNumber());
		return srad;
	}
	
	public IF_Command createARSD(CommandArgs args)
	{
		AllRoomsSingleDevice arsd = new AllRoomsSingleDevice();
		arsd.setDeviceName(args.getDeviceName());
		return arsd;
	}
	
	public IF_Command createSFSD(CommandArgs args)
	{
		SingleFloorSingleDevice sfsd = new SingleFloorSingleDevice();
		sfsd.setDeviceName(args.getDeviceName());
		sfsd.setFloorNumber(args.getFloorNumber());
		return sfsd;
	}
	
	public IF_Command createAORSD(CommandArgs args)
	{
		AllOfRoomSingleDevice aorsd = new AllOfRoomSingleDevice();
		aorsd.setDeviceName(args.getDeviceName());
		aorsd.setRoomName(args.getRoomName());
		return aorsd;
	}
	
	public IF_Command createSFAD(CommandArgs args)
	{
		SingleFloorAllDevices sfad = new SingleFloorAllDevices();
		sfad.setFloorNumber(args.getFloorNumber());
		return sfad;
	}
	
	public IF_Command createCommand(Parser.Scope deviceScope, Parser.Scope roomScope, CommandArgs args) throws SyntaxErrorException
	{	
		IF_Command command = null;
		
		if (deviceScope == Scope.Single)
		{
			command = createSDCommand(roomScope, args);
		}
		else if (deviceScope == Scope.All)
		{
			command = createADCommand(roomScope, args);
		}
		
		if (command == null)
		{
			throw new UnsupportedCommandException(deviceScope, roomScope, args);
		}
		
		command.setGrade(args.getGrade());
		command.setHouse(args.getHouse());
		
		return command;
	}
	
	private IF_Command createADCommand(Parser.Scope roomScope, CommandArgs args)
	{
		switch (roomScope)
		{
			case All:
				return new AllRoomsAllDevices();
				
			case AllOfType:
				return null;
				
			case Floor:
				return createSFAD(args);
				
			case Single:
				return createSRAD(args);
				
			default:
				return null;
		}
	}
	
	private IF_Command createSDCommand(Parser.Scope roomScope, CommandArgs args)
	{
		switch (roomScope)
		{
			case All:
				return createARSD(args);
				
			case AllOfType:
				return createAORSD(args);
				
			case Floor:
				return createSFSD(args);
				
			case Single:
				return createSRSD(args);
				
			default:
				return null;
		}
	}
}
