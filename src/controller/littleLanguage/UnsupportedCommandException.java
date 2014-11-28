package controller.littleLanguage;

import controller.littleLanguage.Parser.Scope;

public class UnsupportedCommandException extends SyntaxErrorException
{
	private static final long serialVersionUID = 1L;
	
	Scope deviceScope;
	Scope roomScope;
	CommandArgs args;

	public UnsupportedCommandException(Scope deviceScope, Scope roomScope, CommandArgs args)
	{
		super("Unsupported Command");
		this.args = args;
		this.deviceScope = deviceScope;
		this.roomScope = roomScope;
	}

	public Scope getDeviceScope()
	{
		return deviceScope;
	}

	public Scope getRoomScope()
	{
		return roomScope;
	}

	public CommandArgs getArgs()
	{
		return args;
	}
	
}
