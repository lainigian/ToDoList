import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import fileTxt.FileException;

public class ToDo 
{
	
	//private static Progetto p1; //p1 punta al progetto Selezionato.
	//=new Progetto("Corso informatica");
	private static String workingDir = System.getProperty("user.dir")+"\\elencoProgetti\\"; //directory del progetto corrente
	
	
	public static String getDirectoryCorrente()
	{
		return workingDir;
	}
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
	
/*	public static Progetto selezionaProgetto(String denominazione) throws progettoNonPresente
	{
		String[] elencoProgetti=elencaProgetti();
		for (int i = 0; i < elencoProgetti.length; i++) 
		{
			if(elencoProgetti[i].equals(denominazione))
			{
				caricaProgetto(denominazione);
				return p1;
			}		
		}	
		throw new progettoNonPresente(denominazione);	
	}
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
	
	//elimina file
	public static boolean eliminaProgetto(String nome) throws progettoNonPresente, IOException
	{
		File daEliminare = new File(workingDir+nome+".bin"); //Referenzia oggetto file da percorso		
		if(daEliminare.exists()) //se esiste...
		{
		/*	if(daEliminare.delete()) //prova a eliminarlo... 
				System.out.println("Progetto eliminato!"); //e conferma...
			else
				throw new IOException("Accesso al file non consentito");
		*/
			return daEliminare.delete();
		}
		else
		{
			throw new progettoNonPresente(nome); 
		}
	}
	
/*	public static void main(String[] args) throws MaxNumeroAttivitaRaggiunto, ClassNotFoundException, IOException 
	{
		String[] elencoProgetti=elencaProgetti();
		for (int i = 0; i < elencoProgetti.length; i++) 
		{
			System.out.println(elencoProgetti[i]);
		}
		
		//-----------Test carica progetto-----------------------
				caricaProgetto(elencoProgetti[0]);
				System.out.println(p1.getDenominazione());
	
	}
*/
}








/*
public static void main(String[] args) throws MaxNumeroAttivitaRaggiunto 
{
	String[] elencoProgetti=elencaProgetti();
	for (int i = 0; i < elencoProgetti.length; i++) 
	{
		System.out.println(elencoProgetti[i]);
	}
}
	

//--------Test elimina progetto (eliminafile)---------OK
		
/*		eliminaProgetto(elencoProgetti[1]);
		elencoProgetti=elencaProgetti();
		for (int i = 0; i < elencoProgetti.length; i++) 
		{
			System.out.println(elencoProgetti[i]);
		}
		
//-----------Test carica progetto-----------------------
		caricaProgetto(elencoProgetti[0]);
		System.out.println(p1.getDenominazione());
		
//----------Test crea progetto--------------------OK
/*		creaProgetto("Corso TPS");
		Attivita[] elencoAttivita;
		elencoAttivita=p1.elencaAttivita();
		if (elencoAttivita.length==0)
			System.out.println("nessuna attività presente");
		else
			for (int i = 0; i < elencoAttivita.length; i++) 
			{
				System.out.println(elencoAttivita[i].getDescrizione());
			}
		
*/
			
		
//-----------Test salva progetto creato-----------------OK
		//salvaProgetto();
		

		
		
		
		/*	//Progetto p1=new Progetto ("Corso informatica");
		 
		try 
		{
			p1.creaAttivita("Introduzione alla OOP", new Date(15,10,2017));
			p1.creaAttivita("Linguaggio JAVA", new Date(1,12,2017));
			p1.creaAttivita("Array in Java", new Date(20,1,2018));
			p1.creaAttivita("Eccezioni in Java", new Date(28,2,2018));
			p1.creaAttivita("File di testo in Java", new Date(10,3,2018));
			p1.creaAttivita("Serializzazione in Java", new Date(20,3,2018));
		} 
		catch (MaxNumeroAttivitaRaggiunto e) 
		{
			System.out.println(e.toString());
			e.printStackTrace();
		}
	
		try 
		{
		//	p1.eliminaAttivita("Array in Java");
			p1.aggiornaAttivita("Array in Java", new Date(25,3,2018));
			p1.aggiornaAttivita("Array in Java", 100,new Date(12,2,2018));
		} 
		catch (AttivitaNonPresente e)
		{
			System.out.println(e.toString());
			e.printStackTrace();
		}
		
		Attivita[] elenco;
		elenco=p1.elencaAttivita();
		System.out.println(p1.getDenominazione());
		for (int i = 0; i < elenco.length; i++) 
		{
			System.out.println(elenco[i].toString());
			System.out.println("-----------------------------");
		}	

		Attivita[] elencoAttivitaCompletate;
		elencoAttivitaCompletate=p1.elencaAttivitaCompletate();
		System.out.println(p1.getDenominazione()+"\nElenco attività completate\n");
		for (int i = 0; i < elencoAttivitaCompletate.length; i++) 
		{
			System.out.println(elencoAttivitaCompletate[i].toString());
			System.out.println("-----------------------------");
		}	
		
	
		Attivita[] elencoAttivitaInScadenza;
		Date dataScadenza=new Date(12,2,2018);
		elencoAttivitaInScadenza=p1.elencaAttivitaInScadenza(dataScadenza);
		System.out.println(p1.getDenominazione()+"\nElenco attività in scadenza prima di "+ dataScadenza.toString()+"\nnon ancora completate\n");
		for (int i = 0; i < elencoAttivitaInScadenza.length; i++) 
		{
			System.out.println(elencoAttivitaInScadenza[i].toString());
			System.out.println("-----------------------------");
		}	
		
		String workingDir = System.getProperty("user.dir"); //directory del progetto corrente
		try 
		{
			p1.esportaAttivitaSuFile(workingDir+"\\stampeProgetti\\"+p1.getDenominazione()+".txt");
			p1.esportaAttivitaCompletateSuFile(workingDir+"\\stampeProgetti\\"+p1.getDenominazione()+"_completate.txt");
			Date dataScadenza2=new Date(15,2,2018);
			p1.esportaAttivitaInScadenzaSuFile(dataScadenza,workingDir+"\\stampeProgetti\\"+p1.getDenominazione()+"_in_Scadenza_"+dataScadenza.getDay()+
					"_"+dataScadenza.getMonth()+"_"+dataScadenza.getYear()+".txt");
		} 
		catch (IOException e) 
		{
			System.out.println(e.toString());
			e.printStackTrace();
		} 
		catch (FileException e) 
		{
			System.out.println(e.toString());
			e.printStackTrace();
		}

		//salvaProgetto();
		String[] elencoProgetti=elencaProgetti();
		for (int i = 0; i < elencoProgetti.length; i++) 
		{
			System.out.println(elencoProgetti[i]);
		}
*/
		
		
		
		
