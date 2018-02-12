package fileTxt;

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

