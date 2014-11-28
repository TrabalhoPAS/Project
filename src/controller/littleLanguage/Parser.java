package controller.littleLanguage;


import controller.littleLanguage.command.Command;
import controller.littleLanguage.command.CommandFactory;
import controller.littleLanguage.command.IF_Command;
import model.interfaces.*;

public class Parser implements IF_Parser
{
	private IF_Lexer lexer;

	CommandArgs commandArgs = new CommandArgs();
	
	public enum Scope
	{
		Single,
		Floor,
		All,
		AllOfType
	}
	
	public Parser(IF_House newHouse)
	{
		this.commandArgs.setHouse(newHouse);
	}
	
	public IF_Command parse(CommandLine inputString) throws Exception
	{
		lexer = new StringLexer(inputString);
		try
		{
			return parse();
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			lexer = null;
		}
	}

	/// Make Command
	private IF_Command parse() throws Exception
	{
		Token.CMD cmd = makeCMD();
		
		Scope deviceScope = makeDevice();
		
		Scope roomScope = makeRoom();
		
		return finalize(cmd, deviceScope, roomScope);
	}
	
	private Token.CMD makeCMD() throws Exception
	{
		Token token = lexer.nextToken();

		/// Pegar ação a ser aplicada
		matchCMD(Token.CMD.CLOSE, Token.CMD.OPEN, Token.CMD.LOCK, Token.CMD.UNLOCK, Token.CMD.TURN_OFF, Token.CMD.TURN_ON, Token.CMD.CHANGE_GRADE);
		Token.CMD cmd = token.getCMD();

		if (cmd == Token.CMD.CHANGE_GRADE)
		{
			/* Se for mudança de grau precisa
			 * de um argumento em número
			 */
			token = lexer.nextToken();
			assertNumber();
			commandArgs.setGrade((int) token.getNumberValue());
		}

		return cmd;
	}
	
	private Scope makeDevice() throws Exception
	{
		Token token = lexer.nextToken();		/// Pegar device a ser usado na ação
		if (token.getType() == Token.Type.CMD)
		{
			/* Os unico Comando aceito é "ALL" 
			 * e significa que todos os device
			 * serão afetados pelo comando */

			matchCMD(Token.CMD.ALL);

			return Scope.All;
		}
		else if (token.getType() == Token.Type.ID)
		{
			/* O ID deve representar o nome do Device 
			 * a ser afetado pelo comando */

			commandArgs.setDeviceName(token.getStringValue());
			return Scope.Single;
		}
		else
		{
			throw new SyntaxErrorException(token.toString(), "CMD or ID");
		}
	}

	private Scope makeRoom() throws Exception
	{
		Token token = lexer.nextToken(); /// Pegar comodo do device
		if (token.getType() == Token.Type.CMD)
		{
			/* Os Comandos aceitos são "ALL" e "END" 
			 * e ambos significam que todos os comodos 
			 * serão afetados pelo comando */
			matchCMD(Token.CMD.ALL, Token.CMD.END);
			return Scope.All;
		}
		else if (token.getType() == Token.Type.ID)
		{
			/* O ID deve representar o nome do Comodo 
			 * a ser afetado pelo comando */
			/// assertID();
			commandArgs.setRoomName(token.getStringValue());
			
			token = lexer.nextToken();
			
			if (token.getType() == Token.Type.NUMBER)
			{
				commandArgs.setFloorNumber((int) token.getNumberValue());
				return Scope.Single;
			}
			
			return Scope.AllOfType;
		}
		else if (token.getType() == Token.Type.NUMBER)
		{
			/* O numero deve representar o andar dos
			 * comodos a serem afetados pelo comando */
			
			commandArgs.setFloorNumber((int) token.getNumberValue());
			return Scope.Floor;
		}
		else
		{
			throw new SyntaxErrorException(token.toString(), "'all' or a room name or a floor number");
		}
	}

	private IF_Command finalize(Token.CMD cmd, Scope deviceScope, Scope roomScope) throws SyntaxErrorException
	{
		IF_Command command = CommandFactory.getInstance().createCommand(deviceScope, roomScope, this.commandArgs);

		command.setAction(Command.makeActionFromCMD(cmd));

		return command;
	}
	
	private void matchCMD(Token.CMD...cmd) throws SyntaxErrorException, Exception
	{
		if (lexer.getToken() == null) lexer.nextToken();
		
		if (lexer.getToken().getType() != Token.Type.CMD)
		{
				throw new SyntaxErrorException(lexer.getToken().toString(), "CMD");
		}
		
		if (cmd.length == 0) return;
		boolean gotIt = false;
		String message = "";
		for (int i = 0; i < cmd.length; i++)
		{
			message += cmd[i];
			if (i < cmd.length-1) message += " or ";
			if (lexer.getToken().getCMD() == cmd[i])
			{
				gotIt = true;
			}
		}
		
		if (!gotIt)
			throw new SyntaxErrorException(lexer.getToken().getCMD().toString(), message);
	}
	
	private void assertID() throws SyntaxErrorException, Exception
	{
		if (lexer.getToken() == null) lexer.nextToken();
		
		if (lexer.getToken().getType() != Token.Type.ID)
			throw new SyntaxErrorException(lexer.getToken().toString(), "ID");
	}
	
	private void assertNumber() throws Exception
	{
		if (lexer.getToken() == null) lexer.nextToken();
		
		if (lexer.getToken().getType() != Token.Type.NUMBER)
			throw new SyntaxErrorException(lexer.getToken().getType().toString(), "Number");
	}
}
