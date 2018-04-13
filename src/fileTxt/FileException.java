package fileTxt;
/**
 * Eccezione, indica il verificarsi di unaeccezione nella gestione dei un file di testo.
 * @author User
 *
 */
public class FileException extends Exception
{
	
	private String motivoEccezione;
	public FileException(String message)
	{
		motivoEccezione=message;
	}
	
	public String getMessage()
	{
		return motivoEccezione;
	}
	

}

