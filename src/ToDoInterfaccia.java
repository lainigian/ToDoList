import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
/**
 * La classe espone metodi statici per interagire con il file system e con i progetti.
 * Consente di elencare i progetti salvati come file binari in una apposita cartella, 
 * Consente di istanziarre un progetto memorizzato sul file system, 
 * consente di salvare un progetto sul file system, consente di creare un nuovo progetto
 *  e di eliminare un progetto dal filee system.
 *  L'unico attributo "workingDir" è un attributo costante che contiene il path della cartella
 *  "elencoProgetti. Tal cartella deve esere presente nella cartella che contiene il file di avvio 
 *  dell'applicazione.
 *  
 * @author User Laini gina Marco
 *
 */
public interface ToDoInterfaccia 
{

	public static final String workingDir = System.getProperty("user.dir")+"\\elencoProgetti\\"; //directory del progetto corrente
	
	public static String getDirectoryCorrente()
	{
		return workingDir;
	}
	
	/**
	 * Elenca i progetti presenti nel file system nella cartella elencoProgetti
	 * @return una stringa contenente il nome dei progetti salvati. Il nome del progetto coincide con il nome
	 * del file binario in cui i dati del progeto sono salvati.
	 */
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
	
	/**
	 * Istanzia un progetto fra quelli presenti nel file system nella cartella elencoProgetti.
	 * @param denominazione E' il nome del progetto da caricare, tale nome deve coincidere con il nome del file 
	 * binario (senza estensione) relativo al progetto memorizzato nella cartella elencoProgetti.
	 * @return Il porgetto cricato dal file system
	 * @throws IOException Eccezione che viene sollevata nel caso in cui non sia possibile leggere dal file system
	 * @throws ClassNotFoundException Eccezione che si genera nel caso in cui la classe Progetto non sia presente nell'applicazione
	 */
	public static Progetto caricaProgetto(String denominazione) throws IOException, ClassNotFoundException
	{	
		
		Progetto p1;
		FileInputStream fileProgetto=new FileInputStream(workingDir+denominazione+".bin");
		ObjectInputStream streamInput= new ObjectInputStream(fileProgetto);
		p1=(Progetto)streamInput.readObject();
		fileProgetto.close();
		return new Progetto(p1);
	}
	
	/**
	 * Memorizza un progetto nel file system nella cartella ElencoProgetti. Il file creato sarà un file binario con nome 
	 * uguale alla denominazione del progeto ed estensione .bin
	 * @param p1 Il progetto da salvare
	 */
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
	
	
	/**
	 * Istanzia un nuovo progetto vuoto, ossia senza attività
	 * @param denominazione La denominazione del progetto istanziato
	 * @return Il progetto istanziato.
	 */
	public static Progetto creaProgetto(String denominazione)
	{
		Progetto p1=new Progetto(denominazione);	
		return p1;
	}
	
	/**
	 * Elimina un progetto dal file system e dall'elenco dei progetti
	 * @param nome E' la denominazione del progetto da eliminare
	 * @return True se il progetto viene eliminato, false altrimenti.
	 * @throws progettoNonPresente Eccezione che viene sollevata quando il progetto da eliminare non è 
	 * presente nela file system nella cartella elencoProgetti
	 * @throws IOException Eccezione che viene sollevata quando non è possibile accedere al file system.
	 */
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
