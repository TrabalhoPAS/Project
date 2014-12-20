package testCase;

import static org.junit.Assert.*;

import java.util.Iterator;

import model.interfaces.IF_Device.Status;
import model.interfaces.IF_Device;
import model.interfaces.IF_House;

import org.junit.Before;
import org.junit.Test;

import controller.HouseFactory;
import controller.littleLanguage.command.Command;
import controller.littleLanguage.command.SingleRoomAllDevices;
import controller.littleLanguage.Token.CMD;

public class SingleRoomAllDevicesTest {
	Command SRAD = new SingleRoomAllDevices();
	IF_House house = HouseFactory.getInstance().createHouse();
	private String roomName = "";	
	private int floor = 0;
	
	@Before
	public void setup()
	{
		((SingleRoomAllDevices) SRAD).setHouse(house);
		((SingleRoomAllDevices) SRAD).setRoomName("QuartoCasal");
		((SingleRoomAllDevices) SRAD).setFloor(1);
		this.roomName = ((SingleRoomAllDevices) SRAD).getRoomName();
		this.floor = ((SingleRoomAllDevices) SRAD).getFloor();
		SRAD.setAction(Command.makeActionFromCMD(CMD.TURN_ON));
	}
	
	@Test
	public void testExecute() {
		try{
			System.out.println("Teste do metodo execute() do SRAD: Ligando todos os dispositivos dos Quartos do 1º Andar...");
			SRAD.execute();
			Iterator<IF_Device> it = house.getRoom(this.roomName, this.floor).getDeviceList().values().iterator();
			while (it.hasNext())
			{
				IF_Device device = it.next();
				System.out.println(device);
				if(!device.getName().equals("Porta"))
				{
					assertEquals(device.getStatus(),Status.ON);
					
				}
				else assertEquals(device.getStatus(),Status.CLOSED);
			}
			System.out.println("Dispositivos ligados com sucesso.\n");
		}catch(Exception e){
			System.out.println(e);
		}
	}

}
