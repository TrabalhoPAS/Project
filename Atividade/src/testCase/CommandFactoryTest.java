package testCase;

import static org.junit.Assert.*;
import model.interfaces.IF_House;

import org.junit.Before;
import org.junit.Test;

import controller.HouseFactory;
import controller.littleLanguage.CommandArgs;
import controller.littleLanguage.Parser;
import controller.littleLanguage.command.AllOfRoomSingleDevice;
import controller.littleLanguage.command.AllRoomsAllDevices;
import controller.littleLanguage.command.AllRoomsSingleDevice;
import controller.littleLanguage.command.CommandFactory;
import controller.littleLanguage.command.SingleFloorAllDevices;
import controller.littleLanguage.command.SingleFloorSingleDevice;
import controller.littleLanguage.command.SingleRoomAllDevices;
import controller.littleLanguage.command.SingleRoomSingleDevice;
import controller.littleLanguage.SyntaxErrorException;
import controller.littleLanguage.Parser.Scope;

public class CommandFactoryTest
{
	
	private Parser.Scope deviceScopeAll, deviceScopeSingle, roomScopeSingle, roomScopeAll, roomScopeFloor,roomScopeAllOfType;
	private CommandArgs args = new CommandArgs();
	private String deviceName = "Luz";
	private String roomName = "Quarto";
	private int floor = 0;
	IF_House house = HouseFactory.getInstance().createHouse();
	
	@Before
	public void setup()
	{
		this.deviceScopeSingle = Scope.Single;
		this.deviceScopeAll = Scope.All;
		this.roomScopeSingle = Scope.Single;
		this.roomScopeFloor = Scope.Floor;
		this.roomScopeAll = Scope.All;
		this.roomScopeAllOfType = Scope.AllOfType;
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
			assertTrue(cmd.createCommand(this.deviceScopeSingle, this.roomScopeSingle, args) instanceof SingleRoomSingleDevice);
			System.out.println("Criado o comando SRSD;");
			assertTrue(cmd.createCommand(this.deviceScopeSingle, this.roomScopeAll, args) instanceof AllRoomsSingleDevice);
			System.out.println("Criado o comando ARSD;");
			assertTrue(cmd.createCommand(this.deviceScopeSingle, this.roomScopeFloor, args) instanceof SingleFloorSingleDevice);
			System.out.println("Criado o comando SFSD;");
			assertTrue(cmd.createCommand(this.deviceScopeSingle, this.roomScopeAllOfType, args) instanceof AllOfRoomSingleDevice);
			System.out.println("Criado o comando AORSD;");
			assertTrue(cmd.createCommand(this.deviceScopeAll, this.roomScopeAll, args) instanceof AllRoomsAllDevices);
			System.out.println("Criado o comando ARAD;");
			assertTrue(cmd.createCommand(this.deviceScopeAll, this.roomScopeFloor, args) instanceof SingleFloorAllDevices);
			System.out.println("Criado o comando SFAD;");
			assertTrue(cmd.createCommand(this.deviceScopeAll, this.roomScopeSingle, args) instanceof SingleRoomAllDevices);
			System.out.println("Criado o comando SRAD.");
			System.out.println("Verificação concluída com sucesso.\n");
		}catch (SyntaxErrorException e)
		{
			e.printStackTrace();
		}
	}

}
