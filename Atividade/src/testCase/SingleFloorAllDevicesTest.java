package testCase;

import static org.junit.Assert.*;

import java.util.Iterator;

import model.interfaces.IF_Device.Status;
import model.interfaces.IF_Device;
import model.interfaces.IF_House;
import model.interfaces.IF_Room;

import org.junit.Before;
import org.junit.Test;

import controller.HouseFactory;
import controller.littleLanguage.command.Command;
import controller.littleLanguage.command.SingleFloorAllDevices;
import controller.littleLanguage.Token.CMD;



public class SingleFloorAllDevicesTest
{
	
	Command SFAD = new SingleFloorAllDevices();
	IF_House house = HouseFactory.getInstance().createHouse();
	
	@Before
	public void setup()
	{
		((SingleFloorAllDevices) SFAD).setFloorNumber(1);
		((SingleFloorAllDevices) SFAD).setHouse(house);
		SFAD.setAction(Command.makeActionFromCMD(CMD.TURN_ON));
	}
	
	@Test
	public void testExecute() {
		try
		{
			System.out.println("Teste do metodo execute() do SFAD: Ligando todos os dispositivos no 1º Andar...");
			SFAD.execute();
			Iterator<IF_Room> it = house.getRoomList().values().iterator();
			while (it.hasNext())
			{
				IF_Room room = it.next();
				if (room.getFloor() == ((SingleFloorAllDevices) SFAD).getFloorNumber())
				{
					Iterator<IF_Device> it2 = room.getDeviceList().values().iterator();
					while (it2.hasNext())
					{
						IF_Device device = it2.next();
						if(!device.getName().equals("Porta"))
						{
							System.out.println("Andar: "+room.getFloor()+", Comodo: "+room.getName() +", "+ room.getDevice(device.getName()));
							assertEquals(device.getStatus(),Status.ON);
						}
						else assertEquals(device.getStatus(),Status.CLOSED);
					}
				}
			}
			System.out.println("Dispositivos ligados com sucesso.\n");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
