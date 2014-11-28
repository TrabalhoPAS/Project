package testCase;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import model.interfaces.IF_Device.Status;
import model.interfaces.IF_House;
import model.interfaces.IF_Room;

import org.junit.Before;

import controller.HouseFactory;
import controller.littleLanguage.command.Command;
import controller.littleLanguage.command.SingleFloorSingleDevice;
import controller.littleLanguage.command.Command.Action;

public class SingleFloorSingleDeviceTest
{
	Command SFSD = new SingleFloorSingleDevice();
	IF_House house = HouseFactory.getInstance().createHouse();
	private int floor = 0;
	private String DeviceName = "";
	@Before
	public void setup()
	{
		((SingleFloorSingleDevice) SFSD).setHouse(house);
		((SingleFloorSingleDevice) SFSD).setDeviceName("Luz");
		((SingleFloorSingleDevice) SFSD).setFloorNumber(1);
		this.floor = ((SingleFloorSingleDevice) SFSD).getFloorNumber();
		this.DeviceName = ((SingleFloorSingleDevice) SFSD).getDeviceName();
		SFSD.setAction(Action.TURN_ON);
	}
	@Test
	public void testExecute() {
		try
		{
			System.out.println("Teste do metodo execute() do SFSD: Ligando apenas as Luzes do 1º Andar...");
			SFSD.execute();
			Iterator<IF_Room> it = house.getRoomList().values().iterator();
			while (it.hasNext())
			{
				IF_Room room = it.next();
				if (room.getFloor() == this.floor && room.getDevice(this.DeviceName).getName().equals("Luz"))
				{
					assertEquals(Status.ON,room.getDevice(this.DeviceName).getStatus());
					System.out.println("Andar: "+room.getFloor()+", Comodo: "+room.getName() +", "+ room.getDevice(this.DeviceName));
				}
			}
			System.out.println("Luzes ligadas com sucesso.\n");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
