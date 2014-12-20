package app;

import java.util.Scanner;

import controller.HouseFactory;
import model.*;
import model.interfaces.*;
import java.io.*;

public class App
{
	private IF_House house = HouseFactory.getInstance().createHouse();
	private IF_Room roomNow = null;
	private IF_Device deviceNow = null;
	private boolean running = true;
	Scanner scanner = new Scanner(System.in);
	
	private void showError(String errorMessage)
	{
		System.err.println();
		System.err.println("Error: \n\t" + errorMessage);
		System.err.println();
        System.out.println("pressione qualquer tecla para continuar...");
        try
        {
        	(new BufferedReader(new InputStreamReader(System.in))).readLine();
        }
        catch (Exception e){}
	}
	
	private String getString()
	{
		return scanner.next();
	}
	
	private int getOption(int...options) throws java.io.IOException
	{
		while (true)
		{
			int opt = Integer.parseInt(getString());
			
			if (options.length == 0) return opt;
			
			for (int i = 0; i < options.length; i++)
			{
				if (opt == options[i])
				{
					return opt;
				}
			}
		}			
		
	}
	
	private void enterRoom() throws java.io.IOException
	{
		try
		{
			System.out.println("Room name: ");
			String name = getString();
			System.out.println("Room floor: ");
			int floor = getOption();
			
			roomNow = house.getRoom(name, floor);
		}
		catch (myException.RoomNotExistentException e)
		{
			showError(e.getRoomName() + " is not a valid room name!");
		}
	}
	
	private void menuHouse() throws java.io.IOException
	{
		System.out.println(house);
		System.out.println("2-Enter Room");
		System.out.println("4-Exit");
		switch (getOption(2, 4))
		{
			case 2:
			{
				enterRoom();
				break;
			}
			case 4:
			{
				running = false;
				break;
			}
		}
	}
	
	private void enterDevice()
	{
		try
		{
			System.out.println("Device Name: ");
			String name = getString();
			deviceNow = roomNow.getDevice(name);
		}
		catch (myException.DeviceNotExistentException e)
		{
			showError(e.getDeviceName() + " is not a valid device name!");
		}
	}
	
	private void menuRoom() throws Exception
	{
		System.out.println(roomNow);
		System.out.println("2-Enter Device");
		System.out.println("4-Go Back to House");
		switch (getOption(2, 4))
		{
			case 2:
			{
				enterDevice();
				break;
			}
			case 4:
			{
				roomNow = null;
				break;
			}
		}
	}
	
	private void menuDevice() throws Exception
	{
		System.out.println(deviceNow);
		System.out.println("1-Turn ON");
		System.out.println("2-Turn OFF");
		System.out.println("3-Change Grade");
		System.out.println("4-Go Back to " + deviceNow.getRoom().getName());
		switch (getOption(1, 2, 3, 4))
		{
			case 1:
			{
				deviceNow.setStatus(Device.Status.ON);
				break;
			}
			case 2:
			{
				deviceNow.setStatus(Device.Status.OFF);
				break;
			}
			case 3:
			{
				int grade = getOption();
				deviceNow.setGrade(grade);
				break;
			}
			case 4:
			{
				deviceNow = null;
				break;
			}
		}
	}
	
	public void menu() throws java.io.IOException, Exception
	{
		while (running)
		{
			if (deviceNow == null)
			{
				if (roomNow == null)
				{
					menuHouse();
				}
				else
				{
					menuRoom();
				}
			}
			else
			{
				menuDevice();
			}
		}
		scanner.close();
	}
	
	public static void main(String args[]) throws Exception
	{
		App app = new App();
		
		app.menu();
	}
}
