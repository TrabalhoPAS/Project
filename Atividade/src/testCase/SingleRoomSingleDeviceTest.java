package testCase;

import static org.junit.Assert.*;
import model.interfaces.IF_Device.Status;
import model.interfaces.IF_House;

import org.junit.Before;
import org.junit.Test;

import controller.HouseFactory;
import controller.littleLanguage.command.Command;
import controller.littleLanguage.command.SingleRoomSingleDevice;
import controller.littleLanguage.Token.CMD;

public class SingleRoomSingleDeviceTest {

	Command SRSD = new SingleRoomSingleDevice();
	IF_House house = HouseFactory.getInstance().createHouse();
	private String deviceName = "";
	private String roomName = "";
	private int floor = 0;
	@Before
	public void setup()
	{
		((SingleRoomSingleDevice) SRSD).setDeviceName("Luz");
		((SingleRoomSingleDevice) SRSD).setFloorNumber(1);
		((SingleRoomSingleDevice) SRSD).setRoomName("QuartoSolteiro");
		((SingleRoomSingleDevice) SRSD).setHouse(house);
		this.deviceName = ((SingleRoomSingleDevice) SRSD).getDeviceName();
		this.roomName = ((SingleRoomSingleDevice) SRSD).getRoomName();
		this.floor = ((SingleRoomSingleDevice) SRSD).getFloorNumber();
		SRSD.setAction(Command.makeActionFromCMD(CMD.TURN_ON));
	}
	
	@Test
	public void testExecute()
	{
		try{
			System.out.println("Teste do metodo execute() do SRSD: Ligando uma Luz no Quarto...");
			SRSD.execute();
			assertEquals(house.getRoom(this.roomName, this.floor).getDevice(this.deviceName).getStatus(),Status.ON);
			System.out.println("Andar: "+this.floor+", Comodo: "+this.roomName+", "+ house.getRoom(this.roomName, this.floor).getDevice(this.deviceName));
			System.out.println("Luz ligada com sucesso.\n");
		}catch(Exception e){
			System.out.println(e);
		}
	}

}
