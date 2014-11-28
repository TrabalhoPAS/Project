package controller.littleLanguage.command;

import java.util.Iterator;

import controller.littleLanguage.Token;
import model.Device;
import model.interfaces.*;

public abstract class Command implements IF_Command
{
	enum Action
	{
		TURN_ON,
		TURN_OFF,
		CHANGE_GRADE,
		OPEN,
		CLOSE,
		LOCK,
		UNLOCK
	}
	protected Action action = null;
	protected IF_House house = null;
	private int grade = 0;
	
	protected void doForADevice(IF_Device device)
	{
		if (action == Action.CHANGE_GRADE)
		{
			device.setGrade(getGrade());
		}
		else
		{
			device.setStatus(Command.getStatusFromAction(action));
		}
	}
	protected void doForARoom(IF_Room room)
	{
		Iterator<IF_Device> it = room.getDeviceList().values().iterator();
		while (it.hasNext())
		{
			IF_Device device = it.next();
			doForADevice(device);
		}
	}
	
	protected void doForAFloor(int floor)
	{
		Iterator<IF_Room> it = house.getRoomList().values().iterator();
		while (it.hasNext())
		{
			IF_Room room = it.next();
			if (room.getFloor() == floor)
			{
				doForARoom(room);
			}
		}
	}
	
	public static Device.Status getStatusFromAction(Action action)
	{
		switch (action)
		{
			case TURN_ON:
			{
				return (Device.Status.ON);
			}
			case TURN_OFF:
			{
				return (Device.Status.OFF);
			}	
			case CLOSE:
			{
				return (Device.Status.CLOSED);
			}	
			case LOCK:
			{
				return (Device.Status.LOCKED);
			}	
			case OPEN:
			{
				return (Device.Status.OPEN);
			}
			case UNLOCK:
			{
				return (Device.Status.UNLOCKED);
			}	
			default:
				return null;
		}
	}
	
	public static Action makeActionFromCMD(Token.CMD cmd)
	{
		switch (cmd)
		{
			case TURN_ON:
			{
				return Action.TURN_ON;
			}
			case TURN_OFF:
			{
				return Action.TURN_OFF;
			}
			case CHANGE_GRADE:
			{
				return Action.CHANGE_GRADE;
			}	
			case CLOSE:
			{
				return Action.CLOSE;
			}	
			case LOCK:
			{
				return Action.LOCK;
			}	
			case OPEN:
			{
				return Action.OPEN;
			}
			case UNLOCK:
			{
				return Action.UNLOCK;
			}	
			default:
				return null;
		}
	}
	
	public Action getAction()
	{
		return action;
	}

	public void setAction(Action action)
	{
		this.action = action;
	}
	
	public IF_House getHouse()
	{
		return house;
	}

	public void setHouse(IF_House house)
	{
		this.house = house;
	}

	public int getGrade()
	{
		return grade;
	}

	public void setGrade(int grade)
	{
		this.grade = grade;
	}
}
