package myException;

public class DeviceNotExistentException extends Exception
{
	private static final long serialVersionUID = 1L;
	
	private String deviceName = "";
	
	public DeviceNotExistentException(String name)
	{
		super();
		this.deviceName = name;
	}
	
	public String getDeviceName()
	{
		return this.deviceName;
	}
	
	public String toString()
	{
		return "DeviceNotExistentException: " + this.deviceName;
	}
	
	public String getMessage()
	{
		return toString();
	}

}
