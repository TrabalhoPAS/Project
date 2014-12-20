package testCase;

import static org.junit.Assert.*;

import java.util.Iterator;

import model.interfaces.IF_House;
import model.interfaces.IF_Room;
import model.interfaces.IF_Device.Status;

import org.junit.Before;
import org.junit.Test;

import controller.HouseFactory;
import controller.littleLanguage.Token.CMD;
import controller.littleLanguage.command.AllOfRoomSingleDevice;
import controller.littleLanguage.command.Command;

public class AllOfRoomSingleDeviceTest
{
	private String deviceName = "";
	private String roomName = "" ;
	Command AORSD = new AllOfRoomSingleDevice();
	IF_House house = HouseFactory.getInstance().createHouse();
	
	@Before
	public void setup()
	{
		((AllOfRoomSingleDevice)AORSD).setHouse(house);
		((AllOfRoomSingleDevice)AORSD).setDeviceName("Porta");
		((AllOfRoomSingleDevice)AORSD).setRoomName("Quarto");
		this.deviceName = ((AllOfRoomSingleDevice)AORSD).getDeviceName();
		this.roomName = ((AllOfRoomSingleDevice)AORSD).getRoomName();
		((AllOfRoomSingleDevice)AORSD).setAction(Command.makeActionFromCMD(CMD.UNLOCK));
	}
	
	@Test
	public void testExecute()
	{
		try
		{
			System.out.println("Teste do metodo execute() do AORSD: Tentando destrancar portas...");
			AORSD.execute();
			Iterator<IF_Room> it = house.getRoomList().values().iterator();
			while (it.hasNext())
			{
				IF_Room room = it.next();
				if (room.getName().equals(this.roomName))
				{
					if(room.getDevice(this.deviceName).getName().equals("Porta"))
					{
						System.out.println("Andar: "+room.getFloor()+", Comodo: "+room.getName() +", "+ room.getDevice(deviceName));
						assertEquals(room.getDevice(this.deviceName).getStatus(),Status.UNLOCKED);					
					}
				}
			}
			System.out.println("Portas destrancadas com sucesso.\n");
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
