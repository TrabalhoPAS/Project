package model;

import model.interfaces.IF_Room;

public class Gradable extends Device
{
	public Gradable(IF_Room room_, String name_)
	{
		super(room_, name_);
	}

	@Override
	public boolean isGradable()
	{
		return true;
	}

	@Override
	public boolean isValidStatus(Status status)
	{
		switch (status)
		{
			case ON:
			case OFF:
				return true;
			default:
				return false;
		}
	}

}
