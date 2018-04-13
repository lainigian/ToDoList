/**
 * Eccezione, indica che una attività non è presente in un progetto
 * @author Laini Gian Marco
 *
 */
public class AttivitaNonPresente extends Exception 
{
	public String toString()
	{
		return ("L'attività non è presente nell'elenco");
	}
}
