package controller.littleLanguage.command;

import controller.littleLanguage.command.Command.Action;
import model.interfaces.IF_House;

public interface IF_Command
{
	public void execute() throws Exception;
	public void setGrade(int grade);
	public void setHouse(IF_House house);
	public void setAction(Action action);
}
