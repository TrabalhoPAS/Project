package controller.littleLanguage;

import controller.littleLanguage.command.IF_Command;

public interface IF_Parser
{
	public IF_Command parse(CommandLine inputLine) throws Exception;
}
