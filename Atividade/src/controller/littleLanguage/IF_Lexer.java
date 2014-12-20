package controller.littleLanguage;

public interface IF_Lexer
{
	public Token getToken();
	public Token nextToken() throws Exception;
}
