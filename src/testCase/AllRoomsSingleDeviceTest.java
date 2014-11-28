package testCase;

import static org.junit.Assert.*;

import java.util.Iterator;

import model.interfaces.IF_House;
import model.interfaces.IF_Room;
import model.interfaces.IF_Device.Status;

import org.junit.Before;
import org.junit.Test;

import controller.HouseFactory;
import controller.littleLanguage.command.AllRoomsSingleDevice;
import controller.littleLanguage.command.Command;
import controller.littleLanguage.command.Command.Action;

public class AllRoomsSingleDeviceTest
{

	Command ARSD = new AllRoomsSingleDevice();
	IF_House house = HouseFactory.getInstance().createHouse();
	String deviceName = "";
	@Before
	public void setup()
	{
		((AllRoomsSingleDevice)ARSD).setHouse(house);
		((AllRoomsSingleDevice)ARSD).setDeviceName("Luz");
		deviceName = ((AllRoomsSingleDevice)ARSD).getDeviceName();
		((AllRoomsSingleDevice)ARSD).setAction(Action.TURN_ON);
	}
	
	@Test
	public void testExecute()
	{
		try
		{
			System.out.println("Teste do metodo execute() do ARSD: Ligando as Luzes dos comodos...");
			ARSD.execute();
			Iterator<IF_Room> it = house.getRoomList().values().iterator();
			while (it.hasNext())
			{
				IF_Room room = it.next();
				if(room.getDevice(deviceName).getName().equals("Luz"))
				{
					System.out.println("Andar: "+room.getFloor()+", Comodo: "+room.getName() +", "+ room.getDevice(deviceName));
					assertEquals(room.getDevice(deviceName).getStatus(),Status.ON);
				}
				else assertEquals(room.getDevice(deviceName).getStatus(),Status.CLOSED);
			}
			System.out.println("Luzes ligadas com sucesso.\n");
		}catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
