/**
 * Eccezione. Indica che il progetto selezionato non � presente
 * @author Laini Gian Marco
 *
 */
public class progettoNonPresente extends Exception 
{
	
	
	private String messaggio="";
	
	public progettoNonPresente(String nomeProgetto)
	{
		messaggio="Il progetto "+nomeProgetto+" non � presente";
	}
	
	public String toString()
	{
		return messaggio;
	}
	
}
