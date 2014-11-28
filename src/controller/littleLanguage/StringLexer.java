package controller.littleLanguage;

import java.util.StringTokenizer;

public class StringLexer implements IF_Lexer
{
	Token token = null;
	StringTokenizer input = null;
	
	public StringLexer(CommandLine inputString)
	{
		this.input = new StringTokenizer(inputString.getContent());
	}
	
	private Token wordToken(String string)
	{
		Token.CMD cmd = Token.verifyCMD(string); 
		if (cmd == Token.CMD.NO_CMD)
			return new Token(string);
		else
			return new Token(cmd);
	}
	
	public Token nextToken() throws Exception
	{
		if (input.hasMoreTokens())
		{
			String tokenString = input.nextToken();
			try
			{
				token = new Token(Integer.parseInt(tokenString));
			}
			catch (java.lang.NumberFormatException e)
			{
				token = wordToken(tokenString);
			}
		}
		else
		{
			token = new Token(Token.CMD.END);
		}
		
		return token;
	}
	
	public Token getToken()
	{
		return this.token;
	}
}
