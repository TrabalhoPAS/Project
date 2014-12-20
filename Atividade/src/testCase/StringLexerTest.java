package testCase;

import static org.junit.Assert.*;

import org.junit.Test;

import controller.littleLanguage.CommandLine;
import controller.littleLanguage.IF_Lexer;
import controller.littleLanguage.StringLexer;
import controller.littleLanguage.Token;
import controller.littleLanguage.Token.CMD;
import controller.littleLanguage.Token.Type;

public class StringLexerTest
{
	
	private String input = "desligar Luz Quarto 1";
	CommandLine cmdln = CommandLine.valueOf(input);	
	IF_Lexer lexer = new StringLexer(cmdln);
	private Token token;

	
	@Test
	public void testNextToken()
	{
		try
		{
			System.out.println("Verificando a identifica��o de tokens no StringLexer...");
			System.out.println("Comando passado: \""+this.cmdln.getContent()+"\"");
			this.token = ((StringLexer)lexer).nextToken();
			assertEquals(CMD.TURN_OFF,this.token.getCMD());
			System.out.println("1� token encontrado: "+this.token.getCMD()+". \tTipo token: "+this.token.getType());
			this.token = ((StringLexer)lexer).nextToken();
			assertEquals("Luz",this.token.getStringValue());
			System.out.println("2� token encontrado: "+this.token.getStringValue()+". \tTipo token: "+this.token.getType());
			this.token = ((StringLexer)lexer).nextToken();
			assertEquals(Type.ID,this.token.getType());
			System.out.println("3� token encontrado: "+this.token.getStringValue()+". \tTipo token: "+this.token.getType());
			this.token = ((StringLexer)lexer).nextToken();
			assertEquals(Type.NUMBER,this.token.getType());
			System.out.println("4� token encontrado: "+this.token.getNumberValue()+". \tTipo token: "+this.token.getType());
			System.out.println("Verifica��o concluida com sucesso\n");
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
