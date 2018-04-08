import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public interface ToDoInterfaccia 
{

	public static final String workingDir = System.getProperty("user.dir")+"\\elencoProgetti\\"; //directory del progetto corrente
	
	public static String getDirectoryCorrente()
	{
		return workingDir;
	}
	
	//elenco dei file binari di progetto presenti nella cartella elencoProgetti
	public static String[] elencaProgetti()
	{
		File filesPresenti=new File(workingDir); //classe File, crea una rappresentazione astratta dei file in una directory
						
		int numeroFilesPresenti=filesPresenti.list().length;
		String[] elencoProgetti=new String[numeroFilesPresenti];
		elencoProgetti=filesPresenti.list();
		for (int i = 0; i < numeroFilesPresenti; i++)
			elencoProgetti[i]=elencoProgetti[i].substring(0, elencoProgetti[i].length()-4); //tolgo l'estensione.bin ai nomi dei file
		return elencoProgetti;		
	}
	
	public static Progetto caricaProgetto(String denominazione) throws IOException, ClassNotFoundException
	{	
		
		Progetto p1;
		FileInputStream fileProgetto=new FileInputStream(workingDir+denominazione+".bin");
		ObjectInputStream streamInput= new ObjectInputStream(fileProgetto);
		p1=(Progetto)streamInput.readObject();
		fileProgetto.close();
		return new Progetto(p1);
	}
	
	public static void salvaProgetto(Progetto p1)
	{
		String workingDir = System.getProperty("user.dir"); //directory del progetto corrente
		FileOutputStream fileProgetto;

		try 
		{
			fileProgetto = new FileOutputStream(workingDir+"\\elencoProgetti\\"+p1.getDenominazione()+".bin");
			ObjectOutputStream outputStream=new ObjectOutputStream(fileProgetto);
			outputStream.writeObject(p1);
			outputStream.flush();
			fileProgetto.close();			
		} 
		catch (IOException e) 
		{
			System.out.println("Impossibile scrivere il file di dati");
			e.printStackTrace();
		} 
	}
	
	public static Progetto creaProgetto(String nome)
	{
		Progetto p1=new Progetto(nome);	
		return p1;
	}
	
	public static boolean eliminaProgetto(String nome) throws progettoNonPresente, IOException
	{
		File daEliminare = new File(workingDir+nome+".bin"); //Referenzia oggetto file da percorso		
		if(daEliminare.exists()) //se esiste...
		{
			return daEliminare.delete();
		}
		else
		{
			throw new progettoNonPresente(nome); 
		}
	}
	
	
	
}
