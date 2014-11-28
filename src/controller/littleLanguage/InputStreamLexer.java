package controller.littleLanguage;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class InputStreamLexer implements IF_Lexer
{
	private StreamTokenizer input;
	private Token token = null;

    public InputStreamLexer(InputStream in)
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        input = new StreamTokenizer(reader);
        input.resetSyntax();
        //input.lowerCaseMode(true); // Não é Case Sensitive
        input.eolIsSignificant(true);
        input.parseNumbers();
        input.wordChars('a', 'z');
        input.wordChars('A', 'Z');
        input.whitespaceChars(0, ' '); // Do 1º ao 32º (espaço)
    }
    
    private void wordToken()
    {
    	Token.CMD cmd = Token.verifyCMD(input.sval);
    	if (cmd == Token.CMD.NO_CMD)
    	{
    		this.token = new Token(input.sval);
    	}
    	else 
    	{
    		this.token = new Token(cmd);
    	}
    }

    public Token nextToken() throws java.io.IOException
    {
        switch (input.nextToken())
        {
	        case StreamTokenizer.TT_EOF:
	        case StreamTokenizer.TT_EOL:
	        {
	        	this.token = new Token(Token.CMD.END);
	        	break;
	        }
	        case StreamTokenizer.TT_NUMBER:
	        {
	        	this.token = new Token(input.nval);
	        	break;
	        }
	        case StreamTokenizer.TT_WORD:
	        {
	        	wordToken();
	        	break;
	        }
	        default:
	        {
	        	token = null;
	        	break;
	        }
        }
        
        return this.token;
    }
    
    public Token getToken()
    {
    	return this.token;
    }

}
