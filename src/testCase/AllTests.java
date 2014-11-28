package testCase;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AllOfRoomSingleDeviceTest.class, AllRoomsAllDevicesTest.class,
		AllRoomsSingleDeviceTest.class, CommandFactoryTest.class,
		ParserTest.class, SingleFloorAllDevicesTest.class,
		SingleFloorSingleDeviceTest.class, SingleRoomAllDevicesTest.class,
		SingleRoomSingleDeviceTest.class, StringLexerTest.class })
public class AllTests {
	
}
