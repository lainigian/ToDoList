/**
 * Eccezione, indica che una attivit� non � presente in un progetto
 * @author Laini Gian Marco
 *
 */
public class AttivitaNonPresente extends Exception 
{
	public String toString()
	{
		return ("L'attivit� non � presente nell'elenco");
	}
}
