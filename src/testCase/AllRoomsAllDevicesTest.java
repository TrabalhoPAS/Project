package testCase;

import static org.junit.Assert.*;

import java.util.Iterator;

import model.interfaces.IF_Device;
import model.interfaces.IF_House;
import model.interfaces.IF_Room;
import model.interfaces.IF_Device.Status;

import org.junit.Before;
import org.junit.Test;

import controller.HouseFactory;
import controller.littleLanguage.command.AllRoomsAllDevices;
import controller.littleLanguage.command.Command;
import controller.littleLanguage.command.Command.Action;

public class AllRoomsAllDevicesTest
{
	
	Command AFAD = new AllRoomsAllDevices();
	IF_House house = HouseFactory.getInstance().createHouse();
	
	@Before
	public void setup()
	{
		((AllRoomsAllDevices)AFAD).setHouse(house);
		((AllRoomsAllDevices)AFAD).setAction(Action.TURN_ON);
	}
	@Test
	public void testExecute()
	{
		try
		{
			System.out.println("Teste do metodo execute() do ARAD: Ligando todos os dispositivos da casa...");
			AFAD.execute();
			Iterator<IF_Room> it = house.getRoomList().values().iterator();
			while (it.hasNext())
			{
				IF_Room room = (IF_Room)it.next();
				Iterator<IF_Device> it2 = room.getDeviceList().values().iterator();
				while (it2.hasNext())
				{
					IF_Device device = it2.next();
					if(device.getName().equals("Luz")||device.getName().equals("Camera")||device.getName().equals("Ar"))
					{
						System.out.println("Andar: "+room.getFloor()+", Comodo: "+room.getName() +", "+ room.getDevice(device.getName()));
						assertEquals(device.getStatus(),Status.ON);
					}
					else assertEquals(device.getStatus(),Status.CLOSED);
				}
			}
			System.out.println("Dispositivos ligados com sucesso.\n");
		}catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
