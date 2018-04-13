import java.io.*;
/**
 * Classe di utililit� per le operazioni di input dalla tastiera
 * @author Laini Gian Marco
 *
 */
public class ConsoleInput 

{
	//ATTRIBUTI
	private InputStreamReader input;
	private BufferedReader reader;
	
	/**
	 * Costruttore
	 */
	public ConsoleInput()
	{
		reader=new BufferedReader(new InputStreamReader(System.in));
	}
	
	/**
	 * Lettura di un numero intero
	 * @return il numero inviato dalla tastiera
	 * @throws NumberFormatException inserimento dato non conforme
	 * @throws IOException mancata lettura
	 */
	public int readInt() throws NumberFormatException, IOException
	{
		return (Integer.parseInt(reader.readLine()));
	}
	
	/**
	 * Lettura di un numero double
	 * @return il numero inviato dalla tastiera
	 * @throws NumberFormatException inserimento dato non conforme
	 * @throws IOException mancata lettura
	 */
	
	public double readDouble() throws NumberFormatException, IOException
	{
		return (Double.parseDouble(reader.readLine()));
	}
	
	/**
	 * Lettura di un numero long
	 * @return il numero inviato dalla tastiera
	 * @throws NumberFormatException inserimento dato non conforme
	 * @throws IOException mancata lettura
	 */
	public long readLong() throws NumberFormatException, IOException
	{
		return Long.parseLong(reader.readLine());
	}

	/**
	 * Lettura di un valore double
	 * @return il valore (true o false) inviato dalla tastiera
	 * @throws NumberFormatException inserimento dato non conforme
	 * @throws IOException mancata lettura
	 */
	public boolean readBoolean() throws IOException,NumberFormatException
	{
		return Boolean.parseBoolean(reader.readLine());
	}
	
	/**
	 * Lettura di una stringa
	 * @return la stringa inviata dalla tastiera
	 * @throws NumberFormatException Eccezione che si verifica quando viene inserita una stringa nulla
	 * @throws IOException mancata lettura
	 */
	
	public String readString() throws IOException, NumberFormatException
	{
		String s=reader.readLine();
		if (s.compareTo("")==0)
			throw new NumberFormatException();
		return (s);
	}
}

