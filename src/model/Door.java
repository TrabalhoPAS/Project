package model;

import model.interfaces.IF_Room;

public class Door extends Device
{
	public Door(IF_Room newRoom, String newName)
	{
		super(newRoom, newName);
	}

	@Override
	public boolean isGradable()
	{
		return false;
	}

	@Override
	public boolean isValidStatus(Status status)
	{
		switch (status)
		{
			case CLOSED:
			case OPEN:
			case LOCKED:
			case UNLOCKED:
				return true;
			default:
				return false;
		}
	}

}
