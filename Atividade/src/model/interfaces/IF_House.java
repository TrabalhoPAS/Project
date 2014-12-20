package model.interfaces;

import java.util.HashMap;

public interface IF_House
{
	public HashMap<String, IF_Room> getRoomList();
	public IF_Room getRoom(String name, int floor) throws myException.RoomNotExistentException;
	public void addRoom(String name, int floor) throws myException.DuplicateRoomNameException;
}
