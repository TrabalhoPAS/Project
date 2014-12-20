package app;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import model.interfaces.IF_House;
import controller.HouseFactory;
import controller.littleLanguage.CommandLine;
import controller.littleLanguage.IF_Parser;
import controller.littleLanguage.Parser;

public class App2
{
	IF_Parser parser;
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	IF_House house;
	
	public App2()
	{
		house = HouseFactory.getInstance().createHouse();
		parser = new Parser(house);
	}
	
	private void showError(String errorMessage)
	{
		System.err.println();
		System.err.println("Error: \n\t" + errorMessage);
		System.err.println();
        System.out.println("pressione qualquer tecla para continuar...");
        try
        {
        	(new BufferedReader(new InputStreamReader(System.in))).readLine();
        }
        catch (Exception e){}
	}
	
	public void menu() throws Exception
	{
		boolean running = true;
		while (running)
		{
			System.out.println(house);
			System.out.println("Digite Comando:");
			CommandLine inputCommand = CommandLine.valueOf(reader.readLine());
			if (inputCommand.getContent().equalsIgnoreCase("sair"))
				running = false;
			else
			{
				try
				{
					parser.parse(inputCommand).execute();						
				}
				catch (Exception e)
				{
					showError(e.getMessage());
				}
			}
		}
	}

	public static void main(String[] args) throws Exception
	{
		App2 app = new App2();
		
		app.menu();
	}

}
