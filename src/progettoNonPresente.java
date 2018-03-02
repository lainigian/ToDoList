
public class progettoNonPresente extends Exception 
{
	
	
	private String messaggio="";
	
	public progettoNonPresente(String nomeProgetto)
	{
		messaggio="Il progetto "+nomeProgetto+" non è presente";
	}
	
	public String toString()
	{
		return messaggio;
	}
	
}
