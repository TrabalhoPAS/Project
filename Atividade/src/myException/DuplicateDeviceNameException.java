package myException;

public class DuplicateDeviceNameException extends Exception
{
	private static final long serialVersionUID = 1L;
	
	private String deviceName = "";
	
	public DuplicateDeviceNameException(String name)
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
		return "DuplicateDeviceNameException: " + this.deviceName;
	}

}
