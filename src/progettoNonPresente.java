/**
 * Eccezione. Indica che il progetto selezionato non è presente
 * @author Laini Gian Marco
 *
 */
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
