package controller.littleLanguage;

public class CommandLine
{
	private String content = "";
	
	public static CommandLine valueOf(String input)
	{
		return new CommandLine(input); 
	}
	
	private CommandLine(String input)
	{
		this.content = input;
	}
	
	public String toString()
	{
		return content;
	}
	
	public String getContent()
	{
		return content;
	}
}
