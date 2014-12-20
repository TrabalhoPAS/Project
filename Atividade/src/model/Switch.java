package model;

import model.interfaces.IF_Room;

public class Switch extends Device
{
	public Switch(IF_Room room, String name)
	{
		super(room, name);
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
			case OFF:
			case ON:
				return true;
			default:
				return false;
		}
	}

}
