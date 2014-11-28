package controller.littleLanguage;

public class SyntaxErrorException extends Exception
{
	private static final long serialVersionUID = 1L;
	
	public SyntaxErrorException(String error)
	{
		super(error);
	}
	
	public SyntaxErrorException(String gotToken, String expected)
	{
		super("Got " + gotToken + " when expecting " + expected);
	}

}
