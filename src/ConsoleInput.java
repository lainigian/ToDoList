import java.io.*;

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
	 * @return
	 * @throws NumberFormatException inserimento dato non conforme
	 * @throws IOException mancata lettura
	 * @return valore intero letto
	 */
	public int readInt() throws NumberFormatException, IOException
	{
		return (Integer.parseInt(reader.readLine()));
	}
	
	/**
	 * @return
	 * @throws NumberFormatException inserimento dato non conforme
	 * @throws IOException mancata lettura
	 * @return valore double letto
	 */
	
	public double readDouble() throws NumberFormatException, IOException
	{
		return (Double.parseDouble(reader.readLine()));
	}
	
	/**
	 * @return
	 * @throws NumberFormatException inserimento dato non conforme
	 * @throws IOException mancata lettura
	 * @return valore long letto
	 */
	public long readLong() throws NumberFormatException, IOException
	{
		return Long.parseLong(reader.readLine());
	}

	/**
	 * @return
	 * @throws NumberFormatException inserimento dato non conforme
	 * @throws IOException mancata lettura
	 * @return valore boolean letto
	 */
	public boolean readBoolean() throws IOException,NumberFormatException
	{
		return Boolean.parseBoolean(reader.readLine());
	}
	
	/**
	 * @return
	 * @throws NumberFormatException inserimento dato non conforme
	 * @throws IOException mancata lettura
	 * @return stringa letta
	 */
	
	public String readString() throws IOException, NumberFormatException
	{
		String s=reader.readLine();
		if (s.compareTo("")==0)
			throw new NumberFormatException();
		return (s);
	}
}

