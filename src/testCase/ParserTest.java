package testCase;

import static org.junit.Assert.*;
import model.interfaces.IF_House;
import controller.littleLanguage.Parser;
import controller.littleLanguage.CommandLine;
import controller.littleLanguage.command.AllOfRoomSingleDevice;
import controller.littleLanguage.command.AllRoomsAllDevices;
import controller.littleLanguage.command.AllRoomsSingleDevice;
import controller.littleLanguage.command.SingleFloorAllDevices;
import controller.littleLanguage.command.SingleFloorSingleDevice;
import controller.littleLanguage.command.SingleRoomAllDevices;
import controller.littleLanguage.command.SingleRoomSingleDevice;

import org.junit.Before;
import org.junit.Test;

import controller.HouseFactory;

public class ParserTest
{
	IF_House house = HouseFactory.getInstance().createHouse();
	Parser parse = new Parser(house);
	private CommandLine inputString;
	private CommandLine inputString1;
	private CommandLine inputString2;
	private CommandLine inputString3;
	private CommandLine inputString4;
	private CommandLine inputString5;
	private CommandLine inputString6;	
	
	@Before
	public void setup()
	{
		this.inputString = CommandLine.valueOf("ligar Ar Quarto 1"); //SRSD
		this.inputString1 = CommandLine.valueOf("destrancar Porta"); //ARSD
		this.inputString2 = CommandLine.valueOf("ligar camera 0"); //SFSD
		this.inputString3 = CommandLine.valueOf("desligar Luz Quarto"); //AORSD
		this.inputString4 = CommandLine.valueOf("ligar tudo"); //AllRoomsAllDevices
		this.inputString5 = CommandLine.valueOf("desligar tudo 1"); //SFAD
		this.inputString6 = CommandLine.valueOf("ligar tudo Quarto 0"); //SRAD
	}
	
	@Test
	public void testParse()
	{
		try
		{
			System.out.println("Teste do Facade Parser: Criando os comandos mediante os dados inseridos...");
			assertTrue(parse.parse(this.inputString) instanceof SingleRoomSingleDevice);
			System.out.println("Entrada: \""+this.inputString+"\" \t Objeto Command criado: SRSD.");
			assertTrue(parse.parse(this.inputString1) instanceof AllRoomsSingleDevice);
			System.out.println("Entrada: \""+this.inputString1+"\" \t Objeto Command criado: ARSD.");
			assertTrue(parse.parse(this.inputString2) instanceof SingleFloorSingleDevice);
			System.out.println("Entrada: \""+this.inputString2+"\" \t Objeto Command criado: SFSD.");
			assertTrue(parse.parse(this.inputString3) instanceof AllOfRoomSingleDevice);
			System.out.println("Entrada: \""+this.inputString3+"\" \t Objeto Command criado: AORSD.");
			assertTrue(parse.parse(this.inputString4) instanceof AllRoomsAllDevices);
			System.out.println("Entrada: \""+this.inputString4+"\" \t\t Objeto Command criado: ARAD.");
			assertTrue(parse.parse(this.inputString5) instanceof SingleFloorAllDevices);
			System.out.println("Entrada: \""+this.inputString5+"\" \t Objeto Command criado: SFAD.");
			assertTrue(parse.parse(this.inputString6) instanceof SingleRoomAllDevices);
			System.out.println("Entrada: \""+this.inputString6+"\" \t Objeto Command criado: SRAD.");
			System.out.println("Teste do Facade Parser realizado com sucesso.\n");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
