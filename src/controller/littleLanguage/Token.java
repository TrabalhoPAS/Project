package controller.littleLanguage;

public class Token
{
	public enum Type
	{
		CMD,
		ID,
		NUMBER
	}
	public enum CMD
	{
		NO_CMD,
		END,
		ALL,
		TURN_ON,
		TURN_OFF,
		OPEN,
		CLOSE,
		LOCK,
		UNLOCK,
		CHANGE_GRADE
	}
	
	public static CMD verifyCMD(String word)
	{
		if (word.equalsIgnoreCase("ligar")) return CMD.TURN_ON;
		if (word.equalsIgnoreCase("desligar")) return CMD.TURN_OFF;
		if (word.equalsIgnoreCase("abrir")) return CMD.OPEN;
		if (word.equalsIgnoreCase("fechar")) return CMD.CLOSE;
		if (word.equalsIgnoreCase("todo")) return CMD.ALL;
		if (word.equalsIgnoreCase("tudo")) return CMD.ALL;
		if (word.equalsIgnoreCase("trancar")) return CMD.LOCK;
		if (word.equalsIgnoreCase("destrancar")) return CMD.UNLOCK;
		if (word.equalsIgnoreCase("mudar")) return CMD.CHANGE_GRADE;
		return CMD.NO_CMD;
	}
	
	private Type type;
	private CMD cmd = CMD.NO_CMD;
	private String stringValue = "";
	private double numberValue = 0;
	
	public Token(String newStringValue)
	{
		this.type = Type.ID;
		this.stringValue = newStringValue;
	}
	
	public Token(double newNumberValue)
	{
		this.type = Type.NUMBER;
		this.numberValue = newNumberValue;
	}
	
	public Token(CMD cmd_)
	{
		this.type = Type.CMD;
		this.cmd = cmd_;
	}
	
	public Type getType()
	{
		return this.type;
	}
	
	public CMD getCMD()
	{
		return this.cmd;
	}
	
	public String getStringValue()
	{
		return this.stringValue;
	}
	
	public double getNumberValue()
	{
		return this.numberValue;
	}
	
	public String toString()
	{
		switch (type)
		{
			case CMD:
				return "CMD(" + this.getCMD() + ")";
			case ID:
				return "ID(" + this.getStringValue() + ")";
			case NUMBER:
				return "" + this.getNumberValue();
			default:
				return "nothing";
		}
	}

}
