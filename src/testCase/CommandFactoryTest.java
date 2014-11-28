package testCase;

import static org.junit.Assert.*;
import model.interfaces.IF_House;

import org.junit.Before;
import org.junit.Test;

import controller.HouseFactory;
import controller.littleLanguage.CommandArgs;
import controller.littleLanguage.Parser;
import controller.littleLanguage.Parser.Kind;
import controller.littleLanguage.command.AllOfRoomSingleDevice;
import controller.littleLanguage.command.AllRoomsAllDevices;
import controller.littleLanguage.command.AllRoomsSingleDevice;
import controller.littleLanguage.command.CommandFactory;
import controller.littleLanguage.command.SingleFloorAllDevices;
import controller.littleLanguage.command.SingleFloorSingleDevice;
import controller.littleLanguage.command.SingleRoomAllDevices;
import controller.littleLanguage.command.SingleRoomSingleDevice;
import controller.littleLanguage.SyntaxErrorException;

public class CommandFactoryTest
{
	
	private Parser.Kind deviceKindAll, deviceKindSingle, roomKindSingle, roomKindAll, roomKindFloor,roomKindAllOfType;
	private CommandArgs args = new CommandArgs();
	private String deviceName = "Luz";
	private String roomName = "Quarto";
	private int floor = 0;
	IF_House house = HouseFactory.getInstance().createHouse();
	
	@Before
	public void setup()
	{
		this.deviceKindSingle =Kind.Single;
		this.deviceKindAll = Kind.All;
		this.roomKindSingle = Kind.Single;
		this.roomKindFloor = Kind.Floor;
		this.roomKindAll = Kind.All;
		this.roomKindAllOfType = Kind.AllOfType;
		this.args.setDeviceName(this.deviceName);
		this.args.setHouse(house);
		this.args.setDeviceName(this.deviceName);
		this.args.setFloorNumber(this.floor);
		this.args.setRoomName(this.roomName);
	}
	
	@Test
	public void testCreateCommand()
	{
		CommandFactory cmd = new CommandFactory();
		try
		{
			System.out.println("Verificando a criacao de comandos do CommadFactory...");
			assertTrue(cmd.createCommand(this.deviceKindSingle, this.roomKindSingle, args) instanceof SingleRoomSingleDevice);
			System.out.println("Criado o comando SRSD;");
			assertTrue(cmd.createCommand(this.deviceKindSingle, this.roomKindAll, args) instanceof AllRoomsSingleDevice);
			System.out.println("Criado o comando ARSD;");
			assertTrue(cmd.createCommand(this.deviceKindSingle, this.roomKindFloor, args) instanceof SingleFloorSingleDevice);
			System.out.println("Criado o comando SFSD;");
			assertTrue(cmd.createCommand(this.deviceKindSingle, this.roomKindAllOfType, args) instanceof AllOfRoomSingleDevice);
			System.out.println("Criado o comando AORSD;");
			assertTrue(cmd.createCommand(this.deviceKindAll, this.roomKindAll, args) instanceof AllRoomsAllDevices);
			System.out.println("Criado o comando ARAD;");
			assertTrue(cmd.createCommand(this.deviceKindAll, this.roomKindFloor, args) instanceof SingleFloorAllDevices);
			System.out.println("Criado o comando SFAD;");
			assertTrue(cmd.createCommand(this.deviceKindAll, this.roomKindSingle, args) instanceof SingleRoomAllDevices);
			System.out.println("Criado o comando SRAD.");
			System.out.println("Verificação concluída com sucesso.\n");
		}catch (SyntaxErrorException e)
		{
			e.printStackTrace();
		}
	}

}
